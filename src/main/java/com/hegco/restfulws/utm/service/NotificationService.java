/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.service;

import com.hegco.restfulws.utm.model.Notification;
import java.util.List;

/**
 *
 * @author hegco
 */
public interface NotificationService {
    public List<Notification> getNotifications();
    public Notification notify(String subject, String message, List<String> toAddress,List<String>ccAddress);
}
