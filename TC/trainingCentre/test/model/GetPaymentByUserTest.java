/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class GetPaymentByUserTest {
    
    public GetPaymentByUserTest() {
    }
    
    
    public static Payment payment1;
    public static Payment payment2;
   public static Payment payment3;
   public static User user1;
   public static List<Payment> payments;
   
    
    @Before
    public void setUp() {
       payment1 = new Payment();
       payment2 = new Payment();
       payment3 = new Payment();
       user1 = new User();
       
       //set user1 as attr. of payment1
       
       payment1.setUser(user1);
       
       //add Payments to ArrayList
       
      payments = new ArrayList<Payment>();
       
       payments.add(payment1);
       payments.add(payment2);
       payments.add(payment2);
       
        System.out.println("payments.size() =" + payments.size());
       
       
              
        
    }
    
       @Test
       public void userEqualsTest() throws Exception{
           boolean test = false;
           for(Payment p: payments){
               if(p.getUser() == user1)
                   System.out.println("bollean test = true");
               test =true;
              
           }
           
           
           assertTrue("boolean test is equal to true", test);
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
