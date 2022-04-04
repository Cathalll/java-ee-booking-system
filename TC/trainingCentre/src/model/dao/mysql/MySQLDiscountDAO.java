package model.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.DiscountDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Discount;
import model.Price;
import model.Training;
import model.UserRole;

/**
 * 
 */
public class MySQLDiscountDAO implements DiscountDAO {

    /**
     * Default constructor
     */
    public MySQLDiscountDAO() {
    }
    
    private static MySQLDiscountDAO instance;
    
    static{
        instance = new MySQLDiscountDAO();
    }
    
    public static MySQLDiscountDAO getInstance(){
        return instance;
    }
    
    private static final String GETALL = "SELECT idDiscounts, percentDiscount,nameDiscount,userRole,enabledDiscount, idUserRoles, nameUserRoles, enabledUserRoles from discounts JOIN userRoles on userRole = idUserRoles WHERE enabledDiscount = 1";
    private static final String GETBYID = "SELECT idDiscounts, percentDiscount,nameDiscount,userRole,enabledDiscount, idUserRoles, nameUserRoles, enabledUserRoles from discounts JOIN userRoles on userRole = idUserRoles WHERE enabledDiscount = 1 AND idDiscounts = ?";
    private static final String DELETE = "UPDATE discounts SET enabledDiscount = 0 WHERE idDiscounts = ?";
    private static final String SAVE = "INSERT INTO discounts(percentDiscount,nameDiscount,userRole) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE discounts SET percentDiscount = ?, nameDiscount = ?, userRole = ? WHERE idDiscounts = ?";
    

    @Override
    public Optional<Discount> get(int id) {
        
        Discount d = null;
        UserRole ur =null;
        
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(GETBYID);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ps.setInt(1,id);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(rs.next()){
                
                 ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                
                d =  new Discount(rs.getInt("idDiscounts"), rs.getInt("percentDiscount"), rs.getString("nameDiscount"), ur, rs.getBoolean("enabledDiscount"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return Optional.ofNullable(d);
       
    }

    @Override
    public List<Discount> getAll() {
        List<Discount> discounts = new ArrayList<Discount>();
        
        
                List<Training> trainings =  new ArrayList<Training>();
           Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        UserRole ur = null;
        
        Discount d = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps= c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(rs.next()){
                
                ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                
                d =  new Discount(rs.getInt("idDiscounts"), rs.getInt("percentDiscount"), rs.getString("nameDiscount"), ur, rs.getBoolean("enabledDiscount"));
                
                discounts.add(d);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
         
        return discounts;
        
    }

    @Override
    public int save(Discount t) {
        Connection c = null;
        PreparedStatement ps = null;
        int status = 0;
        
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps = c.prepareStatement(SAVE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1,t.getPercent());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(2,t.getName());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(3,t.getUserRole().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

        return status;
    }

    @Override
    public int update(Discount t) {
        
          int status = 0;
          

        
         Connection c = null;
       
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, t.getPercent());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(2, t.getName());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(3, t.getUserRole().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(4,t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        finally{
            
              try {
                  ps.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
            
            
              try {
                  c.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
       
        
         
        return status; 
        
    }

    @Override
    public int delete(Discount t) {
        
                  
         int status = 0;
        Connection c = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(DELETE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
             try {
                 ps.close();
             } catch (SQLException ex) {
                 Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
            
             try {
                 c.close();
             } catch (SQLException ex) {
                 Logger.getLogger(MySQLDiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        return status;
        
    }



    @Override
    public List<Discount> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}