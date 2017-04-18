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

        List<Partition> partitionList = partitionService.getPartitions();
        Collections.sort(partitionList);
        List<Model.PartitionItem> partitionItems1 = new ArrayList<>();
        List<Model.PartitionItem> partitionItems2 = new ArrayList<>();

        Model.PartitionItem partitionItem;
        int tmp = partitionList.size();
        int step = tmp / 2;
        int counter = 0;
        for (Partition partition : partitionList) {
            partitionItem = new Model.PartitionItem();
            partitionItem.partitionId = partition.getId();
            partitionItem.partitionName = getNormalName(partition.getName(), 46);
            if (counter <= step) {
                partitionItems1.add(partitionItem);
            } else {
                partitionItems2.add(partitionItem);
            }
            counter++;
        }
        Model model = new Model();
        Collections.sort(partitionItems1);
        Collections.sort(partitionItems2);
        model.partitionItems1 = partitionItems1;
        model.partitionItems2 = partitionItems2;

        Collections.sort(partitionItems1);
        Collections.sort(partitionItems2);

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
        public List<PartitionItem> partitionItems1;
        public List<PartitionItem> partitionItems2;

        public List<PartitionItem> getPartitionItems1() {
            return partitionItems1;
        }
        public List<PartitionItem> getPartitionItems2() {
            return partitionItems2;
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
