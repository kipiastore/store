package ru.store.entities;

public class BestCompany {

    private int id;
    private Company company;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "BestCompany{" +
                "id=" + id +
                ", company=" + company +
                '}';
    }
}
