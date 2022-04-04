/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.String.valueOf;
import java.sql.Timestamp;
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
public class TeacherHasAConflictTest {
    
    public TeacherHasAConflictTest() {
    }
    
    private static Teacher teacher;
    private static Session session4L;
    private static Session sessionTest;
    private static SessionDAO sessionDAO;
    private Session session6;
    private Session session9;
    
    @Before
    public void setUp() {
        
        sessionDAO = new MySQLSessionDAO();
        
        
        sessionTest = new Session();
        
        session4L =null;
        
         
        Collection<Session> sessions = sessionDAO.sessions();
        
        System.out.println("Let's test using the Session with the id 4L");
        
        for(Session s: sessions){
            if(s.getId().equals(4L))
                session4L = s;
        }
        
        System.out.println("session4L getID() and .getTitle() = " + session4L.getId() + " " + session4L.getTitle());
        
        System.out.println(" test is based on the whether the two Sessions take place in the same semester, and on the same day");
        System.out.println("So, take startDate and endDate from session4L, and assign to sessionTest");
        
        sessionTest.setStartDate(session4L.getStartDate());
        
        sessionTest.setEndDate(session4L.getEndDate());
        
        System.out.println("the Teacher we will test with is the teacher of session4L");
        
        teacher = session4L.getTeacher();
        
//////////////////////////////////////////////
        
        System.out.println("teacher .getID() and .getName() = " + teacher.getId() + " " + teacher.getName());
        
        
        System.out.println("session6 takes place on the same day as session4, so should provoke a conflict");
        
        session6 = new Session();
        
            for(Session s: sessions){
            if(s.getId().equals(6L))
                session6 = s;
        }
            
            System.out.println("session6.getID() =" + session6.getId());
           /////////////////////////////////////////////////////////////////////// 
            
            System.out.println("session9 should not provoke a conflict (takes place on a different day of the week");
            
             session9 = new Session();
        
            for(Session s: sessions){
            if(s.getId().equals(9L))
                session9 = s;
        }
            
            
            
            
        

        
    }
    
    @Test
    public void teacherHasAConflictwithSessionTest() throws Exception{
        
         //test is done via acessing the Sessions that that the Teacher has been assigned to via the DAO
       
        boolean teacherHasConflict = teacher.teacherHasAConflict(sessionTest);
        
        System.out.println("teacher has a conflict with sessionTest" + teacherHasConflict);
        
        assertTrue("teacher has a conflict with sessionTest", teacherHasConflict);
 
       
    }
    
    @Test
    public void teacherHasConflictSession6() throws Exception{
        
        boolean teacherHasConflictSession6 = teacher.teacherHasAConflict(session6);
        
        assertTrue("teacher has a conflict with session6", teacherHasConflictSession6);
        
    }
    
        @Test
    public void teacherHasConflictSession9() throws Exception{
        
        boolean teacherHasConflictSession9 = teacher.teacherHasAConflict(session9);
        
        assertFalse("teacher has a conflict with session9", teacherHasConflictSession9);
        
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
