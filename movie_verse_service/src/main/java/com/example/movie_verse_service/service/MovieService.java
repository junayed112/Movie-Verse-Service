package com.example.movie_verse_service.service;

import com.example.movie_verse_service.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private List<Movie> movies;

    public List<Movie> searchMovies(String query) {
        return movies.stream()
                .filter(movie -> movie.getTitle().contains(query)
                        || movie.getCategoryList().stream().anyMatch(category -> category.getCategoryName().contains(query))
                        || movie.getCast().stream().anyMatch(actor -> actor.getName().contains(query)))
                .sorted((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle()))
                .collect(Collectors.toList());
    }

    public Movie getMovieDetails(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst().orElse(null);
    }
}

