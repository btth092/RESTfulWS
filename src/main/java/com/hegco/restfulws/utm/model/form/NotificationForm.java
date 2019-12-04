/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.model.form;

import com.hegco.restfulws.utm.model.*;
import java.io.Serializable;
import java.util.List;
import static java.util.UUID.randomUUID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hegco
 */
@XmlRootElement
public class NotificationForm implements Serializable{
    private String toAddress,ccAddress;
    private String subject,message,messadeId,status;
    
    

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessadeId() {
        return messadeId;
    }

    public void setMessadeId(String messadeId) {
        this.messadeId = messadeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
