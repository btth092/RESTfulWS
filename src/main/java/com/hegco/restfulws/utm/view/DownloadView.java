/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.view;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.View;

/**
 *
 * @author hegco
 */
public class DownloadView implements View{
String fileName,fileContent;
private byte[]bytes;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    public DownloadView(String fileName, String fileContent, byte[] bytes) {
      this.fileName=fileName;
this.fileContent=fileContent;
this.bytes=bytes;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        
        hsr1.setHeader("Content-Disposition","attachment; filename="+this.fileName);
        
        hsr1.setContentType("application/octet-stream");
        
        hsr1.getOutputStream().write(this.bytes);
    }

    @Override
    public String getContentType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
