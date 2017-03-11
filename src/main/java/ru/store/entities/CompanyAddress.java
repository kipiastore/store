package ru.store.entities;

import com.google.gson.Gson;

/**
 *
 */
public class CompanyAddress {

    private Integer id;
    private String address;
    private Integer regionId;
    private String phones;
    private String information;
    private Integer companyId;

    public CompanyAddress() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getRegionId() {
        return regionId;
    }
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    public String getPhones() {
        return phones;
    }
    public void setPhones(String phones) {
        this.phones = phones;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
