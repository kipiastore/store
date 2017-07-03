package ru.store.entities;


import java.util.Date;

/**
 * Created by Akex on 27.03.2017.
 */
public  class CountingPortalPage {

    public Integer id;

    public  Integer  countPortal=0;

    public  Integer  countPortalToday=0;

    private Date countUpdateToday;

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


    public  Integer getCountPortalToday() {
        return countPortalToday;
    }
    public  void setCountPortalToday(Integer count) {
        countPortalToday = count;
    }
    public  void setCountPortalToday() {
        countPortalToday = countPortalToday+1;
    }


    public Date getCountUpdateToday() {
        return countUpdateToday;
    }
    public void setCountUpdateToday(Date countUpdateToday) {
        this.countUpdateToday = countUpdateToday;
    }
}
