/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.AddressDAO;
import model.dao.mysql.MySQLAddressDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class AddressTest {
    
    public AddressTest() {
    }
    
    //private static MySQLAddressDAO addressDAO;
    private static AddressDAO addressDAO;
    
    @Before
    public void setUp() {
        
        addressDAO = new MySQLAddressDAO();
        
        
    }
    
    @Test
    public void testGetAll() throws Exception{
        System.out.println("test getAll");
        
        List<Address> expResult = null;
        List<Address> result = addressDAO.getAll();
        
        System.out.println("addressDAO.getAll().size() = " + result.size());
        
        System.out.println("address#0 .toString = " + result.get(0).toString());
        
        assertNotEquals("addressDAO.getAll() is != null",expResult, result);
        
        
        
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
