package model.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.ClassroomDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Classroom;

/**
 * 
 */
public class MySQLClassroomDAO implements ClassroomDAO {

    /**
     * Default constructor
     */
    public MySQLClassroomDAO() {
    }
    
     private static MySQLClassroomDAO instance;
    
    static {
        instance  = new  MySQLClassroomDAO();
    }
    
    
    public static  MySQLClassroomDAO getInstance(){
        return instance;
    }
    
        private static final String GETALL = "SELECT idClassroom, nameClassroom, capacityClassroom, enabledClassroom FROM classroom WHERE enabledClassroom = 1";
    private static final String GETBYID = "SELECT idClassroom, nameClassroom, capacityClassroom, enabledClassroom FROM classroom WHERE enabledClassroom = 1 AND idClassroom =?";
    private static final String DELETE = "UPDATE classroom SET enabledClassroom = 0 WHERE idClassroom = ?";
    private static final String SAVE = "INSERT INTO classroom(nameClassroom, capacityClassroom) VALUES(?,?)";
    private static final String UPDATE = "UPDATE classroom SET nameClassroom = ?, capacityClassroom = ? WHERE idClassroom = ?";


    @Override
    public Optional<Classroom> get(int id) {
      
        Classroom cl = null;
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(GETBYID);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            if(rs.next()){
                
                cl = new Classroom(rs.getInt("idClassroom"),rs.getString("nameClassroom"),rs.getInt("capacityClassroom"),rs.getBoolean("enabledClassroom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return Optional.ofNullable(cl);
        }
        
        
         
        
    }

    @Override
   public List<Classroom> getAll() {
       List<Classroom> classrooms = new ArrayList<Classroom>();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
//         String sql = "SELECT idClassroom, nameClassroom, capacityClassroom, enabledClassroom FROM classroom WHERE enabledClassroom = 1";
         
        try {
            ps = c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            while(rs.next()){
                Classroom cl = new Classroom(rs.getInt("idClassroom"),rs.getString("nameClassroom"),rs.getInt("capacityClassroom"),rs.getBoolean("enabledClassroom"));
                
                 classrooms.add(cl);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
           try {
               rs.close();
           } catch (SQLException ex) {
               Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
           }
            
           try {
               ps.close();
           } catch (SQLException ex) {
               Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
           }
            
           try {
               c.close();
           } catch (SQLException ex) {
               Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        
       
         
        
      return classrooms;
    }

    @Override
    public int save(Classroom t) {
         Connection c = null;
        PreparedStatement ps = null;
        int status = 0;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try { 
            ps = c.prepareStatement(SAVE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(1,t.getName());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(2, t.getCapacity());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
        return status;
        
         
    }

    @Override
    public int update(Classroom t) {
        
        int status = 0;
        
         Connection c = null;
       
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        try {
            ps = c.prepareStatement(UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(1,t.getName());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(2, t.getCapacity());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(3, t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
         
       
    }

    @Override
    public int delete(Classroom t) {
        
         int status = 0;
        Connection c = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(DELETE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClassroomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        return status;
        
    }

    @Override
    public List<Classroom> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}