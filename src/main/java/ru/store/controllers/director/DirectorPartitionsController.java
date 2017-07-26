package ru.store.controllers.director;

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
import ru.store.exceptions.NullPointerException;
import java.util.*;

@Controller
public class DirectorPartitionsController {
    @Autowired
    private PartitionService partitionService;
    @Autowired
    private SubPartitionService subPartitionService;
    @RequestMapping(value = "/director/partitions", method = RequestMethod.GET)
    public ModelAndView partitions() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }
    @RequestMapping(value = "/director/addpartition", method = RequestMethod.POST)
    public ModelAndView createPartition(@ModelAttribute("partition") Partition partition,
                                        @RequestParam MultiValueMap<String, String> partitionRequestMap) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if(Integer.valueOf(partitionRequestMap.get("partitionLevel").toString().replace("[", "").replace("]", "")) == 1) {
                partitionService.createPartition(partition);
            }
            else{
                if(partitionRequestMap.get("namePartition")!=null) {
                    SubPartition sb = new SubPartition();
                    sb.setPartitionId(Integer.valueOf(partitionRequestMap.get("namePartition").toString().replace("[", "").replace("]", "")));

                    sb.setName(partitionRequestMap.get("name").toString().replace("[", "").replace("]", ""));
                    subPartitionService.createSubPartition(sb);
                }
                else{
                    throw new NullPointerException("Сначало создайте раздел!");
                }
            }
            modelAndView.addObject("successMessage", "Раздел успешно добавлен.");
        } catch (Exception ex) {
            modelAndView.addObject("addError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/director/updatepartition", method = RequestMethod.POST)
    public ModelAndView updateCompany(@ModelAttribute("partition") Partition partition,
                                      @RequestParam("type") String type) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (type.equals("subPartition")) {
                SubPartition tmp = subPartitionService.getSubPartition(partition.getId());
                tmp.setName(partition.getName());
                subPartitionService.updateSubPartition(tmp);
            } else {
                partitionService.updatePartition(partition);
            }
            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/director/deletepartition", method = RequestMethod.POST)
    public ModelAndView deleteCompany(@RequestParam("deleteId") String deleteId,
                                      @RequestParam("type") String type) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (type.equals("partition")) {
                partitionService.deletePartition(Integer.valueOf(deleteId));
            } else {
                subPartitionService.deleteSubPartition(Integer.valueOf(deleteId));
            }
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
        modelAndView.setViewName("director/partitions");
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
            partitionItem.fullName = partition.getName();
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
                partitionItem.fullName = subPartition.getName();
                subPartitionsGroupedByPartition.get(new Model.PartitionItem(subPartition.getPartitionId())).add(partitionItem);
            } else {
                mainPartitionItem = new Model.PartitionItem();
                mainPartitionItem.id = partitionIdToPartitionItem.get(subPartition.getPartitionId()).getId();
                mainPartitionItem.name = getNormalName(partitionIdToPartitionItem.get(subPartition.getPartitionId()).getName());
                mainPartitionItem.fullName = partitionIdToPartitionItem.get(subPartition.getPartitionId()).getName();
                partitionItems = new ArrayList<>();
                partitionItem = new Model.PartitionItem();
                partitionItem.id = subPartition.getId();
                partitionItem.name = getNormalName(subPartition.getName());
                partitionItem.fullName = subPartition.getName();
                partitionItems.add(partitionItem);
                subPartitionsGroupedByPartition.put(mainPartitionItem, partitionItems);
            }
        }
        model.subPartitionsGroupedByPartition = subPartitionsGroupedByPartition;

        Map<Model.PartitionItem2, Set<Model.PartitionItem2>> subPartitionsGroupedByPartition2 = new TreeMap<>();
        Set<Model.PartitionItem2> partitionItem2List;

        for (Model.PartitionItem partitionItem1 : subPartitionsGroupedByPartition.keySet()) {
            partitionItem2List = new TreeSet<>();
            if (subPartitionsGroupedByPartition.get(partitionItem1) == null) {
                subPartitionsGroupedByPartition2.put(new Model.PartitionItem2(partitionItem1.getId(), partitionItem1.getName(), partitionItem1.getFullName()), partitionItem2List);
                continue;
            }

            for (Model.PartitionItem partitionItem2 : subPartitionsGroupedByPartition.get(partitionItem1)) {
                partitionItem2List.add(new Model.PartitionItem2(partitionItem2.getId(), partitionItem2.getName(), partitionItem2.getFullName()));
            }
            subPartitionsGroupedByPartition2.put(new Model.PartitionItem2(partitionItem1.getId(), partitionItem1.getName(), partitionItem1.getFullName()), partitionItem2List);
        }
        model.subPartitionsGroupedByPartition2 = subPartitionsGroupedByPartition2;
    }

    private String getNormalName(String name) {
        if (name != null && name.length() > 24)
            return name.substring(0, 24) + "..";
        else
            return name;
    }

    @RequestMapping(value = "/director/addpartition", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/director/partitions";
    }
    @RequestMapping(value = "/director/updatepartition", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/director/partitions";
    }
    @RequestMapping(value = "/director/deletepartition", method = RequestMethod.GET)
    public String redirect3() {
        return "redirect:/director/partitions";
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
        public Map<PartitionItem2, Set<PartitionItem2>> subPartitionsGroupedByPartition2;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public List<PartitionItem> getPartitionItems() {
            return partitionItems;
        }
        public Map<PartitionItem, List<PartitionItem>> getSubPartitionsGroupedByPartition() {
            return subPartitionsGroupedByPartition;
        }
        public Map<PartitionItem2, Set<PartitionItem2>> getSubPartitionsGroupedByPartition2() {
            return subPartitionsGroupedByPartition2;
        }

        public static class PartitionItem {
            public int id;
            public String name;
            public String fullName;

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
            public String getFullName() {
                return fullName;
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
            @Override
            public String toString() {
                return "PartitionItem{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", fullName='" + fullName + '\'' +
                        '}';
            }
        }

        public static class PartitionItem2 implements Comparable<PartitionItem2> {
            public int id;
            public String name;
            public String fullName;

            public int getId() {
                return id;
            }
            public String getName() {
                return name;
            }
            public String getFullName() {
                return fullName;
            }

            public PartitionItem2() { }
            public PartitionItem2(int id, String name, String fullName) {
                this.id = id;
                this.name = name;
                this.fullName = fullName;
            }

            @Override
            public int compareTo(PartitionItem2 o) {
                return this.fullName.compareTo(o.fullName);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                PartitionItem2 that = (PartitionItem2) o;
                return Objects.equals(fullName, that.fullName);

            }
            @Override
            public int hashCode() {
                return fullName.hashCode();
            }

            @Override
            public String toString() {
                return "PartitionItem2{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", fullName='" + fullName + '\'' +
                        '}';
            }
        }
    }
}
