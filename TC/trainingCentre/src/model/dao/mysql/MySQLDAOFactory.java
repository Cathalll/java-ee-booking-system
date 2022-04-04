/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysql;

import java.sql.Connection;
import model.dao.AbstractDAOFactory;
import model.dao.AddressDAO;
import model.dao.ClassroomDAO;
import model.dao.DiscountDAO;
import model.dao.LoginDAO;
import model.dao.PaymentDAO;
import model.dao.PaymentTypeDAO;
import model.dao.PriceDAO;
import model.dao.SessionDAO;
import model.dao.StatusDAO;
import model.dao.StudentDAO;
import model.dao.TeacherDAO;
import model.dao.TrainingDAO;
import model.dao.UserDAO;
import model.dao.UserRoleDAO;

/**
 *
 * @author Cathal
 */
public class MySQLDAOFactory extends AbstractDAOFactory {
    
      //private constructor - so, can't be created outside the class
    private MySQLDAOFactory(){};
    
     private static MySQLDAOFactory instance;
     
     static{
         instance = new MySQLDAOFactory();
     }

    public static MySQLDAOFactory getInstance() {
        
        return instance;
    }
    
    
//    public Connection getConnection(){
//        Connection c = MySQLConnectionFactory.getInstance().getConnection(
//                "jdbc:mysql://127.0.0.1:3306/trainingCentre", "SGBD_USER","puS6WI1v8Rk06Jag");
//        return c;
//    }

    @Override
    public AddressDAO createAddressDAO() {
       return MySQLAddressDAO.getInstance();
    }

    @Override
    public ClassroomDAO createClassroomDAO() {
       return MySQLClassroomDAO.getInstance();
    }

    @Override
    public UserRoleDAO createUserRoleDAO() {
        return MySQLUserRoleDAO.getInstance();
    }

    @Override
    public PriceDAO createPriceDAO() {
       return MySQLPriceDAO.getInstance();
    }

    @Override
    public TrainingDAO createTrainingDAO() {
        return MySQLTrainingDAO.getInstance();
    }

    @Override
    public DiscountDAO createDiscountDAO() {
        return MySQLDiscountDAO.getInstance();
    }

    @Override
    public UserDAO createUserDAO() {
       return MySQLUserDAO.getInstance();
    }

    @Override
    public StudentDAO createStudentDAO() {
        return MySQLStudentDAO.getInstance();
    }

    @Override
    public TeacherDAO createTeacherDAO() {
        return MySQLTeacherDAO.getInstance();
    }

    @Override
    public SessionDAO createSessionDAO() {
       return MySQLSessionDAO.getInstance();
    }

    @Override
    public LoginDAO createLoginDAO() {
       return MySQLLoginDAO.getInstance();
    }

    @Override
    public PaymentTypeDAO createPaymentTypeDAO() {
        return MySQLPaymentTypeDAO.getInstance();
    }

    @Override
    public PaymentDAO createPaymentDAO() {
       return MySQLPaymentDAO.getInstance();
    }

    @Override
    public StatusDAO createStatusDAO() {
      return MySQLStatusDAO.getInstance();
    }
    
    
    
    
    
}
