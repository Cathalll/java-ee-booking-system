package model;
import java.util.*;
import model.dao.AbstractDAOFactory;
import model.dao.SessionDAO;

/**
 * 
 */
public class Teacher extends User {

    /**
     * Default constructor
     */
    public Teacher() {
    }
    
    
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
    SessionDAO sessionDAO = factory.createSessionDAO();
    
    ///////////////////////methods
    
    public boolean teacherHasAConflict(Session session){
        
        boolean teacherHasAConflict = false;
        
        Collection <Session> sessions = sessionDAO.sessions();
        
        ArrayList<Session> teacherSessions = new ArrayList<Session>();
        
        for(Session s: sessions){
            if(s.getTeacher().getId() == this.getId()){
                teacherSessions.add(s);
            }
        }
        
        
        
      
       
              //three conditions need to be true in order for there to be a conflict:
            //  - the Session must take in the same semester
            // - AND the same day of the week
            // - AND have the same Teacher assigned to teach it
            
       
       for(Session s: teacherSessions){
           
            //if both Sessions take place in the spring semester and take place on the same day of the week, teacherHasAConflict = true
            if(session.isAutumnSemester() && s.isAutumnSemester() && session.getDayOfTheWeek() == s.getDayOfTheWeek()){
                teacherHasAConflict = true;
                //if both Sessions take place in the spring semester and take place on the same day of the week, teacherHasAConflict = true
            }else if(!session.isAutumnSemester() && !s.isAutumnSemester() && session.getDayOfTheWeek() == s.getDayOfTheWeek()){
                
               teacherHasAConflict = true;
            }
            
            
           
           
       }
       
       return teacherHasAConflict;
        
        
        
    }
    
    
////////////constructor, getters and setters, etc

    public Teacher(int id, UserRole userRole, String name, String surname, String email, boolean enabled) {
        super(id, userRole, name, surname, email, enabled);
    }
    
    

    @Override
    public String toString() {
        return "Teacher{" + super.toString() +'}';
    }
    
    
    
    

  
  
    
    


}