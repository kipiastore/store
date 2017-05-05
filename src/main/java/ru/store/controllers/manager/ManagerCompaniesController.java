package ru.store.controllers.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Company;
import ru.store.entities.CompanyAddress;
import ru.store.entities.CompanyReminder;
import ru.store.service.CompanyAddressService;
import ru.store.service.CompanyReminderService;
import ru.store.service.CompanyService;
import ru.store.service.LoadMapsService;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ManagerCompaniesController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyAddressService companyAddressService;
    @Autowired
    private CompanyReminderService companyReminderService;

    @Autowired
    private LoadMapsService loadMapsServiceService;

    @Autowired
    private SearchByPage search;

    private Boolean iSChoiceComments;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
    @RequestMapping(value = "/manager/companies", method = RequestMethod.GET)
    public ModelAndView company() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }
    @RequestMapping(value = "/manager/updatecompany", method = RequestMethod.POST)
    public ModelAndView updateCompany(@ModelAttribute("company") Company company, @RequestParam("hiddenId") String id,
                                      @RequestParam("UpAddressJson") String UpAddressJson,
                                      @RequestParam("UpDelete") String UpDelete){
        ModelAndView modelAndView = new ModelAndView();
        try {
            company.setId(Integer.valueOf(id));
            companyService.updateCompanyByManager(company);
            companyAddressService.updateCompanyAddresses(buildCompanyAddress(company, UpAddressJson));
            companyAddressService.deleteCompanyAddress(UpDelete.split(","));
            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }
    @RequestMapping(value = "/manager/searchcompany", method = RequestMethod.POST)
    public ModelAndView searchCompany(@RequestParam MultiValueMap<String, String> searchMap,
                                      @RequestParam("selectSearchCompanyByType")String selectSearchCompanyByType,
                                      @RequestParam("selectSearchCompanyByPaymentStatus")String selectSearchCompanyByPaymentStatus){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Company> companies;
        iSChoiceComments=false;
        companies= search.search(searchMap,selectSearchCompanyByType,selectSearchCompanyByPaymentStatus,modelAndView,auth);
        List<CompanyReminder>companyReminders=companyReminderService.getLastCompaniesReminderType();
        List<String> stringAllCounts=companyReminderService.getAllCompanyReminderAmount();
        iSChoiceComments=search.getIsShowAllCompanyWithComments();
        Model model = new Model();
        loadPage(model, modelAndView);
        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
        System.out.println(iSChoiceComments);
        if(iSChoiceComments==false) {
            for (Company company : companies) {
                model.companyList.add(convert(company,companyReminders,stringAllCounts));
            }
        }
        else{
            if(search.getChoice()==1){
                searchByChoice(companies,companyReminders,stringAllCounts,model,1);
            }
            if(search.getChoice()==2){
                searchByChoice(companies,companyReminders,stringAllCounts,model,2);
            }
        }
        return modelAndView;
    }
    private List<CompanyAddress> buildCompanyAddress(Company company, String addressJson) {
        List<CompanyAddress> companyAddresses = new Gson().fromJson(addressJson,
                new TypeToken<List<CompanyAddress>>(){}.getType());
        for (CompanyAddress companyAddress : companyAddresses)
            companyAddress.setCompanyId(company.getId());
        return companyAddresses;
    }
    private void loadPage(Model model, ModelAndView modelAndView) {
        model.selectedPageNum=1;
        loadCompanies(model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("manager/companies");
    }
    private void loadCompanies(Model model) {
        List<Company> companies=new ArrayList<>();
        List<CompanyReminder>companyReminders=companyReminderService.getCompanyReminders();
        List<CompanyAddress> companyAddresses=companyAddressService.getCompanyAddresses();
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
        model.filterListMap = groupByFilter(companies);
        model.message = "Последние редактированные фирмы:";
        model.companyList = new ArrayList<>();
        List<CompanyReminder>companyLastReminders=companyReminderService.getLastCompaniesReminderType();
        List<String> s=companyReminderService.getAllCompanyReminderAmount();
        for (Company company : companies) {
            model.companyList.add(convert(company,companyLastReminders,s));
        }
    }
    private String getNormalName(String name) {
        if (name != null && name.length() > 26)
            return name.substring(0, 26) + "..";
        else
            return name;
    }

    private void searchByChoice(List<Company>companies,List<CompanyReminder>companyReminders,List<String>stringAllCounts,Model model,int choice) {
        System.out.println("Выборррррррр поиска"+choice);
        Model.CompaniesItem c;
        for (Company company : companies) {
            c = convert(company, companyReminders,stringAllCounts);
            System.out.println("---"+c.getTypeOfNote());
            if (choice == 1) {
                if (c.getTypeOfNote() != "") {
                    model.companyList.add(c);
                }
            }
            if (choice == 2) {
                if (c.getTypeOfNote() == "") {
                    model.companyList.add(c);
                }
            }
        }
    }
    private Model.CompaniesItem convert(Company company,List<CompanyReminder> companyLastReminders,List<String>allRemindersCount) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        Model.CompaniesItem companyItem = new Model.CompaniesItem();
        companyItem.id = company.getId();
        companyItem.name = getNormalName(company.getName());
        companyItem.directorFullName = company.getDirectorFullName();
        companyItem.legalAddress = company.getLegalAddress();
        companyItem.phone = company.getPhone();
        companyItem.typeOfNote = "";
        if(!companyLastReminders.isEmpty()) {
            for (CompanyReminder companyReminder : companyLastReminders) {
                if (company.getId() == companyReminder.getCompanyId()) {
                    for (String s : allRemindersCount) {
                        String[] tempS = s.split("-");
                        if (Integer.parseInt(tempS[0]) == companyReminder.getCompanyId()) {
                            companyItem.typeOfNote = sdf.format(companyReminder.getDateReminder()) + " " + companyReminder.getTypeReminder() + " " + tempS[1];
                        }
                    }
                }
            }
        }
        return companyItem;
    }
    private Map<Model.Filter, List<Model.CompaniesItem>> groupByFilter(List<Company> companies) {
        Map<Model.Filter, List<Model.CompaniesItem>> result = new TreeMap<>();
        Model.Filter filter;
        List<Model.CompaniesItem> companyItems;
        List<Model.CompaniesItem> allCompanyItems = new ArrayList<>();
        boolean isAdded;
        for (Company company : companies) {
            isAdded = false;
            if (company.getDateOfContract() != null&&company.getDateOfStartContract()!=null&&company.getDateOfEndContract()!=null) { // 07.03.17!!!!!
                if (company.getDateOfEndContract().getTime() - new Date().getTime() < 604800000 &&
                        company.getDateOfEndContract().getTime() - new Date().getTime() > 259200000) {
                    isAdded = true;
                    if (result.get(new Model.Filter(1)) == null) {
                        filter = new Model.Filter(1);
                        filter.name = "До конца договора 7 д.";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(1)).add(convertForFilter(company));
                    }
                }
                if (company.getDateOfEndContract().getTime() - new Date().getTime() < 259200000 &&
                        company.getDateOfEndContract().getTime() - new Date().getTime() > 86400000) {
                    isAdded = true;
                    if (result.get(new Model.Filter(2)) == null) {
                        filter = new Model.Filter(2);
                        filter.name = "До конца договора 3 д.";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(2)).add(convertForFilter(company));
                    }
                }
                if (company.getDateOfEndContract().getTime() - new Date().getTime() < 86400000 &&
                        company.getDateOfEndContract().getTime() - new Date().getTime() > 0) {
                    isAdded = true;
                    if (result.get(new Model.Filter(3)) == null) {
                        filter = new Model.Filter(3);
                        filter.name = "До конца договора 1 д.";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(3)).add(convertForFilter(company));
                    }
                }
                if (company.getDateOfEndContract().getTime() < new Date().getTime() &&
                        (!company.getIsClosed())) {
                    isAdded = true;
                    if (result.get(new Model.Filter(4)) == null) {
                        filter = new Model.Filter(4);
                        filter.name = "Срок истек, не отключены";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(4)).add(convertForFilter(company));
                    }
                }
                if (company.getIsOffPosition()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(5)) == null) {
                        filter = new Model.Filter(5);
                        filter.name = "Отключены позиции";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(5)).add(convertForFilter(company));
                    }
                }
                if (company.getIsRedirect()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(6)) == null) {
                        filter = new Model.Filter(6);
                        filter.name = "Переадресация";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(6)).add(convertForFilter(company));
                    }
                }
                if (company.getIsPriority()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(7)) == null) {
                        filter = new Model.Filter(7);
                        filter.name = "Приоритет";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(7)).add(convertForFilter(company));
                    }
                }
                if (false) { // wtf?!
                    isAdded = true;
                    if (result.get(new Model.Filter(8)) == null) {
                        filter = new Model.Filter(8);
                        filter.name = "Только сайт";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(8)).add(convertForFilter(company));
                    }
                }
                if (!company.getIsShowForOperator()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(9)) == null) {
                        filter = new Model.Filter(9);
                        filter.name = "Выкл. в справочной";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(9)).add(convertForFilter(company));
                    }
                }
                if (!company.getIsShowForSite()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(10)) == null) {
                        filter = new Model.Filter(10);
                        filter.name = "Выкл. на сайте";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(10)).add(convertForFilter(company));
                    }
                }
                if (false) { // wtf..
                    isAdded = true;
                    if (result.get(new Model.Filter(11)) == null) {
                        filter = new Model.Filter(11);
                        filter.name = "Доп. услуги";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(11)).add(convertForFilter(company));
                    }
                }
                if (company.getDateOfEndContract().getTime() < new Date().getTime() && company.getIsPriority()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(12)) == null) {
                        filter = new Model.Filter(12);
                        filter.name = "Срок истек. Приоритет вкл.";
                        companyItems = new ArrayList<>();
                        companyItems.add(convertForFilter(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(12)).add(convertForFilter(company));
                    }
                }
                if (!isAdded)
                    allCompanyItems.add(convertForFilter(company));
            }
            if (allCompanyItems.size() != 0) {
                filter = new Model.Filter(16);
                filter.name = "Остальные";
                result.put(filter, allCompanyItems);
            }
        }
        return result;
    }
    private Model.CompaniesItem convertForFilter(Company company) {
        Model.CompaniesItem companyItem = new Model.CompaniesItem();
        companyItem.id = company.getId();
        companyItem.name = getNormalName(company.getName());
        if(company.getDateOfEndContract()!=null) {
            companyItem.dateOfEndContract = company.getDateOfEndContract().toString().substring(0, 11);
        }
        else{
            companyItem.dateOfEndContract = "";
        }
        companyItem.manager = company.getManager();
        return companyItem;
    }
    @RequestMapping(value = "/manager/updatecompany", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/manager/companies";
    }
    @RequestMapping(value = "/manager/searchcompany", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/manager/companies";
    }
    @RequestMapping(value = "/manager/searchacts", method = RequestMethod.GET)
    public String redirect4() {
        return "redirect:/manager/acts";
    }
    @RequestMapping(value = "/manager/searchnotes", method = RequestMethod.GET)
    public String redirect5() {
        return "redirect:/manager/notes";
    }
    @RequestMapping(value = "/manager/searchdebtors", method = RequestMethod.GET)
    public String redirect6() {
        return "redirect:/manager/debtors";
    }
}
