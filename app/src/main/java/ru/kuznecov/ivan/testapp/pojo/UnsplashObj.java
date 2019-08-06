package ru.kuznecov.ivan.testapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UnsplashObj {

    @SerializedName("total")
    private long totalObj;
    @SerializedName("total_pages")
    private long totalPages;
    @SerializedName("results")
    List<PhotoData> photoDataList = new ArrayList<>();

    public UnsplashObj() {
    }

    public long getTotalObj() {
        return totalObj;
    }

    public void setTotalObj(long totalObj) {
        this.totalObj = totalObj;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<PhotoData> getPhotoDataList() {
        return photoDataList;
    }

    public void setPhotoDataList(List<PhotoData> photoDataList) {
        this.photoDataList = photoDataList;
    }

    @Override
    public String toString() {
        return "UnsplashObj{" +
                "totalObj=" + totalObj +
                ", totalPages=" + totalPages +
                '}';
    }
}
