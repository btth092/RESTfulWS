/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.repository;

import com.hegco.restfulws.utm.model.Notification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hegco
 */
@Repository("notificationRepository")
public class NotificationRepositoryImpl implements NotificationRepository{
    private static Map<String,Notification> notificationDB= new HashMap();

    @Override
    public List<Notification> getNotifications() {
        List<Notification> notification= new ArrayList();
        notification.addAll(notificationDB.values());
        return notification;
    }

    @Override
    public Notification getNotification(String id) {
        return notificationDB.get(id);
    }

    @Override
    public Notification createNotification(String subject, String message, List<String> toAddress, List<String> ccAddress,String status) {
        Notification notification= new Notification(subject,message,toAddress,ccAddress,status);
        
        addNotification(notification);
        return notification;
    }

    @Override
    public void addNotification(Notification notification) {
    notificationDB.put(notification.getMessadeId(), notification);
       }

    @Override
    public Notification updateNotification(String id, Notification notification) {
        notificationDB.put(id, notification);
        return notification;
    }
    
}
