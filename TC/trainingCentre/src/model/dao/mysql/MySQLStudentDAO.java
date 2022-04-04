package model.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.StudentDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;
import model.Session;
import model.Status;
import model.Student;
import model.User;
import model.UserRole;

/**
 * 
 */
public class MySQLStudentDAO implements StudentDAO {

    /**
     * Default constructor
     */
    public MySQLStudentDAO() {
    }

    private static MySQLStudentDAO instance;
    
    static{
        instance  = new MySQLStudentDAO();
    }
    
    public static MySQLStudentDAO getInstance(){
        return instance;
    }
    
     private static final String GETALL = "SELECT idUsers, role, name, surname, telephone, email, users.status, enabled,idAddress, street, houseNumber, city, postcode, enabledAddress,idUserRoles, nameUserRoles, enabledUserRoles, idStatus, nameStatus, discount, enabledStatus from users JOIN address on idUsers = idUser JOIN userRoles ON role =idUserRoles JOIN status ON users.status = idStatus  where idUserRoles = 4 AND enabled = 1";
     private static final String GETBYID = "SELECT idUsers, role, name, surname, telephone, email, users.status, enabled,idAddress, street, houseNumber, city, postcode, enabledAddress,idUserRoles, nameUserRoles, enabledUserRoles, idStatus, nameStatus, discount, enabledStatus from users JOIN address on idUsers = idUser JOIN userRoles ON role =idUserRoles JOIN status ON users.status = idStatus  where idUserRoles = 4 AND enabled = 1 AND idUsers = ?";
    
  

    @Override
    public Optional<Student> get(int id) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
          Student s = null;
          
          UserRole ur = null;

        Address a = null;
        Status st = null;
        try {
            c = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ps = c.prepareStatement(GETBYID);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        try {
            if(rs.next()){
                
                 ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                
               
                
                a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
                        rs.getBoolean("enabledAddress"));
                
                st = new Status(rs.getInt("idStatus"), rs.getString("nameStatus"), rs.getInt("discount"),rs.getBoolean("enabledStatus"));
                
                s = new Student(st,rs.getInt("idUsers"), ur, rs.getString("name"), rs.getString("surname"), rs.getString("telephone"), rs.getString("email"),rs.getBoolean("enabled"), a);
                
                
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
        
        return Optional.ofNullable(s);
        
    }

    @Override
    public List<Student> getAll() {
          
      List<Student> students = new ArrayList<Student>();
       Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
          Student s = null;
          
          UserRole ur = null;

        Address a = null;
        Status st = null;
        try {
            c = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps= c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(rs.next()){
                ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                
               
                
                a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
                        rs.getBoolean("enabledAddress"));
                
                st = new Status(rs.getInt("idStatus"), rs.getString("nameStatus"), rs.getInt("discount"),rs.getBoolean("enabledStatus"));
                
                s = new Student(st,rs.getInt("idUsers"), ur, rs.getString("name"), rs.getString("surname"), rs.getString("telephone"), rs.getString("email"),rs.getBoolean("enabled"), a);
                
               
                

                students.add(s);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        finally{
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
        }
        return students;
        
        
           
        
    
    }

    @Override
    public List<Student> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int save(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}