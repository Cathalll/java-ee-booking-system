package model.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.TrainingDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Price;
import model.Training;

/**
 * 
 */
public class MySQLTrainingDAO implements TrainingDAO {

    /**
     * Default constructor
     */
    public MySQLTrainingDAO() {
    }
    
    private static MySQLTrainingDAO instance;
    
    static{
        instance  = new MySQLTrainingDAO();
    }
    
    public static MySQLTrainingDAO getInstance(){
        return instance;
    }
    

    
    
    private static final String GETALL = "SELECT idTraining, titleTraining, description, price,duration,trainingcapacity, enabledTraining, idPrices, amount, namePrices, enabledPrices FROM training JOIN prices on price= idPrices WHERE enabledTraining = 1";
    private static final String GETBYID = "SELECT idTraining, titleTraining, description, price,duration, trainingcapacity, enabledTraining,idPrices, amount, namePrices, enabledPrices FROM training JOIN prices on price= idPrices WHERE enabledTraining = 1 AND idTraining = ?";
    private static final String DELETE = "UPDATE training SET enabledTraining = 0 WHERE idTraining = ?";
    private static final String SAVE = "INSERT INTO training(titleTraining,price, duration, trainingCapacity,description) VALUES(?,?,?,?,?)";
    private static final String UPDATE = "UPDATE training SET titleTraining = ?, description = ?, price = ?, duration = ?, trainingCapacity =? WHERE idTraining = ?";
    
    
    

    @Override
    public Optional<Training> get(int id) {
       
        Training t = null;
        Price p =null;
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps = c.prepareStatement(GETBYID);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1,id);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(rs.next()){
                
                 p = new Price(rs.getInt("idPrices"),rs.getDouble("amount"),rs.getString("namePrices"),rs.getBoolean("enabledPrices"));
                
                t = new Training(rs.getInt("idTraining"),rs.getString("titleTraining"),rs.getString("description"),p,rs.getInt("duration"),rs.getInt("trainingCapacity"),
                        rs.getBoolean("enabledTraining"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        
        
        return Optional.ofNullable(t);
        
        
        
    }

    @Override
    public List<Training> getAll() {
        
        List<Training> trainings =  new ArrayList<Training>();
           Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        Price p = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps= c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(rs.next()){
                
                p = new Price(rs.getInt("idPrices"),rs.getDouble("amount"),rs.getString("namePrices"),rs.getBoolean("enabledPrices"));
                
                Training t = new Training(rs.getInt("idTraining"),rs.getString("titleTraining"),rs.getString("description"),p,rs.getInt("duration"),rs.getInt("trainingCapacity"),
                        rs.getBoolean("enabledTraining"));
                
                trainings.add(t);
    
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return trainings;
        
    }

    @Override
    public List<Training> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int save(Training t) {
        
          Connection c = null;
        PreparedStatement ps = null;
        int status = 0;
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps = c.prepareStatement(SAVE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(1,t.getTitle());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(2, t.getPrice().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(3, t.getDuration());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(4, t.getCapacity());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(5,t.getDescription());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
              try {
                  ps.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              try {
                  c.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
        
        return status;
        
        
    }

    @Override
    public int update(Training t) {
        
          int status = 0;
          
//          Training tr = null;
//          Price p = null;
        
         Connection c = null;
       
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(1,t.getTitle());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(2, t.getDescription());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(3,t.getPrice().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(4, t.getDuration());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(5, t.getCapacity());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(6,t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
              try {
                  ps.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              try {
                  c.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              
            
        }
        
        return status;

        
        
    }

    @Override
    public int delete(Training t) {
        
                
         int status = 0;
        Connection c = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(DELETE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
             try {
                 ps.close();
             } catch (SQLException ex) {
                 Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            
             try {
                 c.close();
             } catch (SQLException ex) {
                 Logger.getLogger(MySQLTrainingDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        
        
        return status;
       
    }

}