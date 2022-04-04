/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.UserRoleDAO;
import model.dao.mysql.CheckProperties;
import model.dao.mysql.MySQLUserRoleDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class UserRoleTest {
    
    public UserRoleTest() {
    }
    
    private static UserRoleDAO userRoleDAO;
    
    @Before
    public void setUp() {
        
        userRoleDAO =  new MySQLUserRoleDAO();
        
        try {
            Connection testConnection = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void getAll() throws Exception{
        
        System.out.println("test getAll");
        
        List<UserRole> expResult = null;
        List<UserRole> result = userRoleDAO.getAll();
        
        System.out.println("userRoleDAO.getAll().size  = " + userRoleDAO.getAll().size());
        
        assertNotEquals("userRoleDAO.getAll() is !null",expResult, result);
    }
    
//    @Test
//    public void 
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
