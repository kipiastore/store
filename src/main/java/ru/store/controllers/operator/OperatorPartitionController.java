package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class OperatorPartitionController {

    @Autowired
    private PartitionDAO partitionDAO;
    @Autowired
    private SubPartitionDAO subPartitionDAO;

    @RequestMapping(value = "/operator/partition/*", method = RequestMethod.GET)
    public String partition(HttpServletRequest request, Model model) {
        String[] splitResult = request.getRequestURL().toString().split("partition/");
        int partitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+"))
            partitionId = Integer.valueOf(splitResult[1]);
        else
            return "redirect:/operator";

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
        return "operator/partition";
    }

    public static class CallServicePartitionModel {
        private PartitionItem partitionItem;
        private List<SubPartitionItem> subPartitionItems;

        public PartitionItem getPartitionItem() {
            return partitionItem;
        }
        public void setPartitionItem(PartitionItem partitionItem) {
            this.partitionItem = partitionItem;
        }
        public List<SubPartitionItem> getSubPartitionItems() {
            return subPartitionItems;
        }
        public void setSubPartitionItems(List<SubPartitionItem> subPartitionItems) {
            this.subPartitionItems = subPartitionItems;
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
