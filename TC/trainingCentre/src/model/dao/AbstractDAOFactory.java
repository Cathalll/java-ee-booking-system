/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.mysql.MySQLDAOFactory;

/**
 *
 * @author Cathal
 */
public abstract class AbstractDAOFactory {
    

    private static AbstractDAOFactory factory;
    
    public static void setFactory(AbstractDAOFactory factory) {
        AbstractDAOFactory.factory = factory;
    }

    public static AbstractDAOFactory getFactory() {
       return MySQLDAOFactory.getInstance();
    }
    
    
    
  
    
        public abstract AddressDAO createAddressDAO();
        
        public abstract ClassroomDAO createClassroomDAO();
        
        public abstract UserRoleDAO createUserRoleDAO();

        public abstract PriceDAO createPriceDAO();

    public abstract TrainingDAO createTrainingDAO();

    public abstract DiscountDAO createDiscountDAO(); 

    public abstract UserDAO createUserDAO();

    public abstract StudentDAO createStudentDAO();

    public abstract TeacherDAO createTeacherDAO();

    public abstract SessionDAO createSessionDAO();

    public abstract LoginDAO createLoginDAO();
    
    public abstract PaymentTypeDAO createPaymentTypeDAO();

    public abstract PaymentDAO createPaymentDAO();

    public abstract StatusDAO createStatusDAO(); 
            
            
            
   
    
    
}
