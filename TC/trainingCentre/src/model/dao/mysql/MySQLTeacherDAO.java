package model.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.TeacherDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;
import model.Teacher;
import model.User;
import model.UserRole;

/**
 * 
 */
public class MySQLTeacherDAO implements TeacherDAO {

    /**
     * Default constructor
     */
    public MySQLTeacherDAO() {
    }
    
        private static MySQLTeacherDAO instance;
    
    static{
        instance  = new MySQLTeacherDAO();
    }
    
    public static MySQLTeacherDAO getInstance(){
        return instance;
    }
    
    private static final String GETALL = "SELECT idUsers, role, name, surname, telephone, email, enabled,idAddress, street, houseNumber, city, postcode, enabledAddress,idUserRoles, nameUserRoles, enabledUserRoles from users JOIN address on idUsers = idUser JOIN userRoles ON role =idUserRoles WHERE role = 3 AND enabled = 1";
      private static final String GETBYID = "SELECT idUsers, role, name, surname, telephone, email, enabled,idAddress, street, houseNumber, city, postcode, enabledAddress,idUserRoles, nameUserRoles, enabledUserRoles from users JOIN address on idUsers = idUser JOIN userRoles ON role =idUserRoles WHERE role = 3 AND enabled = 1 AND idUsers = ?";

    @Override
    public Optional<Teacher> get(int id) {
       Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
          Teacher t = null;
        UserRole ur = null;
        Address a = null;
        
        try {
            c = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps = c.prepareStatement(GETBYID);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(rs.next()){
                
                ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                
//                a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
//                        rs.getBoolean("enabledAddress"));

                t= new Teacher(rs.getInt("idUsers"), ur, rs.getString("name"), rs.getString("surname"), rs.getString("email"),rs.getBoolean("enabled"));




            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      finally{  
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
          return Optional.ofNullable(t);
        
    }

    @Override
    public List<Teacher> getAll() {
               ArrayList<Teacher> teachers = new ArrayList<Teacher>();
       Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
         Teacher t = null;
        UserRole ur = null;
        Address a = null;
        try {
            c = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps= c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            while(rs.next()){
                ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                
//                a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
//                        rs.getBoolean("enabledAddress"));
                
                t= new Teacher(rs.getInt("idUsers"), ur, rs.getString("name"), rs.getString("surname"), rs.getString("email"),rs.getBoolean("enabled"));
                
                teachers.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
        return teachers;
    }

    @Override
    public List<Teacher> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int save(Teacher t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Teacher t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Teacher t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}