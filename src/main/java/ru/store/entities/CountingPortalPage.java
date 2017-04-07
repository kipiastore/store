package ru.store.entities;

/**
 * Created by Akex on 27.03.2017.
 */
public  class CountingPortalPage {

    public Integer id;

    public static Integer  countPortal=0;

    public CountingPortalPage(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public  Integer getCountPortal() {
        return countPortal;
    }

    public  void setCountPortal(Integer count) {
        countPortal = count;
    }
    public  void setCountPortal() {
        countPortal = countPortal+1;
    }
}
