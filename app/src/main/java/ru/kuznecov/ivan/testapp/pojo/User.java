package ru.kuznecov.ivan.testapp.pojo;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("username")
    private String name;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
