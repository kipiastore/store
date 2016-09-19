package ru.store.controllers.callService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.store.controllers.callService.models.CallServiceHomeModel;
import ru.store.dao.interfaces.PartitionDAO;
import ru.store.entities.Partition;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class CallServiceHomeController {

    @Autowired
    private PartitionDAO partitionDAO;

    @RequestMapping(value = "/callService", method = RequestMethod.GET)
    public String manager(Model model) {
        List<CallServiceHomeModel.PartitionItem> partitionItems = new ArrayList<>();
        CallServiceHomeModel.PartitionItem partitionItem;
        for (Partition partition : partitionDAO.getPartitions()) {
            partitionItem = new CallServiceHomeModel.PartitionItem();
            partitionItem.setPartitionId(partition.getId());
            partitionItem.setPartitionName(partition.getName());
            partitionItems.add(partitionItem);
        }
        CallServiceHomeModel callServiceHomeModel = new CallServiceHomeModel();
        callServiceHomeModel.setPartitionItems(partitionItems);
        model.addAttribute("callServiceHomeModel", callServiceHomeModel);

        model.addAttribute("prefix", "callService/");

        return "callService/callServiceHome";
    }
}
