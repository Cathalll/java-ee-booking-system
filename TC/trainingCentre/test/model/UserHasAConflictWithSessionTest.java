/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import model.dao.*;
import model.dao.UserDAO;
import model.dao.mysql.MySQLUserDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class UserHasAConflictWithSessionTest {
    
    public UserHasAConflictWithSessionTest() {
    }
    
    private static User eddie;
    private static User user6;
    private static Session session4L;
    private static Session sessionTest;
    private static SessionDAO sessionDAO;
    private static UserDAO userDAO;
    private static int eddieID;
    private Collection<Session> eddieSessions;
    private boolean eddieHasConflict;
    private static User larry;
    private boolean larryHasAConflict;
    private static int larryID;
    
    
    @Before
    public void setUp() {
        
        //this method goes via the SessionDAO, so need to test with real entries in database
        
        
        ///testing for a User that has a conflict
        
        System.out.println("User id=4, name='Eddie' is registerd to two sessions, session id=2 & session id=3");
        userDAO = new MySQLUserDAO();
        eddieID = 4;
        eddie = userDAO.get(eddieID).get();
        
     
        System.out.println("eddie id = + " + eddie.getId() + "and name = " + eddie.getName());
        
        sessionTest = new Session();
        
        //set the startTime and endTime of sessionTest to be the same as one of edddie's currently-enrolled Sessions
        
        String startString = "2021-09-06 18:00:00.000";
        String endString = "2021-12-13 18:00:00.000";
        
        Timestamp start = Timestamp.valueOf(startString);
        Timestamp end =  Timestamp.valueOf(endString);
        
        sessionTest.setStartDate(start);
        sessionTest.setEndDate(end);
        
     
      
      
      ////testing for a User who does not have a conflict
      
        System.out.println("User id=9, Larry, is not enrolled in any Session, so the Session should not cause a conflict");
        
        larryID = 9;
        larry =  userDAO.get(larryID).get();
        System.out.println("Larry name and UserRole = " + larry.getName() + ", " + larry.getUserRole().getName());
        
        
        
        System.out.println("larry conflict boolean = " + larryHasAConflict);
      
      
        
     
         
        
    }
    
   @Test 
   public void userHasConflict() throws Exception{
       
        eddieHasConflict = eddie.sessionIsInConflict(sessionTest);
       
        
        
        assertTrue("User eddie has conflict with testSession", eddieHasConflict);
       
   }
   
   @Test
   public void userDoesNotHaveConflict() throws Exception{
       
       larryHasAConflict = larry.sessionIsInConflict(sessionTest);
       
       assertFalse("larry has a conflict with sessionTest", larryHasAConflict);
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
