package ru.store.controllers.callService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.store.controllers.callService.models.CallServicePartitionModel;
import ru.store.dao.interfaces.PartitionDAO;
import ru.store.dao.interfaces.SubPartitionDAO;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class CallServicePartitionController {

    @Autowired
    private PartitionDAO partitionDAO;
    @Autowired
    private SubPartitionDAO subPartitionDAO;

    @RequestMapping(value = "/callService/partition/*", method = RequestMethod.GET)
    public String partition(HttpServletRequest request, Model model) {
        String[] splitResult = request.getRequestURL().toString().split("partition/");
        int partitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+"))
            partitionId = Integer.valueOf(splitResult[1]);
        else
            return "redirect:/callService";
        Partition partition = partitionDAO.getPartitionById(partitionId);
        List<SubPartition> subPartitions = subPartitionDAO.getSubPartitionsByPartition(partition);
        // build the model
        CallServicePartitionModel.PartitionItem partitionItem = new CallServicePartitionModel.PartitionItem();
        partitionItem.setPartitionId(partition.getId());
        partitionItem.setPartitionName(partition.getName());
        List<CallServicePartitionModel.SubPartitionItem> subPartitionItems = new ArrayList<>();
        CallServicePartitionModel.SubPartitionItem subPartitionItem;
        for (SubPartition subPartition : subPartitions) {
            subPartitionItem = new CallServicePartitionModel.SubPartitionItem();
            subPartitionItem.setSubPartitionId(subPartition.getId());
            subPartitionItem.setSubPartitionName(subPartition.getName());
            subPartitionItems.add(subPartitionItem);
        }
        CallServicePartitionModel callServicePartitionModel = new CallServicePartitionModel();
        callServicePartitionModel.setPartitionItem(partitionItem);
        callServicePartitionModel.setSubPartitionItems(subPartitionItems);
        model.addAttribute("callServicePartitionModel", callServicePartitionModel);
        model.addAttribute("prefix", "../");
        return "callService/callServicePartition";
    }


}
