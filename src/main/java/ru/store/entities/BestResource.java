package ru.store.entities;

public class BestResource {

    private String id;
    private String title;
    private String imageUrl;
    private Resource resource;

    public BestResource() {

    }

    public BestResource(String id, String title, String imageUrl, Resource resource) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.resource = resource;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

}
