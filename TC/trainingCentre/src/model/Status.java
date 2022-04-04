/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Cathal
 */
public class Status {
    
    public Status(){}
    
    private int id;
    private String name;
    private int discount;
    private boolean enabled;

    public Status(int id, String name, int discount, boolean enabled) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Status{" + "id=" + id + ", name=" + name + ", discount=" + discount + ", enabled=" + enabled + '}';
    }
    
    
    
}
