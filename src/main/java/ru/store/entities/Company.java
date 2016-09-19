package ru.store.entities;

import java.util.Collection;

/**
 *
 */
public class Company {

    private int id;
    private String name;
    private Partition partition;
    private Collection<SubPartition> subPartitions;
    private String logo;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Partition getPartition() {
        return partition;
    }
    public void setPartition(Partition partition) {
        this.partition = partition;
    }
    public Collection<SubPartition> getSubPartitions() {
        return subPartitions;
    }
    public void setSubPartitions(Collection<SubPartition> subPartitions) {
        this.subPartitions = subPartitions;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
}
