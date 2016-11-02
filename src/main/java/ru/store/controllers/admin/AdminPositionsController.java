package ru.store.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.*;
import ru.store.entities.Package;
import ru.store.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
public class AdminPositionsController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/admin/positions", method = RequestMethod.GET)
    public ModelAndView positions() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addsubpartitions", method = RequestMethod.POST)
    public ModelAndView updateCompany(@ModelAttribute("company") Company company,
                                      @RequestParam("hiddenId") String id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            System.out.println(company);
            System.out.println(id);
            company.setId(Integer.valueOf(id));
            companyService.updatePartitionCompany(company);
            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/positionsearchcompany", method = RequestMethod.POST)
    public ModelAndView searchCompany(@RequestParam MultiValueMap<String, String> searchMap) {
        ModelAndView modelAndView = new ModelAndView();
        List<Company> companies = new ArrayList<>();
        try {
            String value;
            for (String key : searchMap.keySet()) {
                value = searchMap.get(key).get(0).trim();
                System.out.println(value);
                if (!key.equals("_csrf") && !value.isEmpty()) {
                    if (key.equals("name")) {
                        companies = companyService.findCompaniesByName(value);
                        break;
                    }
                    if (key.equals("legalName")) {
                        companies = companyService.findCompaniesByLegalName(value);
                        break;
                    }
                    if (key.equals("phone")) {
                        companies = companyService.findCompaniesByPhone(value);
                        break;
                    }
                    if (key.equals("contractNum")) {
                        //companies = companyService.getCompaniesByLastUpdate(); wtf?
                        break;
                    }
                    if (key.equals("email")) {
                        companies = companyService.findCompaniesByEmail(value);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
        for (Company company : companies) {
            model.companyList.add(convert(company));
        }
        return modelAndView;
    }

    private void loadPage(Model model, ModelAndView modelAndView) {
        model.selectedPageNum = 2;
        loadCompanies(model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("admin/positions");
    }

    private void loadCompanies(Model model) {
        model.message = "Последние редактированные фирмы:";
        model.companyList = new ArrayList<>();
        for (Company company : companyService.getCompaniesByLastUpdate()) {
            model.companyList.add(convert(company));
        }

    }

    private Model.CompaniesItem convert(Company company) {
        Model.CompaniesItem companyItem = new Model.CompaniesItem();
        companyItem.id = company.getId();
        companyItem.name = getNormalName(company.getName());
        return companyItem;
    }

    private String getNormalName(String name) {
        if (name != null && name.length() > 26)
            return name.substring(0, 26) + "..";
        else
            return name;
    }

    @RequestMapping(value = "/admin/addsubpartitions", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/admin/positions";
    }
    @RequestMapping(value = "/admin/positionsearchcompany", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/admin/positions";
    }

    public static class Model {
        /**
         * 1 = companies
         * 2 = positions
         * 3 = partitions
         * 4 = users
         * 5 = managers
         * 6 = regions
         * 7 = packages
         * 8 = reports
         */
        public int selectedPageNum;
        public List<CompaniesItem> companyList;
        public String message;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public List<CompaniesItem> getCompanyList() {
            return companyList;
        }
        public String getMessage() {
            return message;
        }

        public static class CompaniesItem {
            public int id;
            public String name;
            public String manager;

            public int getId() {
                return id;
            }
            public String getName() {
                return name;
            }
            public String getManager() {
                return manager;
            }

        }
    }
}
