/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hegco
 */
@XmlRootElement
public class NotificationLinkListResource {
    private List<Link> links;
    private List<Notification> notifications;
    @XmlElement(name="link")
    public List<Link> getLinks(){return links;}
    public void setLinks(List<Link> links){this.links=links;}
    
    public void addLink(Link link){links.add(link);}
    @XmlElement(name="data")
    public List<Notification> getNotification(){return notifications;}
    public void setNotifications(List<Notification> notifications){this.notifications=notifications;}
            
}
