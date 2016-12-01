package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.beans.EmailSender;
import ru.store.beans.GoogleCaptcha;
import ru.store.controllers.operator.OperatorCompanyController;
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
        List<Integer> companies = companyDAO.getOptimizationCompanies();
        Set<Integer> availableCompany = new TreeSet<>();
        for (Integer companyId : companies) {
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
        for (SubPartition subPartition : subPartitionDAO.getSubPartitionsByPartitionId(partitionId)) {
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
    }

}
