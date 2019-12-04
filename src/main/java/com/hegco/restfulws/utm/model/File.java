/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hegco
 */
@XmlRootElement
public class File implements Serializable {
    private long serialVersionUID;
    private String name,path,fullPath,sizeBytes;
    private Link _link;

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(long serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public String getName() {
        return name.replaceAll("\\\\", "/");
    }

    public void setName(String noame) {
        this.name = noame;
    }

    public String getPath() {
        return path.replaceAll("\\\\", "/");
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFullPath() {
        return fullPath.replaceAll("\\\\", "/");
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getSizeBytes() {
        return sizeBytes;
    }

    public void setSizeBytes(String sizeBytes) {
        this.sizeBytes = sizeBytes;
    }

    public Link getLink() {
        return _link;
    }

    public void setLink(Link _link) {
        this._link = _link;
    }
}
