package ru.store.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Company;
import ru.store.service.CompanyAddressService;
import ru.store.service.CompanyReminderService;
import ru.store.service.CompanyService;
import ru.store.service.PackageService;

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
    public ModelAndView searchByDebtors(@RequestParam MultiValueMap<String, String> searchMap, @RequestParam("selectSearchCompany")String selectSearchType){
        ModelAndView modelAndView=new ModelAndView();
        Model model=new Model();
        List<Company> companies=searchByPage.search(searchMap,selectSearchType,modelAndView);
        loadPage(modelAndView,model);
        iSChoiceComments=searchByPage.getIsShowAllCompanyWithComments();
        loadPage(modelAndView,model);
        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
        for (Company company : companies) {
            if (company.getDateOfEndContract().getTime() < new Date().getTime()) {

                if (iSChoiceComments == false) {
                    List<Model.CompaniesItem> list = convert(company);
                    for (Model.CompaniesItem m : list) {
                        model.companyList.add(m);
                    }
                } else {
                    if (searchByPage.getChoice() == 1) {
                        searchByChoice(companies, model, 1);
                    }
                    if (searchByPage.getChoice() == 2) {
                        searchByChoice(companies, model, 2);
                    }
                }
            }
        }
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
        model.companyList = new ArrayList<>();
            for (Company company : companies) {
                if (company.getDateOfEndContract().getTime() < new Date().getTime()) {
                    List<Model.CompaniesItem> list = convert(company);
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
    private void searchByChoice(List<Company>companies,Model model,int choice) {
        List<Model.CompaniesItem> companyItems;
        for (Company company : companies) {
            companyItems = convert(company);
            for(Model.CompaniesItem companyItem:companyItems) {
                if (choice == 1) {
                    if (!companyItem.getNote().get(0).equals("")) {
                        model.companyList.add(companyItem);
                    }
                }
                if (choice == 2) {
                    if (companyItem.getNote().get(0).equals("")) {
                        model.companyList.add(companyItem);
                    }
                }
            }
        }
    }
    private List<Model.CompaniesItem> convert(Company company) {
        List<Model.CompaniesItem> companyItems = new ArrayList<>();
        Model.CompaniesItem companyItem;
            companyItem=new Model.CompaniesItem();
            companyItem.name=company.getName();
            companyItem.companyPackage=packageService.getPackage(company.getCompanyPackageId()).getName();
            companyItem.debt=checkIsDebt(company);
            companyItem.directorFullName=company.getDirectorFullName();
            companyItem.companyAddresses=companyAddressService.getCompanyAddressString(company.getId());
        if(!companyReminderService.getCompanyReminderDateHourType(company.getId()).equals("")) {
            companyItem.note=companyReminderService.getCompanyReminderDateHourType(company.getId());
        }
        else{
            companyItem.note= new ArrayList<>();
            companyItem.note.add("");
        }
            companyItems.add(companyItem);
        return companyItems;
    }
    private String checkIsDebt(Company company) {
        int debt=0;
        if (company.getDateOfEndContract().getTime() < new Date().getTime()){
           long debtDays=((new Date().getTime()-company.getDateOfEndContract().getTime())/86400000);
            Calendar calendar=GregorianCalendar.getInstance();
            int debtPerDay=company.getCostOf()/calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            debt=debtPerDay*(int)debtDays;
        }
        return debt+"гр";
    }
 }
