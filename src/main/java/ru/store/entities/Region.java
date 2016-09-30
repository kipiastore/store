package ru.store.entities;

import java.sql.Timestamp;

/**
 *
 */
public class Region {

    private Integer id;
    private String name;
    private Timestamp createdDate = new Timestamp(new java.util.Date().getTime());
    private String owner;

    public Region() {

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

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", owner='" + owner + '\'' +
                '}';
    }
}
