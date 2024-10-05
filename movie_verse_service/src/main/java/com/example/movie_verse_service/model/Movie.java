package com.example.movie_verse_service.model;
import java.util.List;

public class Movie {
    private String title;
    private List<Actor> cast; // List of actors representing the cast
    private String category;
    private String releaseDate;
    private double budget;
    private double rating;

    public Movie(String title, List<Actor> cast, String category, String releaseDate, double budget, double rating) {
        this.title = title;
        this.cast = cast;
        this.category = category;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.rating = rating;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
                "title='" + title + '\'' +
                ", cast=" + cast +
                ", category='" + category + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", budget=" + budget +
                ", rating=" + rating +
                '}';
    }
}

