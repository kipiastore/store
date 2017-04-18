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
        Collections.sort(subPartitions);
        Model.PartitionItem partitionItem = new Model.PartitionItem();
        partitionItem.partitionId = partition.getId();
        partitionItem.partitionName = getNormalName(partition.getName(), 46);

        List<Model.SubPartitionItem> subPartitionItems1 = new ArrayList<>();
        List<Model.SubPartitionItem> subPartitionItems2 = new ArrayList<>();
        Model.SubPartitionItem subPartitionItem;

        int tmp = subPartitions.size();
        int step = tmp / 2;
        int counter = 0;
        for (SubPartition subPartition : subPartitions) {
            subPartitionItem = new Model.SubPartitionItem();
            subPartitionItem.subPartitionId = subPartition.getId();
            subPartitionItem.subPartitionName = getNormalName(subPartition.getName(), 46);
            if (counter <= step) {
                subPartitionItems1.add(subPartitionItem);
            } else {
                subPartitionItems2.add(subPartitionItem);
            }
            counter++;
        }
        Model model = new Model();
        model.partitionItem = partitionItem;
        model.subPartitionItems1 = subPartitionItems1;
        model.subPartitionItems2 = subPartitionItems2;
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
        public List<SubPartitionItem> subPartitionItems1;
        public List<SubPartitionItem> subPartitionItems2;
        public Set<CompanyItem> companyHiPrior;

        public Set<CompanyItem> getCompanyHiPrior() {
            return companyHiPrior;
        }
        public PartitionItem getPartitionItem() {
            return partitionItem;
        }
        public List<SubPartitionItem> getSubPartitionItems1() {
            return subPartitionItems1;
        }
        public List<SubPartitionItem> getSubPartitionItems2() {
            return subPartitionItems2;
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
