package com.example.movie_verse_service.service;

import com.example.movie_verse_service.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> users;

    public User register(String email) {
        User user = new User(email);
//        Optional<User> existingUser = getUser(email);
//        if(existingUser.isPresent()){
//            throw new RuntimeException("This user is already exist");
//        }
        users.add(user);
        return user;
    }

    public Optional<User> getUser(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}

