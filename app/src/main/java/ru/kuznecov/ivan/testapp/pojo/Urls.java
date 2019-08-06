package ru.kuznecov.ivan.testapp.pojo;

import com.google.gson.annotations.SerializedName;

public class Urls {

    @SerializedName("small")
    private String image;

    public Urls() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
