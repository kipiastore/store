package ru.store.controllers.models;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 05.09.2016.
 */
public class HomeModel {

    List<MenuItem> menuItemList;
    Map<String, List<BestResourcesItem>> bestResourcesItemMap;

    public HomeModel(List<MenuItem> menuItemList, Map<String, List<BestResourcesItem>> bestResourcesItemMap) {
        this.menuItemList = menuItemList;
        this.bestResourcesItemMap = bestResourcesItemMap;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }
    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
    public Map<String, List<BestResourcesItem>> getBestResourcesItemMap() {
        return bestResourcesItemMap;
    }
    public void setBestResourcesItemMap(Map<String, List<BestResourcesItem>> bestResourcesItemMap) {
        this.bestResourcesItemMap = bestResourcesItemMap;
    }

}
