package ru.store.entities;

import java.util.Collection;

/**
 *
 */
public class SubPartition {

    private int id;
    private String name;
    private Partition partition;
    private Collection<Company> companies;

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
    public Collection<Company> getCompanies() {
        return companies;
    }
    public void setCompanies(Collection<Company> companies) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubPartition that = (SubPartition) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "SubPartition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", partition=" + partition +
                ", companies=" + companies +
                '}';
    }
}
