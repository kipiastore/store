package ru.store.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Company;
import ru.store.entities.CompanySubPartition;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;
import ru.store.service.CompanyService;
import ru.store.service.CompanySubPartitionService;
import ru.store.service.PartitionService;
import ru.store.service.SubPartitionService;

import java.util.*;

/**
 *
 */
@Controller
public class AdminPositionsController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private SubPartitionService subPartitionService;
    @Autowired
    private PartitionService partitionService;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;

    @RequestMapping(value = "/admin/positions", method = RequestMethod.GET)
    public ModelAndView positions() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addsubpartitions", method = RequestMethod.POST)
    public ModelAndView updateCompany(@RequestParam("positions") String positions,
                                      @RequestParam("companyId") String companyId) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            CompanySubPartition companySubPartitionItem;
            List<CompanySubPartition> companySubPartitions = companySubPartitionService.findCompanySubpartitionByCompanyId(Integer.valueOf(companyId));
            Set<Integer> currentSubPartitionIds = new HashSet<>();
            Set<Integer> newSubPartitionIds = new HashSet<>();
            List<Integer> toDeleteSubPartitionIds = new ArrayList<>();
            for (CompanySubPartition companySubPartition : companySubPartitions) {
                currentSubPartitionIds.add(companySubPartition.getSubPartitionId());
            }
            for (String id : positions.split(",")) {
                if (id.equals("-1"))
                    continue;
                if (!currentSubPartitionIds.contains(Integer.valueOf(id))) {
                    companySubPartitionItem = new CompanySubPartition();
                    companySubPartitionItem.setCompanyId(Integer.valueOf(companyId));
                    companySubPartitionItem.setSubPartitionId(Integer.valueOf(id));
                    companySubPartitionService.createCompanySubPartition(companySubPartitionItem);
                }
                newSubPartitionIds.add(Integer.valueOf(id));
            }
            for (CompanySubPartition companySubPartition : companySubPartitions) {
                if (!newSubPartitionIds.contains(companySubPartition.getSubPartitionId())) {
                    toDeleteSubPartitionIds.add(companySubPartition.getSubPartitionId());
                }
            }
            companySubPartitionService.deleteCompanySubpartitionBySubPartitionIds(toDeleteSubPartitionIds);
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
                //7.03.17
                else{
                    companies = companyService.getCompanies();
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
        List<Model.PartitionItem> partitionItems = new ArrayList<>();
        Model.PartitionItem partitionItem;
        Model.PartitionItem mainPartitionItem;
        Map<Integer, Model.PartitionItem> partitionIdToPartitionItem = new HashMap<>();
        Map<Model.PartitionItem, List<Model.PartitionItem>> subPartitionsGroupedByPartition = new HashMap<>();
        for (Partition partition : partitionService.getPartitions()) {
            partitionItem = new Model.PartitionItem();
            partitionItem.id = partition.getId();
            partitionItem.name = getNormalName(partition.getName());
            partitionIdToPartitionItem.put(partition.getId(), partitionItem);
            partitionItems.add(partitionItem);
            subPartitionsGroupedByPartition.put(partitionItem, null);
        }
        for (SubPartition subPartition : subPartitionService.getSubPartitions()) {
            if (subPartitionsGroupedByPartition.get(new Model.PartitionItem(subPartition.getPartitionId())) != null) {
                partitionItem = new Model.PartitionItem();
                partitionItem.id = subPartition.getId();
                partitionItem.name = getNormalName(subPartition.getName());
                subPartitionsGroupedByPartition.get(new Model.PartitionItem(subPartition.getPartitionId())).add(partitionItem);
            } else {
                mainPartitionItem = new Model.PartitionItem();
                mainPartitionItem.id = partitionIdToPartitionItem.get(subPartition.getPartitionId()).getId();
                mainPartitionItem.name = getNormalName(partitionIdToPartitionItem.get(subPartition.getPartitionId()).getName());
                partitionItems = new ArrayList<>();
                partitionItem = new Model.PartitionItem();
                partitionItem.id = subPartition.getId();
                partitionItem.name = getNormalName(subPartition.getName());
                partitionItems.add(partitionItem);
                subPartitionsGroupedByPartition.put(mainPartitionItem, partitionItems);
            }
        }
        model.subPartitionsGroupedByPartition = subPartitionsGroupedByPartition;
    }

    private Model.CompaniesItem convert(Company company) {
        Model.CompaniesItem companyItem = new Model.CompaniesItem();
        companyItem.id = company.getId();
        companyItem.name = getNormalName(company.getName());
        return companyItem;
    }

    private String getNormalName(String name) {
        if (name != null && name.length() > 24)
            return name.substring(0, 24) + "..";
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
        public Map<PartitionItem, List<PartitionItem>> subPartitionsGroupedByPartition;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public List<CompaniesItem> getCompanyList() {
            return companyList;
        }
        public String getMessage() {
            return message;
        }
        public Map<PartitionItem, List<PartitionItem>> getSubPartitionsGroupedByPartition() {
            return subPartitionsGroupedByPartition;
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

        public static class PartitionItem {
            public int id;
            public String name;

            public PartitionItem() {
            }

            public PartitionItem(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }
            public String getName() {
                return name;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                PartitionItem that = (PartitionItem) o;
                return id == that.id;

            }
            @Override
            public int hashCode() {
                return id;
            }
        }
    }
}
