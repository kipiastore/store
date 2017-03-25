package ru.store.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Company;
import ru.store.entities.CompanyReminder;
import ru.store.service.CompanyReminderService;
import ru.store.service.CompanyService;

import java.text.SimpleDateFormat;
import java.util.*;

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

    List<Company> tempList;
    //21.02.17
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
    @RequestMapping(value = "/manager/notes", method = RequestMethod.GET)
    public ModelAndView notes(){
        ModelAndView modelAndView=new ModelAndView();
        Model model=new Model();
        loadNotesForFirstPage(modelAndView,model);
        return modelAndView;
    }
    @RequestMapping(value = "/manager/searchnotesbyfirstpage", method = RequestMethod.POST)
    public ModelAndView searchCompany(@RequestParam MultiValueMap<String, String> searchMap,
                                      @RequestParam("selectSearchCompanyByType")String selectSearchCompanyByType,
                                      @RequestParam("selectSearchCompanyByPaymentStatus")String selectSearchCompanyByPaymentStatus) {
        ModelAndView modelAndView = new ModelAndView();
        List<Company> companies;
        iSChoiceComments=false;
        companies= searchByPage.search(searchMap,selectSearchCompanyByType,selectSearchCompanyByPaymentStatus,modelAndView);
        iSChoiceComments=searchByPage.getIsShowAllCompanyWithComments();
        Model model = new Model();
        loadNotesForFirstPage(modelAndView,model);
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
    private void loadNotesForFirstPage(ModelAndView modelAndView, Model model){
        model.selectedPageNum=4;
        loadCompaniesNotesForFirstPage(model);
        modelAndView.addObject("model",model);
        modelAndView.addObject("prefix","");
        modelAndView.setViewName("manager/notesFirst");
    }
    private void loadCompaniesNotesForFirstPage(Model model) {
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        List<Model.CompaniesItem> companyItems = new ArrayList<>();
        Model.CompaniesItem companyItem;
        List<CompanyReminder> companyReminders;
        // запросы в цыкле - это плохо
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
                companyItem.dateOfNote = sdf.format(companyReminder.getDateReminder());
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
    //22.02.17
    @RequestMapping(value = "/manager/filtercompanynotes", method = RequestMethod.POST)
    public ModelAndView filterCompanyNotes( @RequestParam("notesDateFrom")Date notesDateFrom,@RequestParam("notesDateTo")Date notesDateTo) {
        ModelAndView modelAndView = new ModelAndView();
        Model model=new Model();
        System.out.println("========================"+notesDateFrom+"---"+notesDateTo);
        List<CompanyReminder> companyReminders;
        if(notesDateTo==null && notesDateFrom!=null){
            companyReminders = companyReminderService.getCompanyRemindersByFilterFromDate(notesDateFrom);
        }
        else if(notesDateFrom==null && notesDateTo!=null){
            companyReminders = companyReminderService.getCompanyRemindersByFilterToDate(notesDateTo);
        }
        else if(notesDateFrom==null &&notesDateTo==null){
            companyReminders = companyReminderService.getCompanyReminders();
        }
        else{
            companyReminders = companyReminderService.getCompanyRemindersByFilter(notesDateFrom, notesDateTo);
        }
        loadPageByFilter(modelAndView,model,companyReminders);
        return modelAndView;
    }
    private void loadPageByFilter(ModelAndView modelAndView, Model model,List<CompanyReminder> companyReminders){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        model.selectedPageNum=4;
        List<Model.CompaniesItem> companiesItems=new ArrayList<>();
        Model.CompaniesItem companiesItem;
        for(Company company:tempList ) {
            for (CompanyReminder companyReminder : companyReminders) {
                if(company.getName().equals(companyReminder.getCompanyName())) {
                    companiesItem = new Model.CompaniesItem();
                    companiesItem.name = company.getName();
                    companiesItem.dateOfNote = sdf.format(companyReminder.getDateReminder());
                    companiesItem.commentOfNote = companyReminder.getCommentReminder();
                    companiesItem.typeOfNote = companyReminder.getTypeReminder();
                    companiesItems.add(companiesItem);
                }
            }
        }
        model.companyList = new ArrayList<>();
        for(Model.CompaniesItem m:companiesItems) {
            model.companyList.add(m);
        }
        modelAndView.addObject("model",model);
        modelAndView.addObject("prefix","");
        modelAndView.setViewName("manager/notesFirst");
    }

    @RequestMapping(value = "/manager/searchnotesbysecondpage", method = RequestMethod.POST)
    public ModelAndView searchCompanyBySearchMenu (@RequestParam MultiValueMap<String, String> searchMap) {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadNotesForSecondPage(modelAndView,model,searchMap);
        return modelAndView;
    }
    private void loadNotesForSecondPage(ModelAndView modelAndView,Model model,MultiValueMap<String, String> searchMap) {
        loadCompaniesNotesForSecondPage(getCompaniesNotesForSecondPage(modelAndView,searchMap),modelAndView,model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("manager/notesSecond");
    }
    //27.02.17
    private ModelAndView loadCompaniesNotesForSecondPage(List<Company> companies,ModelAndView modelAndView, Model model) {
        System.out.println("----"+companies);
        List<CompanyReminder>companyReminders=companyReminderService.getCompanyReminders();
        System.out.println("----"+companyReminders);
        model.reminderList = new ArrayList<>();
            for (Company company : companies) {
                for (CompanyReminder companyReminder : companyReminders) {
                    if (company.getId()==companyReminder.getCompanyId()){
                        model.reminderList.add(convertForSecondPage(companyReminder));
                    }
                }
            }
        return modelAndView;
    }
    //27.02.17
    private List<Company> getCompaniesNotesForSecondPage(ModelAndView modelAndView,MultiValueMap<String, String> searchMap ) {
        List<Company> companies=new ArrayList();
        try {
            String value;
            for (String key : searchMap.keySet()) {
                value = (searchMap.get(key) + "").replace("[", "").replace("]", "").trim();
                if (!key.equals("_csrf") && !value.isEmpty()) {
                    if (key.equals("name")) {
                        companies = companyService.findCompaniesByName(value);
                        break;
                    }
                    if (key.equals("phone")) {
                        companies = companyService.findCompaniesByPhone(value);
                        break;
                    }
                    if (key.equals("email")) {
                        companies = companyService.findCompaniesByEmail(value);
                        break;

                    }
                    if (key.equals("contractNum")) {
                        //companies = companyService.findCompaniesByContractNum(value);
                        break;
                    }

                }
                else{
                    companies = companyService.getCompanies();
                }
            }
        } catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        return companies;
    }
    private Model.CompaniesItem convertForSecondPage(CompanyReminder companyReminder) {
        Model.CompaniesItem companiesItem = new Model.CompaniesItem();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        companiesItem.name = companyReminder.getCompanyName();
        companiesItem.dateOfNote = sdf.format(companyReminder.getDateReminder());
        companiesItem.commentOfNote = companyReminder.getCommentReminder();
        companiesItem.typeOfNote = companyReminder.getTypeReminder();
        companiesItem.hourOfNote = companyReminder.getHourReminder();
        return companiesItem;
    }
    @RequestMapping(value = "/manager/searchnotesbycalendar", method = RequestMethod.POST)
    public ModelAndView searchMenuDate (@RequestParam("hiddenSearchDate") Date date,
                                        @RequestParam("selectMonth") String selectMonth,
                                        @RequestParam (value = "submitSearchToday",required = false) String submitSearchToday,
                                        @RequestParam (value = "submitSearchTomorrow",required = false)String submitSearchTomorrow,
                                        @RequestParam (value = "submitSearchWeek",required = false)String submitSearchWeek) {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadNotesForSecondPageByCalendar(modelAndView,model,date,selectMonth,submitSearchToday,submitSearchTomorrow,submitSearchWeek);
        return modelAndView;
    }
    private void loadNotesForSecondPageByCalendar(ModelAndView modelAndView, Model model,
                                      Date date, String selectMonth,
                                      String submitSearchToday,String submitSearchTomorrow,String submitSearchWeek ) {
        loadCompaniesNotesByCalendar(date, searchByCalendar(date, selectMonth,submitSearchToday,submitSearchTomorrow, submitSearchWeek),model);
        modelAndView.addObject("model",model);
        modelAndView.addObject("prefix","");
        modelAndView.setViewName("manager/notesSecond");
    }
    private void loadCompaniesNotesByCalendar(Date date,List<CompanyReminder>companyReminders,Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        List<Model.CompaniesItem> companiesItems=new ArrayList<>();
        List<Model.CompaniesItem> remindersItems=new ArrayList<>();
        List<Company>companies=companyService.getCompanies();
        Model.CompaniesItem companiesItem;
            for (CompanyReminder companyReminder : companyReminders) {
                companiesItem = new Model.CompaniesItem();
                companiesItem.name = companyReminder.getCompanyName();
                companiesItem.dateOfNote = sdf.format(companyReminder.getDateReminder());
                companiesItem.commentOfNote = companyReminder.getCommentReminder();
                companiesItem.typeOfNote = companyReminder.getTypeReminder();
                companiesItem.hourOfNote = companyReminder.getHourReminder();
                remindersItems.add(companiesItem);
            }
            model.reminderList = new ArrayList<>();
            for (Model.CompaniesItem m : remindersItems) {
                model.reminderList.add(m);
            }
        for(Company company:companies) {
            if(company.getDateOfContract()!=null&&company.getDateOfEndContract().getTime()-date.getTime()<=259200000)
            {
                companiesItem = new Model.CompaniesItem();
                companiesItem.nameForNotes = company.getName();
                companiesItem.noteMain = "";
                companiesItem.historyOfNote = companyReminderService.getCompanyReminderAmount(company.getId());
                companiesItem.periodOfContract = "c "+sdf.format(company.getDateOfStartContract())+" по "+sdf.format(company.getDateOfEndContract());
                companiesItem.debt = checkIsDebt(company.getId());
                companiesItem.note = companyReminderService.getLastCompanyReminderDateHourType(company.getId());
                companiesItems.add(companiesItem);
                model.companyList = new ArrayList<>();
                for (Model.CompaniesItem m : companiesItems) {
                    model.companyList.add(m);
                }
            }
        }
    }
    private String checkIsDebt(Integer companyId) {
        int debt=0;
        Company company=companyService.getCompany(companyId);
        if (company.getDateOfEndContract().getTime() < new Date().getTime()){
            long debtDays=((new Date().getTime()-company.getDateOfEndContract().getTime())/86400000);
            Calendar calendar= GregorianCalendar.getInstance();
            int debtPerDay=company.getCostOf()/calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            debt=debtPerDay*(int)debtDays;
        }
        return debt+"гр";
    }
    //21.02.2017
    private List<CompanyReminder> searchByCalendar(Date date, String selectMonth, String submitSearchToday, String submitSearchTomorrow, String submitSearchWeek ){

        List<CompanyReminder>companyReminders=new ArrayList<>();
        //ger reminders by date
        if(selectMonth.equals("12")&&submitSearchToday==null &&submitSearchTomorrow==null&&submitSearchWeek==null) {
           companyReminders = companyReminderService.getCompanyReminderByDate(date);
        }
        //get reminders by month
        if(!selectMonth.equals("12")&&submitSearchToday==null &&submitSearchTomorrow==null&&submitSearchWeek==null) {
            companyReminders = companyReminderService.getCompanyReminderByMonth(selectMonth);
        }
        //get reminders by today
        if(selectMonth.equals("12")&&submitSearchToday!=null &&submitSearchTomorrow==null&&submitSearchWeek==null) {
            companyReminders = companyReminderService.getCompanyReminderByDateToday(date);
        }
        //get reminders by tomorrow
        if(selectMonth.equals("12")&&submitSearchToday==null &&submitSearchTomorrow!=null&&submitSearchWeek==null) {
            companyReminders = companyReminderService.getCompanyReminderByDateTomorrow(date);
        }
        //get reminders by week
        if(selectMonth.equals("12")&&submitSearchToday==null &&submitSearchTomorrow==null&&submitSearchWeek!=null) {
            companyReminders = companyReminderService.getCompanyReminderByDateWeek(date);
        }
        return companyReminders;
    }

}
