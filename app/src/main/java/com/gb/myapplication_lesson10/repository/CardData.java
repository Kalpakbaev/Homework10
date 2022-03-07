package com.gb.myapplication_lesson10.repository;

public class CardData {
    private String title;
    private String description;
    private int picture;


    private boolean like;

    public CardData(String title, String description, int picture, boolean like) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.like = like;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPicture() {
        return picture;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isLike() {
        return like;
    }


}