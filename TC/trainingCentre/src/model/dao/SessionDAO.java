package model.dao;


import java.util.*;
import model.Session;
import model.User;

/**
 * 
 */
public interface SessionDAO extends DAO<Session>{
    
    public Collection<Session> sessions();
    
    public Collection<Session> sessions(User user);
    
    

}