/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.LoginBean;
import model.dao.AddressDAO;
import model.dao.UserDAO;
import model.dao.UserRoleDAO;
import model.dao.mysql.MySQLAddressDAO;
import model.dao.mysql.MySQLUserDAO;
import model.dao.mysql.MySQLUserRoleDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class RegisterUserTest {
    
    public RegisterUserTest() {
    }
     private static  AddressDAO addressDAO;
     private static UserDAO userDAO;
     private static LoginBean loginBean;
     private static UserRole ur;
     private static UserRoleDAO userRoleDAO;
     private static User u;
    
    
    @Before
    public void setUp() {
        
        ///change Connection dburl to test url
        
        addressDAO = new MySQLAddressDAO();
        userDAO = new MySQLUserDAO();
        loginBean = new LoginBean();
        ur = new UserRole();
        userRoleDAO =  new MySQLUserRoleDAO();
        u = new User();

    }
    
    @Test
    public void testRegisterNewUser() throws Exception{
    //testing registration of a new User and Address
    
   
        
        ur = userRoleDAO.get(4).get();
        
        loginBean.setPassword("123456");
        
        u.setUserRole(ur);
        u.setName("John");
        u.setSurname("Smarcher");
        u.setEmail("johnS@gmail.com");
        u.setTelephone("01-84343847");
        
        Status studentStatus = new Status();
      //'enrolled' is that Status by default
        studentStatus.setId(4);
        
        System.out.println("method returns id of most recently-added User, which is then used for the creation of the Address");
                
        int lastUserID = userDAO.save(u, loginBean, studentStatus);
        
          Address a = new Address();

        a.setStreet("Lane");
        a.setHouseNumber("47a");
        a.setCity("Limerick");
        a.setPostcode("Testtest");

        int status = addressDAO.save(a,lastUserID);
 
        assertTrue("if status is equal to one, User + Address added successfully", status ==1);
        
      
    }
    @After
    public void tearDown() {
        
        //change Connection dburl to live url
    }

   
}
