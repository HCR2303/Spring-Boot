package com.ebac.sprinboot.controller;

import com.ebac.sprinboot.dto.User;
import com.ebac.sprinboot.sevice.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService service;

    @InjectMocks
    UserController userController;

    @Test
    void getUsers() {
        int numUsers = 4;
        List<User> expected = crearUsers(numUsers);
        when(service.getAllUsers()).thenReturn(expected);

        List<User> actual = userController.getUsers();

        assertEquals (numUsers, actual.size());
        assertEquals (expected, actual);
    }

    @Test
    void getUsersEmpty() {

        when(service.getAllUsers()).thenReturn(List.of());

        List<User> actual = userController.getUsers();

        assertTrue(actual.isEmpty());
        verify(service, times(1)).getAllUsers();
    }

    @Test
    void getUser() {
        int idUser = 1;
        Optional<User> expected = Optional.of(crearUsers(idUser).get(0));
        when(service.getById(1)).thenReturn(expected);
        ResponseEntity<User> userRE = userController.getUser(idUser);
        User actualUser = userRE.getBody();

        assertEquals (200,userRE.getStatusCode().value());
        assertEquals ("Nombre 1",actualUser.getName());
    }

    @Test
    void getUserEmpty() {

        Optional<User> expected = Optional.empty();
        when(service.getById(1)).thenReturn(expected);
        ResponseEntity<User> userRE = userController.getUser(1);
        User actualUser = userRE.getBody();

        assertEquals(404,userRE.getStatusCode().value());
        assertTrue (Objects.isNull(actualUser));

    }

    @Test
    void createUser() throws URISyntaxException {

        User expected = crearUsers(1).get(0);

        when(service.CreateUser(expected)).thenReturn(expected);

        ResponseEntity<User> userRE = userController.createUser(expected);
        User actualUser = userRE.getBody();

        assertEquals (201,userRE.getStatusCode().value());
        assertTrue (Objects.isNull(actualUser));
    }

    @Test
    void updateUser() {
        int idUser = 1;
        //Se crea usuario default
        User usuarioDefault = crearUsers(idUser).get(0);

        //Se crea usuario actualizado
        User usuarioActualizado = new User();
        usuarioActualizado.setName("actualizado");

        when(service.getById(idUser)).thenReturn(Optional.of(usuarioDefault));
        doNothing().when(service).UpdateUser(usuarioActualizado);

        ResponseEntity<User> userRE = userController.updateUser((int) idUser, usuarioActualizado);
        User actualUser = userRE.getBody();

        assertEquals (200,userRE.getStatusCode().value());
        assertTrue(Objects.nonNull(actualUser));
        assertEquals ("actualizado",actualUser.getName());

    }

    @Test
    void updateUserNotFound() {
        Integer idUser = 1;
        //Se crea usuario default
        User usuarioDefault = crearUsers(idUser).get(0);

        //Se crea usuario actualizado
        User usuarioActualizado = new User();
        usuarioActualizado.setName("actualizado");

        when(service.getById(idUser)).thenReturn(Optional.empty());

        ResponseEntity<User> userRE = userController.updateUser((int) idUser, usuarioActualizado);
        User actualUser = userRE.getBody();

        assertEquals (404,userRE.getStatusCode().value());
        assertNull(actualUser);
        verify(service, never()).UpdateUser(usuarioActualizado);

    }

    @Test
    void deleteUser() {
        int idUser = 1;

        doNothing().when(service).DeleteUser(idUser);

        ResponseEntity<Void> userRE = userController.deleteUser(idUser);

        assertEquals (204,userRE.getStatusCode().value());
        verify(service, times(1)).DeleteUser(idUser);

    }

    public static List<User> crearUsers(int numUsers) {

        return IntStream.range(1, numUsers + 1).mapToObj(i -> {
            User user = new User();
            user.setId(i);
            user.setName("Nombre " + i);
            user.setPassword("Password " + i);
            user.setEmail(user.getName() + "@mockito.com");
            return user;
        }).collect(Collectors.toList());

    };
}