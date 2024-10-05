package com.example.movie_verse_service.serviceTest;

import com.example.movie_verse_service.model.Actor;
import com.example.movie_verse_service.model.Category;
import com.example.movie_verse_service.model.Movie;
import com.example.movie_verse_service.model.User;
import com.example.movie_verse_service.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

public class MovieServiceTest {

    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        movieService = new MovieService();
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
    public void testAddAndFindMovieById() {
        Movie movie = createMovie();
        movieService.addMovie(movie);

        Optional<Movie> foundMovie = movieService.getMovieById(1L);
        assertTrue(foundMovie.isPresent());
        assertEquals("Inception", foundMovie.get().getTitle());
    }

    @Test
    public void testFindMovieByInvalidId() {
        Movie movie = createMovie();
        movieService.addMovie(movie);
        Optional<Movie> foundMovie = movieService.getMovieById(999L);
        assertFalse(foundMovie.isPresent());
    }

    @Test
    public void testGetAllMovies() {

        Movie movie1 = createMovie();
        Movie movie2 = createSecondMovie();
        movieService.addMovie(movie1);
        movieService.addMovie(movie2);

        List<Movie> allMovies = movieService.getAllMovies();
        assertEquals(2, allMovies.size());
        assertTrue(allMovies.contains(movie1));
        assertTrue(allMovies.contains(movie2));
    }

    @Test
    public void testSearchMovies() {

        Movie movie1 = createMovie();
        Movie movie2 = createSecondMovie();
        movieService.addMovie(movie1);
        movieService.addMovie(movie2);

        List<Movie> results = movieService.searchMovies("Inception");
        assertEquals(1, results.size());
        assertEquals("Inception", results.get(0).getTitle());

        results = movieService.searchMovies("The Dark Knight");
        assertEquals(1, results.size());
        assertEquals("The Dark Knight", results.get(0).getTitle());

        results = movieService.searchMovies("Leonardo DiCaprio");
        assertEquals(1, results.size());
        System.out.println("Actor's name: "+ results.get(0).getCast().get(0).getName());
        assertEquals("Leonardo DiCaprio", results.get(0).getCast().get(0).getName());
    }
}

