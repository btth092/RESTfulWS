/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.service;

import com.hegco.restfulws.utm.model.Notification;
import com.hegco.restfulws.utm.repository.NotificationRepository;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 *
 * @author hegco
 */
@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired 
    MailSender mailSender;
    @Autowired
    NotificationRepository notificationRepository;
        
    private static final Logger logger=LogManager.getLogger();
    @Override
    public List<Notification> getNotifications() {
        return notificationRepository.getNotifications();
        
    }
    
    
    @Async
    @Override
    public Notification notify(String subject, String message, List<String> toAddress, List<String> ccAddress) {
        
      String status;
       StopWatch stopwatch = new StopWatch();
       stopwatch.start();
       String thread= Thread.currentThread().getName();
       logger.info("{} started subject={}, message={}, toAddress{}, ccAddress={}",thread,subject,message,toAddress,ccAddress);
      
       try{ SimpleMailMessage email= new SimpleMailMessage();
       email.setTo(String.join(",", toAddress));
       email.setSubject(subject);
       email.setText(message);
       mailSender.send(email);
       status="Sent";
       }catch (Exception e){
           status="error";
       logger.error(e.getMessage());
       }
       stopwatch.stop();
       logger.info("{} took {} secs",thread,stopwatch.getTotalTimeSeconds());
       return        notificationRepository.createNotification(subject, message, toAddress, ccAddress,status);

       
       
    }
    
}
