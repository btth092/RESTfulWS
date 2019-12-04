/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.model;

import java.io.Serializable;
import java.util.HashMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.http.HttpMethod;

/**
 *
 * @author hegco
 */
@XmlRootElement
public class OptionsDoc implements Serializable {
    private HashMap methods= new HashMap<HttpMethod,String>();
    @XmlElement(name="methods")
    public HashMap getMethods() {
        return methods;
    }

    public void setMethods(HashMap methods) {
        this.methods = methods;
    }
}
