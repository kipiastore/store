package ru.store.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.store.service.PackageService;
import ru.store.entities.Package;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class AdminPackagesController {

    @Autowired
    private PackageService packageService;

    @RequestMapping(value = "/admin/packages", method = RequestMethod.GET)
    public ModelAndView packages() {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(modelAndView, model);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/addpackage", method = RequestMethod.POST)
    public ModelAndView createPackage(@ModelAttribute("package") Package aPackage) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            packageService.createPackage(aPackage);
            modelAndView.addObject("successMessage", "Пакет успешно добавлен.");
        } catch (Exception ex) {
            modelAndView.addObject("addError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        loadPage(modelAndView, model);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/updatepackage", method = RequestMethod.POST)
    public ModelAndView updatePackage(@ModelAttribute("package") Package aPackage,
                                      @RequestParam("hiddenId") String id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            aPackage.setId(Integer.valueOf(id));
            packageService.updatePackage(aPackage);
            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        loadPage(modelAndView, model);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/deletepackage", method = RequestMethod.POST)
    public ModelAndView deletePackage(@RequestParam("packageId") String packageId) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            packageService.deletePackage(Integer.valueOf(packageId));
            modelAndView.addObject("successMessage", "Пакет успешно удален.");
        } catch(Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
        }
        Model model = new Model();
        loadPage(modelAndView, model);
        return modelAndView;
    }

    private void loadPage(ModelAndView modelAndView, Model model) {
        model.selectedPageNum = 8;
        loadRegions(model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("admin/packages");
    }

    private void loadRegions(Model model) {
        List<Model.PackageItem> packageItems = new ArrayList<>();
        Model.PackageItem packageItem;
        for (Package aPackage : packageService.getPackages()) {
            packageItem = new Model.PackageItem();
            packageItem.id = aPackage.getId();
            if (aPackage.getName() != null && aPackage.getName().length() > 24)
                packageItem.name = aPackage.getName().substring(0, 24) + "..";
            else
                packageItem.name = aPackage.getName();
            packageItem.priority = aPackage.getPriority();
            packageItems.add(packageItem);
        }
        model.packageItems = packageItems;
    }

    @RequestMapping(value = "/admin/addpackage", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/admin/packages";
    }
    @RequestMapping(value = "/admin/deletepackage", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/admin/packages";
    }
    @RequestMapping(value = "/admin/updatepackage", method = RequestMethod.GET)
    public String redirect3() {
        return "redirect:/admin/packages";
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
        public List<PackageItem> packageItems;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public List<PackageItem> getPackageItems() {
            return packageItems;
        }

        public static class PackageItem {
            public int id;
            public String name;
            public int priority;

            public int getId() {
                return id;
            }
            public String getName() {
                return name;
            }
            public int getPriority() {
                return priority;
            }
        }
    }
}
