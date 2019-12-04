/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.service;

import com.hegco.restfulws.utm.model.User;
import com.hegco.restfulws.utm.repository.UserRepository;
import com.hegco.restfulws.utm.repository.UserRepositoryImpl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hegco
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.getDB();
    }
    
    @Override
    public User getUser(String nombre) {
        return userRepository.getUser(nombre);
    }
    
    @Override
    public User createUser(String nombre, String apellido, String edad, String sexo, String estatura, String peso, String IMC) {
        User user = new User(nombre, apellido, edad, sexo, estatura, peso, IMC);
        user.setFecha("" + new Date());
        userRepository.addUser(user);
        return user;
        
    }
    
    @Override
    public void deleteUser(String nombre) {
        userRepository.deleteUser(nombre);
    }
    
    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user.getNombre(), user);
    }
    
}
