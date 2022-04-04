/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;
import model.dao.SessionDAO;
import model.dao.UserDAO;
import model.dao.mysql.MySQLSessionDAO;
import model.dao.mysql.MySQLUserDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class TestUserHasConflictSession {
    
    public TestUserHasConflictSession() {
    }
    
     private Session session2;
    private Session session3;
    private Session session4;
    private Session session6;
    private SessionDAO sessionDAO;
    private User userdd21;
    private UserDAO userDAO;
    private Collection <Session> dd21sessions;
     private Collection <Session> sessions;
     private Session session8Wed;
     private Session session9;
    
    @Before
    public void setUp() {
        
          userDAO = new MySQLUserDAO();
        userdd21 = userDAO.get(21).get();
        
         sessionDAO = new MySQLSessionDAO();
         
         sessions = sessionDAO.sessions();
        
        

        
        session2 = new Session();
        session3 = new Session();
       session4 = new Session();
        session6 = new Session();
        
                for(Session s: sessions){
            if(s.getId().equals(2L)){
                session2 =s;
            }
 
          
        }
        
        
        for(Session s: sessions){
            if(s.getId().equals(3L)){
                session3 =s;
            }
 
          
        }
        
                for(Session s: sessions){
            if(s.getId().equals(4L)){
                session4 =s;
            }
 
          
        }
        
        
        
        dd21sessions = sessionDAO.sessions(userdd21);
        
        for(Session s: dd21sessions){
            System.out.println("Session id + title = " + s.getId() + ", " + s.getTitle());
        }
        
        System.out.println("sessionDAO.sessions(userdd21).size() = " + sessionDAO.sessions(userdd21).size()); /// dd21 is sign=up for Sessions #2, #7 (autumn) an d#4 (spring)
        
         boolean session3isConflict = false;
         
         System.out.println("session3 is in conflict with dd21, so test with this one");
         
         
        boolean session3result =  false;
        //get all Session where User is enrolled
//        Collection <Session> enrolledSessions = sessionDAO.sessions(this);
        dd21sessions = sessionDAO.sessions(userdd21);
        for(Session s: dd21sessions){
            // User already has Session in the same semester in same day of the week - both conditions need ot be true in order
            // for there to be a conflict
            if(session3.isAutumnSemester() && s.isAutumnSemester() && (session3.getDayOfTheWeek() == s.getDayOfTheWeek())){
                System.out.println("session3. isAutumnSemester() = " +session3.isAutumnSemester());
                System.out.println("s. isAutumnSemester() = " +s.isAutumnSemester());
                  System.out.println("session3.getDayOfTheWeek() = " +session3.getDayOfTheWeek());
                  System.out.println("s.getDayOfTheWeek = " + s.getDayOfTheWeek());
                  
                session3isConflict = true;
               
                
            }else if(!session3.isAutumnSemester() && !s.isAutumnSemester() && session3.getDayOfTheWeek() == s.getDayOfTheWeek()){
                
                session3isConflict = true;
                
            }
            
        }
        
        System.out.println("value of session3result after reassigned (should be true) = " + session3result);
        
        
        System.out.println("sesssion3isConflict, after going through user.sessionIsInConflict(Session session) method - should be 'true' - equals:" + session3isConflict);
        
        //try leaving the 'else' of with two Sessions that do *not* conflict - session4 should be false
        
         boolean session4isConflict = false;
        
        
           for(Session s: dd21sessions){
            // User already has Session in the same semester in same day of the week - both conditions need ot be true in order
            // for there to be a conflict
            if((session4.isAutumnSemester() && s.isAutumnSemester()) && (session4.getDayOfTheWeek() == s.getDayOfTheWeek())){
               
                  
                session4isConflict = true;
               
                
            }else if(!session4.isAutumnSemester() && !s.isAutumnSemester() && session4.getDayOfTheWeek() == s.getDayOfTheWeek()){
                
                session4isConflict = true;
            }
                
//            }else{
//                
//                session4isConflict = false;
//            }
 
        }
           
           System.out.println("value of session4 after loop: " + session4isConflict);
        
        
        //try again
        
        
        
        System.out.println("###############");
        System.out.println("testing without looping ");
        
        Session session100 = new Session();
        session100.setCapacity(10);
        session100.setEnabled(true);
        
        Session session101 = new Session();
        session101.setCapacity(10);
        session101.setEnabled(true);
        
        boolean bb = false;
        
        if(session100.isEnabled()  && session101.isEnabled() && session100.getCapacity() == session101.getCapacity()){
            bb= true;
            
        }else{
            bb = false;
        }
        
        System.out.println("Value of bb =" +bb);
        
        
        System.out.println("###################################################");
        
        System.out.println("going through every possible combination");
        
        Session session3a = new Session();
        session3a = session3;
        
        System.out.println("session3a.getId() = " + session3a.getId());
        
        
          boolean session3aresult =  false;


        for(Session s: dd21sessions){

            if(session3a.isAutumnSemester() && s.isAutumnSemester() && (session3a.getDayOfTheWeek() == s.getDayOfTheWeek())){
                
                session3aresult = true;
                System.out.println("1. session3aresult(should be true) = " +  session3aresult);
                
            }else if(!session3a.isAutumnSemester() && !s.isAutumnSemester() && session3a.getDayOfTheWeek() == s.getDayOfTheWeek()){
                
                 session3aresult = true;
                System.out.println("2. session3aresult(should be true) = " +  session3aresult);
                
            
            
        }else{
                
                 session3aresult = false;
                System.out.println("3. session3aresult(should be true) = " +  session3aresult);
                
                }
            
            System.out.println("4. value of session3aresult inside end of loop (should be true, still) = " +  session3aresult);
        
        
        }
        
        System.out.println("5. session3aresult(should be true) = " +  session3aresult);
        
        System.out.println("####################");
        
        System.out.println("ok trying session4.... wait dd21 *is* already signed in for session4, no wonder i could not figure it out");
        System.out.println("");
        
        
        session8Wed =  new Session();
        
                for(Session s: sessions){
            if(s.getId().equals(8L)){
                session8Wed =s;
            }
 
          
        }
                
                System.out.println("session8Wed id and title = " + session8Wed.getId() + ", " + session8Wed.getTitle());
        
         boolean session8isConflict = false;
         
         
         


        for(Session s: dd21sessions){

            if(session8Wed.isAutumnSemester() && s.isAutumnSemester() && (session8Wed.getDayOfTheWeek() == s.getDayOfTheWeek())){
                
                session8isConflict = true;
                System.out.println("1. session8isConflict(should be false) = " +  session8isConflict);
                
            }else if(!session8Wed.isAutumnSemester() && !s.isAutumnSemester() && session8Wed.getDayOfTheWeek() == s.getDayOfTheWeek()){
                
                 session8isConflict = true;
                System.out.println("2. session8isConflict(should be false) = " +  session8isConflict);
                
            
            
        }else{
                
                 session8isConflict = false;
                System.out.println("3. session8isConflict(should be false) = " +  session8isConflict);
                
                }
            
            System.out.println("4. value of session8isConflict inside end of loop (should be false, still) = " +  session8isConflict);
        
        
        }
        
           System.out.println("5. session8isConflict(should be false) = " +  session8isConflict);
        
        System.out.println("so when there is no conflict, works with an 'else' - try removing the 'else', see if it still returns correct value (false)");
        
         boolean session8NoElse = false;
         

        for(Session s: dd21sessions){

            if(session8Wed.isAutumnSemester() && s.isAutumnSemester() && (session8Wed.getDayOfTheWeek() == s.getDayOfTheWeek())){
                
                session8NoElse = true;
                System.out.println("1. session8NoElse(should be false) = " +  session8NoElse);
                
            }else if(!session8Wed.isAutumnSemester() && !s.isAutumnSemester() && session8Wed.getDayOfTheWeek() == s.getDayOfTheWeek()){
                
                 session8NoElse = true;
                System.out.println("2. session8NoElse(should be false) = " +  session8NoElse);
                
            
            
        }
            
            System.out.println("4. value of session8NoElse inside end of loop (should be false, still) = " +  session8NoElse);
        
        
        }
        
           System.out.println("5. session8NoElse(should be false) = " +  session8NoElse);
        
        
        
        System.out.println("###########################################################################");
        
        System.out.println("tesing one more time, definitively, that session3, which takes place on the same day as one of session2, which dd21 is signed up for ");
        
         boolean session3NoElse =  false;


        for(Session s: dd21sessions){

            if(session3.isAutumnSemester() && s.isAutumnSemester() && (session3.getDayOfTheWeek() == s.getDayOfTheWeek())){
                
                session3NoElse = true;
                System.out.println("1. session3NoElse(should be true) = " +  session3NoElse);
                
            }else if(!session3.isAutumnSemester() && !s.isAutumnSemester() && session3.getDayOfTheWeek() == s.getDayOfTheWeek()){
                
                 session3NoElse = true;
                System.out.println("2. session3NoElse(should be true) = " +  session3NoElse);
                
            
            
        }
            
            System.out.println("(3 is gone) 4 . value of session3NoElse inside end of loop (should be true, still) = " +  session3NoElse);
        
        
        }
        
        System.out.println("5. session3NoElse(should be true) = " +  session3NoElse);
        
        
        
        System.out.println("#################################");
        
        System.out.println("now testing for session9 - takes place in spring on Wed (so, no conflict)");
        
        session9 = new Session();
        
        for(Session s: sessions){
            if(s.getId().equals(9L)){
                session9 = s;
            }
        }
        
        System.out.println("session9.getTitle() = " + session9.getTitle());
        
        boolean session9HasConflict = false;
        
        

        for(Session s: dd21sessions){

            if(session9.isAutumnSemester() && s.isAutumnSemester() && (session9.getDayOfTheWeek() == s.getDayOfTheWeek())){
                
                session9HasConflict = false;
                System.out.println("1. session9HasConflict(should be false) = " +  session9HasConflict);
                
            }else if(!session9.isAutumnSemester() && !s.isAutumnSemester() && session9.getDayOfTheWeek() == s.getDayOfTheWeek()){
                
                 session9HasConflict = false;
                System.out.println("2. session9HasConflict(should be false) = " +  session9HasConflict);

            
        }
            
       
        
  
        
    }
        
             System.out.println("value of ession9HasConflict after loop (should be false) = " + session9HasConflict);
        
    }
    
    @Test 
    public void testSessionsCollectionDD21() throws Exception {
        
          
        
    
        
        assertTrue("dd21 sessions > 1", dd21sessions.size() >1 );
        
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
