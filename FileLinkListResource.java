/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hegco
 */
@XmlRootElement
public class FileLinkListResource extends Resource {
    
    private List<Link> links= new ArrayList();
    private List<File> files= new ArrayList();
    
    @XmlElement(name="link")
    @Override
    public List<Link> getLinks() {
        return links;
    }

    @Override
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
  public void addLink(Link link){links.add(link);}

    public void setFiles(List<File> files) {
        this.files = files;
    }
    @XmlElement(name="file")
    public List<File> getFiles(){
    return files;}
    
    
    
}
