/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import model.*;

/**
 *
 * @author Cathal
 */
public class StudentIsEnrolledInSessionTest {
    
    public StudentIsEnrolledInSessionTest() {
    }
    
    private static Session session;
    private static User enrolledUser1;
    private static User unEnrolledUser3;
    private static Student enrolledStudent1;
    private  static Student enrolledStudent2;
    private static Student unEnrolledStudent3;
  
    
    
    
    
    @Before
    public void setUp() {
        
        System.out.println("Sessions have an ArrayList<Student>, whereas the session object takes <User> as an object, so the test will use both");
        


        System.out.println("test");
        

      
         session = new Session();
        


        session.setTitle("my Session title");
        
        System.out.println("session.getTitle() = " + session.getTitle());
        
        //create Users
        enrolledUser1 = new User();
        unEnrolledUser3 = new User();
        
        //create Students
        enrolledStudent1 = new Student();
        enrolledStudent2 = new Student();
        unEnrolledStudent3 = new Student();
        
        //assign IDs to  each
        
        enrolledUser1.setId(1);
        enrolledStudent1.setId(1);
        
         enrolledStudent2.setId(2);
          
          
        unEnrolledUser3.setId(3);
        unEnrolledStudent3.setId(3);
        
        
        //add enrolled Students to session.students();
        
        List<Student> students = new ArrayList<>();
        
        students.add(enrolledStudent1);
        students.add(enrolledStudent2);
        
        session.setStudents(students);
        System.out.println("have created the List<Student>, added each Student, and now set this in session");
        
         System.out.println("session.students.size() = " +session.getStudents().size());
        
        
        
    }
    
    @Test
    public void testUserIsEnrolledInSession() throws Exception{
        
        System.out.println("test to see if enrolledUser1 is present in session.getStudents()");
        
        boolean userIsEnrolled = session.studentIsEnrolled(enrolledUser1);
        
        
        assertTrue("enrolledUser1 is enrolled",userIsEnrolled = true);
    }
    
    public void testUserIsNotEnrolledinSession() throws Exception{
        
        System.out.println("test to see if User unEnrolledUser3 is *not* enrolled in Session session");
        
        boolean userIsEnrolled = session.studentIsEnrolled(enrolledUser1);
        
        assertFalse("unEnrolledUser3 is enrolled", userIsEnrolled);
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
