package com.example.movie_verse_service.model;
import java.util.List;

public class Movie {
    private Long id;
    private String title;
    private List<Actor> cast;
    private List<Category> categoryList;
    private String releaseDate;
    private double budget;
    private double rating;

    public Movie(Long id, String title, List<Actor> cast, List<Category> categoryList, String releaseDate, double budget, double rating) {
        this.id = id;
        this.title = title;
        this.cast = cast;
        this.categoryList = categoryList;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cast=" + cast +
                ", categoryList=" + categoryList +
                ", releaseDate='" + releaseDate + '\'' +
                ", budget=" + budget +
                ", rating=" + rating +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "Movie{" +
//                "title='" + title + '\'' +
//                ", cast=" + cast +
//                ", categoryList='" + categoryList + '\'' +
//                ", releaseDate='" + releaseDate + '\'' +
//                ", budget=" + budget +
//                ", rating=" + rating +
//                '}';
//    }
}

