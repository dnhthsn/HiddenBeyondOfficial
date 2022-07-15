package com.example.hiddenbeyondofficial.model;

import java.io.Serializable;
import java.util.List;

public class AllCategory implements Serializable {
    private String categoryTitle;

    private List<Videos> categoryItem = null;

    public AllCategory(String categoryTitle, List<Videos> categoryItemList) {
        this.categoryTitle = categoryTitle;
        this.categoryItem = categoryItemList;
    }

    public List<Videos> getCategoryItem() {
        return categoryItem;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }
}
