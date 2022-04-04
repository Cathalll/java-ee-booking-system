/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Cathal
 */
public class Item {
    
    //default constructor
    public Item(){}
    
    private double finalPrice;
    private Session session;

    ///// methods
    
    
    public void updatefinalPrice(Status status){
        int percentDiscount = status.getDiscount();
        
        double basePrice = this.session.getTraining().getPrice().getAmount();
        
        double discountAmount = basePrice /100 * percentDiscount;
        
        double finPrice = basePrice - discountAmount;
        
        this.finalPrice = finPrice;
    }
    
    public boolean hasDiscount(Status status){
        
        boolean hasDiscount = false;
        
        if(status.getDiscount() >0){
            hasDiscount = true;
        }else{
            hasDiscount = false;
        }
        
        
        return hasDiscount;
    }
    
    public boolean matchesSession(Session session){
        boolean matchesSession = false;
        
        if(this.session == session){
            matchesSession = true;
        }
        
        return matchesSession;
    }
    
        public boolean matchesItemInList(List<Item> cart){
        boolean matchesItem = false;
        
        if(cart == null){
            matchesItem = false;
            
        }else if(cart != null){
            for(Item i:cart){
                if(this.equals(i)){
                    matchesItem = true;
                
            }
        }
  
    }
        
          return matchesItem;
    
        }
    
    
    ///constructor, getter and setter, etc
    public Item(double finalPrice, Session session) {
        this.finalPrice = finalPrice;
        this.session = session;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    
    
    
    
    
    
}
