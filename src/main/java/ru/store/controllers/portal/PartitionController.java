package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.beans.EmailSender;
import ru.store.beans.GoogleCaptcha;
import ru.store.controllers.operator.OperatorCompanyController;
import ru.store.controllers.operator.OperatorSearchController;
import ru.store.dao.interfaces.*;
import ru.store.entities.*;
import ru.store.utility.Util;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class PartitionController {

    @Autowired
    private PartitionDAO partitionDAO;
    @Autowired
    private SubPartitionDAO subPartitionDAO;
    @Autowired
    private CompanySubPartitionDAO companySubPartitionDAO;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private PackageDAO packageDAO;

    @RequestMapping(value = "/partition/*", method = RequestMethod.GET)
    public ModelAndView partition(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String[] splitResult = request.getRequestURL().toString().split("partition/");

        int partitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+")) {
            partitionId = Integer.valueOf(splitResult[1]);
        } else {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        if (partitionDAO.getPartitionById(partitionId) == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        Map<Integer, Integer> subPartitionIdToCount = new HashMap<>();
        List<Integer> companiesIds = companyDAO.getOptimizationCompanies();
        Set<Integer> availableCompany = new TreeSet<>();
        for (Integer companyId : companiesIds) {
            availableCompany.add(companyId);
        }
        List<Model.PartitionItem.SubPartitionItem> subPartitionItems = new ArrayList<>();
        Model.PartitionItem.SubPartitionItem subPartitionItem;
        for (CompanySubPartition companySubPartition : companySubPartitionDAO.getCompanySubPartitions()) {
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
        for (SubPartition subPartition : subPartitionDAO.getSubPartitionsByPartitionId(partitionId)) {
            subPartitionIds.add(subPartition.getId());
            subPartitionItem = new Model.PartitionItem.SubPartitionItem();
            subPartitionItem.subPartitionId = subPartition.getId();
            subPartitionItem.subPartitionName = getNormalName(subPartition.getName(), 32);
            if (subPartitionIdToCount.get(subPartition.getId()) != null)
                subPartitionItem.companyCount = subPartitionIdToCount.get(subPartition.getId());
            subPartitionItems.add(subPartitionItem);
        }

        Model.PartitionItem partitionItem = new Model.PartitionItem();
        Partition partition = partitionDAO.getPartitionById(partitionId);
        partitionItem.partitionId = partition.getId();
        partitionItem.partitionName = getNormalName(partition.getName(), 50);
        partitionItem.subPartitionItems = subPartitionItems;

        Model model = new Model();
        model.partitionItem = partitionItem;


        Map<Integer, Integer> packageIdToPriority = OperatorSearchController.priorityHandler(packageDAO.getPackages());
        List<CompanySubPartition> companySubPartitions = companySubPartitionDAO.findCompanySubpartitionBySubPartitionsId(subPartitionIds);
        Set<Integer> companyIds = new HashSet<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            companyIds.add(companySubPartition.getCompanyId());
        }
        List<Company> companies = companyDAO.getPortalCompanies(new ArrayList<>(companyIds));
        List<Model.CompanyItem> companyItems = new ArrayList<>();
        Model.CompanyItem companyItem;
        for (Company company : companies) {
            companyItem = new Model.CompanyItem();
            if (packageIdToPriority.get(company.getCompanyPackageId()) != null)
                companyItem.colorPoint = packageIdToPriority.get(company.getCompanyPackageId());
            if (company.getIsPriority())
                companyItem.colorPoint = 100;
            companyItem.companyId = company.getId();
            companyItem.companyInformation = company.getDescription();
            companyItem.companyName = company.getName();
            companyItems.add(companyItem);
        }
        model.companyHiPrior = new ArrayList<>();
        int counter = 0;
        for (Model.CompanyItem companyItem1 : companyItems) {
            model.companyHiPrior.add(companyItem1);
            counter++;
            if (counter > 10)
                break;
        }

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
        public PartitionItem getPartitionItem() {
            return partitionItem;
        }
        public List<CompanyItem> companyHiPrior;
        public List<CompanyItem> getCompanyHiPrior() {
            return companyHiPrior;
        }

        public static class PartitionItem {
            public int partitionId;
            public String partitionName;
            public List<SubPartitionItem> subPartitionItems;

            public int getPartitionId() {
                return partitionId;
            }
            public String getPartitionName() {
                return partitionName;
            }
            public List<SubPartitionItem> getSubPartitionItems() {
                return subPartitionItems;
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
