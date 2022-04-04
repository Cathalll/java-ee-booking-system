/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.StatusDAO;
import model.dao.mysql.MySQLStatusDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class StatusTest {
    
    public StatusTest() {
        
    }
    
    private static StatusDAO statusDAO;
    
    @Before
    public void setUp() {
        
        statusDAO = new MySQLStatusDAO();
    }
    
    @Test
    public void testGetAll() throws Exception{
        System.out.println("test getAll ");
        
        List<Status> expResult = null;
        List<Status> result  = statusDAO.getAll();
        
        System.out.println("statusDAO.getAll().size() = " + result.size());
        
        assertNotEquals("statusDAO.getAll() is != null",expResult, result);
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
