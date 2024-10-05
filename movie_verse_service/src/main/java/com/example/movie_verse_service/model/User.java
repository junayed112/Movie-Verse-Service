package com.example.movie_verse_service.model;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private List<Movie> favoriteMovies = new ArrayList<>();

    public User(String email) {
        this.email = email;
    }

    // Getters and Setters for email and favoriteMovies
    public void addFavoriteMovie(Movie movie) {
        favoriteMovies.add(movie);
    }

    public void removeFavoriteMovie(Movie movie) {
        favoriteMovies.remove(movie);
    }
}