package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import model.Classroom;
import model.dao.ClassroomDAO;
import model.dao.mysql.MySQLClassroomDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class ClassroomTest {
    
    public ClassroomTest() {
    }
    
    private static ClassroomDAO classroomDAO;
    
    @Before
    public void setUp() {
        
        classroomDAO = new MySQLClassroomDAO();
    }
    
    @Test
    public void getAll() throws Exception{
        
        System.out.println("testGetAll");
        
        List<Classroom> expResult = null;
        List<Classroom> result = classroomDAO.getAll();
        
        System.out.println("classroomDAO.getAll().size() = "+classroomDAO.getAll().size());
        
        assertNotEquals("classroomDAO.getAll() is !=null",expResult,result);
        
    }
    
//    public void testAdd
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
