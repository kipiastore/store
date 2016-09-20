package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.store.dao.interfaces.BestCompanyDAO;
import ru.store.dao.interfaces.PartitionDAO;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.*;
import ru.store.service.UserService;

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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {

        User user = new User();
        user.setUsername("test" + new Date().getTime());
        user.setPassword("1234");
        userService.createUser(user, "ROLE_ADMIN");

        // prepare part of the model;
        List<HomeModel.PartitionItem> partitionItems = new ArrayList<>();
        HomeModel.PartitionItem partitionItem;
        for (Partition partition : partitionDAO.getPartitions()) {
            partitionItem = new HomeModel.PartitionItem();
            partitionItem.setCompanyCount((int) (Math.random() * 4000)); // todo get company count from bd
            partitionItem.setPartitionId(partition.getId());
            partitionItem.setPartitionName(partition.getName());
            partitionItems.add(partitionItem);
        }
        // prepare part of the model;
        Map<Integer, List<HomeModel.SubPartitionItem>> subPartitionItemsGroupByPartition = new HashMap<>();
        List<HomeModel.SubPartitionItem> subPartitionItems;
        HomeModel.SubPartitionItem subPartitionItem;
        for (SubPartition subPartition : subPartitionDAO.getSubPartitions()) {
            subPartitionItem = new HomeModel.SubPartitionItem();
            subPartitionItem.setSubPartitionId(subPartition.getId());
            subPartitionItem.setSubPartitionName(subPartition.getName());
            if (subPartitionItemsGroupByPartition.get(subPartition.getPartition().getId()) != null)
                subPartitionItemsGroupByPartition.get(subPartition.getPartition().getId()).add(subPartitionItem);
            else {
                subPartitionItems = new ArrayList<>();
                subPartitionItems.add(subPartitionItem);
                subPartitionItemsGroupByPartition.put(subPartition.getPartition().getId(), subPartitionItems);
            }
        }
        // prepare next part of the model;
        List<HomeModel.BestCompanyItem> bestCompanyItems = new ArrayList<>();
        HomeModel.BestCompanyItem bestCompanyItem;
        for (BestCompany bestCompany : bestCompanyDAO.getBestCompanies()) {
            bestCompanyItem = new HomeModel.BestCompanyItem();
            bestCompanyItem.setCompanyId(bestCompany.getCompany().getId());
            bestCompanyItem.setCompanyName(bestCompany.getCompany().getName());
            bestCompanyItem.setCompanyLogoFileName(bestCompany.getCompany().getLogo());
            bestCompanyItems.add(bestCompanyItem);
        }
        // prepare the model; load the model to the page;
        int columnCount = 7;
        Map<Integer, List<HomeModel.BestCompanyItem>> bestCompanyItemsMap = cutPie(bestCompanyItems, columnCount);
        HomeModel homeModel = new HomeModel();
        homeModel.setPartitionItems(partitionItems);
        homeModel.setSubPartitionItemsGroupByPartition(subPartitionItemsGroupByPartition);
        homeModel.setBestCompanyItems(bestCompanyItemsMap);
        model.addAttribute("homeModel", homeModel);
        return "portal/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Model model) {
        return main( model);
    }

    private <T> Map<Integer, List<T>> cutPie(List<T> inputList, int piecesNumber) {
        Map<Integer, List<T>> result = new HashMap<>();
        List<T> piece = new ArrayList<>();
        int pieceCounter = 0;
        int itemCounter = 0;
        for (T item : inputList) {
            if (itemCounter == piecesNumber) {
                result.put(pieceCounter, piece);
                piece = new ArrayList<>();
                pieceCounter++;
                itemCounter = 0;
            }
            piece.add(item);
            itemCounter++;
        }
        return result;
    }

    public static class HomeModel {
        private List<PartitionItem> partitionItems;
        private Map<Integer, List<SubPartitionItem>> subPartitionItemsGroupByPartition;
        private Map<Integer, List<BestCompanyItem>> bestCompanyItems;

        public List<PartitionItem> getPartitionItems() {
            return partitionItems;
        }
        public void setPartitionItems(List<PartitionItem> partitionItems) {
            this.partitionItems = partitionItems;
        }
        public Map<Integer, List<BestCompanyItem>> getBestCompanyItems() {
            return bestCompanyItems;
        }
        public void setBestCompanyItems(Map<Integer, List<BestCompanyItem>> bestCompanyItems) {
            this.bestCompanyItems = bestCompanyItems;
        }
        public Map<Integer, List<SubPartitionItem>> getSubPartitionItemsGroupByPartition() {
            return subPartitionItemsGroupByPartition;
        }
        public void setSubPartitionItemsGroupByPartition(Map<Integer, List<SubPartitionItem>> subPartitionItemsGroupByPartition) {
            this.subPartitionItemsGroupByPartition = subPartitionItemsGroupByPartition;
        }

        public static class PartitionItem {
            private int partitionId;
            private String partitionName;
            private int companyCount;

            public int getPartitionId() {
                return partitionId;
            }
            public void setPartitionId(int partitionId) {
                this.partitionId = partitionId;
            }
            public String getPartitionName() {
                return partitionName;
            }
            public void setPartitionName(String partitionName) {
                this.partitionName = partitionName;
            }
            public int getCompanyCount() {
                return companyCount;
            }
            public void setCompanyCount(int companyCount) {
                this.companyCount = companyCount;
            }
        }

        public static class BestCompanyItem {
            private int companyId;
            private String companyLogoFileName;
            private String companyName;

            public int getCompanyId() {
                return companyId;
            }
            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }
            public String getCompanyLogoFileName() {
                return companyLogoFileName;
            }
            public void setCompanyLogoFileName(String companyLogoFileName) {
                this.companyLogoFileName = companyLogoFileName;
            }
            public String getCompanyName() {
                return companyName;
            }
            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }
        }

        public static class SubPartitionItem {
            private int subPartitionId;
            private String subPartitionName;

            public int getSubPartitionId() {
                return subPartitionId;
            }
            public void setSubPartitionId(int subPartitionId) {
                this.subPartitionId = subPartitionId;
            }
            public String getSubPartitionName() {
                return subPartitionName;
            }
            public void setSubPartitionName(String subPartitionName) {
                this.subPartitionName = subPartitionName;
            }
        }
    }
}
