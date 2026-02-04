package com.ebac.sprinboot.controller;

import com.ebac.sprinboot.dto.User;
import com.ebac.sprinboot.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/usuarios")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        Optional<User> userFound= userService.getById(id);
        return userFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/usuarios")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        userService.CreateUser(user);
        return ResponseEntity.created(new URI("/usuarios")).build();
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> userFound= userService.getById(id);
        if(userFound.isPresent()){
            user.setId(userFound.get().getId());
            userService.UpdateUser(user);
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.DeleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
