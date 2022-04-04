
package model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.dao.UserRoleDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserRole;

/**
 * 
 */
public class MySQLUserRoleDAO implements UserRoleDAO {

    /**
     * Default constructor
     */
    public MySQLUserRoleDAO() {
    }
    
    private static MySQLUserRoleDAO instance;
    
    static {
        instance  = new  MySQLUserRoleDAO();
    }
    
    
    public static  MySQLUserRoleDAO getInstance(){
        return instance;
    }
    
    private static final String GETALL = "SELECT idUserRoles, nameUserRoles, enabledUserRoles FROM userRoles WHERE enabledUserRoles =1";
    
    private static final String GETBYID = "SELECT idUserRoles, nameUserRoles, enabledUserRoles FROM userRoles WHERE enabledUserRoles =1 AND idUserRoles =?";
    
    private static final String DELETE = "UPDATE userRoles SET enabledUserRoles = 0 WHERE idUserRoles = ?";
    
    private static final String SAVE = "INSERT INTO userRoles(nameUserRoles) VALUES(?)";
    
    private static final String UPDATE = "Update userRoles SET nameUserRoles = ? WHERE idUserRoles = ?";
    
    
    
    public Optional<UserRole> get(int id) {
        UserRole ur = null;
         Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(GETBYID);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(rs.next()){
                
                ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.ofNullable(ur);
         
        
    
    }



    @Override
    public List<UserRole> getAll() {
        List<UserRole> uRoles = new ArrayList<UserRole>();
        
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        try {
            ps = c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(rs.next()){
                UserRole ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"),rs.getBoolean("enabledUserRoles"));
                
                uRoles.add(ur);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        return uRoles;
    }

    @Override
    public List<UserRole> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int save(UserRole t) {
        Connection c = null;
        PreparedStatement ps = null;
        int status = 0;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(SAVE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(1, t.getName());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
           status= ps.executeUpdate();
            
             } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
        finally{
              try {
                  ps.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
            
              try {
                  c.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
       
        return status;
        
    }

    @Override
    public int update(UserRole t) {
        
        int status = 0;
        
         Connection c = null;
       
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps.setString(1, t.getName());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(2, t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status= ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
         
        
        
        
        return status;
    }
    
    

    @Override
    public int delete(UserRole t) {
        
//        int id = t.getId();
        int status = 0;
        Connection c = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(DELETE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
//         ps.setObject(t, t.getId());
        try {
            ps.setInt(1, t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
         
        try {
           status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
         return status;
         
        
    }

}