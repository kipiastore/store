package ru.store.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Company;
import ru.store.entities.CompanyReminder;
import ru.store.service.CompanyReminderService;
import ru.store.service.CompanyService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akex on 05.10.2016.
 */
@Controller
public class ManagerNotesController {
    @Autowired
    SearchByPage searchByPage;
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyReminderService companyReminderService;
    private Boolean iSChoiceComments;
    public List<Company> tempList;
    @RequestMapping(value = "/manager/notes", method = RequestMethod.GET)
    public ModelAndView notes(){
        ModelAndView modelAndView=new ModelAndView();
        Model model=new Model();
        loadPage(modelAndView,model);
        return modelAndView;
    }
    @RequestMapping(value = "/manager/searchcompanynotes", method = RequestMethod.POST)
    public ModelAndView searchCompany(@RequestParam MultiValueMap<String, String> searchMap, @RequestParam("selectSearchCompany")String selectSearchType,@RequestParam("selectSearchCompanyByPaymentStatus")String selectSearchCompanyByPaymentStatus) {
        ModelAndView modelAndView = new ModelAndView();
        List<Company> companies;
        iSChoiceComments=false;
        companies= searchByPage.search(searchMap,selectSearchType,modelAndView);
        iSChoiceComments=searchByPage.getIsShowAllCompanyWithComments();
        Model model = new Model();
        loadPage(modelAndView,model);
        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
        if(iSChoiceComments==false) {
            for (Company company : companies) {
                List<Model.CompaniesItem> list=convert(company);
                for(Model.CompaniesItem m:list) {
                    model.companyList.add(m);
                }
            }
        }
        else{
            if(searchByPage.getChoice()==1){
                searchByChoice(companies,model,1);
            }
            if(searchByPage.getChoice()==2){
                searchByChoice(companies,model,2);
            }
        }
        tempList=new ArrayList<>();
        tempList=companies;
        return modelAndView;
    }
    @RequestMapping(value = "/manager/filtercompanynotes", method = RequestMethod.POST)
    public ModelAndView filterCompanyNotes( @RequestParam("notesDateFrom")String notesDateFrom,@RequestParam("notesDateTo")String notesDateTo) {
        ModelAndView modelAndView = new ModelAndView();
        Model model=new Model();
        System.out.println("========================"+notesDateFrom+"---"+notesDateTo);
        List<CompanyReminder> companyReminders;
        if(notesDateTo.equals("")&&!notesDateFrom.equals("")){
            companyReminders = companyReminderService.getCompanyRemindersByFilterFromDate(notesDateFrom);
        }
        else if(notesDateFrom.equals("")&&!notesDateTo.equals("")){
            companyReminders = companyReminderService.getCompanyRemindersByFilterToDate(notesDateTo);
        }
        else if(notesDateFrom.equals("")&&notesDateTo.equals("")){
            companyReminders = companyReminderService.getCompanyReminders();
        }
        else{
             companyReminders = companyReminderService.getCompanyRemindersByFilter(notesDateFrom, notesDateTo);
        }
        loadPageByFilter(modelAndView,model,companyReminders);
        return modelAndView;
    }
    private void loadPageByFilter(ModelAndView modelAndView, Model model,List<CompanyReminder> companyReminders){
        model.selectedPageNum=4;
        List<Model.CompaniesItem> companiesItems=new ArrayList<>();
        Model.CompaniesItem companiesItem;
        for(Company company:tempList ) {
                for (CompanyReminder companyReminder : companyReminders) {
                    if(company.getName().equals(companyReminder.getCompanyName())) {
                        if(companyReminder!=null) {
                            companiesItem = new Model.CompaniesItem();
                            companiesItem.name = company.getName();
                            companiesItem.dateOfNote = companyReminder.getDateReminder();
                            companiesItem.commentOfNote = companyReminder.getCommentReminder();
                            companiesItem.typeOfNote = companyReminder.getTypeReminder();
                            companiesItems.add(companiesItem);
                        }
                }
            }
        }
        model.companyList = new ArrayList<>();
        for(Model.CompaniesItem m:companiesItems) {
            model.companyList.add(m);
        }
        modelAndView.addObject("model",model);
        modelAndView.addObject("prefix","");
        modelAndView.setViewName("manager/notes");
    }
    private void loadPage(ModelAndView modelAndView, Model model){
        model.selectedPageNum=4;
        loadCompanies(model);
        modelAndView.addObject("model",model);
        modelAndView.addObject("prefix","");
        modelAndView.setViewName("manager/notes");
    }
    private void loadCompanies(Model model) {
        List<Company> companies = companyService.getCompanies();
        model.companyList=new ArrayList<>();
        for(Company company:companies) {
                List<Model.CompaniesItem> list = convert(company);
                for (Model.CompaniesItem m : list) {
                    model.companyList.add(m);
                }
            }
        tempList=companies;
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
                        if (!companyItem.getTypeOfNote().equals("")) {
                            model.companyList.add(companyItem);
                        }
                    }
                    if (choice == 2) {
                        if (companyItem.getTypeOfNote().equals("")) {
                            model.companyList.add(companyItem);
                        }
                }
            }
        }
    }
    private List<Model.CompaniesItem> convert(Company company) {
        List<Model.CompaniesItem> companyItems = new ArrayList<>();
        Model.CompaniesItem companyItem;
        List<CompanyReminder> companyReminders;
        companyReminders= companyReminderService.getCompanyReminders(company.getId());
        if(companyReminders.isEmpty()){
            companyItem=new Model.CompaniesItem();
            companyItem.name=company.getName();
            companyItem.dateOfNote="";
            companyItem.typeOfNote="";
            companyItem.commentOfNote="";
            companyItems.add(companyItem);
        }
        else {
            for (CompanyReminder companyReminder : companyReminders) {
                companyItem = new Model.CompaniesItem();
                companyItem.name = getNormalName(companyReminder.getCompanyName());
                companyItem.dateOfNote = companyReminder.getDateReminder();
                if (companyReminder.getCommentReminder() != null) {
                    companyItem.commentOfNote = companyReminder.getCommentReminder();
                } else {
                    companyItem.commentOfNote = "";
                }
                companyItem.typeOfNote = companyReminder.getTypeReminder();
                companyItems.add(companyItem);
            }
        }
        return companyItems;
    }
}
