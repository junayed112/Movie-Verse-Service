package com.example.movie_verse_service.service;

import com.example.movie_verse_service.model.Movie;
import com.example.movie_verse_service.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    private final MovieService movieService;

    public UserService(MovieService movieService) {
        this.movieService = movieService;
    }

    public User register(String name, String email) {
        Boolean status = isEmailExists(email);
        if(status){
            throw new IllegalArgumentException("Email already exists");
        }
        else {
            User user = new User(name, email);
            users.add(user);
            return user;
        }
    }

    private boolean isEmailExists(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    public Optional<User> getUser(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public void addMovieToFavorites(String email, Long movieId) {
        Optional<Movie> existingMovie = movieService.getMovieById(movieId);
        if(existingMovie.isPresent()) {
            Movie movie = existingMovie.get();
            getUser(email).ifPresent(user -> user.addFavoriteMovie(movie));
        }
        else{
            throw new IllegalArgumentException("Movie Not Found");
        }
    }

    public void removeMovieFromFavorites(String email, Long movieId) {
        Optional<Movie> existingMovie = movieService.getMovieById(movieId);
        if(existingMovie.isPresent()) {
            Movie movie = existingMovie.get();
            getUser(email).ifPresent(user -> user.removeFavoriteMovie(movie));
        }
        else{
            throw new IllegalArgumentException("Movie Not Found");
        }
    }

    public List<Movie> getFavoriteMovies(String email) {
        return getUser(email).map(User::getFavoriteMovies).orElse(null);
    }

    public List<Movie> searchFavoriteMovies(String email, String query) {
        return getUser(email)
                .map(user -> user.getFavoriteMovies().stream()
                        .filter(movie -> movie.getTitle().contains(query)
                                || movie.getCast().stream().anyMatch(actor -> actor.getName().contains(query))
                                || movie.getCategoryList().stream().anyMatch(c -> c.getCategoryName().contains(query)))
                        .sorted((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle()))
                        .collect(Collectors.toList()))
                .orElse(null);
    }
}

