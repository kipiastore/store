package ru.store.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public ModelAndView createPartition(@RequestParam("partitionLevel") String partitionLevel,
                                        @RequestParam("namePartition") String namePartition,
                                        @RequestParam("name") String name,
                                        @ModelAttribute("partition") Partition partition) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if(Integer.valueOf(partitionLevel) == 1) {
                partitionService.createPartition(partition);
            }
            else{
                SubPartition sb = new SubPartition();
                sb.setNamePartition(namePartition);
                sb.setName(name);
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
            partitionService.deletePartition(Integer.valueOf(partitionId));
            modelAndView.addObject("successMessage", "Раздел успешно удален.");
        } catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }
    private void loadPage( Model model, ModelAndView modelAndView) {
        model.selectedPageNum = 3;
        loadPartitions(model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.addObject("partitions", loadPartitionList());
        modelAndView.setViewName("admin/partitions");
    }

    private void loadPartitions(Model model) {
        List<Model.PartitionItem> partitionItems = new ArrayList<>();
        Model.PartitionItem partitionItem;
        for (Partition partition : partitionService.getPartitions()) {
            partitionItem = new Model.PartitionItem();
            partitionItem.id = partition.getId();
            partitionItem.name = partition.getName();
            partitionItems.add(partitionItem);
        }
        model.partitionItems = partitionItems;
    }
    private ArrayList<String> loadPartitionList() {
        List<Partition> partitionP = partitionService.getPartitions();
        ArrayList<String> partitions = new ArrayList<>();
        for (int i = 0; i < partitionP.size(); i++) {
            partitions.add(i, partitionP.get(i).getName());
        }
        return partitions;
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
        public Map<String, List<PartitionItem>> subPartitionsGroupedByPartition;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public List<PartitionItem> getPartitionItems() {
            return partitionItems;
        }
        public Map<String, List<PartitionItem>> getSubPartitionsGroupedByPartition() {
            return subPartitionsGroupedByPartition;
        }

        public static class PartitionItem {
            public int id;
            public String name;

            public int getId() {
                return id;
            }
            public String getName() {
                return name;
            }
        }
    }
}
