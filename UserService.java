/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.service;

import com.hegco.restfulws.utm.model.User;
import com.hegco.restfulws.utm.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author hegco
 */
public interface UserService {
public List<User> getAll();
public User getUser(String nombre);
public User createUser(String nombre,String apellido,String edad,String sexo,String estatura,String peso, String IMC);
public void deleteUser(String nombre);  
public void updateUser(User user);
    
    
}
