package com.northlinuxpioneers.arash.medicalarticles;

/**
 * Created by Arash on 1/3/2015.
 */
public class Item {
    private int id;
    private String title;
    private String text;
    private String picURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }
}
