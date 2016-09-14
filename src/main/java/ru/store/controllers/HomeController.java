package ru.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
/*
        List<PartitionItem> partitionItemList = new ArrayList<>();
        PartitionItem partitionItem;
        for (int i = 0; i < 30; i++) {
            partitionItem = new PartitionItem();
            partitionItem.setCounter((int) (Math.random() * 3000));
            partitionItem.setId(i + "");
            partitionItem.setTitle("Дизайн - Графика - Фото");
            partitionItemList.add(partitionItem);
        }



        Map<String, List<BestResourcesItem>> bestResourcesItemMap = new HashMap<>();

        List<BestResourcesItem> bestResourcesItemList;
        BestResourcesItem resourcesItem;
        for (int k = 0; k < 3; k++) {
            bestResourcesItemList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                resourcesItem = new BestResourcesItem();
                resourcesItem.setTitle("Дизайн - Графика - Фото");
                resourcesItem.setId(i + "");
                resourcesItem.setImageUrl("testLogo.jpg");
                bestResourcesItemList.add(resourcesItem);
            }
            bestResourcesItemMap.put(k + "", bestResourcesItemList);
        }



        HomeModel homeModel = new HomeModel(partitionItemList, bestResourcesItemMap);
        model.addAttribute("homeModel", homeModel);
        */
        return "portal/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home( Model model) {
        return main( model);
    }

}
