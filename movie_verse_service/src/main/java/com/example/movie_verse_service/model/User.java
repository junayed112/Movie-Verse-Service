package com.example.movie_verse_service.model;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String name;
    private List<Movie> favoriteMovies = new ArrayList<>();

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void addFavoriteMovie(Movie movie) {
        if (!favoriteMovies.contains(movie)) {
            favoriteMovies.add(movie);
        }
    }

    public void removeFavoriteMovie(Movie movie) {
        favoriteMovies.remove(movie);
    }
}
