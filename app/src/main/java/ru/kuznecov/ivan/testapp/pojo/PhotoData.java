package ru.kuznecov.ivan.testapp.pojo;

import com.google.gson.annotations.SerializedName;

public class PhotoData {

    @SerializedName("id")
    private String id;
    @SerializedName("description")
    private String description;
    @SerializedName("urls")
    private Urls urls;
    @SerializedName("likes")
    private int likes;
    @SerializedName("user")
    private User user;

    public PhotoData() {
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
