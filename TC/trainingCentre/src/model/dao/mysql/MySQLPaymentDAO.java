package model.dao.mysql;


import beans.SessionBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.PaymentDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Payment;
import model.PaymentType;
import model.Session;
import model.Status;
import model.User;
import model.UserRole;

/**
 * 
 */
public class MySQLPaymentDAO implements PaymentDAO {

    /**
     * Default constructor
     */
    public MySQLPaymentDAO() {
    }
    
    private static MySQLPaymentDAO instance;
    
    static{
        instance  = new MySQLPaymentDAO();
    }
    
    public static MySQLPaymentDAO getInstance(){
        return instance;
    }
    
     private static final String SAVE = "INSERT INTO payments(paymentsType,amountPayments,user,session,created) VALUES(?,?,?,?,now())";
     private static final String GETALL = "SELECT idPayments, payments.paymentsType, amountPayments, payments.user, payments.session, payments.created, payments.enabled, idPaymentsType, namePaymentsType, enabledPaymentsType, idUsers, role, name, surname, email, idUserRoles, nameUserRoles, enabledUserRoles, idSession, titleSession, idStatus, nameStatus, discount, enabledStatus FROM payments JOIN users ON payments.user = idUsers JOIN userRoles ON role =idUserRoles JOIN paymentsType ON payments.paymentsType = idPaymentsType JOIN session ON payments.session = idSession JOIN status ON users.status = idStatus WHERE payments.enabled = 1";
//     private static final String DELETE = "DELETE FROM payments WHERE user = ? AND session = ?";
     private static final String DELETE = "DELETE FROM payments WHERE idPayments =?";

    @Override
    public Optional<Payment> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Payment> getAll() {
        
        ArrayList<Payment> payments = new ArrayList<Payment>();
        
         Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        User u = null;
        UserRole ur = null;
        Status st = null;
        Session s = null;

//        SessionBean sb = null;
        PaymentType pt = null;
        Payment p = null;
        
        
        try {
            c = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            ps= c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            while(rs.next()){
                
                
                ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                
                st = new Status(rs.getInt("idStatus"), rs.getString("nameStatus"), rs.getInt("discount"),rs.getBoolean("enabledStatus"));
                
                pt = new PaymentType(rs.getInt("idPaymentsType"),rs.getString("namePaymentsType"),rs.getBoolean("enabledPaymentsType"));
                
                Long sessID = rs.getLong("idSession");
                
                s= new Session();
                s.setId(sessID);
                s.setTitle(rs.getString("titleSession"));
                
//                s =  new Session(sessID,rs.getString("titleSession"));
                
                u = new User(rs.getInt("idUsers"),ur,rs.getString("name"),rs.getString("surname"),rs.getString("email"));
                
                p =  new Payment(rs.getInt("idPayments"),pt,rs.getDouble("amountPayments"),u,s,rs.getTimestamp("created"),st,rs.getBoolean("enabled"));
                
                payments.add(p);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     finally{   
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
        return payments;
        
                
        
        
       
    }



    @Override
    public int save(Payment t) {
        Connection c = null;
        PreparedStatement ps = null;
        int status = 0;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps = c.prepareStatement(SAVE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, t.getPaymentType().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setDouble(2, t.getAmount());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(3, t.getUser().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setLong(4, t.getSession().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }//end of finally loop
        
        return status;
    }

    @Override
    public int update(Payment t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Payment t) {
        Connection c = null;

        PreparedStatement ps = null;
        int status = 0;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps = c.prepareStatement(DELETE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1,t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLPaymentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        return status;
    }

    @Override
    public List<Payment> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}