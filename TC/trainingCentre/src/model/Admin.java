package model;
import java.util.*;

/**
 * 
 */
public class Admin extends User {

    /**
     * Default constructor
     */
    public Admin() {
    }

    public Admin(int id, UserRole userRole, String name, String surname, String telephone, String email, boolean enabled, Address address) {
        super(id, userRole, name, surname, telephone, email, enabled, address);
    }

    

    @Override
    public String toString() {
        return "Admin{" + super.toString() +'}';
    }
    
    



}