package com.ebac.sprinboot.sevice;

import com.ebac.sprinboot.dto.User;
import com.ebac.sprinboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /*En esta sección de servicio se deben de colocar la validaciones de negocio para
    cada métod0 utilizado de la implementación de la interfaz de repositorio (SAVE, UPDATE, FINDBYID, ...)*/
    public User CreateUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getById(int id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void UpdateUser(User user) {
        userRepository.save(user);
    }

    public void DeleteUser(int id) {
        userRepository.deleteById(id);
    }
}
