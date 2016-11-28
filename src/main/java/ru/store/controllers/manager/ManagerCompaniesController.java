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
import ru.store.service.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @RequestMapping(value = "/manager/searchdate", method = RequestMethod.POST)
    public ModelAndView searchCalendarDate (@RequestParam("hiddenSearchDate") String date) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("ДАТА"+date);
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }
    @RequestMapping(value = "/manager/searchcompany", method = RequestMethod.POST)
    public ModelAndView searchCompany(@RequestParam MultiValueMap<String, String> searchMap, @RequestParam("selectSearchCompany")String selectSearchType) {
        ModelAndView modelAndView = new ModelAndView();
        List<Company> companies;
        iSChoiceComments=false;
        companies= search.search(searchMap,selectSearchType,modelAndView);
        iSChoiceComments=search.getIsShowAllCompanyWithComments();
        Model model = new Model();
        loadPage(model, modelAndView);
        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
        if(iSChoiceComments==false) {
            for (Company company : companies) {
                model.companyList.add(convert(company));
            }
        }
            else{
                if(search.getChoice()==1){
                    searchByChoice(companies,model,1);
                }
                if(search.getChoice()==2){
                    searchByChoice(companies,model,2);
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
        List<Model.CompanyAddressItem> companyAddressItems = new ArrayList<>();
        Model.CompanyAddressItem companyAddressItem;
        for (Company company : companies) {
            companyAddressItem = new Model.CompanyAddressItem();
            companyAddressItem.setCompanyId(company.getId());
            companyAddressItem.setCompanyAddresses(companyAddressService.getCompanyAddresses(company.getId()));
            companyAddressItems.add(companyAddressItem);
        }
        List<Model.CompanyReminderItem> companyReminderItems = new ArrayList<>();

        Model.CompanyReminderItem companyReminderItem;
        for (Company company : companies) {
            companyReminderItem = new Model.CompanyReminderItem();
            companyReminderItem.setCompanyId(company.getId());
            companyReminderItem.setCompanyReminders(companyReminderService.getCompanyReminders(company.getId()));
            companyReminderItems.add(companyReminderItem);
        }

        model.companiesJson = companies.toString();
        model.companyAddressJson = companyAddressItems.toString();
        model.companyReminderJson = companyReminderItems.toString();
        model.regions = regionService.getRegions();
        model.numOfAddress = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        model.message = "Последние редактированные фирмы:";
        model.companyList = new ArrayList<>();

        for (Company company : companyService.getCompaniesByLastUpdate()) {
            model.companyList.add(convert(company));
        }
    }
    private String getNormalName(String name) {
        if (name != null && name.length() > 26)
            return name.substring(0, 26) + "..";
        else
            return name;
    }

    private void searchByChoice(List<Company>companies,Model model,int choice) {
        System.out.println("Выборррррррр поиска"+choice);
        Model.CompaniesItem c;
        for (Company company : companies) {
            c = convert(company);
            if(choice==1) {
                if(c.getTypeOfNote()!="") {
                    model.companyList.add(c);
                }
            }
            if(choice==2) {
                   if(c.getTypeOfNote()=="") {
                       model.companyList.add(c);
                   }
            }
        }
    }
    private Model.CompaniesItem convert(Company company) {
        Model.CompaniesItem companyItem = new Model.CompaniesItem();
        companyItem.id = company.getId();
        companyItem.name = getNormalName(company.getName());
        companyItem.directorFullName = company.getDirectorFullName();
        companyItem.legalAddress = company.getLegalAddress();
        companyItem.phone = company.getPhone();
        companyItem.typeOfNote= companyReminderService.getLastCompanyReminderType(company.getId());
        return companyItem;
    }
    @RequestMapping(value = "/manager/updatecompany", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/manager/companies";
    }
    @RequestMapping(value = "/manager/searchdate", method = RequestMethod.GET)
    public String redirect3() {
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
