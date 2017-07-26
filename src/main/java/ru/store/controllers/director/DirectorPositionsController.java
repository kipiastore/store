package ru.store.controllers.director;

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
public class DirectorPositionsController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private SubPartitionService subPartitionService;
    @Autowired
    private PartitionService partitionService;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;

    @RequestMapping(value = "/director/positions", method = RequestMethod.GET)
    public ModelAndView positions() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/director/addsubpartitions", method = RequestMethod.POST)
    public ModelAndView updateCompany(@RequestParam("positions") String positions,
                                      @RequestParam("companyId") String companyId) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            CompanySubPartition companySubPartitionItem;
            List<CompanySubPartition> companySubPartitions = companySubPartitionService.findCompanySubpartitionByCompanyId(Integer.valueOf(companyId));
            Set<Integer> currentSubPartitionIds = new HashSet<>();
            Set<Integer> newSubPartitionIds = new HashSet<>();
            List<Integer> toDeleteCompanySubPartitionIds = new ArrayList<>();
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
                    toDeleteCompanySubPartitionIds.add(companySubPartition.getId());
                }
            }
            companySubPartitionService.deleteCompanySubpartitionIds(toDeleteCompanySubPartitionIds);
            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/director/positionsearchcompany", method = RequestMethod.POST)
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
        modelAndView.setViewName("director/positions");
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
            partitionItem.fullName = partition.getName();
            partitionIdToPartitionItem.put(partition.getId(), partitionItem);
            partitionItems.add(partitionItem);
            subPartitionsGroupedByPartition.put(partitionItem, null);
        }
        for (SubPartition subPartition : subPartitionService.getSubPartitions()) {
            if (subPartitionsGroupedByPartition.get(new Model.PartitionItem(subPartition.getPartitionId())) != null) {
                partitionItem = new Model.PartitionItem();
                partitionItem.id = subPartition.getId();
                partitionItem.name = getNormalName(subPartition.getName());
                partitionItem.fullName = subPartition.getName();
                subPartitionsGroupedByPartition.get(new Model.PartitionItem(subPartition.getPartitionId())).add(partitionItem);
            } else {
                mainPartitionItem = new Model.PartitionItem();
                mainPartitionItem.id = partitionIdToPartitionItem.get(subPartition.getPartitionId()).getId();
                mainPartitionItem.name = getNormalName(partitionIdToPartitionItem.get(subPartition.getPartitionId()).getName());
                partitionItems = new ArrayList<>();
                partitionItem = new Model.PartitionItem();
                partitionItem.id = subPartition.getId();
                partitionItem.name = getNormalName(subPartition.getName());
                partitionItem.fullName = subPartition.getName();
                partitionItems.add(partitionItem);
                subPartitionsGroupedByPartition.put(mainPartitionItem, partitionItems);
            }
        }
        model.subPartitionsGroupedByPartition = subPartitionsGroupedByPartition;

        Map<Model.PartitionItem2, Set<Model.PartitionItem2>> subPartitionsGroupedByPartition2 = new TreeMap<>();
        Set<Model.PartitionItem2> partitionItem2List;

        for (Model.PartitionItem partitionItem1 : subPartitionsGroupedByPartition.keySet()) {
            partitionItem2List = new TreeSet<>();
            if (subPartitionsGroupedByPartition.get(partitionItem1) == null) {
                subPartitionsGroupedByPartition2.put(new Model.PartitionItem2(partitionItem1.getId(), partitionItem1.getName(), partitionItem1.getFullName()), partitionItem2List);
                continue;
            }

            for (Model.PartitionItem partitionItem2 : subPartitionsGroupedByPartition.get(partitionItem1)) {
                partitionItem2List.add(new Model.PartitionItem2(partitionItem2.getId(), partitionItem2.getName(), partitionItem2.getFullName()));
            }
            subPartitionsGroupedByPartition2.put(new Model.PartitionItem2(partitionItem1.getId(), partitionItem1.getName(), partitionItem1.getFullName()), partitionItem2List);
        }
        model.subPartitionsGroupedByPartition2 = subPartitionsGroupedByPartition2;
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

    @RequestMapping(value = "/director/addsubpartitions", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/director/positions";
    }
    @RequestMapping(value = "/director/positionsearchcompany", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/director/positions";
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
        public Map<PartitionItem2, Set<PartitionItem2>> subPartitionsGroupedByPartition2;

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
        public Map<PartitionItem2, Set<PartitionItem2>> getSubPartitionsGroupedByPartition2() {
            return subPartitionsGroupedByPartition2;
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
            public String fullName;

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
            public String getFullName() {
                return fullName;
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

        public static class PartitionItem2 implements Comparable<PartitionItem2> {
            public int id;
            public String name;
            public String fullName;

            public int getId() {
                return id;
            }
            public String getName() {
                return name;
            }
            public String getFullName() {
                return fullName;
            }

            public PartitionItem2() { }
            public PartitionItem2(int id, String name, String fullName) {
                this.id = id;
                this.name = name;
                this.fullName = fullName;
            }

            @Override
            public int compareTo(PartitionItem2 o) {
                return this.fullName.compareTo(o.fullName);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                PartitionItem2 that = (PartitionItem2) o;
                return Objects.equals(fullName, that.fullName);

            }
            @Override
            public int hashCode() {
                return fullName.hashCode();
            }

            @Override
            public String toString() {
                return "PartitionItem2{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", fullName='" + fullName + '\'' +
                        '}';
            }
        }
    }
}
