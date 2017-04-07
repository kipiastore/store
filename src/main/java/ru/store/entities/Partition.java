package ru.store.entities;

/**
 *
 */
public class Partition implements Comparable<Partition> {

    private int id;
    private String name;
    private Integer countPartition=0;
    public Partition() {

    }

    @Override
    public int compareTo(Partition o) {
        return this.name.compareTo(o.name);
    }


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

    public int getCountPartition() {
        return countPartition;
    }

    public void setCountPartition(int countPartition) {
        this.countPartition = countPartition;
    }

    public void setCountPartition() {
        this.countPartition = countPartition+1;
    }
}
