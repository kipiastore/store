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
    private Timestamp createdDate = new Timestamp(new java.util.Date().getTime());
    private String owner;

    public Package() {

    }
    public Package(Integer id, String name, Integer priority, Timestamp createdDate, String owner) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.createdDate = createdDate;
        this.owner = owner;
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

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", cost=" + cost +
                ", numOfPositions=" + numOfPositions +
                ", createdDate=" + createdDate +
                ", owner='" + owner + '\'' +
                '}';
    }
}
