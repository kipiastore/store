package ru.store.controllers.models;

/**
 * Created by User on 05.09.2016.
 */
public class MenuItem {

    private String id;
    private String title;
    private int counter;

    public MenuItem(String id, String title, int counter) {
        this.id = id;
        this.title = title;
        this.counter = counter;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
