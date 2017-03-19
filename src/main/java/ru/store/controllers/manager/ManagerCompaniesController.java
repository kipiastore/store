package ru.store.controllers.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Company;
import ru.store.entities.CompanyAddress;
import ru.store.entities.CompanyReminder;
import ru.store.service.*;

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
    private RegionService regionService;
    @Autowired
    private PackageService packageService;
    @Autowired
    private UserService userService;
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
        List<Company> companies;
        iSChoiceComments=false;
        companies= search.search(searchMap,selectSearchCompanyByType,selectSearchCompanyByPaymentStatus,modelAndView);
        List<CompanyReminder>companyReminders=companyReminderService.getLastCompaniesReminderType();
        List<String> stringAllCounts=companyReminderService.getAllCompanyReminderAmount();
        iSChoiceComments=search.getIsShowAllCompanyWithComments();
        Model model = new Model();
        loadPage(model, modelAndView);
        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
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
        loadCompanies(model);
        model.selectedPageNum=1;
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
            modelAndView.setViewName("manager/companies");
    }
    private void loadCompanies(Model model) {
        List<Company> companies = companyService.getCompanies();
        List<CompanyAddress>companyAddresses=companyAddressService.getCompanyAddresses();
        List<CompanyReminder>companyReminders=companyReminderService.getCompanyReminders();
        List<Model.CompanyAddressItem> companyAddressList = new ArrayList<>();
        List<Model.CompanyReminderItem> companyRemindersList = new ArrayList<>();
        Model.CompanyAddressItem companyAddressItem;
        Model.CompanyReminderItem companyReminderItem;
        Map<Integer,List<CompanyReminder>> mapReminders=new HashMap<>();
        Map<Integer,List<CompanyAddress>> mapAddresses=new HashMap<>();
        for (Company company : companies) {
            List<CompanyAddress> companyAddressesList;
            List<CompanyReminder> companyReminderList;
            for (CompanyAddress companyAddress : companyAddresses) {
                if(company.getId()==companyAddress.getCompanyId()) {
                    if(mapAddresses.get(company.getId())==null){
                        companyAddressesList=new ArrayList<>();
                        companyAddressesList.add(companyAddress);
                        mapAddresses.put(company.getId(),companyAddressesList);
                    }
                    else{
                        mapAddresses.get(company.getId()).add(companyAddress);
                    }
                }
            }
            for (CompanyReminder companyReminder : companyReminders) {
               if(company.getId()==companyReminder.getCompanyId()) {
                       if(mapReminders.get(company.getId())==null){
                           companyReminderList=new ArrayList<>();
                           companyReminderList.add(companyReminder);
                           mapReminders.put(company.getId(),companyReminderList);
                }
                else{
                           mapReminders.get(company.getId()).add(companyReminder);
                }
               }
            }
        }
        for(Integer companyId:mapAddresses.keySet()) {
            companyAddressItem = new Model.CompanyAddressItem();
            companyAddressItem.companyAddresses = mapAddresses.get(companyId);
            companyAddressItem.setCompanyId(companyId);
            companyAddressList.add(companyAddressItem);
        }
        for(Integer companyId:mapReminders.keySet()){
            companyReminderItem = new Model.CompanyReminderItem();
            companyReminderItem.companyReminders = mapReminders.get(companyId);
            companyReminderItem.setCompanyId(companyId);
            companyRemindersList.add(companyReminderItem);
        }
        //model.companiesJson = companies.toString();
        model.companyAddressJson = companyAddressList.toString();
        model.companyReminderJson = companyRemindersList.toString();
        model.regions = regionService.getRegions();
        model.numOfAddress = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        model.message = "Последние редактированные фирмы:";
        model.companyList = new ArrayList<>();
        List<CompanyReminder>companyLastReminders=companyReminderService.getLastCompaniesReminderType();
        List<String> s=companyReminderService.getAllCompanyReminderAmount();
        for (Company company : companyService.getCompaniesByLastUpdate()) {
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
        for(CompanyReminder companyReminder:companyLastReminders) {
            if(company.getId()==companyReminder.getCompanyId()) {
                for(String s:allRemindersCount) {
                   String []tempS=s.split("-");
                    if(Integer.parseInt(tempS[0])==companyReminder.getCompanyId()) {
                        companyItem.typeOfNote = sdf.format(companyReminder.getDateReminder()) + " " + companyReminder.getTypeReminder()+" "+tempS[1];
                    }
                }
            }
        }
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
