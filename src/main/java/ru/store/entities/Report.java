package ru.store.entities;

import java.sql.Timestamp;

/**
 *
 */

public class Report {

    private Integer Id;
    private String name;
    private Integer companyId;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private String lastModifiedBy;
    private String owner;
    private String description;
    private Integer fileId;

    public Report() {
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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
    public Integer getFileId() {
        return fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "Report{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", companyId=" + companyId +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", owner='" + owner + '\'' +
                ", description='" + description + '\'' +
                ", fileId=" + fileId +
                '}';
    }
}
