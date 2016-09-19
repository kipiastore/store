package ru.store.entities;

/**
 *
 */
public class PriceList {

    private String id;
    private String name;
    private String photo;
    private String description;
    private Company company;
    private SubPartition subPartition;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public SubPartition getSubPartition() {
        return subPartition;
    }
    public void setSubPartition(SubPartition subPartition) {
        this.subPartition = subPartition;
    }
}
