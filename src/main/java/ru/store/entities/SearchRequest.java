package ru.store.entities;

/**
 * Created by Asura on 13.05.17.
 */
public class SearchRequest {

    private int id;
    private String value;
    private int counter;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
