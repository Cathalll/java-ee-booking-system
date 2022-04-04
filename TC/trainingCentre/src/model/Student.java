package model;
import java.util.*;

/**
 * 
 */
public class Student extends User {

    /**
     * Default constructor
     */
        public Student(){}
//    public User user;
private Status status;



/////methods


 
 
  public boolean studentHasAConflict(Session session){
        
        boolean studentHasAConflict = true;
        
        Collection <Session> sessions = sessionDAO.sessions();
        
       Student student = this;
       
//       User user = student.
       
              //three conditions need to be true in order for there to be a conflict:
            //  - the Session must take in the same semester
            // - AND the same day of the week
            // - AND the student must already be registered to the Session
            
       
       for(Session s: sessions){
           
           List<Student> students = s.getStudents();
//           
           for(Student st: students){
//           
            //if both Sessions take place in the spring semester and take place on the same day of the week, studentHasAConflict = true
            if(session.isAutumnSemester() && s.isAutumnSemester() && session.getDayOfTheWeek() == s.getDayOfTheWeek() && st.getId() == this.getId()){
                studentHasAConflict = true;
                //if both Sessions take place in the spring semester and take place on the same day of the week, studentHasAConflict = true
            }else if(!session.isAutumnSemester() && !s.isAutumnSemester() && session.getDayOfTheWeek() == s.getDayOfTheWeek() &&  st.getId() == this.getId()){
                
              studentHasAConflict = true;
            }
            
            else{
                
                 studentHasAConflict = false;
            }
           
           }// end of Student st: students loop
       }// end of Session s: sessions loop
       
       return studentHasAConflict;
        
        
        
    }


///constructor, getter and setter

    public Student(Status status, int id, UserRole userRole, String name, String surname, String telephone, String email, boolean enabled, Address address) {
        super(id, userRole, name, surname, telephone, email, enabled, address);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    

    
    
    
    
    

//    @Override
//    public String toString() {
//        return "Student{" + super.toString() +'}';
//    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//    
//    

    @Override
    public String toString() {
        return "Student{" + super.toString() + "status=" + status + '}';
    }
    
    


    
    
    
    
    
    


    
    


}