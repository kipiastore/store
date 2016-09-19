package ru.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.store.controllers.models.HomeModel;
import ru.store.dao.interfaces.BestCompanyDAO;
import ru.store.dao.interfaces.PartitionDAO;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.BestCompany;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * home.jsp
 */
@Controller
public class HomeController {

    @Autowired
    private PartitionDAO partitionDAO;
    @Autowired
    private BestCompanyDAO bestCompanyDAO;
    @Autowired
    private SubPartitionDAO subPartitionDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
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
        return "home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
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

}
