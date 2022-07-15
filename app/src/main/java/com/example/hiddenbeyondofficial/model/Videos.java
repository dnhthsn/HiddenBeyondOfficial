package com.example.hiddenbeyondofficial.model;

public class Videos {
    private String name, category, image, video;

    public Videos() {
    }

    public Videos(String name, String category, String image, String video) {
        this.name = name;
        this.category = category;
        this.image = image;
        this.video = video;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
