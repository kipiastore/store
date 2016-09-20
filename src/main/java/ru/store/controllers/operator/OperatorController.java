package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.store.dao.interfaces.PartitionDAO;
import ru.store.entities.Partition;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class OperatorController {

    @Autowired
    private PartitionDAO partitionDAO;

    @RequestMapping(value = "/operator", method = RequestMethod.GET)
    public String manager(HttpServletRequest request, Model model) {
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

        if (request.getRequestURL().toString().endsWith("/"))
            model.addAttribute("prefix", "");
        else
            model.addAttribute("prefix", "operator/");
        return "operator/index";
    }

    public static class CallServiceHomeModel {
        private List<PartitionItem> partitionItems;

        public List<PartitionItem> getPartitionItems() {
            return partitionItems;
        }
        public void setPartitionItems(List<PartitionItem> partitionItems) {
            this.partitionItems = partitionItems;
        }

        public static class PartitionItem {
            private int partitionId;
            private String partitionName;

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
        }
    }
}
