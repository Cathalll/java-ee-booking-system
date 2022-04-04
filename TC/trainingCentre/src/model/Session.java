package model;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * 
 */
public class Session {

    /**
     * Default constructor
     */
    public Session() {
    }

    private Long id;

    private Training training;

    private Timestamp startDate;

    private Timestamp endDate;

    private int duration;

    private int recurs;

    private Classroom classroom;

    private Teacher teacher;

    private int capacity;

    private String title;

    private boolean enabled;
    
    private List<Student> students;

//    private List<Discount> discounts;

    public Session(Long id, Training training, Timestamp startDate, Timestamp endDate, int duration, int recurs, Classroom classroom, Teacher teacher, int capacity, String title, boolean enabled, List<Student> students) {
        this.id = id;
        this.training = training;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.recurs = recurs;
        this.classroom = classroom;
        this.teacher = teacher;
        this.capacity = capacity;
        this.title = title;
        this.enabled = enabled;
        this.students = students;
    }
    
    ///constructor with fewer parameters for use in Payments object
    
//    public Session(Long id, String title){
//        this.id = id;
//        this.title = title;
//        
//        
//    }

    ///methods

   
    
//  public boolean hasConflict(User user){
//      
//      
//  }
    
        public void addStudent(Student student) {
        students.add(student);
    }
        
    public int getDayOfTheWeek(){
        
        LocalDateTime localDT = LocalDateTime.fromDateFields(startDate);
        int weekDay = localDT.getDayOfWeek();
        return weekDay;
        

        
    }  
    
    public Boolean isAutumnSemester(){
        
       boolean result = false; 
       LocalDateTime endDateLDT = LocalDateTime.fromDateFields(endDate);
       //All spring semester courses are finished by the end of June - so, any autumn semester would finish later than taht
       if(endDateLDT.getMonthOfYear() > 6){
           result = true;
       }
       
       return result;
        
    }
    
    public int numberOfStudentsEnrolled(){
        int result = this.getStudents().size();
        
        return result;
    }
    
    public void setDiscountedPrice(Status status){
        
    }
    
    public int placesRemaining(){
        int placesRemaining = this.capacity - this.numberOfStudentsEnrolled();
        
        return placesRemaining;
        
        
    }
    
    public boolean studentIsEnrolled(User user){
        boolean isEnrolled = false;
        

  
        
  //getAllStudents currently enrolled
        
       List<Student> students = this.getStudents();
       
       for(Student s: students){
           if(s.getId() == user.getId())
               isEnrolled = true;
       }
      
       
   
       
       return isEnrolled;
        
       
    
    }
    
    
        public boolean studentIsEnrolled(Student student){
        boolean isEnrolled = false;
        

  
        
  //getAllStudents currently enrolled
        
       List<Student> students = this.getStudents();
       
       for(Student s: students){
           if(s == student)
               isEnrolled = true;
       }
      
       
   
       
       return isEnrolled;
        
       
    
    }
    
    
    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRecurs() {
        return recurs;
    }

    public void setRecurs(int recurs) {
        this.recurs = recurs;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Session{" + "id=" + id + ", training=" + training + ", startDate=" + startDate + ", endDate=" + endDate + ", duration=" + duration + ", recurs=" + recurs + ", classroom=" + classroom + ", teacher=" + teacher + ", capacity=" + capacity + ", title=" + title + ", enabled=" + enabled + ", students=" + students + '}';
    }



    
    

}