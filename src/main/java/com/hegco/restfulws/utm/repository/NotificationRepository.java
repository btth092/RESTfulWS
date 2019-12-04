/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.repository;

import com.hegco.restfulws.utm.model.Notification;
import java.util.List;

/**
 *
 * @author hegco
 */
public interface NotificationRepository {
    public List<Notification> getNotifications();
    public Notification getNotification(String id);
    public Notification createNotification(String subject, String message, List<String> toAddress,List<String> ccAddress,String status);
    public Notification updateNotification(String id,Notification notification);
    public void addNotification(Notification notification);
}
