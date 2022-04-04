package model.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.PriceDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Price;

/**
 * 
 */
public class MySQLPriceDAO implements PriceDAO {

    /**
     * Default constructor
     */
    public MySQLPriceDAO() {}
    
        private static MySQLPriceDAO instance;
    
    static {
        instance  = new  MySQLPriceDAO();
    }
    
    
    public static  MySQLPriceDAO getInstance(){
        return instance;
    }
    
    private static final String GETALL = "SELECT idPrices, amount, namePrices, enabledPrices FROM prices WHERE enabledPrices = 1";
    private static final String GETBYID = "SELECT idPrices, amount, namePrices, enabledPrices FROM prices WHERE enabledPrices = 1 AND idPrices = ?";
    private static final String DELETE = "UPDATE prices SET enabledPrices = 0 WHERE idPrices = ?";
    private static final String SAVE = "INSERT INTO prices(amount, namePrices) VALUES(?,CONCAT('€',?))";
    private static final String UPDATE = "UPDATE prices SET amount = ?, namePrices = CONCAT('€',?) WHERE idPrices = ?";

    @Override
    public Optional<Price> get(int id) {
        Price p = null;
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(GETBYID);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            if(rs.next()){
                
                p = new Price(rs.getInt("idPrices"),rs.getDouble("amount"),rs.getString("namePrices"),rs.getBoolean("enabledPrices"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return Optional.ofNullable(p);
        
         
        
    }

    @Override
    public List<Price> getAll() {
        List<Price> prices = new ArrayList<Price>();
        
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps = c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(rs.next()){
                Price p = new Price(rs.getInt("idPrices"),rs.getDouble("amount"),rs.getString("namePrices"),rs.getBoolean("enabledPrices"));
                
                prices.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
 
        return prices;
    }

    @Override
    public int save(Price t) {
        Connection c = null;
//        ResultSet rs = null;
        PreparedStatement ps = null;
        int status = 0;
      
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(SAVE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setDouble(1, t.getAmount());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            int x = (int)t.getAmount();
            ps.setInt(2,x);
                    
                    } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
         
         return status;
         
         
    }

    @Override
    public int update(Price t) {
        
         int status = 0;
        
         Connection c = null;
       
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setDouble(1, t.getAmount());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int x = (int) t.getAmount();
        
        try {
            ps.setInt(2, x);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(3, t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          finally{
                
             try {
                 ps.close();
             } catch (SQLException ex) {
                 Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
             
             try {
                 c.close();
             } catch (SQLException ex) {
                 Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
                
                
                }
        
      
        
          
          return status;
    }

    @Override
    public int delete(Price t) {
        
         int status = 0;
        Connection c = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(DELETE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPriceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        return status;
    }

    @Override
    public List<Price> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}