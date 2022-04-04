package model;

import java.util.*;
import model.dao.AbstractDAOFactory;
import model.dao.SessionDAO;

/**
 *
 */
public class Classroom {

    AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
    SessionDAO sessionDAO = factory.createSessionDAO();

    /**
     * Default constructor
     */
    public Classroom() {
    }

    private int id;

    private String name;

    private int capacity;

    private boolean enabled;

    public Classroom(int id, String name, int capacity, boolean enabled) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.enabled = enabled;
    }

    ///////methods
    public boolean hasSufficientCapacity(Session session) {
        boolean hasSufficientCapacity = false;
        //if the Classroom capacity is greater than the Session's capacity, hasSufficientCapacity = true
        if (this.getCapacity() >= session.getCapacity()) {
            hasSufficientCapacity = true;
        } else {
            hasSufficientCapacity = false;

        }

        return hasSufficientCapacity;
    }

    public boolean classroomHasAConflict(Session session) {
        boolean classroomHasAConflict = false;

        //get List<Session> using this classroom
        Collection<Session> sessions = sessionDAO.sessions();

        for (Session s : sessions) {

            //three conditions need to be true in order for there to be a conflict:
            //  - the Session must take in the same semester
            // - AND the same day of the week
            // - AND take place in the same Classroom
            //if both Sessions take place in the spring semester and take place on the same day of the week, classroomHasConflict = true
            if (session.isAutumnSemester() && s.isAutumnSemester() && session.getDayOfTheWeek() == s.getDayOfTheWeek() && s.getClassroom().getId() == this.getId()) {
                classroomHasAConflict = true;
                //if both Sessions take place in the spring semester and take place on the same day of the week, classroomHasConflict = true
            } else if (!session.isAutumnSemester() && !s.isAutumnSemester() && session.getDayOfTheWeek() == s.getDayOfTheWeek() && s.getClassroom().getId() == this.getId()) {

                classroomHasAConflict = true;
            }

        } // end of Session s: sessions loop

        return classroomHasAConflict;
    }

    public boolean hasConflicts(Session session) {
        
      
        
       boolean hasConflicts = false;
       
       
          //get List<Session> using this classroom
        Collection<Session> sessions = sessionDAO.sessions();

        for (Session s : sessions) {

            //three conditions need to be true in order for there to be a conflict:
            //  - the Session must take in the same semester
            // - AND the same day of the week
            // - AND take place in the same Classroom
            //if both Sessions take place in the spring semester and take place on the same day of the week, classroomHasConflict = true
            if (session.isAutumnSemester() && s.isAutumnSemester() && session.getDayOfTheWeek() == s.getDayOfTheWeek() && s.getClassroom().getId() == this.getId()) {
                hasConflicts = true;
                //if both Sessions take place in the spring semester and take place on the same day of the week, classroomHasConflict = true
            } else if (!session.isAutumnSemester() && !s.isAutumnSemester() && session.getDayOfTheWeek() == s.getDayOfTheWeek() && s.getClassroom().getId() == this.getId()) {

                hasConflicts = true;
            }
            
            else if(this.getCapacity() < session.getCapacity()){
                
                hasConflicts = true;
                
            }

        } // end of Session s: sessions loop

       return hasConflicts;
       
    }

//        public boolean classroomHasAConflict(Session session){
//        boolean classroomHasAConflict = false;
//        
//        //get List<Session> using this classroom
//        
//        Collection <Session> sessions = sessionDAO.sessions();
//        
//        List<Session> sessionsWithThisClassroom = new ArrayList<Session>();
//        
//        for(Session s: sessions){
//            
//            if(s.getClassroom().equals(this)){
//                sessionsWithThisClassroom.add(s);
//            }
//            
//             }// end of Session s: sessions loop
//            
//                    // now to check Session session against the List<Session> sessionsWithThisClassroom
//        for(Session sess: sessionsWithThisClassroom){
//            if(session.isAutumnSemester() && sess.isAutumnSemester() && session.getDayOfTheWeek() == sess.getDayOfTheWeek()){
//                classroomHasAConflict = true;
//                //if both Sessions take place in the spring semester and take place on the same day of the week, classroomHasConflict = true
//            }else if(!session.isAutumnSemester() && !sess.isAutumnSemester() && session.getDayOfTheWeek() == sess.getDayOfTheWeek()){
//                
//                classroomHasAConflict = true;
//            }
//            
//            else{
//                
//                 classroomHasAConflict = false;
//            }
//        }// end of Session s: sessionsWithThisClassroom loop
//        
//   
//
//       
//        
//
//        
//        return classroomHasAConflict;
//    }
//    
//    public boolean classroomHasAConflict(Session session){
//        boolean classroomHasAConflict = true;
//        
//        //get List<Session> using this classroom
//        
//        Collection <Session> sessions = sessionDAO.sessions();
//        
//        List<Session> sessionsWithThisClassroom = new ArrayList<Session>();
//        
//        for(Session s: sessions){
//            
//            if(s.getClassroom().equals(this)){
//                sessionsWithThisClassroom.add(s);
//            }
//            
//                    // now to check Session session againstr the List<Session> sessionsWithThisClassroom
//        for(Session sess: sessionsWithThisClassroom){
//            if(session.isAutumnSemester() && sess.isAutumnSemester() && session.getDayOfTheWeek() == sess.getDayOfTheWeek()){
//                classroomHasAConflict = true;
//            }else{
//                
//                 classroomHasAConflict = false;
//            }
//        }// end of Session s: sessionsWithThisClassroom loop
//        
//   
//
//        }// end of Session s: sessions loop
//        
//
//        
//        return classroomHasAConflict;
//    }
//    
    ////////////// Getters and Setters, etc
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Classroom{" + "id=" + id + ", name=" + name + ", capacity=" + capacity + ", enabled=" + enabled + '}';
    }

}
