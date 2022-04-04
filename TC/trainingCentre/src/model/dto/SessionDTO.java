/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import model.Session;

/**
 *
 * @author Cathal
 */
public class SessionDTO {
    
    private Session session;
    private String color;

    public SessionDTO(Session session, String color) {
        this.session = session;
        this.color = color;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
    
    
    
}
