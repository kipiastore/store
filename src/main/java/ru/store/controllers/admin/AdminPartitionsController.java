package ru.store.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Partition;
import ru.store.entities.SubPartition;
import ru.store.service.PartitionService;
import ru.store.service.SubPartitionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminPartitionsController {
    @Autowired
    private PartitionService partitionService;
    @Autowired
    private SubPartitionService subPartitionService;
    @RequestMapping(value = "/admin/partitions", method = RequestMethod.GET)
    public ModelAndView partitions() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }
    @RequestMapping(value = "/admin/addpartition", method = RequestMethod.POST)
    public ModelAndView createPartition(@ModelAttribute("partition") Partition partition,
                                        @RequestParam MultiValueMap<String, String> partitionRequestMap) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if(Integer.valueOf(partitionRequestMap.get("partitionLevel").toString().replace("[", "").replace("]", "")) == 1) {
                partitionService.createPartition(partition);
            }
            else{
                SubPartition sb = new SubPartition();
                sb.setPartitionId(Integer.valueOf(partitionRequestMap.get("namePartition").toString().replace("[", "").replace("]", "")));
                sb.setName(partitionRequestMap.get("name").toString().replace("[", "").replace("]", ""));
                subPartitionService.createSubPartition(sb);
            }
            modelAndView.addObject("successMessage", "Раздел успешно добавлен.");
        } catch (Exception ex) {
            modelAndView.addObject("addError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/updatepartition", method = RequestMethod.POST)
    public ModelAndView updateCompany(@ModelAttribute("partition") Partition partition) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            partitionService.updatePartition(partition);
            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/deletepartition", method = RequestMethod.POST)
    public ModelAndView deleteCompany(@RequestParam("partitionId") String partitionId) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            subPartitionService.deleteSubPartition(Integer.valueOf(partitionId));
            modelAndView.addObject("successMessage", "Раздел успешно удален.");
        } catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }
    private void loadPage(Model model, ModelAndView modelAndView) {
        model.selectedPageNum = 3;
        loadPartitions(model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("admin/partitions");
    }

    private void loadPartitions(Model model) {
        List<Model.PartitionItem> partitionItems = new ArrayList<>();
        Model.PartitionItem partitionItem;
        Model.PartitionItem mainPartitionItem;
        Map<Integer, Model.PartitionItem> partitionIdToPartitionItem = new HashMap<>();
        Map<Model.PartitionItem, List<Model.PartitionItem>> subPartitionsGroupedByPartition = new HashMap<>();
        for (Partition partition : partitionService.getPartitions()) {
            partitionItem = new Model.PartitionItem();
            partitionItem.id = partition.getId();
            partitionItem.name = getNormalName(partition.getName());
            partitionIdToPartitionItem.put(partition.getId(), partitionItem);
            partitionItems.add(partitionItem);
            subPartitionsGroupedByPartition.put(partitionItem, null);
        }
        model.partitionItems = partitionItems;
        for (SubPartition subPartition : subPartitionService.getSubPartitions()) {
            if (subPartitionsGroupedByPartition.get(new Model.PartitionItem(subPartition.getPartitionId())) != null) {
                partitionItem = new Model.PartitionItem();
                partitionItem.id = subPartition.getId();
                partitionItem.name = getNormalName(subPartition.getName());
                subPartitionsGroupedByPartition.get(new Model.PartitionItem(subPartition.getPartitionId())).add(partitionItem);
            } else {
                mainPartitionItem = new Model.PartitionItem();
                mainPartitionItem.id = partitionIdToPartitionItem.get(subPartition.getPartitionId()).getId();
                mainPartitionItem.name = getNormalName(partitionIdToPartitionItem.get(subPartition.getPartitionId()).getName());
                partitionItems = new ArrayList<>();
                partitionItem = new Model.PartitionItem();
                partitionItem.id = subPartition.getId();
                partitionItem.name = getNormalName(subPartition.getName());
                partitionItems.add(partitionItem);
                subPartitionsGroupedByPartition.put(mainPartitionItem, partitionItems);
            }
        }
        model.subPartitionsGroupedByPartition = subPartitionsGroupedByPartition;
    }

    private String getNormalName(String name) {
        if (name != null && name.length() > 24)
            return name.substring(0, 24) + "..";
        else
            return name;
    }

    @RequestMapping(value = "/admin/addpartition", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/admin/partitions";
    }
    @RequestMapping(value = "/admin/updatepartition", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/admin/partitions";
    }
    @RequestMapping(value = "/admin/deletepartition", method = RequestMethod.GET)
    public String redirect3() {
        return "redirect:/admin/partitions";
    }

    public static class Model {
        /**
         * 1 = companies
         * 2 = positions
         * 3 = partitions
         * 4 = users
         * 5 = managers
         * 6 = regions
         * 7 = packages
         * 8 = reports
         */
        public int selectedPageNum;
        public List<PartitionItem> partitionItems;
        public Map<PartitionItem, List<PartitionItem>> subPartitionsGroupedByPartition;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public List<PartitionItem> getPartitionItems() {
            return partitionItems;
        }
        public Map<PartitionItem, List<PartitionItem>> getSubPartitionsGroupedByPartition() {
            return subPartitionsGroupedByPartition;
        }

        public static class PartitionItem {
            public int id;
            public String name;

            public PartitionItem() {
            }

            public PartitionItem(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }
            public String getName() {
                return name;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                PartitionItem that = (PartitionItem) o;
                return id == that.id;

            }
            @Override
            public int hashCode() {
                return id;
            }
        }
    }
}
