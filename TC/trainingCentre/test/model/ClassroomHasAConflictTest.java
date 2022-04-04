/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;
import model.dao.ClassroomDAO;
import model.dao.SessionDAO;
import model.dao.mysql.MySQLClassroomDAO;
import model.dao.mysql.MySQLSessionDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class ClassroomHasAConflictTest {

    public ClassroomHasAConflictTest() {
    }

    private Classroom classroom104;
    private Session newSession;
    private Session newSession1;
    private Session session4;
    private ClassroomDAO classroomDAO;
    private SessionDAO sessionDAO;
    private Collection<Session> sessions;
    private Session session10;
    private Session bigSession;

    @Before
    public void setUp() {

        //Classroom with id =4, Classroom 104, has a Session in the spring semester on the Monday (Session #4)
        //Create another Session, assign the same start and end date as Session #4, anbd try to assign it to Classroom #4 (classroom104)
        classroomDAO = new MySQLClassroomDAO();

        classroom104 = new Classroom();

        classroom104 = classroomDAO.get(4).get();

        System.out.println("classroom104 name = " + classroom104.getName());

        sessionDAO = new MySQLSessionDAO();

        sessions = sessionDAO.sessions();

        session4 = new Session();

        for (Session s : sessions) {
            if (s.getId().equals(4L)) {
                session4 = s;
            }

        }

        newSession = new Session();

        newSession.setStartDate(session4.getStartDate());

        newSession.setEndDate(session4.getEndDate());

        //Session #10 does not have a conflict with classroom104 - instantiate session10, give its start and end values to newSession1 to test for !conflict
        newSession1 = new Session();

        session10 = new Session();

        for (Session s : sessions) {
            if (s.getId().equals(10L)) {
                session10 = s;
            }

        }
        
        System.out.println("session10.getId() = " + session10.getId());
        
        //assign same startdate and enddate as non-conflicting session10
        
       newSession1.setStartDate(session10.getStartDate());

        newSession1.setEndDate(session10.getEndDate());
        
        
        System.out.println("newSession1.getStartDate() = " + newSession1.getStartDate().toString());
        
        
        //testing now with a Sssion with a greater capacity than the classroom, to test for capacity + scheduling conflicts in one test
        
        bigSession = new Session();
        
        //set the same startDates and endDates of newSession1, which has NO scheduling conflicts
        
        bigSession.setStartDate(newSession1.getStartDate());
        
        bigSession.setEndDate(newSession1.getEndDate());
        
        //now set a capacity > than that of classroom104
        bigSession.setCapacity(150);
    }

    @Test
    public void newSessionHasConflict() throws Exception {

        boolean conflict = classroom104.classroomHasAConflict(newSession); // should be 'true'

        assertTrue("classroom104 has a conflict with newSession", conflict);

    }
    
    @Test
    public void newSession1HasNoConflict() throws Exception{
        boolean conflict = classroom104.classroomHasAConflict(newSession1);
        
        assertFalse("classroom104 has a conflict with newSession", conflict);
    }
    
    @Test
    public void bigSessionHasNoSchedulingConflict() throws Exception{
        boolean conflict =  classroom104.classroomHasAConflict(bigSession);
        
        assertFalse("classroom104 has a scheduling conflict with bigSession", conflict);
    }
    
    @Test 
    public void bigSessionHasCapacityConflict() throws Exception{
        
        boolean conflict = classroom104.hasConflicts(bigSession);
        
        assertTrue("classroom104 has a conflict with bigSession", conflict);
        
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
