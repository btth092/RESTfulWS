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
public class UserLinkListResource extends Resource{
	private List<Link> links = new ArrayList<>();
	private List<Link> userLinks = new ArrayList<>();
	
	@XmlElement(name = "link")
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void addLink(Link link) {
		this.links.add(link);
	}
	
	@XmlElement(name="data")
	public List<Link> getUserLinks() {
		return userLinks;
	}

	public void setUserLinks(List<Link> userLinks) {
		this.userLinks = userLinks;
	}
	
	public void addUserLink(Link link){
		this.userLinks.add(link);
	}
}