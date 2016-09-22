package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView manager(HttpServletRequest request) {

        List<Model.PartitionItem> partitionItems = new ArrayList<>();
        Model.PartitionItem partitionItem;
        for (Partition partition : partitionDAO.getPartitions()) {
            partitionItem = new Model.PartitionItem();
            partitionItem.partitionId = partition.getId();
            partitionItem.partitionName = partition.getName();
            partitionItems.add(partitionItem);
        }
        Model model = new Model();
        model.partitionItems = partitionItems;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model", model);

        if (request.getRequestURL().toString().endsWith("/"))
            modelAndView.addObject("prefix", "");
        else
            modelAndView.addObject("prefix", "operator/");

        modelAndView.setViewName("operator/index");
        return modelAndView;
    }

    public static class Model {
        public List<PartitionItem> partitionItems;

        public List<PartitionItem> getPartitionItems() {
            return partitionItems;
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
    }
}