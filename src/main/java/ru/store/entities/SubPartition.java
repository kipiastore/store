package ru.store.entities;

/**
 *
 */
public class SubPartition implements Comparable<SubPartition> {

    private int id;
    private Integer partitionId;
    private String name;
    private Integer countSubPartition=0;
    public SubPartition() {

    }

    @Override
    public int compareTo(SubPartition o) {
        return this.name.compareTo(o.name);
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

    public int getCountSubPartition() {
        return countSubPartition;
    }

    public void setCountSubPartition(int countSubPartition) {
        this.countSubPartition = countSubPartition;
    }
    public void setCountSubPartition() {
        this.countSubPartition = countSubPartition+1;
    }

}
