package ru.store.entities;

/**
 *
 */
public class Partition {

    private int id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Partition partition = (Partition) o;

        return id == partition.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
