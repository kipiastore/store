package ru.store.entities;

import java.sql.Timestamp;

/**
 *
 */
public class Act {

    private Integer Id;
    private String name;
    private Integer companyId;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private String lastModifiedBy;
    private String owner;
    private String description;
    private Integer fileId;
    private String limitation;
    private Integer total;
    private Integer payment;

    public Act() {
        Timestamp tmp = new Timestamp(new java.util.Date().getTime());
        createdDate = tmp;
        lastModifiedDate = tmp;
    }

    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getFileId() {
        return fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
    public String getLimitation() {
        return limitation;
    }
    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public Integer getPayment() {
        return payment;
    }
    public void setPayment(Integer payment) {
        this.payment = payment;
    }
}
