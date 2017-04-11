package ru.store.controllers.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Partition;
import ru.store.service.PartitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@Controller
public class OperatorController {

    @Autowired
    private PartitionService partitionService;

    @RequestMapping(value = "/operator", method = RequestMethod.GET)
    public ModelAndView manager(HttpServletRequest request) {

        List<Model.PartitionItem> partitionItems = new ArrayList<>();
        Model.PartitionItem partitionItem;
        for (Partition partition : partitionService.getPartitions()) {
            partitionItem = new Model.PartitionItem();
            partitionItem.partitionId = partition.getId();
            partitionItem.partitionName = getNormalName(partition.getName(), 36);
            partitionItems.add(partitionItem);
        }
        Model model = new Model();
        model.partitionItems = partitionItems;

        Collections.sort(partitionItems);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model", model);

        if (request.getRequestURL().toString().endsWith("/"))
            modelAndView.addObject("prefix", "");
        else
            modelAndView.addObject("prefix", "operator/");

        modelAndView.setViewName("operator/index");
        return modelAndView;
    }

    private String getNormalName(String name, int length) {
        if (name != null && name.length() > length)
            return name.substring(0, length) + "..";
        else
            return name;
    }

    public static class Model {
        public List<PartitionItem> partitionItems;

        public List<PartitionItem> getPartitionItems() {
            return partitionItems;
        }

        public static class PartitionItem implements Comparable<PartitionItem> {
            public int partitionId;
            public String partitionName;

            @Override
            public int compareTo(PartitionItem o) {
                return this.partitionName.compareTo(o.partitionName);
            }

            public int getPartitionId() {
                return partitionId;
            }
            public String getPartitionName() {
                return partitionName;
            }
        }
    }
}
