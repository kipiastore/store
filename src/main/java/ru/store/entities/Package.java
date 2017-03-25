package ru.store.entities;

import java.sql.Timestamp;

/**
 *
 */
public class Package {

    private Integer id;
    private String name;
    private Integer priority;
    private Integer cost;
    private Integer numOfPositions;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private String lastModifiedBy;

    private String owner;

    public Package() {
        Timestamp tmp = new Timestamp(new java.util.Date().getTime());
        createdDate = tmp;
        lastModifiedDate = tmp;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public Integer getCost() {
        return cost;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }
    public Integer getNumOfPositions() {
        return numOfPositions;
    }
    public void setNumOfPositions(Integer numOfPositions) {
        this.numOfPositions = numOfPositions;
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
}
