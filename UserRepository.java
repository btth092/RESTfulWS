/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.repository;

import com.hegco.restfulws.utm.model.User;
import java.util.List;

/**
 *
 * @author hegco
 */
public interface UserRepository {
    
    public List<User> getDB();
    
    
    public void addUser(User user);
    public User getUser(String nombre);
    public void updateUser(String nombre,User user);
    public void deleteUser(String nombre);
}
