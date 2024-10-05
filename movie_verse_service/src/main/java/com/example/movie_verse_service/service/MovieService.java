package com.example.movie_verse_service.service;

import com.example.movie_verse_service.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private List<Movie> movies;

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public Optional<Movie> getMovieById(long id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst();
    }
    public List<Movie> getAllMovies() {
        return movies;
    }

    public List<Movie> searchMovies(String query) {
        return movies.stream()
                .filter(movie -> movie.getTitle().contains(query)
                        || movie.getCategoryList().stream().anyMatch(category -> category.getCategoryName().contains(query))
                        || movie.getCast().stream().anyMatch(actor -> actor.getName().contains(query)))
                .sorted((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle()))
                .collect(Collectors.toList());
    }

    public Movie getMovieDetails(Long movieId) {
        return movies.stream()
                .filter(movie -> Objects.equals(movie.getId(), movieId))
                .findFirst().orElse(null);
    }


}

