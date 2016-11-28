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
public class ManagerActsController {
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

    @RequestMapping(value = "/manager/acts", method = RequestMethod.GET)
    public ModelAndView acts(){
        ModelAndView modelAndView=new ModelAndView();
        Model model=new Model();
        loadPage(modelAndView,model);
        return modelAndView;
    }
    @RequestMapping(value = "/manager/searchcompanybyacts", method = RequestMethod.POST)
    public ModelAndView searchByActs(@RequestParam MultiValueMap<String, String> searchMap, @RequestParam("selectSearchCompany")String selectSearchType){
        ModelAndView modelAndView=new ModelAndView();
        Model model=new Model();
        List<Company> companies=searchByPage.search(searchMap,selectSearchType,modelAndView);
        loadPage(modelAndView,model);
        loadPage(modelAndView,model);
        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
        for (Company company : companies) {
            List<Model.CompaniesItem> list = convert(company);
            for (Model.CompaniesItem m : list) {
                model.companyList.add(m);
            }
        }

        return modelAndView;
    }
    private void loadPage(ModelAndView modelAndView,Model model){
        model.selectedPageNum=2;
        loadCompanies(model);
        modelAndView.addObject("model",model);
        modelAndView.addObject("prefix","");
        modelAndView.setViewName("manager/acts");
    }
    private void loadCompanies(Model model){
        List<Company> companies=companyService.getCompanies();
        model.companyList = new ArrayList<>();
        for (Company company : companies) {
                List<Model.CompaniesItem> list = convert(company);
                for (Model.CompaniesItem m : list) {
                    model.companyList.add(m);

            }
        }
    }
    private String getNormalName(String name) {
        if (name != null && name.length() > 26)
            return name.substring(0, 26) + "..";
        else
            return name;
    }
    private List<Model.CompaniesItem> convert(Company company) {
        List<Model.CompaniesItem> companyItems = new ArrayList<>();
        Model.CompaniesItem companyItem;
        companyItem=new Model.CompaniesItem();
        companyItem.name=company.getName();
        companyItem.manager=company.getManager();
        companyItem.timeOfContract="";
        companyItem.costOf=company.getCostOf();
        companyItem.noteOfActs="";
        companyItem.dateOfPaid="";
        companyItem.act="";
        companyItems.add(companyItem);
        return companyItems;
    }
}
