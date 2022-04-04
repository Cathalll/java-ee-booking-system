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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PaymentType;
import model.dao.PaymentDAO;
import model.dao.PaymentTypeDAO;

/**
 *
 * @author Cathal
 */
public class MySQLPaymentTypeDAO implements PaymentTypeDAO {
    
      public MySQLPaymentTypeDAO(){}
      
          private static MySQLPaymentTypeDAO instance;
    
    static {
        instance = new MySQLPaymentTypeDAO();
    }
    
    public static MySQLPaymentTypeDAO getInstance(){
        return instance;
    }
    
    private static final String GETALL = "SELECT idPaymentsType, namePaymentsType, enabledPaymentsType from paymentsType where enabledPaymentsType = 1";
    

    @Override
    public List<PaymentType> getAll() {
        
     ArrayList<PaymentType> paymentTypes = new ArrayList<PaymentType>();
     
       Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        
        PaymentType pt = null;
        
          try {
              c = CheckProperties.checkConnectionDB();
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(MySQLPaymentTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
          }
         
          try {
              ps= c.prepareCall(GETALL);
          } catch (SQLException ex) {
              Logger.getLogger(MySQLPaymentTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          try {
              rs = ps.executeQuery();
          } catch (SQLException ex) {
              Logger.getLogger(MySQLPaymentTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
          }
           
          try {
              while(rs.next()){
                  
                  pt = new PaymentType(rs.getInt("idPaymentsType"),rs.getString("namePaymentsType"),rs.getBoolean("enabledPaymentsType"));
                  
                  paymentTypes.add(pt);
                  
              }
          } catch (SQLException ex) {
              Logger.getLogger(MySQLPaymentTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          try {
              rs.close();
          } catch (SQLException ex) {
              Logger.getLogger(MySQLPaymentTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          
          try {
              ps.close();
          } catch (SQLException ex) {
              Logger.getLogger(MySQLPaymentTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          
          try {
              c.close();
          } catch (SQLException ex) {
              Logger.getLogger(MySQLPaymentTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          
          return paymentTypes;
    }
    
}
