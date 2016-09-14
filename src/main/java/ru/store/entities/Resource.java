package ru.store.entities;

import java.util.Collection;

public class Resource {

    private String id;
    private String title;
    private Partition partition;
    private Collection<SubPartition> subPartitions;

    private Resource() {

    }

    public Resource(String id, String title) {
        this.id = id;
        this.title = title;
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

}
