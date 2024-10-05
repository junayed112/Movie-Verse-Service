package com.example.movie_verse_service.controller;
import com.example.movie_verse_service.model.Movie;
import com.example.movie_verse_service.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String query) {
        return ResponseEntity.ok(movieService.searchMovies(query));
    }

    @GetMapping("/{title}")
    public ResponseEntity<Movie> getMovieDetails(@PathVariable String title) {
        return ResponseEntity.ok(movieService.getMovieDetails(title));
    }
}

