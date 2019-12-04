/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hegco
 */
@Service
public class FileServiceImpl implements FileService{

    @Override
    public Path getFile(String fileName) {
        Path file= Paths.get(fileName);
        return file;
    }

    @Override
    public List<Path> walkDir(Path path, List<Path> paths) {
        try{
            DirectoryStream<Path> all= Files.newDirectoryStream(path);
            for(Path p : all){
            if(Files.isDirectory(p)){
                walkDir(p,paths);
            }
            paths.add(p);
            }
        }catch(Exception e){}
        return paths;
    }

    @Override
    public boolean uploadFile(MultipartFile file, String name, String path) {
        boolean check=false;
        try{
            Path filePath=Paths.get(path);
            if(Files.notExists(filePath)){Files.createDirectory(filePath);}
            BufferedOutputStream bo= new BufferedOutputStream(new FileOutputStream(new File(filePath.toString()+
                    File.separator+name)));
            FileCopyUtils.copy(file.getInputStream(),bo);
            bo.close();
            check=true;
        }catch(Exception e){} 
        return check;
    }

    @Override
    public String delete(String path) {
        Path filePath=Paths.get(path);
        if(Files.exists(filePath)){try{Files.delete(filePath);}
        catch(Exception e){}}
        return"File deleted";
    }
    
}
