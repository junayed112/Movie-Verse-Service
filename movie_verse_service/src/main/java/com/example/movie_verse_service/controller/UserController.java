package com.example.movie_verse_service.controller;
import com.example.movie_verse_service.model.Movie;
import com.example.movie_verse_service.model.User;
import com.example.movie_verse_service.service.UserService;
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
    public User register(@RequestParam String email) {
        return userService.register(email);
    }

    @GetMapping("/{email}/favorites")
    public ResponseEntity<List<Movie>> getFavorites(@PathVariable String email) {
        Optional<User> user = userService.getUser(email);
        return ResponseEntity.ok(user.map(User::getFavoriteMovies).orElse(null));
    }

    @PostMapping("/{email}/favorites")
    public ResponseEntity<String> addFavorite(@PathVariable String email, @RequestBody Movie movie) {
        userService.getUser(email).ifPresent(user -> user.addFavoriteMovie(movie));
        return ResponseEntity.ok("Successfully Added as Favorites");
    }

    @DeleteMapping("/{email}/favorites")
    public ResponseEntity<String> removeFavorite(@PathVariable String email, @RequestBody Movie movie) {
        userService.getUser(email).ifPresent(user -> user.removeFavoriteMovie(movie));
        return ResponseEntity.ok("Successfully Removed From Favorites");
    }
}

