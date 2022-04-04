package model.dao.mysql;


import beans.LoginBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.UserDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;
import model.Admin;
import model.Status;
import model.Student;
import model.Teacher;
import model.User;
import model.UserRole;

/**
 * 
 */
public class MySQLUserDAO implements UserDAO {

    /**
     * Default constructor
     */
    public MySQLUserDAO() {
    }
    
    private static MySQLUserDAO instance;
    
    static{
        instance  = new MySQLUserDAO();
    }
    
    public static MySQLUserDAO getInstance(){
        return instance;
    }
    
    
        private static final String GETALL = "SELECT idUsers, role, name, surname, telephone, email, enabled,idAddress, street, houseNumber, city, postcode, enabledAddress,idUserRoles, nameUserRoles, enabledUserRoles from users JOIN address on idUsers = idUser JOIN userRoles ON role =idUserRoles where enabled = 1";
    private static final String GETBYID = "SELECT idUsers, role, name, surname, telephone, email, enabled,idAddress, street, houseNumber, city, postcode, enabledAddress,idUserRoles, nameUserRoles, enabledUserRoles from users JOIN address on idUsers = idUser JOIN userRoles ON role =idUserRoles  where enabled = 1 AND idUsers = ?";
    private static final String DELETE = "UPDATE users set enabled = 0 WHERE idUsers =?";
    private static final String SAVE = "INSERT INTO users(role,name, surname, telephone, email, password, status) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE users SET role = ?, name =?, surname = ?, telephone = ?, email = ?, password = ?, status =? WHERE idUsers = ?";
    private static final String LOGIN ="SELECT idUsers, role, name, surname,password, telephone, email, enabled,idAddress, street, houseNumber, city, postcode, enabledAddress,idUserRoles, nameUserRoles, enabledUserRoles from users JOIN address on idUsers = idUser JOIN userRoles ON role =idUserRoles  where enabled = 1 AND email = ?";
    
    

    @Override
    public Optional<User> get(int id) {
         Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
              User u = null;
        UserRole ur = null;
        Address a = null;
        
        try {
            c = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps = c.prepareStatement(GETBYID);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1,id);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(rs.next()){
                
                ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                 
                 a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
                rs.getBoolean("enabledAddress"));
                 
                 u = new User(rs.getInt("idUsers"), ur, rs.getString("name"), rs.getString("surname"), rs.getString("telephone"), rs.getString("email"),rs.getBoolean("enabled"), a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
             try {
                 rs.close();
             } catch (SQLException ex) {
                 Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
                    
             try {
                 ps.close();
             } catch (SQLException ex) {
                 Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
            
             try {
                 c.close();
             } catch (SQLException ex) {
                 Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        return Optional.ofNullable(u);
        
  
        
        
    }

    @Override
    public List<User> getAll() {
        ArrayList<User> users = new ArrayList<User>();
       Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
          User u = null;
        UserRole ur = null;
        Address a = null;
        try {
            c = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        
        try {
            ps= c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(rs.next()){
                 ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                 
                 a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
                rs.getBoolean("enabledAddress"));
                 
                 u = new User(rs.getInt("idUsers"), ur, rs.getString("name"), rs.getString("surname"), rs.getString("telephone"), rs.getString("email"),rs.getBoolean("enabled"), a);
                 
                 users.add(u);
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
                
        
        return users;
    }

    @Override
    public List<User> getAll(Object o) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public int save(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Optional<User> authenticate(LoginBean loginBean) {
         String email = loginBean.getEmail();
         String password = loginBean.getPassword();
         
         UserRole ur = null;
         Address a = null;
         User u = null;
//         Status st = null;
         String dbPassword = null;
         Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
        try {
            ps = c.prepareStatement(GETBYID);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(1, email);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if(rs.next()){
                
                 ur = new UserRole(rs.getInt("idUserRoles"),rs.getString("nameUserRoles"), rs.getBoolean("enabledUserRoles"));
                 
//                 st = new Status(rs.getInt("idStatus"), rs.getString("nameStatus"), rs.getInt("discount"),rs.getBoolean("enabledStatus"));
                 
                 a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
                rs.getBoolean("enabledAddress"));
                 
//                 u = new User(rs.getInt("idUsers"), ur, rs.getString("name"), rs.getString("surname"), rs.getString("telephone"), rs.getString("email"),rs.getBoolean("enabled"), a);
                    u = new User();
                    u.setName(rs.getString("name"));
                    
                 
                 dbPassword = rs.getString("password");
                 
                 boolean pwCheck = BCrypt.BCrypt.checkpw(password,dbPassword);
                  
//                  if(pwCheck){
//                      
//                      System.out.println("password check has passed");
//                      if(u.getUserRole().getId() ==4){
//                          u = new Student();
//                          
//                      }else if(u.getUserRole().getId() ==3){
//                      u = new Teacher();
//                  }
//                      
//                      else if(u.getUserRole().getId() == 2){
//                          u = new Admin();
//                      }
//                  }else{
//                      System.out.println("passsword check has failed");
//                      u = null;
                  //}

            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        


         
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return Optional.ofNullable(u);
         
         
         
         
         
    }
    
    
    
    @Override
        public int save(User t, LoginBean loginBean, Status studentStatus) {
            
        Connection c = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        int status = 0;
        int lastUserID = 0;
        
        //strings for hashing password before insert
        String hashed ="";
        String plaintext ="";
        String salt = "";
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String lastUserSQL = "SELECT idUsers FROM users order by idUsers DESC LIMIT 1";
        
        try {
            ps = c.prepareStatement(SAVE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, t.getUserRole().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(2,t.getName());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(3, t.getSurname());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(4,t.getTelephone());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(5, t.getEmail());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
       plaintext = loginBean.getPassword();
         salt =  BCrypt.BCrypt.gensalt();
          hashed = BCrypt.BCrypt.hashpw(plaintext, salt);
        
        try {
            ps.setString(6,hashed);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(7,studentStatus.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(status ==1){
            
            try {
                ps1 = c.prepareStatement(lastUserSQL);
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                rs = ps1.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(rs.next()){
                    
                    lastUserID = rs.getInt("idUsers");
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }else{
            lastUserID = 0;
        }
        
       
      
              
              
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        try {
            ps1.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
                
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
              
              
              
       return lastUserID;
    }
    public int update(User t, LoginBean loginBean, Status studentStatus) {
        
        int status = 0;
        
         //strings for hashing password before insert
        String hashed ="";
        String plaintext ="";
        String salt = "";
        
          
        
         Connection c = null;
       
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        

         
        try {
            ps = c.prepareStatement(UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1,t.getUserRole().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(2,t.getName());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(3,t.getSurname());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(4,t.getTelephone());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(5,t.getEmail());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               plaintext = loginBean.getPassword();
         salt =  BCrypt.BCrypt.gensalt();
          hashed = BCrypt.BCrypt.hashpw(plaintext, salt);
          
        try {
            ps.setString(6,hashed);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(7,studentStatus.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        try {
            ps.setInt(8,t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return status;
         
         
        
    }

    @Override
    public int delete(User t) {
        
          int status = 0;
        Connection c = null;
        PreparedStatement ps = null;
        
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            ps = c.prepareStatement(DELETE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, t.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
              try {
                  ps.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
            
              try {
                  c.close();
              } catch (SQLException ex) {
                  Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
        
        }
        
        return status;
        
       
    }

    
    public int update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}