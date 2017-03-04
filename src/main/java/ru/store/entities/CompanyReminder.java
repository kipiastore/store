package ru.store.entities;

import com.google.gson.Gson;

import java.util.Date;

public class CompanyReminder {
    private Integer id;
    private String hourReminder;
    private Date dateReminder;
    private String typeReminder;
    private String commentReminder;
    private Integer companyId;
    private String companyName;
    public CompanyReminder(){}
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getDateReminder() {
        return dateReminder;
    }
    public void setDateReminder(Date dateReminder) {
        this.dateReminder = dateReminder;
    }
    public String getTypeReminder() {
        return typeReminder;
    }
    public void setTypeReminder(String typeReminder) {
        this.typeReminder = typeReminder;
    }
    public String getCommentReminder() {
        return commentReminder;
    }
    public void setCommentReminder(String commentReminder) {
        this.commentReminder = commentReminder;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHourReminder() {
        return hourReminder;
    }
    public void setHourReminder(String hourReminder) {
        this.hourReminder = hourReminder;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
