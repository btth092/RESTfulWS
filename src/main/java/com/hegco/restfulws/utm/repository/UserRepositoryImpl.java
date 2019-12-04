/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.repository;

import com.hegco.restfulws.utm.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hegco
 */
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository{
    private static Map<String,User> db=new HashMap<String,User>();

    @Override
    public List<User> getDB() {
        ArrayList<User> users=new ArrayList();
        Set<String> nombres= db.keySet();
        for(String n:nombres){
        users.add(db.get(n));
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        db.put(user.getNombre(), user);
    }

    @Override
    public User getUser(String nombre) {
        return db.get(nombre);
    }

    @Override
    public void updateUser(String nombre, User user) {
        db.replace(nombre, user);
    }

    @Override
    public void deleteUser(String nombre) {
        db.remove(nombre);
    }
}
