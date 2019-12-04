/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.service;

import java.nio.file.Path;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hegco
 */
public interface FileService {
    public Path getFile(String fileName);
    public List<Path> walkDir(Path path, List<Path>paths);
    public boolean uploadFile(MultipartFile file, String name, String path);
    public String delete(String path);
    
}
