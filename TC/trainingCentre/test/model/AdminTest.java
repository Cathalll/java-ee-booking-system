/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.AdminDAO;
import model.dao.mysql.MySQLAdminDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class AdminTest {
    
    public AdminTest() {
    }
    
    private static AdminDAO adminDAO;
    @Before
    public void setUp() {
        
        adminDAO = new MySQLAdminDAO();
        
        
    }
    
    @Test
    public void getAll() throws Exception{
        
         System.out.println("test getAll");
         
         List<Admin> expResult = null;
         List<Admin> result = adminDAO.getAll();
         
         assertNotEquals("adminDAO.getAll() is !null",expResult, result);
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
