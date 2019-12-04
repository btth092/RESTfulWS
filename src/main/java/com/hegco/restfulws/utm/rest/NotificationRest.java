/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.rest;

import com.hegco.restfulws.utm.model.Link;
import com.hegco.restfulws.utm.model.NotificationLinkListResource;
import com.hegco.restfulws.utm.model.OptionsDoc;
import com.hegco.restfulws.utm.model.form.NotificationForm;
import com.hegco.restfulws.utm.service.NotificationService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author hegco
 */
@Controller
@RequestMapping(value="/api/v1/notify")
public class NotificationRest {
    @Autowired
     NotificationService notificationService;
    
    private static final Logger logger= LogManager.getLogger();
    @RequestMapping(method=RequestMethod.OPTIONS)
    
    
    @ResponseBody
    public ResponseEntity<?> showOptions(){
    HttpHeaders h= new HttpHeaders();
    h.add("Allow","GET,OPTIONS,POST");
    OptionsDoc options= new OptionsDoc();
        options.getMethods().put(HttpMethod.GET, "List notifications submitted");
        options.getMethods().put(HttpMethod.OPTIONS, "Resource documentation");
        options.getMethods().put(HttpMethod.POST, "Submits notifications to send");
        return ResponseEntity.accepted().headers(h).body(options.getMethods());
    }
    
    @RequestMapping(method=RequestMethod.GET,headers={"content-type=text/json","content-type=application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> getNotificationsJSON (){
        List<Link> _links= new ArrayList();
        _links.add(new Link(ServletUriComponentsBuilder.fromCurrentServletMapping().path("/").build().toString(),"api"));    
   _links.add(new Link(ServletUriComponentsBuilder.fromCurrentServletMapping().path("/").build().toString(),"api")); 
        Map<String,Object>response= new HashMap();
        response.put("_links", _links);
        response.put("data",notificationService.getNotifications());
        return response;
        
    }
    @RequestMapping(method=RequestMethod.GET, headers = {"content-type=text/xml","content-type=application/xml"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public NotificationLinkListResource getNotificatonsXML(){
    List<Link>_links=new ArrayList();
    _links.add(new Link(ServletUriComponentsBuilder.fromCurrentServletMapping().path("/").build().toString(),"api"));    
   _links.add(new Link(ServletUriComponentsBuilder.fromCurrentServletMapping().path("/").build().toString(),"api"));    
   NotificationLinkListResource notificationLinksListResource= new NotificationLinkListResource();
   notificationLinksListResource.setLinks(_links);
notificationLinksListResource.setNotifications(notificationService.getNotifications());
return notificationLinksListResource;}
    
    
    @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> notify(@RequestBody NotificationForm form) {
		try {
			List<String> toAddress = Arrays.asList(form.getToAddress().split(";"));
			List<String> ccAddress = new ArrayList<>();
			if(form.getCcAddress() != null)
				ccAddress = Arrays.asList(form.getCcAddress().split(";"));
			notificationService.notify(form.getSubject(), form.getMessage(), toAddress, ccAddress);
			return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.accepted().body(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
    }
