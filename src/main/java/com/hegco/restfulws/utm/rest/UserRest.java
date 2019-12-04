/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.rest;

import com.hegco.restfulws.utm.model.Link;
import com.hegco.restfulws.utm.model.OptionsDoc;
import com.hegco.restfulws.utm.model.User;
import com.hegco.restfulws.utm.model.UserResource;
import com.hegco.restfulws.utm.model.form.UserForm;
import com.hegco.restfulws.utm.rest.exception.ResourceNotFoundException;
import com.hegco.restfulws.utm.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author hegco
 */
@Controller
@RequestMapping("/api/v1/user")
public class UserRest {
    @Autowired
    UserService userService;
    
   
    

    @RequestMapping(method = RequestMethod.GET,headers={"content-type=text/json","content-type=application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getUsersJson() {
        List<User> users = userService.getAll();
        List<String> names= new ArrayList();
        
        for (User p : users) {
            
            names.add(p.getNombre());
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("names", names);
        response.put("users", users);
        return response;
    }

    
    @RequestMapping(value="/user/{name}",method=RequestMethod.GET, headers = {"content-type=text/xml","content-type=application/xml"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
	public UserResource getUserXML(@PathVariable("name") String name) {
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentServletMapping();

		if (userService.getUser(name) == null){
			throw new ResourceNotFoundException();}
                else{

		UserResource resource = new UserResource();
		resource.addLink(new Link(builder.path("/user/").build().toString(), "user"));
		resource.addLink(new Link(builder.path(name).build().toString(), "self"));
		resource.setUser(userService.getUser(name));
		return resource;}
}
    @RequestMapping(method=RequestMethod.DELETE)
    
    public void deleteUser(@RequestParam String name){
    userService.deleteUser(name);
    }
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<User> create(@RequestBody UserForm userForm){
        User newUser= userService.createUser(userForm.getNombre(), userForm.getApellido(), userForm.getEdad(), userForm.getSexo()
                , userForm.getEstatura(), userForm.getPeso(), userForm.getIMC());
        String uri=ServletUriComponentsBuilder.fromCurrentServletMapping().path("/user/"+userForm.getNombre()).buildAndExpand(newUser.getNombre()).toString();
        
        HttpHeaders headers= new HttpHeaders();
        headers.add("Location", uri);
        return  ResponseEntity.accepted().headers(headers).body(newUser);
        
        
    }
    
    @RequestMapping(value="/user/{name}",method=RequestMethod.POST,headers={"Content-Type=application/json"})
    public void update(@PathVariable("name") String name,@RequestBody UserForm userForm){
    User user= userService.getUser(name);
    if(user!=null){
        User newUser= new User(userForm.getNombre(), userForm.getApellido(), userForm.getEdad(), userForm.getSexo()
                , userForm.getEstatura(), userForm.getPeso(), userForm.getIMC());
        newUser.setFecha(""+new Date());
    userService.updateUser(newUser);
    }
    else{
    throw new ResourceNotFoundException();
    }
    
    }
    @RequestMapping( method = RequestMethod.OPTIONS)
	public ResponseEntity<?> userIndex() {
		HttpHeaders h = new HttpHeaders();
		h.add("Allow", "OPTIONS,GET,POST");
		
		HashMap<HttpMethod, String> methods = new HashMap<>();
		methods.put(HttpMethod.GET, "Lists users available.");
		methods.put(HttpMethod.OPTIONS, "Resource documentation.");
		methods.put(HttpMethod.POST, "Creates specified user with form (nombre,apellido,edad,sexo,estatura,peso,IMC).");
		
		OptionsDoc options = new OptionsDoc();
		options.setMethods(methods);
		
		return ResponseEntity.accepted().headers(h).body(options.getMethods());
	}

	@RequestMapping(value = "/user/{name}", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> userOptions(@PathVariable("name") String name) {
		if (userService.getUser(name) == null){
			throw new ResourceNotFoundException();}
                else{
		HttpHeaders h = new HttpHeaders();
		h.add("Allow", "OPTIONS,GET,PUT,DELETE");
		
		HashMap<HttpMethod, String> methods = new HashMap<>();
		methods.put(HttpMethod.GET, "Displays specified user's information.");
		methods.put(HttpMethod.OPTIONS, "Resource documentation.");
		methods.put(HttpMethod.PUT, "Updates specified user's information with form (nombre,apellido,edad,sexo,estatura,peso,IMC).");
		methods.put(HttpMethod.DELETE, "Deletes specified user.");
		
		OptionsDoc options = new OptionsDoc();
		options.setMethods(methods);
		
		return ResponseEntity.accepted().headers(h).body(options.getMethods());}
	}
    
    
}
