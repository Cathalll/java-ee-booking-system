/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Status;
import model.User;
import model.dao.StatusDAO;

/**
 *
 * @author Cathal
 */
public class MySQLStatusDAO implements StatusDAO{
    
    
    public MySQLStatusDAO(){}
    
    private static MySQLStatusDAO instance;
    
    static{
        instance = new MySQLStatusDAO();
    }
    
    public static MySQLStatusDAO getInstance(){
        
        return instance;
    }
    
     private static final String GETALL = "SELECT idStatus, nameStatus, discount, enabledStatus FROM status WHERE enabledStatus = 1";
    private static final String GETBYID = "";
    private static final String GETBYUSER = "SELECT idStatus, nameStatus, discount, enabledStatus FROM status WHERE idStatus = (SELECT status FROM users WHERE idUsers = ?)";

    @Override
    public Optional<Status> get(int id) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Status> getAll() {
       List<Status> statuses = new ArrayList<Status>();
       
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Status s = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
        try {
            while(rs.next()){
                
                s =  new Status(rs.getInt("idStatus"), rs.getString("nameStatus"), rs.getInt("discount"),rs.getBoolean("enabledStatus"));
                
                statuses.add(s);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      finally{  
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return statuses;
    }

    @Override
    public List<Status> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int save(Status t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Status t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Status t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Status> getByUser(User user) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Status st =  null;
        
        try {
            c = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps = c.prepareStatement(GETBYUSER);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1,user.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            if(rs.next()){
                
                st = new Status(rs.getInt("idStatus"), rs.getString("nameStatus"), rs.getInt("discount"),rs.getBoolean("enabledStatus"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
        finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
            }


            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        return Optional.ofNullable(st);
        
          
         
         
    }
    
}
