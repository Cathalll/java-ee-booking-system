package model.dao;


import java.util.*;
import model.Address;

/**
 * 
 */
public interface AddressDAO extends DAO<Address> {
    
    public List<Address> getAll();
    
     public int save(Address t, int lastUserID);
    
    
    
    
    

}