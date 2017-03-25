package ru.store.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Company;
import ru.store.entities.CompanyAddress;
import ru.store.entities.CompanyReminder;
import ru.store.entities.Package;
import ru.store.service.CompanyAddressService;
import ru.store.service.CompanyReminderService;
import ru.store.service.CompanyService;
import ru.store.service.PackageService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Akex on 05.10.2016.
 */
@Controller
public class ManagerDebtorsController {
    @Autowired
    SearchByPage searchByPage;
    @Autowired
    CompanyReminderService companyReminderService;
    @Autowired
    PackageService packageService;
    @Autowired
    CompanyAddressService companyAddressService;
    @Autowired
    CompanyService companyService;
    private Boolean iSChoiceComments;

    @RequestMapping(value = "/manager/debtors", method = RequestMethod.GET)
    public ModelAndView debtors(){
        ModelAndView modelAndView=new ModelAndView();
        Model model=new Model();
        loadPage(modelAndView,model);
        return modelAndView;
    }
    @RequestMapping(value = "/manager/searchcompanybydebtors", method = RequestMethod.POST)
    public ModelAndView searchByDebtors(@RequestParam MultiValueMap<String, String> searchMap,
                                        @RequestParam("selectSearchCompanyByType")String selectSearchCompanyByType){
        ModelAndView modelAndView=new ModelAndView();
        Model model=new Model();
        List<Company> companies=searchByPage.search(searchMap,selectSearchCompanyByType,"selectSearchCompanyByPaymentStatusAll",modelAndView);
        List<Package> packages=packageService.getPackages();
        List<CompanyAddress>companyAddresses=companyAddressService.getCompanyAddresses();
        List<CompanyReminder> companyReminders=companyReminderService.getLastCompaniesReminderType();
        iSChoiceComments=searchByPage.getIsShowAllCompanyWithComments();
        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
        for (Company company : companies) {
            if (company.getIsPaid().equals(false)&&company.getDateOfContract()!=null&&company.getDateOfStartContract()!=null&&company.getDateOfEndContract()!=null&&company.getDateOfEndContract().getTime() < new Date().getTime()) {
                if (iSChoiceComments == false) {
                    List<Model.CompaniesItem> list = convert(company,packages,companyReminders,companyAddresses);
                    for (Model.CompaniesItem m : list) {
                        model.companyList.add(m);
                    }
                }
                else {
                        searchByChoice(company, model, searchByPage.getChoice() ,packages,companyReminders,companyAddresses);
                    }
            }
        }
        model.selectedPageNum=3;
        modelAndView.addObject("model",model);
        modelAndView.addObject("prefix","");
        modelAndView.setViewName("manager/debtors");
        return modelAndView;
    }
    private void loadPage(ModelAndView modelAndView,Model model){
        model.selectedPageNum=3;
        loadCompanies(model);
        modelAndView.addObject("model",model);
        modelAndView.addObject("prefix","");
        modelAndView.setViewName("manager/debtors");
    }
    private void loadCompanies(Model model){
        List<Company> companies=companyService.getCompanies();
        List<Package> packages=packageService.getPackages();
        List<CompanyAddress>companyAddresses=companyAddressService.getCompanyAddresses();
        List<CompanyReminder> companyReminders=companyReminderService.getLastCompaniesReminderType();
        model.companyList = new ArrayList<>();
            for (Company company : companies) {
                    if (company.getIsPaid().equals(false)&&company.getDateOfContract()!=null&&company.getDateOfStartContract()!=null&&company.getDateOfEndContract()!=null && company.getDateOfEndContract().getTime() < new Date().getTime()) {
                        List<Model.CompaniesItem> list = convert(company,packages,companyReminders,companyAddresses);
                        for (Model.CompaniesItem m : list) {
                            model.companyList.add(m);
                        }
                    }

            }
        }

    private String getNormalName(String name) {
        if (name != null && name.length() > 26)
            return name.substring(0, 26) + "..";
        else
            return name;
    }
    private void searchByChoice(Company company,Model model,int choice,List<Package> packages,List<CompanyReminder>companyReminders,List<CompanyAddress>companyAddresses) {
        List<Model.CompaniesItem> companyItems;
            companyItems = convert(company,packages,companyReminders,companyAddresses);
            for(Model.CompaniesItem companyItem:companyItems) {
                if (choice == 1) {
                    System.out.println("note----"+companyItem.getNote());
                    if (companyItem.getNote()!=null) {
                        model.companyList.add(companyItem);
                    }
                }
                if (choice == 2) {
                    System.out.println("note----"+companyItem.getNote());
                    if (companyItem.getNote()==null) {
                        model.companyList.add(companyItem);
                    }
                }
            }

    }
    private List<Model.CompaniesItem> convert(Company company,List<Package> packages,List<CompanyReminder>companyReminders,List<CompanyAddress>companyAddresses) {
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        List<Model.CompaniesItem> companyItems = new ArrayList<>();
        Model.CompaniesItem companyItem;
        companyItem=new Model.CompaniesItem();
        companyItem.name=company.getName();
        if(!packages.isEmpty()) {
            for (Package pack:packages){
                if (company.getCompanyPackageId() ==pack.getId()) {
                    companyItem.companyPackage = pack.getName();
                }
            }
        }
        else{
            companyItem.companyPackage = "";
        }
        companyItem.debt=checkIsDebt(company);
        companyItem.directorFullName=company.getDirectorFullName();
        if(!companyAddresses.isEmpty()) {
            List<String>s=new ArrayList();
            for(CompanyAddress companyAddress:companyAddresses) {
                if(company.getId()==companyAddress.getCompanyId()){
                    s.add(companyAddress.getAddress());
                    companyItem.companyAddresses=s;
                }
            }
        }
        else{
            companyItem.companyAddresses= new ArrayList<>();
        }
        if(!companyReminders.isEmpty()) {
            for (CompanyReminder companyReminder:companyReminders){
                if (company.getId() ==companyReminder.getCompanyId()) {
                    companyItem.note = sdf.format(companyReminder.getDateReminder())+" "+companyReminder.getHourReminder()+" "+companyReminder.getTypeReminder();
                }
            }
        }
        else{
            companyItem.note = "";
        }
        companyItems.add(companyItem);
        return companyItems;
    }
    private String checkIsDebt(Company company) {
        float debt=0;
        if (company.getDateOfContract()!=null&&company.getDateOfStartContract()!=null&&company.getDateOfEndContract()!=null&&company.getDateOfEndContract().getTime() < new Date().getTime()){
           long debtDays=((new Date().getTime()-company.getDateOfEndContract().getTime())/86400000);
            Calendar calendar=Calendar.getInstance();
            float debtPerDay=company.getCostOf()/calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            debt=debtPerDay*(int)debtDays;
        }
        return debt+"гр";
    }
 }
