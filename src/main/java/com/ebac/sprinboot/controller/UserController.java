package com.ebac.sprinboot.controller;

import com.ebac.sprinboot.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/usuarios")
    public List<User> getUsers() {
        return List.of(new User());
    }

    @GetMapping("/usuarios/{id}")
    public User getUser(@PathVariable int id) {
        return new User();
    }

    @PostMapping("/usuarios")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        return ResponseEntity.notFound().build();
    }
}
