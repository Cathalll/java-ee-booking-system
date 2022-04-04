package model.dao.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.AddressDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;

/**
 * 
 */
public class MySQLAddressDAO implements AddressDAO {

    /**
     * Default constructor
     */
    public MySQLAddressDAO() {
        
    }
    
    private static MySQLAddressDAO instance;
    
    static {
        instance  = new MySQLAddressDAO();
    }
    
    
    public static MySQLAddressDAO getInstance(){
        return instance;
    }
    
        private static final String GETALL = "SELECT idAddress, street, houseNumber, city, postcode, enabledAddress FROM address WHERE enabledAddress = 1";
    private static final String GETBYID = "SELECT idAddress, street, houseNumber, city, postcode, enabledAddress FROM address WHERE enabledAddress = 1 AND idAddress = ?";
    private static final String DELETE = "UPDATE address SET enabledAddress = 0 WHERE idAddress = ?";
    private static final String SAVE = "INSERT INTO address(idUser, street,houseNumber,city,postcode) VALUES (?,?,?,?,?)";  
    private static final String UPDATE = "UPDATE address SET street = ?,houseNumber = ?,city = ?,postcode = ? WHERE idAddress = ?";

    @Override
    public Optional<Address> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
         public static Connection getConnection() throws ClassNotFoundException{  
         Class.forName("com.mysql.jdbc.Driver");
     String dburl = "jdbc:mysql://127.0.0.1:3306/trainingCentre";
        Connection mysqlConnection = null;
        try{
            mysqlConnection = DriverManager.getConnection(dburl, "SGBD_USER","puS6WI1v8Rk06Jag");
        }catch(SQLException e){e.printStackTrace();}
        return mysqlConnection;
    } 

    @Override
    public List<Address> getAll() {
        List <Address> addresses = new ArrayList<Address>();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            //c = CheckProperties.checkConnectionDB();
            c= getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        String sql = "SELECT idAddress, street, houseNumber, city, postcode, enabledAddress FROM address WHERE enabledAddress = 1";
        
        try {
            ps = c.prepareStatement(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                Address a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
                rs.getBoolean("enabledAddress"));
                
                addresses.add(a);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        return addresses;
    }

    @Override
    public int save(Address t, int lastUserID) {
        
        int status = 0;
        Connection c = null; 
        PreparedStatement ps = null;
       
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(SAVE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1,lastUserID);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(2,t.getStreet());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(3, t.getHouseNumber());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(4, t.getCity());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(5, t.getPostcode());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
           finally{
        
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        

        
        
        }
         
         
         return status;
    }

    @Override
    public int update(Address t) {
        
         int status = 0;
          

        
         Connection c = null;
       
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(1, t.getStreet());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(2,t.getHouseNumber());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(3, t.getCity());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(4, t.getPostcode());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(5, t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        return status;
         
         
        
        
    }

    @Override
    public int delete(Address t) {
        
                        
         int status = 0;
        Connection c = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(DELETE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        return status;
        
    }

    @Override
    public List<Address> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int save(Address t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }





}