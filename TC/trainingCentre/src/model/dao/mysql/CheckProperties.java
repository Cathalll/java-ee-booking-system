/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysql;

//import com.sun.javafx.fxml.PropertyNotFoundException;

import javax.el.PropertyNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Cathal
 */
public class CheckProperties {
    
       public static void checkDbDrivers(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            }catch(ClassNotFoundException cnfe){
                cnfe.printStackTrace();
        }
        
        
    }
       
       public static void checkSystemSetProprty(){
           try{
               System.setProperty("jdbc.driver", "com.mysql.jdbc.Driver");
           }catch(PropertyNotFoundException pnfe){
               pnfe.printStackTrace();
           }
       }
       
       public static Connection checkConnectionDB() throws ClassNotFoundException{
         Class.forName("com.mysql.jdbc.Driver");
      String dburl = "jdbc:mysql://127.0.0.1:3306/trainingCentre";
        
        ///dburl for the test database, for making inserts and updates and deletions in test phase
        //REMEMBER to comment it out and uncomment the live URL once the tests are completed
        
        //String dburl = "jdbc:mysql://127.0.0.1:3306/testTrainingCentre";
        
        Connection mysqlConnection = null;
        try{
            mysqlConnection = DriverManager.getConnection(dburl, "root","password");
        }catch(SQLException e){e.printStackTrace();}
        return mysqlConnection;
    }
       

    
    
    
    
}
