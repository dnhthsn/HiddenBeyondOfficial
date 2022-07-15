package com.example.hiddenbeyondofficial.model;

import java.io.Serializable;

public class News implements Serializable {
    private String name;
    private String image;
    private String content;

    public News() {
    }

    public News(String name, String image, String url) {
        this.name = name;
        this.image = image;
        this.content = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
