/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.System.out;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class UpdateItemPriceTest {
    
    public UpdateItemPriceTest() {
    }
    
         private Session session;
        private Item item;
        private Status status;
        private Status zeroStatus;
        private Price price;
        private Training training;
        private double initialPrice;
        private double finalPrice;
        private Item item1;
        private Item item2;
        private Item item3;

        private double finalPriceNoDiscount;
        
        private double item2FinalPrice;
        private double item3FinalPrice;
    
    @Before
    public void setUp() {
        
        session = new Session();
        status = new Status();
        price = new Price();
        item = new Item();
        training = new Training();
        item1 =  new Item();
        zeroStatus = new Status();
        item2 = new Item();
        item3 = new Item();
        
        System.out.println("Applying a 10% discount on a price of €100 to get the finalPrice of our Item");
        price.setAmount(100);
        status.setDiscount(10);
        training.setPrice(price);
        session.setTraining(training);
        
        item.setSession(session);
        
        initialPrice = item.getSession().getTraining().getPrice().getAmount();
        
        System.out.println("initialPrice = " +initialPrice);
        
        //setting same Session to item1, for testing a status with a 0% discount
        
        item1.setSession(session);
        zeroStatus.setDiscount(0);
        
        
        //testing hasDiscount items
        item2.setSession(session); // 
        item3.setSession(session);
        
        
        
        
   
       
    }
    
    @Test 
    public void testUpdatePriceItemFromStatus() throws Exception{
        System.out.println("price of Item before updating from Status = " + initialPrice);
        
        item.updatefinalPrice(status);
        
        finalPrice = item.getFinalPrice();
        
        System.out.println("price of Item after updating from Status  = " + finalPrice);
        
        assertTrue("finalPrice < initialPrice",finalPrice < initialPrice);
        
    }
    
    @Test
    public void testUpdatePriceItemFromZeroStatus() throws Exception{
        
        System.out.println("price of Item before updating from zeroStatus = " + initialPrice);
        
            item1.updatefinalPrice(zeroStatus);
            
            finalPriceNoDiscount = item1.getFinalPrice();
            
            System.out.println("finalPriceNoDiscount after discouht of 0% applied: " +finalPriceNoDiscount);
            
            assertTrue("initial price and price after 0% discount are the same", initialPrice == finalPriceNoDiscount);
        

        
    }
    @Test
    public void testHasDiscount() throws Exception{
        
//        item2.updatefinalPrice(status);
//        
//        item2FinalPrice = item2.getFinalPrice();
        
        boolean hasADiscount = item2.hasDiscount(status);
        

        assertTrue("Status status has an applicable discount",hasADiscount);
        
        
        
    }
    @Test
    public void testHasNoDiscount() throws Exception{
        boolean hasNoDiscount = item2.hasDiscount(zeroStatus);
        assertFalse("Status zeroStatus has an applicable discount", hasNoDiscount);
    }
            
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
