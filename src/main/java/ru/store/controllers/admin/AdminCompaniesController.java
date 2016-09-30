package ru.store.controllers.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.store.dao.interfaces.RegionDAO;
import ru.store.entities.*;
import ru.store.service.CompanyAddressService;
import ru.store.service.CompanyService;
import ru.store.service.RegionService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
@Controller
public class AdminCompaniesController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyAddressService companyAddressService;
    @Autowired
    private RegionService regionService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping(value = "/admin/companies", method = RequestMethod.GET)
    public ModelAndView company() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addcompany", method = RequestMethod.POST)
    public ModelAndView createCompany(@ModelAttribute("company") Company company,
                                      @RequestParam("addressJson") String addressJson) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            companyService.createCompany(company);
            companyAddressService.createCompanyAddress(buildCompanyAddress(company, addressJson));
            modelAndView.addObject("successMessage", "Компания успешно добавлена.");
        } catch (Exception ex) {
            modelAndView.addObject("addError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        model.addingCompanyJson = company.toString();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/updatecompany", method = RequestMethod.POST)
    public ModelAndView updateCompany(@ModelAttribute("company") Company company, @RequestParam("hiddenId") String id,
                                      @RequestParam("UpAddressJson") String UpAddressJson,
                                      @RequestParam("UpDelete") String UpDelete) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            company.setId(Integer.valueOf(id));
            companyService.updateCompany(company);
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

    @RequestMapping(value = "/admin/deletecompany", method = RequestMethod.POST)
    public ModelAndView deleteCompany(@RequestParam("companyId") String companyId) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            companyService.deleteCompany(Integer.valueOf(companyId));
            modelAndView.addObject("successMessage", "Компания успешно удалена.");
        } catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        loadPage(model, modelAndView);
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
        model.selectedPageNum = 1;
        loadCompanies(model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("admin/companies");
    }

    private void loadCompanies(Model model) {
        List<Model.CompaniesItem> companyItems = new ArrayList<>();
        Model.CompaniesItem companyItem;
        List<Company> companies = companyService.getCompanies();
        List<Model.CompanyAddressItem> companyAddressItems = new ArrayList<>();
        Model.CompanyAddressItem companyAddressItem;
        for (Company company : companies) {
            companyItem = new Model.CompaniesItem();
            companyItem.id = company.getId();
            companyItem.name = company.getName();
            companyItem.dateOfEndContract = company.getDateOfEndContract().toString().substring(0, 11);
            companyAddressItem = new Model.CompanyAddressItem();
            companyAddressItem.setCompanyId(company.getId());
            companyAddressItem.setCompanyAddresses(companyAddressService.getCompanyAddresses(company.getId()));
            companyAddressItems.add(companyAddressItem);
            companyItems.add(companyItem);
        }
        model.companiesItems = companyItems;
        model.companiesJson = companies.toString();
        model.companyAddressJson = companyAddressItems.toString();
        model.regions = regionService.getRegions();
        model.numOfAddress = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    }

    @RequestMapping(value = "/admin/addcompany", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/admin/companies";
    }
    @RequestMapping(value = "/admin/updatecompany", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/admin/companies";
    }
    @RequestMapping(value = "/admin/deletecompany", method = RequestMethod.GET)
    public String redirect3() {
        return "redirect:/admin/companies";
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
        public String addingCompanyJson;
        public String companiesJson;
        public List<CompaniesItem> companiesItems;
        public List<Region> regions;
        public int[] numOfAddress;
        public String companyAddressJson;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public List<CompaniesItem> getCompaniesItems() {
            return companiesItems;
        }
        public String getAddingCompanyJson() {
            return addingCompanyJson;
        }
        public String getCompaniesJson() {
            return companiesJson;
        }
        public List<Region> getRegions() {
            return regions;
        }
        public int[] getNumOfAddress() {
            return numOfAddress;
        }
        public String getCompanyAddressJson() {
            return companyAddressJson;
        }

        public static class CompaniesItem {
            public int id;
            public String name;
            public String dateOfEndContract;

            public int getId() {
                return id;
            }
            public String getName() {
                return name;
            }
            public String getDateOfEndContract() {
                return dateOfEndContract;
            }
        }

        public static class CompanyAddressItem {
            public Integer companyId;
            public List<CompanyAddress> companyAddresses;

            public Integer getCompanyId() {
                return companyId;
            }
            public void setCompanyId(Integer companyId) {
                this.companyId = companyId;
            }
            public List<CompanyAddress> getCompanyAddresses() {
                return companyAddresses;
            }
            public void setCompanyAddresses(List<CompanyAddress> companyAddresses) {
                this.companyAddresses = companyAddresses;
            }

            @Override
            public String toString() {
                return "{" +
                        "\"companyId\":\"" + companyId + "\"," +
                        "\"companyAddresses\":" + companyAddresses + "" +
                        '}';
            }
        }
    }
}