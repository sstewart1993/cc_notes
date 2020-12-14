package com.codeclan.example.files_and_folders_solution.controllers;

import com.codeclan.example.files_and_folders_solution.models.User;
import com.codeclan.example.files_and_folders_solution.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> foundUsers = userRepository.findAll();
        return new ResponseEntity<>(foundUsers, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        Optional<User> foundUser = userRepository.findById(id);
        return new ResponseEntity(foundUser, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> postUser(@RequestBody User user){
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
