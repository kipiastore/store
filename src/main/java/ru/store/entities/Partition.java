package ru.store.entities;

/**
 *
 */
public class Partition implements Comparable<Partition> {

    private int id;
    private String name;

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
}
