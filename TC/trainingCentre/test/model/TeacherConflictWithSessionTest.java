/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
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
public class TeacherConflictWithSessionTest {
    
    public TeacherConflictWithSessionTest() {
    }
    
    private Teacher teacher;
    private Session newSession;
    private Session wedSess;
    private Session session4;
    private SessionDAO sessionDAO;
    private Collection <Session> sessions;
    private Session session3;
    private boolean teacherDoesHaveConflict;
    private Session session9;
    private boolean teacherDoesNotHaveConflict;
    
    Teacher teacherS3;
    
    @Before
    public void setUp() {
        
        sessionDAO = new MySQLSessionDAO();
        
        sessions = sessionDAO.sessions();
       session4 = new Session();
       session3 = new Session();
               
               
        
               
        System.out.println("Testing session4, which takes place on a Monday in the spring semester");
        
        for(Session s: sessions){
            if(s.getId().equals(4L))
                session4 = s;
        }
        
        
                for(Session s: sessions){
            if(s.getId().equals(3L))
                session3 = s;
        }
        
        System.out.println("Training of session4 = " + session4.getTraining().getTitle()); // ok
        
//        System.out.println("Training of session3 = " + session3.getTraining().getTitle()); // ok
//        
        teacher = new Teacher();
       
        
        teacher = session4.getTeacher();
        
        teacherS3 = session4.getTeacher();
        
        System.out.println("teacher first name = " + teacher.getName()); /// should be 'Terrence'
        
                System.out.println("teachers3 first name = " + teacherS3.getName()); /// should be 'Terrence'
        
        
       //assign newSession the same start time and end time as session4, provoke a conflict
       
       newSession = new Session();
       
       newSession.setStartDate(session4.getStartDate());
       
       newSession.setEndDate(session4.getEndDate());
       
        System.out.println("newSession.getEndDate() =" + newSession.getEndDate());
        
       //create a Session that does not have a conflict
       
       wedSess = new Session();
       
       //session9 has no conflict with any of teaher's Sessions.
       
               for(Session s: sessions){
            if(s.getId().equals(9L)){
                wedSess.setStartDate(s.getStartDate());
                wedSess.setEndDate(s.getEndDate());
            
                    }
        }
               
               System.out.println("wedSess.getDatOfWeek() = " + wedSess.getDayOfTheWeek()); // '3' means it takes place on a Wednesday, no conflict
               
               
               
          
        
       
       
        
     
            
            

        
        
        
    }
    
    
    @Test
    public void teaceherIsNotNull() throws Exception{
        
        
        assertTrue("teacher is not null", teacher!= null);
        
        
    }
    
    @Test
    public void teacherHasAConflict() throws Exception{
        
        teacherDoesHaveConflict = teacher.teacherHasAConflict(newSession);
        
        assertTrue("teacher has a conflict with newSession", teacherDoesHaveConflict);
    
    }
    
    @Test
    public void teacherDoesNotHaveConflict() throws Exception{
        
        teacherDoesNotHaveConflict = teacher.teacherHasAConflict(wedSess); //should be false
        
        assertFalse("teacherHasConflict with Session taking place on a Wednesday", teacherDoesNotHaveConflict);
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
