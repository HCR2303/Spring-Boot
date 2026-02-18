package com.ebac.sprinboot.controller;

import com.ebac.sprinboot.dto.User;
import com.ebac.sprinboot.sevice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/usuarios")
    public ResponseWrapper< List<User>> getUsers() {
        ResponseEntity<List<User>> usuarios=ResponseEntity.ok(userService.getAllUsers());
        if  (usuarios.getBody().size()>0){
            return new ResponseWrapper<>(true,"Se obtuvo lista de users",usuarios);
        }else{
            return new ResponseWrapper<>(false,"No existen usuarios", usuarios);
        }

    }

    @GetMapping("/usuarios/{id}")
    public ResponseWrapper<User> getUser(@PathVariable int id) {
        Optional<User> userFound= userService.getById(id);

        if (userFound.isPresent()) {
            log.info("Obteniendo usuario {}", userFound.get().getName());
            ResponseEntity<User> RE= ResponseEntity.ok(userFound.get());
            return new ResponseWrapper<>(true,"Se obteniendo usuario",RE);
        }else {
            log.warn("Buscando ID:{}", id);
            ResponseEntity<User> RE= ResponseEntity.notFound().build();
            return new ResponseWrapper<>(false,"No se encontro el usuario",RE);
        }
    }

    @PostMapping("/usuarios")
    public ResponseWrapper<User> createUser(@RequestBody User user) throws URISyntaxException {
        userService.CreateUser(user);
        String name= user.getName();
        log.info("Creando usuario {}", name);
        ResponseEntity<User> userRE= ResponseEntity.created(new URI("/usuarios")).build();
        return new ResponseWrapper<>(true,"Se creo usuario" + name, userRE);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseWrapper<User> updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> userFound= userService.getById(id);

        if(userFound.isPresent()){
            user.setIdUsuario(userFound.get().getIdUsuario());
            userService.UpdateUser(user);
            log.info("Actualizando usuario {}", user.getName());
            ResponseEntity<User> RE= ResponseEntity.ok(user);
            return new ResponseWrapper<>(true,"Usuario actualizado",RE);
        }else{
            log.warn("No se pudo actualizar el usuario con ID:{}",id);
            ResponseEntity<User> RE= ResponseEntity.notFound().build();
            return new ResponseWrapper<>(false,"Usuario no encontrado",RE);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseWrapper<Void> deleteUser(@PathVariable int id) {
        userService.DeleteUser(id);
        log.info("Eliminando usuario ID:{}", id);
        ResponseEntity<Void> RE= ResponseEntity.noContent().build();
        return new ResponseWrapper<>(true,"Usuario eliminado",RE);
    }
}
