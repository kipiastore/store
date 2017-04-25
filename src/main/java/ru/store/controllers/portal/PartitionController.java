package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.controllers.operator.OperatorSearchController;
import ru.store.entities.*;
import ru.store.service.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class PartitionController {

    @Autowired
    private SubPartitionService subPartitionService;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private PackageService packageService;
    @Autowired
    private CompanyAddressService companyAddressService;
    @Autowired
    private PartitionService partitionService;

    @RequestMapping(value = "/partition/*", method = RequestMethod.GET)
    public ModelAndView partition(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String[] splitResult = request.getRequestURL().toString().split("partition/");

        int partitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+")) {
            partitionId = Integer.valueOf(splitResult[1]);

            //подсчет переходов на разделы
            Partition partition=partitionService.getPartitionById(partitionId);
            partition.setCountPartition();
            partitionService.updatePartition(partition);
            modelAndView.addObject("countInfo","раздела");
            modelAndView.addObject("portalCount",partition.getCountPartition());
            //

        } else {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        if (partitionService.getPartitionById(partitionId) == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        Map<Integer, Integer> subPartitionIdToCount = new HashMap<>();
        List<Integer> companiesIds = companyService.getOptimizationCompanies();
        Set<Integer> availableCompany = new TreeSet<>();
        for (Integer companyId : companiesIds) {
            availableCompany.add(companyId);
        }
        List<Model.PartitionItem.SubPartitionItem> subPartitionItems = new ArrayList<>();
        Model.PartitionItem.SubPartitionItem subPartitionItem;
        for (CompanySubPartition companySubPartition : companySubPartitionService.getCompanySubPartitions()) {
            if (!availableCompany.contains(companySubPartition.getCompanyId()))
                continue;
            if (subPartitionIdToCount.get(companySubPartition.getSubPartitionId()) == null) {
                subPartitionIdToCount.put(companySubPartition.getSubPartitionId(), 1);
            } else {
                subPartitionIdToCount.put(
                        companySubPartition.getSubPartitionId(),
                        subPartitionIdToCount.get(companySubPartition.getSubPartitionId()) + 1);
            }
        }
        List<Integer> subPartitionIds = new ArrayList<>();
        for (SubPartition subPartition : subPartitionService.getSubPartitionsByPartitionId(partitionId)) {
            subPartitionIds.add(subPartition.getId());
            subPartitionItem = new Model.PartitionItem.SubPartitionItem();
            subPartitionItem.subPartitionId = subPartition.getId();
            subPartitionItem.subPartitionName = getNormalName(subPartition.getName(), 31);
            if (subPartitionIdToCount.get(subPartition.getId()) != null)
                subPartitionItem.companyCount = subPartitionIdToCount.get(subPartition.getId());
            subPartitionItems.add(subPartitionItem);
        }

        Model.PartitionItem partitionItem = new Model.PartitionItem();
        Partition partition = partitionService.getPartitionById(partitionId);
        partitionItem.partitionId = partition.getId();
        partitionItem.partitionName = getNormalName(partition.getName(), 50);

        List<Model.PartitionItem.SubPartitionItem> tmp1 = new ArrayList<>();
        List<Model.PartitionItem.SubPartitionItem> tmp2 = new ArrayList<>();
        for (int i = 0; i < subPartitionItems.size(); i++) {
            if ((subPartitionItems.size() % 2) == 0) {
                if (i >= subPartitionItems.size() / 2) {
                    tmp1.add(subPartitionItems.get(i));
                } else {
                    tmp2.add(subPartitionItems.get(i));
                }
            } else {
                if (i > subPartitionItems.size() / 2) {
                    tmp1.add(subPartitionItems.get(i));
                } else {
                    tmp2.add(subPartitionItems.get(i));
                }
            }
        }
        partitionItem.subPartitionItems1 = tmp1;
        partitionItem.subPartitionItems2 = tmp2;

        //partitionItem.subPartitionItems = subPartitionItems;

        Model model = new Model();
        model.partitionItem = partitionItem;


        Map<Integer, Integer> packageIdToPriority = OperatorSearchController.priorityHandler(packageService.getPackages());
        List<CompanySubPartition> companySubPartitions = companySubPartitionService.findCompanySubpartitionBySubPartitionsId(subPartitionIds);
        Set<Integer> companyIds = new HashSet<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            companyIds.add(companySubPartition.getCompanyId());
        }
        List<Company> companies = companyService.getPortalCompanies(new ArrayList<>(companyIds));
        List<Model.CompanyItem> companyItems = new ArrayList<>();
        Model.CompanyItem companyItem;
        Map<Integer, List<CompanyAddress>> companyToCompanyAddress = new HashMap<>();
        for (Company company : companies) {
            company.setDescription(company.getDescription().replaceAll("\n","<br/>").replaceAll("script",""));
            companyToCompanyAddress.put(company.getId(), new ArrayList<CompanyAddress>());
            companyItem = new Model.CompanyItem();
            if (packageIdToPriority.get(company.getCompanyPackageId()) != null)
                companyItem.colorPoint = packageIdToPriority.get(company.getCompanyPackageId());
            if (company.getIsPriority())
                companyItem.colorPoint = 100;
            companyItem.companyId = company.getId();
            companyItem.companyInformation = company.getDescription();
            companyItem.companyName = company.getName();
            companyItem.costOf = company.getCostOf();
            companyItems.add(companyItem);
        }
        model.companyHiPrior = new ArrayList<>();
        int counter = 0;
        for (Model.CompanyItem companyItem1 : companyItems) {
            model.companyHiPrior.add(companyItem1);
            counter++;
            if (counter == 10)
                break;
        }
        List<CompanyAddress> companyAddresses =
                companyAddressService.getCompanyAddresses(new ArrayList<>(companyToCompanyAddress.keySet()));
        for (CompanyAddress companyAddress : companyAddresses) {
            companyToCompanyAddress.get(companyAddress.getCompanyId()).add(companyAddress);
        }
        model.companyToCompanyAddress = companyToCompanyAddress;

        modelAndView.addObject("prefix", "../");
        modelAndView.addObject("model", model);
        modelAndView.setViewName("portal/partition");
        return modelAndView;
    }

    private String getNormalName(String name, int length) {
        if (name != null && name.length() > length)
            return name.substring(0, length) + "..";
        else
            return name;
    }

    public static class Model {
        public PartitionItem partitionItem;
        public List<CompanyItem> companyHiPrior;
        public Map<Integer, List<CompanyAddress>> companyToCompanyAddress;

        public PartitionItem getPartitionItem() {
            return partitionItem;
        }
        public List<CompanyItem> getCompanyHiPrior() {
            return companyHiPrior;
        }
        public Map<Integer, List<CompanyAddress>> getCompanyToCompanyAddress() {
            return companyToCompanyAddress;
        }

        public static class PartitionItem {
            public int partitionId;
            public String partitionName;
            public List<SubPartitionItem> subPartitionItems1;
            public List<SubPartitionItem> subPartitionItems2;

            public int getPartitionId() {
                return partitionId;
            }
            public String getPartitionName() {
                return partitionName;
            }
            public List<SubPartitionItem> getSubPartitionItems1() {
                return subPartitionItems1;
            }
            public List<SubPartitionItem> getSubPartitionItems2() {
                return subPartitionItems2;
            }

            public static class SubPartitionItem {
                public int subPartitionId;
                public String subPartitionName;
                public int companyCount;

                public int getSubPartitionId() {
                    return subPartitionId;
                }
                public String getSubPartitionName() {
                    return subPartitionName;
                }
                public int getCompanyCount() {
                    return companyCount;
                }
            }
        }

        public static class CompanyItem implements Comparable, Comparator<CompanyItem> {
            public int companyId;
            public String companyName;
            public String companyInformation;
            public Integer colorPoint;
            public Integer costOf;

            public int getCompanyId() {
                return companyId;
            }
            public String getCompanyName() {
                return companyName;
            }
            public String getCompanyInformation() {
                return companyInformation;
            }
            public Integer getColorPoint() {
                return colorPoint;
            }
            public Integer getCostOf() {
                return costOf;
            }

            @Override
            public int compare(CompanyItem o1, CompanyItem o2) {
                return o1.colorPoint.compareTo(o2.colorPoint);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                CompanyItem that = (CompanyItem) o;

                return companyId == that.companyId;

            }

            @Override
            public int hashCode() {
                return companyId;
            }

            @Override
            public String toString() {
                return "CompanyItem{" +
                        "companyId=" + companyId +
                        ", companyName='" + companyName + '\'' +
                        ", companyInformation='" + companyInformation + '\'' +
                        ", colorPoint=" + colorPoint +
                        '}';
            }

            @Override
            public int compareTo(Object o) {
                if (this.colorPoint == ((CompanyItem) o).getColorPoint())
                    return 0;
                else if (this.colorPoint > ((CompanyItem) o).getColorPoint()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }

}
