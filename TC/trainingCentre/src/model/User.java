package model;
import java.io.Serializable;
import java.util.*;
import model.dao.AbstractDAOFactory;
import model.dao.SessionDAO;
import model.dao.mysql.MySQLSessionDAO;

/**
 * 
 */
public class User implements Serializable {
    
    AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
    SessionDAO sessionDAO = factory.createSessionDAO();

    /**
     * Default constructor
     */
    public User() {
    }
    
    
//     public User(Student student){}


    int id;
    
    UserRole userRole;

    String name;

    String surname;
    
    private String telephone;
    
    private String email;
    
    private boolean enabled;
    
    private Address address;

    public User(int id, UserRole userRole, String name, String surname, String telephone, String email, boolean enabled, Address address) {
        this.id = id;
        this.userRole = userRole;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
        this.enabled = enabled;
        this.address = address;
    }
    
    public User(int id, UserRole userRole, String name, String surname, String email, boolean enabled){
        this.id = id;
        this.userRole = userRole;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.enabled = enabled;

        
    }
    
        public User(int id, UserRole userRole, String name, String surname, String email){
        this.id = id;
        this.userRole = userRole;
        this.name = name;
        this.surname = surname;
        this.email = email;
       

        
    }
    
    
    /////methods
    
   public boolean sessionIsInConflict(Session session){
//        SessionDAO sessionDAO =  new MySQLSessionDAO();
        
        boolean isConflict = false;
        
        //get all Session where User is enrolled
        Collection <Session> enrolledSessions = sessionDAO.sessions(this);
        for(Session s: enrolledSessions){
            // User already has Session in the same semester in same day of the week - both conditions need ot be true in order
            // for there to be a conflict
            if(session.isAutumnSemester() && s.isAutumnSemester() && session.getDayOfTheWeek() == s.getDayOfTheWeek()){
                isConflict = true;
               //same as abo
            }else if(!session.isAutumnSemester() && !s.isAutumnSemester() && session.getDayOfTheWeek() == s.getDayOfTheWeek()){
                
                isConflict = true;
 
        }

        
    }
        
          return isConflict;
    
   }
        
   //////// getters and setters, etc

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
//
//    @Override
//    public String toString() {
//        return "User{" + "id=" + id + ", userRole=" + userRole + ", name=" + name + ", surname=" + surname + ", telephone=" + telephone + ", email=" + email + ", enabled=" + enabled + ", address=" + address + '}';
//    }

    
    



}