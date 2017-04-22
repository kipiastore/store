package ru.store.controllers.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.*;
import ru.store.entities.Package;
import ru.store.service.*;

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
    @Autowired
    private PackageService packageService;
    @Autowired
    private UserService userService;

    //@Autowired
    //private LoadMapsService loadMapsServiceService;

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
    public ModelAndView updateCompany(@ModelAttribute("company") Company company,
                                      @RequestParam("hiddenId") String id,
                                      @RequestParam("UpAddressJson") String UpAddressJson,
                                      @RequestParam("UpDelete") String UpDelete) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            company.setId(Integer.valueOf(id));
            companyService.updateCompany(company);
            companyAddressService.updateCompanyAddresses(buildCompanyAddress(company, UpAddressJson));
            companyAddressService.deleteCompanyAddress(UpDelete.split(","));
            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        }
        catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
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

    @RequestMapping(value = "/admin/searchcompany", method = RequestMethod.POST)
    public ModelAndView searchCompany(@RequestParam MultiValueMap<String, String> searchMap) {
        ModelAndView modelAndView = new ModelAndView();
        List<Company> companies = new ArrayList<>();
        try {
            String value;
            for (String key : searchMap.keySet()) {
                value = searchMap.get(key).get(0).trim();
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
                else{
                    companies=companyService.getCompanies();
                }
            }
        } catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        Map<String, String> usernameToFullName = new HashMap<>();
        for (User user : userService.getUsers()) {
            if (user.getUserRole().isEmpty()) {
                continue;
            }
            if (new ArrayList<>(user.getUserRole()).get(0).getRole().equals("ROLE_MANAGER")) {
                usernameToFullName.put(user.getUsername(), user.getFullName());
            }
        }
        loadPage(model, modelAndView);

        model.message = "Результаты поиска:";
        model.companyList = new ArrayList<>();
        for (Company company : companies) {
            model.companyList.add(convert(company));
        }
        Map<String, String> packageIdToName = new HashMap<>();
        for (Package aPackage : model.packages) {
            packageIdToName.put(aPackage.getId()+"", aPackage.getName());
        }
        for (Model.CompaniesItem item : model.companyList) {
            item.manager = usernameToFullName.get(item.manager);
            item.aPackage = packageIdToName.get(item.aPackage);
        }

        return modelAndView;
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
    @RequestMapping(value = "/admin/searchcompany", method = RequestMethod.GET)
    public String redirect4() {
        return "redirect:/admin/companies";
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
        List<Company> companies = companyService.getCompanies();
        List<CompanyAddress> companyAddresses=companyAddressService.getCompanyAddresses();
        List<Model.CompanyAddressItem> companyAddressList = new ArrayList<>();
        Model.CompanyAddressItem companyAddressItem;
        Map<Integer,List<CompanyAddress>> mapAddresses=new HashMap<>();
        for (Company company : companies) {
            List<CompanyAddress> companyAddressesList;
            if(!companyAddresses.isEmpty()) {
                for (CompanyAddress companyAddress : companyAddresses) {
                    if (company.getId() == companyAddress.getCompanyId()) {
                        if (mapAddresses.get(company.getId()) == null) {
                            companyAddressesList = new ArrayList<>();
                            companyAddressesList.add(companyAddress);
                            mapAddresses.put(company.getId(), companyAddressesList);
                        } else {
                            mapAddresses.get(company.getId()).add(companyAddress);
                        }
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
        model.companyAddressJson = companyAddressList.toString();
        model.regions = regionService.getRegions();
        model.packages = packageService.getPackages();
        model.filterListMap = groupByFilter(companies);
        List<User> usersFilteredByRole = new ArrayList<>();
        Map<String, String> usernameToFullName = new HashMap<>();
        for (User user : userService.getUsers()) {
            if (user.getUserRole().isEmpty()) {
                continue;
            }
            if (new ArrayList<>(user.getUserRole()).get(0).getRole().equals("ROLE_MANAGER")) {
                usersFilteredByRole.add(user);
                usernameToFullName.put(user.getUsername(), user.getFullName());
            }
        }
        model.managers = usersFilteredByRole;
        model.numOfAddress = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        model.message = "Последние редактированные фирмы:";
        model.companyList = new ArrayList<>();
        List<Company> companyList=companyService.getCompaniesByLastUpdate();
        for (Company company : companyList) {
            model.companyList.add(convert(company));
        }
        Map<String, String> packageIdToName = new HashMap<>();
        for (Package aPackage : model.packages) {
            packageIdToName.put(aPackage.getId()+"", aPackage.getName());
        }
        for (Model.CompaniesItem item : model.companyList) {
            item.manager = usernameToFullName.get(item.manager);
            item.aPackage = packageIdToName.get(item.aPackage);
        }
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
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(1)).add(convert(company));
                    }
                }
                if (company.getDateOfEndContract().getTime() - new Date().getTime() < 259200000 &&
                        company.getDateOfEndContract().getTime() - new Date().getTime() > 86400000) {
                    isAdded = true;
                    if (result.get(new Model.Filter(2)) == null) {
                        filter = new Model.Filter(2);
                        filter.name = "До конца договора 3 д.";
                        companyItems = new ArrayList<>();
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(2)).add(convert(company));
                    }
                }
                if (company.getDateOfEndContract().getTime() - new Date().getTime() < 86400000 &&
                        company.getDateOfEndContract().getTime() - new Date().getTime() > 0) {
                    isAdded = true;
                    if (result.get(new Model.Filter(3)) == null) {
                        filter = new Model.Filter(3);
                        filter.name = "До конца договора 1 д.";
                        companyItems = new ArrayList<>();
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(3)).add(convert(company));
                    }
                }
                if (company.getDateOfEndContract().getTime() < new Date().getTime() &&
                        (!company.getIsClosed())) {
                    isAdded = true;
                    if (result.get(new Model.Filter(4)) == null) {
                        filter = new Model.Filter(4);
                        filter.name = "Срок истек, не отключены";
                        companyItems = new ArrayList<>();
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(4)).add(convert(company));
                    }
                }
                if (company.getIsOffPosition()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(5)) == null) {
                        filter = new Model.Filter(5);
                        filter.name = "Отключены позиции";
                        companyItems = new ArrayList<>();
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(5)).add(convert(company));
                    }
                }
                if (company.getIsRedirect()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(6)) == null) {
                        filter = new Model.Filter(6);
                        filter.name = "Переадресация";
                        companyItems = new ArrayList<>();
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(6)).add(convert(company));
                    }
                }
                if (company.getIsPriority()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(7)) == null) {
                        filter = new Model.Filter(7);
                        filter.name = "Приоритет";
                        companyItems = new ArrayList<>();
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(7)).add(convert(company));
                    }
                }
                if (false) { // wtf?!
                    isAdded = true;
                    if (result.get(new Model.Filter(8)) == null) {
                        filter = new Model.Filter(8);
                        filter.name = "Только сайт";
                        companyItems = new ArrayList<>();
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(8)).add(convert(company));
                    }
                }
                if (!company.getIsShowForOperator()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(9)) == null) {
                        filter = new Model.Filter(9);
                        filter.name = "Выкл. в справочной";
                        companyItems = new ArrayList<>();
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(9)).add(convert(company));
                    }
                }
                if (!company.getIsShowForSite()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(10)) == null) {
                        filter = new Model.Filter(10);
                        filter.name = "Выкл. на сайте";
                        companyItems = new ArrayList<>();
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(10)).add(convert(company));
                    }
                }
                if (false) { // wtf..
                    isAdded = true;
                    if (result.get(new Model.Filter(11)) == null) {
                        filter = new Model.Filter(11);
                        filter.name = "Доп. услуги";
                        companyItems = new ArrayList<>();
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(11)).add(convert(company));
                    }
                }
                if (company.getDateOfEndContract().getTime() < new Date().getTime() && company.getIsPriority()) {
                    isAdded = true;
                    if (result.get(new Model.Filter(12)) == null) {
                        filter = new Model.Filter(12);
                        filter.name = "Срок истек. Приоритет вкл.";
                        companyItems = new ArrayList<>();
                        companyItems.add(convert(company));
                        result.put(filter, companyItems);
                    } else {
                        result.get(new Model.Filter(12)).add(convert(company));
                    }
                }
                if (!isAdded)
                    allCompanyItems.add(convert(company));
            }
            if (allCompanyItems.size() != 0) {
                filter = new Model.Filter(16);
                filter.name = "Остальные";
                result.put(filter, allCompanyItems);
            }
        }
        return result;
    }

    private String getNormalName(String name) {
        if (name != null && name.length() > 24)
            return name.substring(0, 24) + "..";
        else
            return name;
    }
    //07.03.17.
    private Model.CompaniesItem convert(Company company) {
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
        companyItem.aPackage = company.getCompanyPackageId() + "";
        return companyItem;
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
        public List<Region> regions;
        public List<Package> packages;
        public List<User> managers;
        public int[] numOfAddress;
        public String companyAddressJson;
        public Map<Filter, List<CompaniesItem>> filterListMap;
        public List<CompaniesItem> companyList;
        public String message;

        public int getSelectedPageNum() {
            return selectedPageNum;
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
        public List<Package> getPackages() {
            return packages;
        }
        public List<User> getManagers() {
            return managers;
        }
        public Map<Filter, List<CompaniesItem>> getFilterListMap() {
            return filterListMap;
        }
        public List<CompaniesItem> getCompanyList() {
            return companyList;
        }
        public String getMessage() {
            return message;
        }

        public static class Filter implements Comparable {
            public String name;
            public int id;

            public Filter(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }
            public int getId() {
                return id;
            }

            @Override
            public int compareTo(Object o) {
                if (((Filter) o).id == this.id)
                    return 0;
                if (((Filter) o).id < this.id)
                    return 1;
                else
                    return -1;
            }
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Filter filter = (Filter) o;

                return id == filter.id;
            }
            @Override
            public int hashCode() {
                return id;
            }
            @Override
            public String toString() {
                return "Filter{" +
                        "name='" + name + '\'' +
                        ", id=" + id +
                        '}';
            }
        }

        public static class CompaniesItem {
            public int id;
            public String name;
            public String dateOfEndContract;
            public String manager;
            public String aPackage;

            public int getId() {
                return id;
            }
            public String getName() {
                return name;
            }
            public String getDateOfEndContract() {
                return dateOfEndContract;
            }
            public String getManager() {
                return manager;
            }
            public String getaPackage() {
                return aPackage;
            }

            @Override
            public String toString() {
                return "CompaniesItem{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", dateOfEndContract='" + dateOfEndContract + '\'' +
                        ", manager='" + manager + '\'' +
                        ", aPackage='" + aPackage + '\'' +
                        '}';
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