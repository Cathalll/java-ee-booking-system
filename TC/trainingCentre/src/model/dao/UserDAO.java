package model.dao;


import beans.LoginBean;
import java.util.*;
import model.Status;
import model.User;

/**
 * 
 */
public interface UserDAO extends DAO<User> {
    
     public int save(User t, LoginBean loginBean, Status studentStatus);
     
     public int update(User t, LoginBean loginBean, Status studentStatus);
     
      public Optional<User> authenticate(LoginBean loginBean);

}