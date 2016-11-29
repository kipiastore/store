package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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

        Partition partition = partitionDAO.getPartitionById(partitionId);
        if (partition == null) {
            modelAndView.setViewName("redirect:/operator");
            return modelAndView;
        }
        List<SubPartition> subPartitions = subPartitionDAO.getSubPartitionsByPartition(partition);

        Model.PartitionItem partitionItem = new Model.PartitionItem();
        partitionItem.partitionId = partition.getId();
        partitionItem.partitionName = partition.getName();
        List<Model.SubPartitionItem> subPartitionItems = new ArrayList<>();
        Model.SubPartitionItem subPartitionItem;
        for (SubPartition subPartition : subPartitions) {
            subPartitionItem = new Model.SubPartitionItem();
            subPartitionItem.subPartitionId = subPartition.getId();
            subPartitionItem.subPartitionName = subPartition.getName();
            subPartitionItems.add(subPartitionItem);
        }
        Model model = new Model();
        model.partitionItem = partitionItem;
        model.subPartitionItems = subPartitionItems;
        modelAndView.addObject("model", model);

        modelAndView.addObject("prefix", "../");
        modelAndView.setViewName("operator/partition");
        return modelAndView;
    }

    public static class Model {
        public PartitionItem partitionItem;
        public List<SubPartitionItem> subPartitionItems;

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
    }
}
