/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.dao.TeacherDAO;
import model.dao.mysql.MySQLTeacherDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class GetTeacherByIDTest {
    
    public GetTeacherByIDTest() {
    }
    
    private static Teacher teacherTerry;
    private static TeacherDAO teacherDAO;
    
    
    
    @Before
    public void setUp() {
        
        teacherTerry = new Teacher();
        
        teacherDAO = new MySQLTeacherDAO();
        
        // the userId of 'Terry Teacher' is 3
        teacherTerry = teacherDAO.get(3).get();
    }
    
    @Test
    public void teacherTerryIsNotNull() throws Exception{
        assertTrue("teacherTerry is not null", teacherTerry != null);
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
