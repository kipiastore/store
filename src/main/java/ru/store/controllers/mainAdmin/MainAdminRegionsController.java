package ru.store.controllers.mainAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Region;
import ru.store.service.RegionService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class MainAdminRegionsController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "/mainAdmin/regions", method = RequestMethod.GET)
    public ModelAndView regions() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(modelAndView, model);
        return modelAndView;
    }

    @RequestMapping(value = "/mainAdmin/addregion", method = RequestMethod.POST)
    public ModelAndView createRegion(@ModelAttribute("region") Region region) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            regionService.createRegion(region);
            modelAndView.addObject("successMessage", "Район успешно добавлен.");
        } catch (Exception ex) {
            modelAndView.addObject("addError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        loadPage(modelAndView, model);
        return modelAndView;
    }

    @RequestMapping(value = "/mainAdmin/deleteregion", method = RequestMethod.POST)
    public ModelAndView deleteRegion(@RequestParam("regionId") String regionId) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            regionService.deleteRegion(Integer.valueOf(regionId));
            modelAndView.addObject("successMessage", "Район успешно удален.");
        } catch(Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        loadPage(modelAndView, model);
        return modelAndView;
    }

    private void loadPage(ModelAndView modelAndView, Model model) {
        model.selectedPageNum = 7;
        loadRegions(model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("mainAdmin/regions");
    }

    private void loadRegions(Model model) {
        List<Model.RegionItem> regionItems = new ArrayList<>();
        Model.RegionItem regionItem;
        for (Region region : regionService.getRegions()) {
            regionItem = new Model.RegionItem();
            regionItem.id = region.getId();
            if (region.getName() != null && region.getName().length() > 24)
                regionItem.name = region.getName().substring(0, 24) + "..";
            else
                regionItem.name = region.getName();
            regionItems.add(regionItem);
        }
        model.regionItems = regionItems;
    }

    @RequestMapping(value = "/mainAdmin/addregion", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/mainAdmin/regions";
    }
    @RequestMapping(value = "/mainAdmin/deleteregion", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/mainAdmin/regions";
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
        public List<RegionItem> regionItems;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public List<RegionItem> getRegionItems() {
            return regionItems;
        }

        public static class RegionItem {
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
