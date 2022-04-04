/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Cathal
 */
public class MySQLConnectionFactory {
    
    private static MySQLConnectionFactory instance;
    
    private MySQLConnectionFactory(){};

    //static getter
    public static MySQLConnectionFactory getInstance() {
        if(instance ==null)
            instance = new MySQLConnectionFactory();
        return instance;
    }
    
    public Connection getConnection(String dburl, String user, String password){ // this needs to take 3 arguments
        
 
        Connection c = null;
        try{
            c = DriverManager.getConnection(dburl, user, password);
        }catch(SQLException e){e.printStackTrace();}
        return c;
        
    }
    
    
    
}
