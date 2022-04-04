/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysql;

import BCrypt.BCrypt;
import beans.LoginBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;
import model.Admin;
import model.Status;
import model.Student;
import model.Teacher;
import model.User;
import model.UserRole;
import model.dao.LoginDAO;

/**
 *
 * @author Cathal
 */
public class MySQLLoginDAO implements LoginDAO{
    
   public MySQLLoginDAO(){}
    
     private static MySQLLoginDAO instance;
    
    static{
        instance  = new MySQLLoginDAO();
    }
    
    public static MySQLLoginDAO getInstance(){
        return instance;
    }
    
//      private static final String LOGIN ="SELECT idUsers, role, name, surname, telephone, email, enabled,idAddress, street, houseNumber, city, postcode, enabledAddress,idUserRoles, nameUserRoles, enabledUserRoles from users JOIN address on idUsers = idUser JOIN userRoles ON role =idUserRoles  where enabled = 1 AND email = ?";
       private static final String GETUSERBYEMAIL ="SELECT idUsers, role, name, surname, telephone, email, password, users.status, enabled,idAddress, street, houseNumber, city, postcode, enabledAddress,idUserRoles, nameUserRoles, enabledUserRoles, idStatus, nameStatus, discount, enabledStatus from users JOIN address on idUsers = idUser JOIN userRoles ON role =idUserRoles JOIN status ON users.status = idStatus where enabled = 1 AND email = ?";
// this grabs status

    @Override
    public boolean checkUserExists(LoginBean loginBean) {
         
        boolean b = false;
        String email = loginBean.getEmail();
//         String password = loginBean.getPassword();

         UserRole ur = null;

        Address a = null;
        Status st = null;
        User u = null;

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
       try {
           c = CheckProperties.checkConnectionDB();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
         
        try {
            ps = c.prepareStatement(GETUSERBYEMAIL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(1, email);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(rs.next()){
                
                b = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return b;
        

         
    }

    @Override
    public User returnUserIfExists(LoginBean loginBean) {
         boolean b = false;
        String email = loginBean.getEmail();
//         String password = loginBean.getPassword();

         UserRole ur = null;

        Address a = null;
        Status st = null;
        User u = null;

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
       try {
           c = CheckProperties.checkConnectionDB();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
         
        try {
            ps = c.prepareStatement(GETUSERBYEMAIL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(1, email);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(rs.next()){
                
                 ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                 
                 a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
                rs.getBoolean("enabledAddress"));
                
                u = new User(rs.getInt("idUsers"), ur, rs.getString("name"), rs.getString("surname"), rs.getString("telephone"), rs.getString("email"),rs.getBoolean("enabled"), a);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       return u; 
    }

    @Override
    public Optional<User> login(LoginBean loginBean) {
    
        String email = loginBean.getEmail();
         String password = loginBean.getPassword();
         
         //classes compose User
         UserRole ur = null;
        Address a = null;
        Status st = null;
        User u = null;
        
        //for checking entered password against password stored in DB
        String dbPassword = null;
         boolean pwCheck = false;
         BCrypt bcrypt = new BCrypt();

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
       try {
           c = CheckProperties.checkConnectionDB();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
         
        try {
            ps = c.prepareStatement(GETUSERBYEMAIL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(1, email);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(rs.next()){
                
                dbPassword = rs.getString("password");
                
                pwCheck = bcrypt.checkpw(password, dbPassword);
                
                if(pwCheck){
                
                 ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                 
                 a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
                rs.getBoolean("enabledAddress"));
                
                u = new User(rs.getInt("idUsers"), ur, rs.getString("name"), rs.getString("surname"), rs.getString("telephone"), rs.getString("email"),rs.getBoolean("enabled"), a);
                }else{
                    u = null;
                }
               
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLLoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       return Optional.ofNullable(u); 
        
    }
    
}
