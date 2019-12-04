/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.rest;

import com.hegco.restfulws.utm.model.Link;
import com.hegco.restfulws.utm.model.OptionsDoc;
import com.hegco.restfulws.utm.model.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author hegco
 */
@RequestMapping("/api/v1/")
public class IndexRest {
    
    
    @RequestMapping(method=RequestMethod.OPTIONS)
    public ResponseEntity<?> showOptions(){
    OptionsDoc options= new OptionsDoc();
    options.getMethods().put(HttpMethod.GET, "Lists of links available");
        
    HttpHeaders h = new HttpHeaders();
    return ResponseEntity.accepted().headers(h).body(options.getMethods());
    }
    
    
  
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, 
			headers = { "Content-Type=application/json", "Contetn-Type=text/json" })
	public Map<String, Object> indexJson() {
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentServletMapping();
		ArrayList<Link> links = new ArrayList();
		links.add(new Link(builder.path("/").build().toString(), "self"));
		links.add(new Link(builder.path("/user").build().toString(), "user"));
                links.add(new Link(builder.path("/directory").build().toString(), "directory"));
                links.add(new Link(builder.path("/file").build().toString(), "file"));
		HashMap<String, Object> m = new HashMap();
		m.put("_links", links);
		return m;
	}
	
	
	@RequestMapping(method=RequestMethod.GET, 
			headers = { "Content-Type=application/xml", "Contetn-Type=text/xml" })
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Resource indexXml() {
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentServletMapping();
		Resource resource = new Resource();
		resource.addLink(new Link(builder.path("/").build().toString(), "self"));
		resource.addLink(new Link(builder.path("/user").build().toString(), "user"));
                resource.addLink(new Link(builder.path("/directory").build().toString(), "directory"));
                resource.addLink(new Link(builder.path("/file").build().toString(), "file"));
		return resource;
	}
}
