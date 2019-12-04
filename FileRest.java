/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.rest;

import com.hegco.restfulws.utm.model.OptionsDoc;
import com.hegco.restfulws.utm.rest.exception.ResourceNotFoundException;
import com.hegco.restfulws.utm.service.FileService;
import com.hegco.restfulws.utm.view.DownloadView;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


/**
 *
 * @author hegco
 */
@Controller
@RequestMapping(value = "/api/v1/file")
public class FileRest {

    @Autowired
     FileService fileService;

    @RequestMapping(method = RequestMethod.OPTIONS)
    
    public  ResponseEntity<?> showOptions() {
         OptionsDoc options= new OptionsDoc();
        options.getMethods().put(HttpMethod.DELETE, "Deletes specified file in parameter 'path'");
        options.getMethods().put(HttpMethod.GET, "Downloads specified file in parameter 'path'");
        options.getMethods().put(HttpMethod.OPTIONS, "Resource documentation");
        options.getMethods().put(HttpMethod.POST, "Uploads specified file in parameter 'path'");
        HttpHeaders h = new HttpHeaders();
        h.add("allow", "GET,POST,OPTIONS,DELETE");
        return ResponseEntity.accepted().headers(h).body(options.getMethods());
            }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public View downloadFile(@RequestParam String path) {

        try {
            Path file = fileService.getFile(path);
            return new DownloadView(file.getFileName().toString(), Files.probeContentType(file), Files.readAllBytes(file));

        } catch (Exception e) {
            throw new ResourceNotFoundException();

        } 

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteFile(@RequestParam String path) {
        try {
            Path file = fileService.getFile(path);
            String fileName = file.getFileName().toString();
            fileService.delete(path);
            return fileName;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException();
        }

    }

    @RequestMapping(method = RequestMethod.POST,headers={"content-type= multipart/form-data"})
    
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file, @RequestParam String name, @RequestParam String dir) {
        if (!file.isEmpty()) {
            
            fileService.uploadFile(file, name, dir);
            HttpHeaders h = new HttpHeaders();
            h.add("Location",ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/file/?path=").build().toString()+ dir + "/" + name);
            
            return ResponseEntity.ok().headers(h).body(HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }

    }

}
