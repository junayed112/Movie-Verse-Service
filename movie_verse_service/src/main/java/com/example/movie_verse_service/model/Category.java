package com.example.movie_verse_service.model;
public class Category {
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    // Getters and Setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

