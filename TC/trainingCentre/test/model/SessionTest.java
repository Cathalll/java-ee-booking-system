/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;
import model.dao.SessionDAO;
import model.dao.mysql.MySQLSessionDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class SessionTest {
    
    public SessionTest() {
    }
    
    private static SessionDAO sessionDAO;
    
    @Before
    public void setUp() {
        
        sessionDAO = new MySQLSessionDAO();
    }
    
    @Test
    public void sessions() throws Exception{
        
        System.out.println("test sessions (Collection)");
        
        Collection<Session> expResult = null;
         Collection<Session> result = sessionDAO.sessions();
         
         System.out.println("result.size() = " +result.size());
         
         assertNotEquals("Collection<Session> sessions is not null",expResult,result);
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
