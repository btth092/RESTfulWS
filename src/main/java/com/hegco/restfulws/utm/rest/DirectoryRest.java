/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.rest;

import com.hegco.restfulws.utm.model.File;
import com.hegco.restfulws.utm.model.FileLinkListResource;
import com.hegco.restfulws.utm.model.Link;
import com.hegco.restfulws.utm.model.OptionsDoc;
import com.hegco.restfulws.utm.service.FileService;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author hegco
 */
@Controller
@RequestMapping(value = "/api/v1/directory")
public class DirectoryRest {

    @Autowired
    private FileService fileService;

    @RequestMapping(method = RequestMethod.OPTIONS)
    @ResponseBody
    public ResponseEntity<?> showOptions() {
        OptionsDoc options = new OptionsDoc();
        options.getMethods().put(HttpMethod.GET, "Lists specified directory components in parameter 'dir'");
        options.getMethods().put(HttpMethod.OPTIONS, "Resource Documentation");

        HttpHeaders h = new HttpHeaders();
        h.add("allow", "GET,OPTIONS");

        return ResponseEntity.accepted().headers(h).body(options.getMethods());

    }

    @RequestMapping(method = RequestMethod.GET, headers = {"content-type=text/json", "content-type=application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getFilesJson(@RequestParam String dir) {
        List<Path> paths = new ArrayList();
        List<File> files = new ArrayList();
        fileService.walkDir(Paths.get(dir), paths);
        for (Path p : paths) {
            File f = new File();
            f.setName(p.getFileName().toString());
            f.setPath(Paths.get(dir).toAbsolutePath().toString());
            f.setFullPath(p.toAbsolutePath().toString());
            f.setSizeBytes(String.valueOf(p.toFile().length()));
            f.setLink(new Link(ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/file/?path=" + p.toAbsolutePath()).build().toString().replaceAll("\\\\", "/"), "download"));

            files.add(f);
        }
        List<Link> _links = new ArrayList<>();
        _links.add(new Link(ServletUriComponentsBuilder.fromCurrentServletMapping().path("/").build().toString().replaceAll("\\\\", "/"), "api"));
        Map<String, Object> response = new HashMap<>();
        response.put("_links", _links);
        response.put("data", files);
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, headers = {"content-type=text/xml", "content-type=application/xml"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public FileLinkListResource getFilesXML(@RequestParam String dir) {
        List<Link> _links = new ArrayList();
        List<Path> paths = new ArrayList();
        List<File> files = new ArrayList();
        fileService.walkDir(Paths.get(dir), paths);
        for (Path p : paths) {
            File f = new File();
            f.setName(p.getFileName().toString());
            f.setPath(Paths.get(dir).toAbsolutePath().toString());
            f.setFullPath(p.toAbsolutePath().toString());
            f.setSizeBytes(String.valueOf(p.toFile().length()));
            f.setLink(new Link(ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/v1/file/fileDownload/?path=" + p.toAbsolutePath()).build().toString().replaceAll("\\\\", "/"), "download"));

            files.add(f);
        }
        _links.add(new Link(ServletUriComponentsBuilder.fromCurrentServletMapping().path("/").build().toString(), "api"));
        _links.add(new Link(ServletUriComponentsBuilder.fromCurrentServletMapping().path("/directory").build().toString(), "self"));

        FileLinkListResource fllr = new FileLinkListResource();
        fllr.setLinks(_links);
        fllr.setFiles(files);
        return fllr;

    }

}
