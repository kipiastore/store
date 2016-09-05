package ru.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.store.controllers.models.BestResourcesItem;
import ru.store.controllers.models.HomeModel;
import ru.store.controllers.models.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {

        List<MenuItem> menuItemList = new ArrayList<>();
        MenuItem menuItem;
        for (int i = 0; i < 30; i++) {
            menuItem = new MenuItem(i + "", "Дизайн - Графика - Фото", (int) (Math.random() * 3000));
            menuItemList.add(menuItem);
        }

        Map<String, List<BestResourcesItem>> bestResourcesItemMap = new HashMap<>();
        List<BestResourcesItem> bestResourcesItemList;
        BestResourcesItem resourcesItem;
        for (int k = 0; k < 3; k++) {
            bestResourcesItemList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                resourcesItem = new BestResourcesItem(i + "", "Дизайн - Графика - Фото", i + "");
                bestResourcesItemList.add(resourcesItem);
            }
            bestResourcesItemMap.put(k + "", bestResourcesItemList);
        }

        HomeModel homeModel = new HomeModel(menuItemList, bestResourcesItemMap);
        model.addAttribute("homeModel", homeModel);
        return "portal/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home( Model model) {
        return main( model);
    }

}
