package ru.store.entities;

/**
 *
 */
public class SubPartition {

    private int id;
    private Integer partitionId;
    private String name;

    public SubPartition() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Integer getPartitionId() {
        return partitionId;
    }
    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
