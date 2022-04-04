/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.dao.PaymentDAO;
import model.dao.mysql.MySQLPaymentDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class DeletePaymentTest {
    
    public DeletePaymentTest() {
    }
    private static Payment payment;
    private static User user;
    private static Session session;
    private static PaymentDAO paymentDAO;
           
    
    @Before
    public void setUp() {
        
 
        
        user = new User();
        
        session = new Session();
        
        payment = new Payment();
        
       paymentDAO = new MySQLPaymentDAO();
        
       //deleting payment id=16 from test database


payment.setId(16);  
       
   
       
       
    }
    
    @Test
   public void deletePayment() throws Exception{
       
           int status = paymentDAO.delete(payment);
           
           assertTrue("Status is <0, Payment has been deleted", status > 0);
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
