package com.example.dd.home.dto;

public class HistoryItem {

    public String id;
    public String title;
    public int imageRes;
    public String description;

    public HistoryItem(){}

    public HistoryItem(String title,int imageRes){
        this.imageRes = imageRes;
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

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
