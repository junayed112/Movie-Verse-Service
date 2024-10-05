package com.example.movie_verse_service.serviceTest;

import com.example.movie_verse_service.model.Actor;
import com.example.movie_verse_service.model.Category;
import com.example.movie_verse_service.model.Movie;
import com.example.movie_verse_service.model.User;
import com.example.movie_verse_service.service.MovieService;
import com.example.movie_verse_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        movieService = new MovieService();
        userService = new UserService(movieService);
    }

    public User registerUser(){
        return userService.register("Junayed Hossain", "junayed.hossain@gmail.com");
    }

    public Movie createMovie(){
        return new Movie(1L, "Inception", List.of(new Actor("Leonardo DiCaprio", "lead role")),
                List.of(new Category("Sci-Fi")), "2010", 160000000, 8.8);
    }
    public Movie createSecondMovie(){
        return new Movie(2L, "The Dark Knight", List.of(new Actor("Christian Bale", "lead role")),
                List.of(new Category("Action")), "2008", 185000000, 9.0);
    }

    @Test
    public void testRegisterUser() {
        User user = registerUser();
        assertNotNull(user);
        assertEquals("Junayed Hossain", user.getName());
        assertEquals("junayed.hossain@gmail.com", user.getEmail());
    }


    @Test
    public void testRegisterUserWithDuplicateEmail() {
        registerUser();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            registerUser();
        });

        assertEquals("Email already exists", exception.getMessage());
    }

    @Test
    public void testAddMovieToFavorites() {

        Movie movie = createMovie();
        movieService.addMovie(movie);
        User user = registerUser();

        userService.addMovieToFavorites(user.getEmail(), 1L);
        List<Movie> favoriteMovies = userService.getFavoriteMovies(user.getEmail());

        assertEquals(1, favoriteMovies.size());
        assertEquals("Inception", favoriteMovies.get(0).getTitle());
    }

    @Test
    public void testAddMovieToFavorites_MovieNotFound() {
        User user = registerUser();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.addMovieToFavorites(user.getEmail(), 999L);
        });

        assertEquals("Movie Not Found", exception.getMessage());
    }

    @Test
    public void testRemoveMovieFromFavorites() {
        Movie movie = createMovie();
        movieService.addMovie(movie);
        User user = registerUser();
        userService.addMovieToFavorites(user.getEmail(), 1L);

        userService.removeMovieFromFavorites(user.getEmail(), 1L);
        List<Movie> favoriteMovies = userService.getFavoriteMovies(user.getEmail());

        assertTrue(favoriteMovies.isEmpty());
    }

    @Test
    public void testSearchFavoriteMovies() {
        Movie movie1 = createMovie();
        Movie movie2 = createSecondMovie();
        movieService.addMovie(movie1);
        movieService.addMovie(movie2);
        User user = registerUser();
        userService.addMovieToFavorites(user.getEmail(), 1L);
        userService.addMovieToFavorites(user.getEmail(), 2L);

        List<Movie> results = userService.searchFavoriteMovies(user.getEmail(), "Inception");
        assertEquals(1, results.size());
        assertEquals("Inception", results.get(0).getTitle());
    }
}

