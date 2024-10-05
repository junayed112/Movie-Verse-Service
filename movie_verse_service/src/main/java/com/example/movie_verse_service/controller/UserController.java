package com.example.movie_verse_service.controller;

import com.example.movie_verse_service.dto.RegisterDto;
import com.example.movie_verse_service.model.Movie;
import com.example.movie_verse_service.model.User;
import com.example.movie_verse_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        User user = userService.register(registerDto.getName(), registerDto.getEmail());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<Movie>> getFavorites(@RequestParam String email) {
        List<Movie> favoriteMovies = userService.getFavoriteMovies(email);
        return ResponseEntity.ok(favoriteMovies);
    }

    @PostMapping("/add-favorites")
    public ResponseEntity<String> addFavorite(@RequestParam String email, @RequestParam Long movieId) {
        userService.addMovieToFavorites(email, movieId);
        return ResponseEntity.ok("Successfully Added as Favorites");
    }

    @DeleteMapping("/remove-favorite")
    public ResponseEntity<String> removeFavorite(@RequestParam String email, @RequestBody Long movieId) {
        userService.removeMovieFromFavorites(email, movieId);
        return ResponseEntity.ok("Successfully Removed From Favorites");
    }

    @GetMapping("/favorites/search")
    public ResponseEntity<List<Movie>> searchFavoriteMovies(@RequestParam String email, @RequestParam String query) {
        return ResponseEntity.ok(userService.searchFavoriteMovies(email, query));
    }
}

