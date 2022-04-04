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
public class PaymentTest {
    
    public PaymentTest() {
    }
        
    private static Payment payment;
    private static User user;
    private static Session session;
    private static PaymentDAO paymentDAO;
    private static PaymentType paymentType;
    
    
    
    @Before
    public void setUp() {
        
        payment = new Payment();
        user = new User();
        session = new Session();
        paymentDAO = new MySQLPaymentDAO();
        paymentType = new PaymentType();
        
        
    
        
        
    }
    
    @Test
    public void testSave() throws Exception{
        System.out.println("testing save payment");
        
        user.setId(4); 
        session.setId(3L);
        paymentType.setName("Visa");
        paymentType.setId(2);
        
//        payment.setType(2); 
//        payment.setTypeName("Visa");
        payment.setAmount(70.5);
        payment.setUser(user);
        payment.setSession(session);
        payment.setPaymentType(paymentType);
        
        int status = paymentDAO.save(payment);
        
        assertTrue("status == 1, one row inserted", status ==1);
                
        
    }
    
    
    @After
    public void tearDown() {
    }

    //set dbURL from test db back to live db
}
