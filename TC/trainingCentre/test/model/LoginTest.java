/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.LoginBean;
import java.util.Optional;
import model.dao.LoginDAO;
import model.dao.mysql.MySQLLoginDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class LoginTest {
    
    public LoginTest() {
    }
    
    private static LoginDAO loginDAO;
    
    private static LoginBean loginBean;
    
             // good User credentials
        private static final String VALIDADMINEMAIL = "admin@trainingcentre.com";
        private static final String VALIDSTUDENTEMAIL = "eddie-learn@student.com";
        private static final String VALIDSTUDENTPASSWORD = "P123456#";
        private static final String VALIDADMINPASSWORD = "P12345678#";
        
                //bad User credentials
      private static final String INVALIDPASSWORD = "11111111";
        private static final String INVALIDUSEREMAIL = "nonexistantemail@mail.com";
        
        User user = new User();
    
    @Before
    public void setUp() {
        
        loginDAO = new MySQLLoginDAO();
        
        loginBean = new LoginBean();
        
        
        

        
    }
    
    @Test
    public void loginAdminValidCredentials() throws Exception{
        
        System.out.println("testing login method with valid Admin credentials");
//         Optional expResult = null;
        Optional result = null;
       
        
        loginBean.setEmail(VALIDADMINEMAIL);
        loginBean.setPassword(VALIDADMINPASSWORD);
        
        result = loginDAO.login(loginBean);
        
//        System.out.println("User role of result Optional = " +result.get().getClass());
        
        assertTrue("Optional<User> resturned from the database is not empty",result.isPresent());
                
         
        
    }
    @Test
    public void loginStudentValidCredentials() throws Exception{
        
        System.out.println("testing login method with valid Student credentials");
        
        Optional expResult = null;
        Optional result = null;
        
        loginBean.setEmail(VALIDSTUDENTEMAIL);
        loginBean.setPassword(VALIDSTUDENTPASSWORD);
        
        result = loginDAO.login(loginBean);
        
        assertTrue("Optional<User> returned from the database using valid Student credentials",result.isPresent());
    }
    
    @Test
    public void loginAdminInvalidCredentials() throws Exception{
        
        System.out.println("testing login method using valid email, invalid password");
        
//         Optional expResult;
        Optional result;
        
        loginBean.setEmail(VALIDADMINEMAIL);
        loginBean.setPassword(INVALIDPASSWORD);
        
        result = loginDAO.login(loginBean);
        
        assertFalse("Optional<User> returned from the database with invalid Admin credentials used",result.isPresent());
        
       
    }
    
    @Test
    public void loginNonExistentUserValidPassword() throws Exception{
        
        System.out.println("testing login method using non-existent email, but with a correct password");
        
        Optional result;
        
        loginBean.setEmail(INVALIDUSEREMAIL);
        loginBean.setPassword(VALIDSTUDENTPASSWORD);
        
        result = loginDAO.login(loginBean);
        
        assertFalse("Optional<User> returned from database using incorrect email but correct password",result.isPresent());
        
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
