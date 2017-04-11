package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;
import ru.store.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 */
@Controller
public class OperatorPartitionController {

    @Autowired
    private PartitionService partitionService;
    @Autowired
    private SubPartitionService subPartitionService;
    @Autowired
    private PackageService packageService;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/operator/partition/*", method = RequestMethod.GET)
    public ModelAndView partition(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String[] splitResult = request.getRequestURL().toString().split("partition/");
        int partitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+"))
            partitionId = Integer.valueOf(splitResult[1]);
        else {
            modelAndView.setViewName("redirect:/operator");
            return modelAndView;
        }

        Partition partition = partitionService.getPartitionById(partitionId);
        if (partition == null) {
            modelAndView.setViewName("redirect:/operator");
            return modelAndView;
        }
        List<SubPartition> subPartitions = subPartitionService.getSubPartitionsByPartition(partition);

        Model.PartitionItem partitionItem = new Model.PartitionItem();
        partitionItem.partitionId = partition.getId();
        partitionItem.partitionName = getNormalName(partition.getName(), 36);
        List<Model.SubPartitionItem> subPartitionItems = new ArrayList<>();
        Model.SubPartitionItem subPartitionItem;
        List<Integer> subPartitionIds = new ArrayList<>();
        for (SubPartition subPartition : subPartitions) {
            subPartitionIds.add(subPartition.getId());
            subPartitionItem = new Model.SubPartitionItem();
            subPartitionItem.subPartitionId = subPartition.getId();
            subPartitionItem.subPartitionName = getNormalName(subPartition.getName(), 36);
            subPartitionItems.add(subPartitionItem);
        }
        Model model = new Model();
        model.partitionItem = partitionItem;
        model.subPartitionItems = subPartitionItems;
/*
        Map<Integer, Integer> packageIdToPriority = OperatorSearchController.priorityHandler(packageDAO.getPackages());
        List<CompanySubPartition> companySubPartitions = companySubPartitionDAO.findCompanySubpartitionBySubPartitionsId(subPartitionIds);
        List<Integer> companyIds = new ArrayList<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            companyIds.add(companySubPartition.getCompanyId());
        }
        List<Company> companies = companyDAO.getCompanies(companyIds);
        Set<Model.CompanyItem> companyItems = new TreeSet<>();
        Model.CompanyItem companyItem;
        for (Company company : companies) {
            companyItem = new Model.CompanyItem();
            companyItem.colorPoint = packageIdToPriority.get(company.getCompanyPackageId());
            companyItem.companyId = company.getId();
            companyItem.companyInformation = company.getDescription();
            companyItem.companyName = company.getName();
            companyItems.add(companyItem);
        }
        model.companyHiPrior = new TreeSet<>();
        int counter = 0;
        for (Model.CompanyItem companyItem1 : companyItems) {
            model.companyHiPrior.add(companyItem1);
            counter++;
            if (counter > 10)
                break;
        }
        System.out.println(model.companyHiPrior);
*/
        modelAndView.addObject("model", model);

        modelAndView.addObject("prefix", "../");
        modelAndView.setViewName("operator/partition");
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
        public List<SubPartitionItem> subPartitionItems;
        public Set<CompanyItem> companyHiPrior;

        public Set<CompanyItem> getCompanyHiPrior() {
            return companyHiPrior;
        }
        public PartitionItem getPartitionItem() {
            return partitionItem;
        }
        public List<SubPartitionItem> getSubPartitionItems() {
            return subPartitionItems;
        }

        public static class PartitionItem {
            public int partitionId;
            public String partitionName;

            public int getPartitionId() {
                return partitionId;
            }
            public String getPartitionName() {
                return partitionName;
            }
        }

        public static class SubPartitionItem {
            public int subPartitionId;
            public String subPartitionName;

            public int getSubPartitionId() {
                return subPartitionId;
            }
            public String getSubPartitionName() {
                return subPartitionName;
            }
        }

        public static class CompanyItem implements Comparable {
            public int companyId;
            public String companyName;
            public String companyInformation;
            public int colorPoint;

            public int getCompanyId() {
                return companyId;
            }

            public String getCompanyName() {
                return companyName;
            }

            public String getCompanyInformation() {
                return companyInformation;
            }

            public int getColorPoint() {
                return colorPoint;
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
