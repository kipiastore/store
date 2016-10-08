package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.dao.interfaces.BestCompanyDAO;
import ru.store.dao.interfaces.PartitionDAO;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.*;
import ru.store.utility.Util;

import java.util.*;

/**
 *
 */
@Controller
public class PortalController {

    @Autowired
    private PartitionDAO partitionDAO;
    @Autowired
    private BestCompanyDAO bestCompanyDAO;
    @Autowired
    private SubPartitionDAO subPartitionDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        // prepare part of the model;
        Map<Integer, List<Model.PartitionItem.SubPartitionItem>> subPartitionItemsGroupByPartitionId = new HashMap<>();
        List<Model.PartitionItem.SubPartitionItem> subPartitionItems;
        Model.PartitionItem.SubPartitionItem subPartitionItem;
        for (SubPartition subPartition : subPartitionDAO.getSubPartitions()) {
            subPartitionItem = new Model.PartitionItem.SubPartitionItem();
            subPartitionItem.subPartitionId = subPartition.getId();
            subPartitionItem.subPartitionName = subPartition.getName();
            if (subPartitionItemsGroupByPartitionId.get(subPartition.getPartitionId()) != null) {
                subPartitionItemsGroupByPartitionId.get(subPartition.getPartitionId()).add(subPartitionItem);
            } else {
                subPartitionItems = new ArrayList<>();
                subPartitionItems.add(subPartitionItem);
                subPartitionItemsGroupByPartitionId.put(subPartition.getPartitionId(), subPartitionItems);
            }
        }
        // prepare next part of the model;
        List<Model.PartitionItem> partitionItems = new ArrayList<>();
        Model.PartitionItem partitionItem;
        for (Partition partition : partitionDAO.getPartitions()) {
            partitionItem = new Model.PartitionItem();
            partitionItem.companyCount = (int) (Math.random() * 4000); // todo get company count from bd
            partitionItem.partitionId = partition.getId();
            partitionItem.partitionName = partition.getName();
            partitionItem.subPartitionItems = subPartitionItemsGroupByPartitionId.get(partition.getId());
            partitionItems.add(partitionItem);
        }
        // prepare next part of the model again;
        List<Model.BestCompanyItem> bestCompanyItems = new ArrayList<>();
        Model.BestCompanyItem bestCompanyItem;
        for (BestCompany bestCompany : bestCompanyDAO.getBestCompanies()) {
            bestCompanyItem = new Model.BestCompanyItem();
            bestCompanyItem.companyId = bestCompany.getCompany().getId();
            bestCompanyItem.companyName = bestCompany.getCompany().getName();
            bestCompanyItem.companyLogoFileName = "testLogo.jpg";
            bestCompanyItems.add(bestCompanyItem);
        }
        // prepare the model;
        int columnCount = 7;
        Map<Integer, List<Model.BestCompanyItem>> bestCompanyGroupByColumn = Util.cutPie(bestCompanyItems, columnCount);
        Model model = new Model();
        model.partitionItems = partitionItems;
        model.bestCompanyGroupByColumn = bestCompanyGroupByColumn;
        // load the model to the page;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model", model);
        modelAndView.setViewName("portal/index");
        return modelAndView;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView home() {
        return main();
    }

    @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
    public ModelAndView sendMail(@RequestParam MultiValueMap<String, String> mailMap) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/index");
        return modelAndView;
    }

    /**
     * model schema
     */
    public static class Model {
        public List<PartitionItem> partitionItems;
        public Map<Integer, List<BestCompanyItem>> bestCompanyGroupByColumn;

        public List<PartitionItem> getPartitionItems() {
            return partitionItems;
        }
        public Map<Integer, List<BestCompanyItem>> getBestCompanyGroupByColumn() {
            return bestCompanyGroupByColumn;
        }

        public static class PartitionItem {
            public int partitionId;
            public String partitionName;
            public int companyCount;
            public List<SubPartitionItem> subPartitionItems;

            public int getPartitionId() {
                return partitionId;
            }
            public String getPartitionName() {
                return partitionName;
            }
            public int getCompanyCount() {
                return companyCount;
            }
            public List<SubPartitionItem> getSubPartitionItems() {
                return subPartitionItems;
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
        }

        public static class BestCompanyItem {
            public int companyId;
            public String companyLogoFileName;
            public String companyName;

            public int getCompanyId() {
                return companyId;
            }
            public String getCompanyLogoFileName() {
                return companyLogoFileName;
            }
            public String getCompanyName() {
                return companyName;
            }
        }
    }
}
