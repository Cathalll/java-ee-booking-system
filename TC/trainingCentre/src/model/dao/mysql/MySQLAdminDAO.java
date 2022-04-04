package model.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.AdminDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;
import model.Admin;
import model.User;
import model.UserRole;

/**
 * 
 */
public class MySQLAdminDAO implements AdminDAO {

    /**
     * Default constructor
     */
    public MySQLAdminDAO() {
    }
    
       private static MySQLAdminDAO instance;
    
    static{
        instance  = new MySQLAdminDAO();
    }
    
    public static MySQLAdminDAO getInstance(){
        return instance;
    }
    
     private static final String GETALL = "SELECT idUsers, role, name, surname, telephone, email, users.status, enabled,idAddress, street, houseNumber, city, postcode, enabledAddress,idUserRoles, nameUserRoles, enabledUserRoles, idStatus, nameStatus, discount, enabledStatus from users JOIN address on idUsers = idUser JOIN userRoles ON role =idUserRoles JOIN status ON users.status = idStatus  where idUserRoles = 2 AND enabled = 1";

    @Override
    public Optional<Admin> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> admins = new ArrayList<Admin>();
        
         Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        
           Admin  ad = null;
        UserRole ur = null;
        Address a = null;
        try {
            c = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps= c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(rs.next()){
                ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                
                a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
                        rs.getBoolean("enabledAddress"));
                
                ad = new Admin(rs.getInt("idUsers"), ur, rs.getString("name"), rs.getString("surname"), rs.getString("telephone"), rs.getString("email"),rs.getBoolean("enabled"), a);
                
            }   } catch (SQLException ex) {
            Logger.getLogger(MySQLAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        finally{
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        
        return admins;
        
    }

    @Override
    public int save(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public List<Admin> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}