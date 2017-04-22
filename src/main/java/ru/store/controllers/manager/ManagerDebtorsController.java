package ru.store.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import ru.store.service.*;

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

    @Autowired
    private LoadMapsService loadMapsServiceService;

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Model model=new Model();
        List<Company> companies=searchByPage.search(searchMap,selectSearchCompanyByType,"selectSearchCompanyByPaymentStatusAll",modelAndView,auth);
        List<Package> packages=packageService.getPackages();
        List<CompanyAddress>companyAddresses=companyAddressService.getCompanyAddresses();
        List<CompanyReminder> companyReminders=companyReminderService.getLastCompaniesReminderType();
        iSChoiceComments=searchByPage.getIsShowAllCompanyWithComments();
        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
        for (Company company : companies) {
            if (company.getCostOf()!=null && company.getIsPaid().equals(false)&&company.getDateOfContract()!=null&&company.getDateOfStartContract()!=null&&company.getDateOfEndContract()!=null&&company.getDateOfEndContract().getTime() < new Date().getTime()) {
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
        List<Company> companies=new ArrayList<>();
        List<CompanyReminder> companyReminders=companyReminderService.getCompanyReminders();
        List<CompanyAddress> companyAddresses=companyAddressService.getCompanyAddresses();
        List<Package> packages=packageService.getPackages();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        String role= auth.getAuthorities().toString();
        if(role.equals("[ROLE_DIRECTOR]")) {
            System.out.println("director");
            companies = companyService.getCompanies();
        }
        if(role.equals("[ROLE_MANAGER]")) {
            System.out.println("manager");
            companies = companyService.getCompaniesByManagerName(name);
        }
        loadMapsServiceService.load(model,companyReminders,companyAddresses,companies);
        model.message = "Должники:";
        List<CompanyReminder> companyLstReminders=companyReminderService.getLastCompaniesReminderType();
        model.companyList = new ArrayList<>();
        for (Company company : companies) {
            if (company.getCostOf()!=null && company.getIsPaid().equals(false)&&company.getDateOfContract()!=null&&company.getDateOfStartContract()!=null&&company.getDateOfEndContract()!=null && company.getDateOfEndContract().getTime() < new Date().getTime()) {
                List<Model.CompaniesItem> list = convert(company,packages,companyLstReminders,companyAddresses);
                for (Model.CompaniesItem m : list) {
                    model.companyList.add(m);
                }
            }

        }
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
        companyItem.id = company.getId();
        companyItem.name=company.getName();
        companyItem.companyPackage = "";
        if(!packages.isEmpty()) {
            for (Package pack:packages){
                if (company.getCompanyPackageId() ==pack.getId()) {
                    companyItem.companyPackage = pack.getName();
                }
            }
        }
        companyItem.debt = checkIsDebt(company);
        companyItem.directorFullName=company.getDirectorFullName();
        companyItem.companyAddresses= new ArrayList<>();
        if(!companyAddresses.isEmpty()) {
            List<String>s=new ArrayList();
            for(CompanyAddress companyAddress:companyAddresses) {
                if(company.getId()==companyAddress.getCompanyId()){
                    s.add(companyAddress.getAddress());
                    companyItem.companyAddresses=s;
                }
            }
        }
        companyItem.note = "";
        if(!companyReminders.isEmpty()) {
            for (CompanyReminder companyReminder:companyReminders){
                if (company.getId() ==companyReminder.getCompanyId()) {
                    companyItem.note = sdf.format(companyReminder.getDateReminder())+" "+companyReminder.getHourReminder()+" "+companyReminder.getTypeReminder();
                }
            }
        }
        companyItems.add(companyItem);
        return companyItems;
    }
    private String checkIsDebt(Company company) {
        float debt=0;
        long debtDays=((new Date().getTime()-company.getDateOfEndContract().getTime())/86400000);
        Calendar calendar=Calendar.getInstance();
        float debtPerDay=company.getCostOf()/calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        debt=debtPerDay*(int)debtDays;
        return debt+"гр";
    }
}
