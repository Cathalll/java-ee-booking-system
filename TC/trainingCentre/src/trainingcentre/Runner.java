/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainingcentre;

import beans.LoginBean;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import static java.sql.Timestamp.valueOf;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;
import model.Admin;
import model.Classroom;
import model.Discount;
import model.Payment;
import model.PaymentType;
import model.Price;
import model.Session;
import model.Status;
import model.Student;
import model.Teacher;

import model.Training;
import model.User;
import model.UserRole;
import model.dao.AbstractDAOFactory;
import model.dao.AddressDAO;
import model.dao.ClassroomDAO;
import model.dao.DiscountDAO;
import model.dao.LoginDAO;
import model.dao.PaymentDAO;
import model.dao.PaymentTypeDAO;
import model.dao.PriceDAO;
import model.dao.SessionDAO;
import model.dao.StatusDAO;
import model.dao.StudentDAO;
import model.dao.TeacherDAO;

import model.dao.TrainingDAO;
import model.dao.UserDAO;
import model.dao.UserRoleDAO;
import model.dao.mysql.MySQLAddressDAO;
import model.dao.mysql.MySQLDAOFactory;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

/**
 *
 * @author Cathal
 */
public class Runner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // TODO code application logic here
//        
//        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
//        
//        List<Address> addresses = new ArrayList<Address>();
//        AddressDAO AddressDAO = factory.createAddressDAO();
//        addresses = AddressDAO.getAll();

        ///this will go in the init method at the top of servlet
        //init method

       AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        


           AddressDAO addressDAO = factory.createAddressDAO();
           UserRoleDAO userRoleDAO = factory.createUserRoleDAO();
           PriceDAO priceDAO = factory.createPriceDAO();
           TrainingDAO trainingDAO = factory.createTrainingDAO();
           ClassroomDAO classroomDAO = factory.createClassroomDAO();
           DiscountDAO discountDAO = factory.createDiscountDAO();
           UserDAO userDAO = factory.createUserDAO();
           StudentDAO studentDAO = factory.createStudentDAO();
           TeacherDAO teacherDAO = factory.createTeacherDAO();
           SessionDAO sessionDAO = factory.createSessionDAO();
           LoginDAO loginDAO = factory.createLoginDAO();
           PaymentTypeDAO paymentTypeDAO = factory.createPaymentTypeDAO();
           PaymentDAO paymentDAO = factory.createPaymentDAO();
           StatusDAO statusDAO = factory.createStatusDAO();
           

           
//           User eddie4 = new User();
//           eddie4.setId(4)


//       List<Payment> payments = paymentDAO.getAll();
//       
//        System.out.println("payments.size() = " + payments.size());
        
//        System.out.println("testing the user entering '2' as their mode of payment");
//        
//        List<PaymentType> pts = paymentTypeDAO.getAll();
//        
//        
//        
//        int pT = 2;
//        
//        PaymentType paymentT = null;
//        
//        for(PaymentType p: pts ){
//            if(p.getId() == pT){
//                paymentT = p;
//            }
//        }
//        System.out.println("paymentT.getName() = " + paymentT.getName());
//        
//        
        ////
        
        
        ////
        
        User toby = userDAO.get(5).get();
        
        Collection<Session> tobySessions = sessionDAO.sessions(toby);
        
       
        int tobyInt = tobySessions.size();
        
        System.out.println("tobyInt = " + tobyInt);
                   
           Collection<Session> sessions = sessionDAO.sessions();
          
          Object o = null;
          
          Session session5 = null;
          
          for(Session s: sessions){
              if(s.getId().equals(5L)){
                  session5 = s;
              }
          }
          
          boolean sessionsContains4L = false;
          
          Session sess4 =  new Session();
          sess4.setId(4L);
          
//          if(sessions.contains(sessions.)){
//              sessionsContains4L = true;
//          }
          
          System.out.println("sessions contains 4L (shoudl be true)= " +sessionsContains4L);
          
//          
//          System.out.println("session5.getName() = " +session5.getTitle());
//          
//          int sess5Remaining = session5.placesRemaining();
//          
//          System.out.println("session5 number of places remaining = " + sess5Remaining);
//          
//          System.out.println("########");
//          
//          boolean lessThanOne = false;
//          
//          if(session5.placesRemaining() < 1){
//              lessThanOne = true;
//          }
//          
//          System.out.println("session5.placesRemaining is less than one = " + lessThanOne);
//          
          //int session5Count = session5.getStudents().size();
          
          //System.out.println("session5 is autumnSession: " + session5.isAutumnSemester());
          
          //System.out.println("session5 title = " + session5.getTitle());
          
          //System.out.println("numeber of students signed up for session #5 = " + session5Count);
          
//          List<Student> sess4Students = session4.getStudents();
//          
//          for(Student s: sess4Students){
//              System.out.println("s first name and surname = "+ s.getId() + ", " + s.getName() + " " + s.getSurname());
//          }
//          
//          System.out.println("sessoin 4 students = " + session4.getStudents().get);
          
//          System.out.println("#######################");
//
//        int myInt = Integer.parseInt("56");
//        
//        System.out.println("myInt + 4 = " +(myInt +4));
//
//        System.out.println("#################");
//        
//        String id = "4";
//        
//        Session session4L = new Session();
//        
//        session4L.setId(4L);
//        
//        boolean same = false;
//        
//        if(session4L.getId() ==  Integer.parseInt(id)){
//            same = true;
//            System.out.println("oh it worked");
//        }else{
//            
//            System.out.println("oh it did not work");
//            same = false;
//        }
//        
//        System.out.println("value of same  = " + same);
//        
        
        
        
        
//        
//        System.out.println("######################");
//
//
//        User eddie4 = userDAO.get(4).get();
//           
//           Collection<Session> sessionByUser = sessionDAO.sessions(eddie4);
//           
//           for(Session s: sessionByUser){
//               System.out.println(s.toString());
//           }
//           
//           
//           
//
//
//        System.out.println("#####################");
//        
//              System.out.println("testing the algo of classRoomHasAConflict with the code in Runner");
//              
//              Session sessionFour = null;
//              
//              for(Session s: sessions){
//                  if(s.getId().equals(4L)){
//                      sessionFour = s;
//                  }
//              }
//              
//              System.out.println("sessionFour title =" +sessionFour.getTitle());
//              
//              System.out.println("Sesion #4 takes place in spring semester in classroom #4 on aMonday");
//              
//              Session mondaySess = new Session();
//              //just assign the same start Date as sessionFour
//              
// 
//             mondaySess.setStartDate(sessionFour.getStartDate());
//             
//             String mondaySessEndString = "2022-05-23 18:00:00.000";
//             
//             
//             
//             mondaySess.setEndDate(valueOf(mondaySessEndString));
//             
//            
//              
//              Classroom classroomFour = sessionFour.getClassroom();
//              
//              System.out.println("classroomFour.getName() = " + classroomFour.getName());
//              
//             mondaySess.setClassroom(classroomFour);
//             
//             System.out.println("mondaySess.getClassroom() =" + mondaySess.getClassroom().getName());
//              
//              boolean conflict = classroomFour.classroomHasAConflict(mondaySess);
//              
//              System.out.println("classroomFour has a conflict wiht MondaySess (should be true) =  " + conflict); /// not working... shoot
//              
//              System.out.println("now create tuesdaySess, with startDate of mondaySess incremented by one day");
//              
//              Session tuesdaySess = new Session();
//              
//              String tuesday = "2022-01-04 18:00:00.000";
//              
//              Timestamp tuesdayStartDate = valueOf(tuesday);
//              
//              tuesdaySess.setStartDate(tuesdayStartDate);
//              
//              System.out.println("tuesdaySess.getStartDate() = " +tuesdaySess.getStartDate()); // great
//              
//               String tuesdayEndDate = "2022-05-24 18:00:00.000";
//        
//        
//        
//        tuesdaySess.setEndDate(valueOf(tuesdayEndDate));
//              
//              boolean tuesdayConf = classroomFour.classroomHasAConflict(tuesdaySess);
//              
//              System.out.println("classroomFour has a conflict with tesdaySess (should be false) = " +tuesdayConf);
//              
//              
//              System.out.println("should test now with two classes taking place in the same classroom, on the same day, but one different semesters");
//              
//              System.out.println("Session 3 and 4 fit this bill");
              
//              Classroom c4 = session3.getClassroom();
//              
//              System.out.println("c4 name =" + c4.getName());
//              
//              boolean c4ConflSessFour = c4.classroomHasAConflict(sessionFour);
//             
              
              //System.out.println("session3 has a conflict with Classroom 4 (c4 - should be false)" +c4ConflSessFour);
//              
//              System.out.println("###################");
//              
//              System.out.println("testing quickly (really, really quickly) teacherIsInconflict(Session session)");
//              
//              System.out.println("we already have two Sessions in conflict - just add the same teacher to this Session");
//              
//              System.out.println("get the teaceher of Sessoion four");
//              
//              Teacher t4 = sessionFour.getTeacher();
//              
//              System.out.println("assign t4 to mondaySess");
//              
//              mondaySess.setTeacher(t4);
//              
//              boolean t4HasConf = t4.teacherHasAConflict(mondaySess);
////              
//              System.out.println("Teacher of SEssion#4 has conflict with mondaySess (should be true) =  " +t4HasConf);
//              
//              System.out.println(" assign some other teacehr to tuesday sess");
//              
//              Teacher t5 = new Teacher();
//             
//              tuesdaySess.setTeacher(t5);
//              
//              boolean monTueConfl = t5.teacherHasAConflict(mondaySess);
//              
//              System.out.println("t5 has conflict with mondaySess (should be false) = " + monTueConfl);
//              
//////              
////              System.out.println("testing very quickly studentHasAConfl");
////              
//              System.out.println("i have been going about this all wrong - the 'StudentHasAConflict' method goes via the DAO, so the Sessions that they are signed up to needs to come from the DB");
//              
//              System.out.println("Student Eddie Eleve is signed up for Session#2 - create a dummy Session with the same start and end date, use this to test");
//              
//              Session session2 = null;
//              for(Session s: sessions){
//                  if(s.getId().equals(2L)){
//                      
//                      session2 = s;
//                  }
//              }
//              
//              System.out.println("session2.getTitle() = " + session2.getTitle());
//              
//              System.out.println("session2.getStudents.size() =" +  session2.getStudents().size());
//              
//              Student eddie = null;
//              
//              eddie = studentDAO.get(4).get();
//              
//              System.out.println("eddie toString() = " + eddie.getName() + " " +eddie.getId());
//              
//              System.out.println("###############");
//              
//              System.out.println("create clone of Session#2");
//              
//              Session session2a = new Session();
//              
//              session2a.setStartDate(session2.getStartDate());
//              
//             session2a.setEndDate(session2.getEndDate());
//             
//             System.out.println("session2a endDate = " + session2a.getEndDate());
//             
//             System.out.println("session2 endDate = " + session2.getEndDate());
//             
//             boolean eddieHasConflSession2a = eddie.studentHasAConflict(session2a);
//             
//             System.out.println("eddieHasConflSession2a (should be true) = " +eddieHasConflSession2a);
//             
//             
//             
//             
             
             

             
              


              
              
              
//              
//              System.out.println("st enrolled in sessionFour has a conflict with mondaySess (should be true): " + stIsConf);
//              
//              
//              
////              tuesdaySess.addStudent(st);
//               
//              boolean tuesMonStudConfl = st.studentHasAConflict(tuesdaySess);
//              
//              System.out.println("st enrolled in sessionFour has a conflict with tuesdaySess (should be false)" + tuesMonStudConfl);
              
              
                System.out.println("############################");  
             
                
                
//                System.out.println("try creating and enrolling a student in mmondaySess and then testing against sessionFour");
//                
//                Student stt = new Student();
//                
//                stt.setId(21);
//                
////                mondaySess.addStudent(stt);
//                
//                int mondaySessStudentSize = mondaySess.getStudents().size();
//                
////                System.out.println("mondaySess.getStudents().contatins(st1) = " + mondaySess.getStudents().contains(st1));
//
//                System.out.println("mondaySess.getStudents().size() = "+ mondaySessStudentSize);
                
                System.out.println("the problem in the end that my Session with no other attributes can;t have an ArrayList of students....... shit. this is really bad");
                
              
              
              
        
        
        
//        
//        
//        System.out.println("############################");
//        
//        
//        int sessionFourDayOfWeek = sessionFour.getDayOfTheWeek();
//        
//        System.out.println("sessionFour day of week = " + sessionFourDayOfWeek);
//        
//        int tuesdaySessDayOfWeek = tuesdaySess.getDayOfTheWeek();
//        
//        System.out.println("tuesdaySess day of week = " + tuesdaySessDayOfWeek);
//        
////        String tuesdayEndDate = "2022-05-24 18:00:00.000";
////        
////        
////        
////        tuesdaySess.setEndDate(valueOf(tuesdayEndDate));
//        
//        boolean tuesdaySessIsAutumn = tuesdaySess.isAutumnSemester();
//        
//        System.out.println("tuesdaySess is in Autumn = " + tuesdaySessIsAutumn);
//        
//        boolean mondaySessIsAutumn =mondaySess.isAutumnSemester();
//         System.out.println("mondaySess is in Autumn: " + mondaySessIsAutumn);
//         
//         boolean sessionFourinAutumn = sessionFour.isAutumnSemester();
//         
//         mondaySess.setClassroom(classroomFour);
//         
//         System.out.println("mondaySEss classroom = " + mondaySess.getClassroom().getName());
//         
//         System.out.println("sessionFour classroom = " + sessionFour.getClassroom().getName());
//        
//         
//         System.out.println("mondaySess and and sessionFour are on the same day, and in the same classroom, and both in spring. ");
//         
//         boolean mondayFourConfl = true;
//         
//         //if both Sessions are in the autumn semester, and on the same day, and in the same classroom, there is a conflict
//         ///just try putting the classroom test after &&
//         if(mondaySess.isAutumnSemester() && sessionFour.isAutumnSemester() && mondaySess.getDayOfTheWeek() == sessionFour.getDayOfTheWeek()){
//             
//             mondayFourConfl = true;
//         }else if(!mondaySess.isAutumnSemester() && !sessionFour.isAutumnSemester() && mondaySess.getDayOfTheWeek() == sessionFour.getDayOfTheWeek()){
//             mondayFourConfl = true;
//         }else{
//             mondayFourConfl = false;
//         }
//        
//           
//          System.out.println("mondayFourConfl is (should be true): " + mondayFourConfl ); 
//           
//          
//          System.out.println("##################");
//          
//          System.out.println("now to test if Session #4 exists (not appearing when i search for sesssions and students, but mayub becuase there are no students enrolled");
//          
//          for(Session s: sessions){
//              System.out.println(s.getId());
//          }
//          
//          System.out.println("ok, this only works when students are enrolled.... not necessarily a disaster");
//          
//          for(Session s: sessions)
//              if(s.getId().equals(4L)){
//                  System.out.println("the title of Session #4L = "+ s.getTitle());
//                  int enrolled = s.getStudents().size();
//                  System.out.println("number of students enrolled = " + enrolled);
////                  ArrayList<Student> students = new ArrayList<Student>();
//                  for(Student st: s.getStudents()){
//                      System.out.println(st.getId() + " " + st.getName());
//                  }
//                  
//                  
//              }
//          
//          
//          
//          
//          System.out.println("#####################");
//          
//          System.out.println("testing now that a particular student is enrolled in a Session");
//          
//          System.out.println("Eddie Eleve #4, is enrolled in session3");
//          
//          User eddieUser = userDAO.get(4).get();
//          
//          System.out.println("eddieUser.getName() + id = " + eddieUser.getId() + " " +eddieUser.getName());
//          
//          boolean eddieinSession3 = session3.studentIsEnrolled(eddieUser);
//          
//          System.out.println("eddie is enrolled in session3 = " + eddieinSession3);
//          
//          System.out.println("laryry mclearner, #9, is not enrolled in any Sessions");
//          
//          User larryUser = userDAO.get(9).get();
//          
//          System.out.println("larry name and id = " + larryUser.getId() + " " +larryUser.getName());
//          
//          boolean larryInSession3 = session3.studentIsEnrolled(larryUser);
//          
//          System.out.println("Larry is enrolled in Session #3 = " + larryInSession3);
//          
//          System.out.println("##############");
//          
//          Session mySession = new Session();
//          
//          mySession.setCapacity(45);
//          
//          System.out.println("capacity of mySession = " + mySession.getCapacity());
//          
//          
//          
//          
//                    System.out.println("numberOfStudentsEnrolled in session3 = " + session3.numberOfStudentsEnrolled());
//                    
//                    System.out.println("names and ids of all students enrolled in session3:");
//                    
//                     for(Student st: session3.getStudents()){
//                 System.out.println("st: "+ st.getId() + " " + st.getName() + " " + st.getSurname());
//             }
//                     
////                     System.out.println("#4 Eddie Eleve is enrolled twice - he appears only once in the payments table...");
//              
//          
//          System.out.println("try now for session #2, which should only have one student enrolled");
//          
//                      Session session2 = null;
//            for(Session s: sessions){
//                if(s.getId().equals(2L)){
//                    session2 = s;
//                }
//            }
//            
//                                 for(Student st: session2.getStudents()){
//                 System.out.println("st: "+ st.getId() + " " + st.getName() + " " + st.getSurname() + "status = " + st.getStatus());
//             }
//                                 
//                                 
//                                 System.out.println("now let's do a print of the teachers");
//                                 
//               System.out.println("teaher for session2 = "+ session2.getTeacher().getId() + " " +session2.getTeacher().getName() + " " + session2.getTeacher().getSurname());
//               
//               System.out.println("teaher for session3 = "+ session3.getTeacher().getId() + " " +session3.getTeacher().getName() + " " + session2.getTeacher().getSurname());
//               
//               System.out.println("let's get terry teacher's role:");
//               
//               System.out.println(session2.getTeacher().getUserRole().getName());
//          
//          
//          System.out.println("testing now students enrolled and placesRemaining");
//          
//          System.out.println("total places of session #3L = " + session.getCapacity());
//          
//          System.out.println("numberOfStudentsEnrolled = " + session.numberOfStudentsEnrolled());
//          
//          System.out.println("numberOfPlacesRemaining = "+session.placesRemaining());
//          
//          System.out.println("");
//          
//          System.out.println("##############");
//          
//          System.out.println("testing now the isEnrolled method of Session");
//          
//          
//          System.out.println("teacher #7 is the Teacher for both session  #3 and #4 - so, he should get 'true', and teacher #8 should get 'false' for these two Sessions");
//          
//          User teacherSeven = userDAO.get(7).get();
//          
//          User teacherEight = userDAO.get(8).get();
//          
//          //test they are actually teachers
//          
//          
//          System.out.println("teacherSeven = " + teacherSeven.getName() + " " +teacherSeven.getSurname() + "");
//          
//            System.out.println("teacherEight = " + teacherEight.getName() + " " +teacherEight.getSurname() + "");
//            
//            System.out.println("teacherSeven is enrolled for Session #3L  = " + session.userIsEnrolled(teacherSeven));
//            
//            System.out.println("teacherEight is enrolled for Session #3L  = " + session.userIsEnrolled(teacherEight));
//            
//            
//            System.out.println("########################");
//            
//            System.out.println("now testing for a student - user#5 is enrolled in Session #3L, while user#9 is not enrolled in anything");
//            
//            
//              User studentFive = userDAO.get(5).get();
//          
//          User studentTen = userDAO.get(9).get();
//          
//          System.out.println("studentFive = " + studentFive.getName() + " " +studentFive.getSurname() + "");
//          
//            System.out.println("studentNine = " + studentTen.getName() + " " +studentTen.getSurname() + "");  
//            
//            System.out.println("studentFive is enrolled in Session #3L = " +session.userIsEnrolled(studentFive));
//            System.out.println("studentNine is enrolled in Session #3L = " +session.userIsEnrolled(studentTen));
//            
//            
//            System.out.println("now to test studentFive on a Session he is not signed-in for yet (#2L)");
//            Session session2 = null;
//            for(Session s: sessions){
//                if(s.getId().equals(2L)){
//                    session2 = s;
//                }
//            }
//            
//             System.out.println("studentFive is enrolled in Session #2L = " +session2.userIsEnrolled(studentFive)); ////// this is wrong....
//             System.out.println(session2.getTitle());
//             
//             int session2Students = session2.getStudents().size();
//             
//             System.out.println("number of students enrolled in session #2 = " + session2Students);
//             
//             System.out.println("ok there are 6 students enroleld in session#2 - investigate this");
//             
//             for(Student st: session2.getStudents()){
//                 System.out.println("st: "+ st.getId() + " " + st.getName() + " " + st.getSurname());
//             }
//             
//             
//              System.out.println("number of students enrolled in session #3 = " + session.getStudents().size());
//              
//              
//              for(Student st: session.getStudents()){
//                 System.out.println("st: "+ st.getId() + " " + st.getName() + " " + st.getSurname());
//             }
//              
//              System.out.println("let's see if our sessions have Teachers assigned");
//              
//              System.out.println("Session #2 teacher count = " +session2.getTeacher().getId() + " " + session2.getTeacher().getName());
//              
//             System.out.println("SEssion #3 teacher =  " + session.getTeacher().getId() + " " +session.getTeacher().getName());
//             
//             
//             
//             System.out.println("################");
//             System.out.println("test Session #4L");
//             
//             Session sessionFour = null;
//             for(Session s: sessions){
//                 if(s.getId().equals(4L)){
//                     sessionFour = s;
//                 }
//             }
//
//         System.out.println("Session 4 enrolled = " + session.getStudents().size());
//         
//         System.out.println("sessionFour also has six enrolled...");
//         
//              System.out.println("SEssion #4 teacher =  " + sessionFour.getTeacher().getId() + " " +sessionFour.getTeacher().getName());
//              
//              
//              System.out.println("###############################");
//              
//              System.out.println("testing quickly Classroom.classroomHasAConflict(Session session)");
//              
//              System.out.println("Sesion #4 takes place in spring semester in classroom #4 on aMonday");
//              
//              Session mondaySess = new Session();
//              //just assign the same start Date as sessionFour
//              
// 
//             mondaySess.setStartDate(sessionFour.getStartDate());
//              
//              Classroom classroomFour = sessionFour.getClassroom();
//              
//              System.out.println("classroomFour.getName() = " + classroomFour.getName());
//              
//              boolean conflict = classroomFour.classroomHasAConflict(mondaySess);
//              
//              System.out.println("classroomFour has a conflict wiht MondaySess =  " + conflict); /// not working... shoot
//              
//              System.out.println("now create tuesdaySess, with startDate of mondaySess incremented by one day");
//              
//              Session tuesdaySess = new Session();
//              
//              String tuesday = "2022-01-04 18:00:00.000";
//              
//              Timestamp tuesdayStartDate = valueOf(tuesday);
//              
//              tuesdaySess.setStartDate(tuesdayStartDate);
//              
//              System.out.println("tuesdaySess.getStartDate() = " +tuesdaySess.getStartDate()); // great
//              
//              boolean tuesdayConf = classroomFour.classroomHasAConflict(tuesdaySess);
//              
//              System.out.println("classroomFour has a conflict with tesdaySess (should be false) = " +tuesdayConf);
//                      
//              
//              
         
//          System.out.println("o.toString() = " + o.toString());
//          
//          System.out.println("ok, so when you convert long 3L toSting, it drops the 'L'");
//          
//          System.out.println("#########");
//          
//          System.out.println("now try to convert toString, and back to long again");
//          
//          String oString = o.toString();
//          
//          long oLong = Long.parseLong(oString);
//          
//          System.out.println("value of oLoong = " + oLong);
//          
//          int x = 3;
//          
//          int result = (int) (x + oLong);
//          
//          System.out.println("add oLong to an int = " + result);
//          
//          System.out.println("#########");
//          
//          System.out.println("this is all fine, but we need to pass olong through our Sessions Collection now and see if finds anything");
//          
//          
//          
//          ArrayList<Session> cart = new ArrayList<Session>();
//          
//          String parameter = "3"; /// this works
//          
//          //whereas
//          //String parameter = "3L"; // does not work
//          
//          long myLong = Long.parseLong(parameter);
//          
//          System.out.println("myLong = "+myLong + " and type =" + " ");
//          
//          Session sessionSelected = null;
//          
//           for(Session s: sessions){
//            if(s.getId().equals(Long.parseLong(oString))){
//                session =s;
//                
//                cart.add(session);
//            }
//          }
//           
//           System.out.println("number of sessions added to cart = "+ cart.size());
//           
//           for(Session t:cart){
//               System.out.println("our Session ");
//           }
//           
//           
          
           
           
          
          
          
                     //System.out.println("testing session.isAutumnSemester");
//          Session session = null;
//          
//                   
//          for(Session s: sessions){
//            if(s.getId().equals(4L)){
//                session =s;
//            }
//          }
//          
//          boolean test = session.isAutumnSemester();
//          
//          System.out.println("Session #3 is autumn = " + test);
           
//         System.out.println("putting Sessions into a List");


         
//          Collection<Session> sessions = sessionDAO.sessions();
//          
//          Session session = null;
//          for(Session s: sessions){
//            if(s.getId().equals(3L)){
//                session =s;
//            }
//          }
//          
//          System.out.println("session #3 day of the week = " +session.getDayOfTheWeek());
////          System.out.println(session.toString());
//
//            System.out.println("session 3 startdate as timestamp  = " + session.getStartDate());
            
            
            
//
//LocalDateTime localDT = LocalDateTime.fromDateFields(session.getStartDate());
//int weekday = localDT.getDayOfWeek();
//
//        System.out.println("Weekday using LocalDateTime  = " +weekday);
//        
//        System.out.println("testing using today (tuesday's) timestamp");
//        
//        String today = "2021-12-07 02:11:56";
//        Timestamp todTS = valueOf(today);
//        
//        LocalDateTime localDTToday = LocalDateTime.fromDateFields(todTS);
//        
//        int todayWeekDay = localDTToday.getDayOfWeek();
//        
//        System.out.println("int value of dayOfWeek for today, Tuesday = " + todayWeekDay);
//        
//        
        
        
            
          
         
          
          
          
           
           
        //init method
        
        
//        System.out.println("testing statusDAO getByUser method");
//        
//        User u4 = new User();
//        u4.setId(4);
//        
//        Status st = statusDAO.getByUser(u4).get();
//        
//        System.out.println(st.toString());
//        
//        
//          System.out.println("testing again payments list, now  Session() as a parameter instead of SEssion (the old constructor is still defined");
//        
       List<Payment> payments = paymentDAO.getAll();
       
        System.out.println("payments.size() = " + payments.size());
        
        for(Payment p: payments ){
            System.out.println(p.toString());
        }
        
//        System.out.println("testing again payments list, now  wSessionBean as a parameter instead of SEssion (the old constructor is still defined");
//        
//       List<Payment> payments = paymentDAO.getAll();
//       
//        System.out.println("payments.size() = " + payments.size());
        

//        System.out.println("test paymentsType getAll (afterwards, redo PaymentsDAO");
//        
//        List<PaymentType> paymentTypes = paymentTypeDAO.getAll();
//        
//        for(PaymentType pt: paymentTypes){
//            System.out.println(pt.toString());
//        }
        
        
        
        
        
//        System.out.println("testing Users with the overloaded method (have removed session overloaded method, and it is no longer broken");
//        
//        
//         User eddie1 = userDAO.get(4).get();
//         
//         System.out.println("few of eddie1's params = " + eddie1.getEmail() + eddie1.getSurname());
//        
//        
//        
//        
//        System.out.println("testing PaymentDAO.getAll()");
//        
        
//        List<Payment> payments = paymentDAO.getAll();
//        
//        for(Payment p: payments){
//            System.out.println("id  = " + p.getId() + ",session name = " + p.getSession().getTitle());
//        }
////        System.out.println("test paymentsType getAll (afterwards, redo PaymentsDAO");
////        
////        List<PaymentType> paymentTypes = paymentTypeDAO.getAll();
////        
////        for(PaymentType pt: paymentTypes){
////            System.out.println(pt.toString());
////        }
//        
        
        
        
//        System.out.println("testing TestEvents");
//        
//        System.out.println("###############################");
//        
//        TestEventsDAO testEventsDAO = new MySQLTestEventsDAO();
//        
//        List<TestEvents> events = testEventsDAO.events();
//        
//        for(TestEvents te: events){
//            System.out.println(te.toString());
//        }
//        
//        
        System.out.println("###############################");
        
//        System.out.println("testing the checkUserExists(LoginBean loginBean) method");
//        
//        LoginBean loginBean = new LoginBean();
//        String email = "doesnotexist@mail.com";
//        loginBean.setEmail(email);
//       
//        boolean exists = loginDAO.checkUserExists(loginBean);
//        
//        System.out.println("user baduser@hotmail.com exists: " + exists);
            

           
//        System.out.println("now testing method loginDAO.returnUserIfExists ");
//
//        LoginBean loginBean = new LoginBean();
//        String email = "baduser@hotmail.com";
//        loginBean.setEmail(email);
//        User u = loginDAO.returnUserIfExists(loginBean);
//        
//        System.out.println(u.toString()); // the boolean one is better
        
//          String plaintextStudent = "P123456#";
//          String plaintextAdmin = "P12345678#";
//          String salt1 =  BCrypt.BCrypt.gensalt();
//          String salt2 = BCrypt.BCrypt.gensalt();
//          String studentHashed = BCrypt.BCrypt.hashpw(plaintextStudent, salt1);
//          String adminHashed = BCrypt.BCrypt.hashpw(plaintextAdmin, salt2);
//          
//          System.out.println("studentHashed = " + studentHashed);
//          System.out.println("adminHashed = " + adminHashed);
//        
//        
        
//        System.out.println("now trying with eddie eleve, #4, whose payment has been enabled ot 1 again");
//        
//        User eddie = userDAO.get(4).get();
//        
//        
//                   Collection<Session> sessions = sessionDAO.sessions(eddie);
//
//        for(Session s: sessions){
////            if(s.getTeacher().equals(muinteoir))
//            System.out.println("session id = " + s.getId()+ ", training = " +s.getTraining().getTitle());
//            
//            }
//        
//        System.out.println("testing getSessionByUser -- Michael Muniteoir, #7, is the teacher of two sessions (#3 and #4");
//        
////        User muinteoir = userDAO.get(7).get();
        
        
//           Collection<Session> sessions = sessionDAO.sessions(muinteoir);
//
//        for(Session s: sessions){
////            if(s.getTeacher().equals(muinteoir))
//            System.out.println("session id = " + s.getId()+ ", training = " +s.getTraining().getTitle());
//            
//            }
//         
        
        
        
        /////// this works
      // String email = "admin@trainingcentre.com"; // admin works; try with student
//String email = "eddie-learn@student.com";
////String email = "nonexistantemail@mail.com";
//
//        LoginBean lb= new LoginBean();
//        lb.setEmail(email);
//        lb.setPassword("123456789"); 
//        boolean b = loginDAO.checkUserExists(lb);
//        
//        System.out.println("User with admin's email exists " + b);
//        
//        
//        System.out.println("##########################");
//        
//        Optional u = loginDAO.login(lb);
//        
//        if(u.isPresent()){
//            User user = (User) u.get();
//            System.out.println("logged in User role = " + user.getUserRole().toString()); //working with student also
//        }else{
//            System.out.println("sorry, optional.isPresent() test failed - so such user with that email");
//        }
//        
//        
        
//        String password = "123456";
//        String dbPassword  = "$2a$12$KHN2J5CdnYar.V7eempvK.S/WRCyDakKiAz91drhoErZz8XedTI9G";
//           boolean pwCheck = BCrypt.BCrypt.checkpw(password,dbPassword);
//           
//           System.out.println("boolean pwCheck = " +pwCheck);
//
//        int admin = 2;
//        int teacher = 7;
//        int student 
//        
//        LoginBean lb = new LoginBean();
//        lb.setEmail("admin@trainingcentre.com");
//        lb.setPassword("12345678");
//        
//        User uLog = userDAO.authenticate(lb).get();
//        
//        System.out.println("uLog name, surname, role = " + uLog.getName() + " " +uLog.getSurname() + " " +uLog.getEmail());
 
//        System.out.println("Our admin's id is 2, Terry Teacher's id is 3 -  let's see if we can get them using User.getByID");  
//        
//        User adminUser =  new Admin();
//        
//        adminUser = userDAO.get(2).get();
//        
//        System.out.println(adminUser.toString());
//        
//        User teacherUser = new Teacher();
//        
//        teacherUser = userDAO.get(3).get();
//        
//        System.out.println("teacher = " + teacherUser.toString());
//        
//        
//        System.out.println("student get by id");
//        
//        Student s= studentDAO.get(5).get();
//        
//        System.out.println("toby to string  = " + s.toString());
//        
//        
//        Collection<Session> sessions = sessionDAO.sessions();
//
//        for(Session s: sessions){
//            if(s.getId().equals(3L)){
//                System.out.println("students enrolled in session with id == 3L :"+s.getStudents());
//            }
//        }
//        
//        User u = new Student();
        
        
   
    
    
//        Session session2 = sessions.contains(sessionDAO.get(2));
       
//        
//        System.out.println("testing session getAll"); 
//        List<Session> sessions  = sessionDAO.getAll();
//        
//        int x = sessions.size();
//        
//        System.out.println("sessions.size() = "+ x);
//        
//        for(Session s: sessions){
//            System.out.println(s.toString());
//        }
        
//           
//   List<Student> students = studentDAO.getAll();
//   
//   for(Student st: students){
//       System.out.println(st.toString());
//   }

//        System.out.println("testing  students after adding status");
//        
//        List<User> users = userDAO.getAll();
//        
//        List<Student> students = studentDAO.getAll();
//        
////        for(User u: users){
////            System.out.println(u.toString());
////        }
////        
//        System.out.println("########################");
//        
//        for(Student s: students){
//            System.out.println(s.toString());
//                    
//        }
      
        
      
        
        
        
        
        
//         System.out.println("testing if I can get a list of students and teachers from my list of users");
//        User user = new Teacher();
        
//        User user = userDAO.get(3).get();
//        
//        if(user instanceof Teacher){
//            Teacher teacher  = (Teacher) user;
//            System.out.println("my new teaccher =" +teacher.toString());
//        }else{
//            System.out.println("user is not a teacher");
//        }
//        
        
//       List<User> users = userDAO.getAll();
//       ArrayList<User> teachers = new ArrayList<User>();
//       int teacherRole =3;
//       
//       for(User u: users ){
//           
       
       
        
        
       
        
//        User teacher  = userDAO.get(7).get(); /// error: user cannot be cast to model teacher
//        
//        users.add(teacher);
//        
//        User student1 = userDAO.get(4).get();
//        
//        User student2 = userDAO.get(5).get();
//        
//        users.add(student1);
//        users.add(student2);
//        
//        System.out.println("length of users with teacehr and student added = " + users.size()); // ok
//        
//        System.out.println("################");
//        
//        System.out.println("trying to create a teacher out of a user");
//        
//        Teacher t = new Teacher();
//        
//        t = teacher;
//        
//        int teacherRole = 3;
//                
                
        //// i think i am amkgin 

//        System.out.println("testing if I can get a list of students and teachers from my list of users");
//        
//        List<User> users = new ArrayList<User>();
//        
//        User t  = (Teacher) userDAO.get(7).get(); /// error: user cannot be cast to model teacher
//        
//        users.add(t);
//        
//        System.out.println("length of users with one teacher added = " + users.size());
//        
        // add one teacher
        
       
//List<Teacher> teachers = teacherDAO.getAll();
//
//for(Teacher t: teachers){
//    System.out.println(t.toString());
//}

int hours = 3;

String startTime = "2021-09-06 18:00:00.00";
//String newStart =  "2021-02-06 23:20:46.00";

//String validTS = "2021-02-06 23:20:46.000";
Timestamp ts = Timestamp.valueOf(startTime);

LocalDateTime localD = LocalDateTime.fromDateFields(ts);

LocalDateTime endTime = localD.plusHours(hours);

        System.out.println("my local date  = " +localD);
        
        System.out.println("my endTime LocalDate = " + endTime);
        
        System.out.println("printing ts.toString and see what happens:");
        System.out.println(ts.toString());
               
        System.out.println("#######");
        System.out.println("testig using one of the values stored in the events db");
        
        String j = "2016-01-01 00:00:00";
        
        Timestamp jTs = Timestamp.valueOf(j);
        
        System.out.println("jTs.toString() = " + jTs.toString());
        
        String jgetTimeStr = "1648508400000";
        
        Long jgetTime = Long.parseLong(jgetTimeStr);
        
        Date myDate = new Date(jgetTime);
        
        String formatted = new SimpleDateFormat("yyyy-MM-dd").format(myDate);
        
        System.out.println("formatted = " + formatted);
        
        
        
//        LocalDateTime ldj =LocalDateTime.parse(jgetTime);
//        
//        System.out.println("ldj = " +ldj.toString());
//        
//        
//       String jsOutput =  "Tue Mar 29 2022 01:00:00 GMT+0200 (Central European Summer Time)" ;
//       LocalDateTime ldFromJS = LocalDateTime.parse(jsOutput);
//       
//        System.out.println("ldFromJS = " +ldFromJS);

               




//System.out.println(ts);

// DateTime dateTime = new DateTime(newStart);
// String result  = dateTime.plusHours(hours).toString("yyyy-MM-dd");
// 
//        System.out.println("Jodatime restult = " + result);
 





//User u = new Student();
////
//u = userDAO.get(4).get();
////
////        System.out.println(u.toString()); //this works - this is correct
////        
////        
//User us = new Student();
//
//u = userDAO.get(3).get();
//
//        System.out.println(u.toString()); //this also works - even though it should not 
//        //possibly becase i need to still add the... no the dao constraints are already there for student
//         System.out.println(us.toString());       
//                
//ArrayList<Student> students = new ArrayList<User>(); // not working


//   Student st = new User(); ///didn't work
//   
//        System.out.println(st.toString());
   
        
//        
//                        Address a = new Address();
//        
//        a.setId(10);
//        
//        int status = addressDAO.delete(a);
//        
//        System.out.println("delete status = " + status);
//        
                 
        
//                 System.out.println("tsting aaddress.update()");
//                 Address a = new Address();
//        a.setId(10);
//        a.setStreet("Boulevard");
//        a.setHouseNumber("54b");
//        a.setCity("clare");
//        a.setPostcode("CL 27 X1");
//        
//        int status = addressDAO.update(a);
//        
//        System.out.println("address update status =" + status);
        
        
        
        
        //testing user insert
//        User u = new User();
//        LoginBean loginBean = new LoginBean();
//        UserRole ur = userRoleDAO.get(4).get();
//        
//        loginBean.setPassword("123456");
//        
//        u.setUserRole(ur);
//        u.setName("John");
//        u.setSurname("Smarcher");
//        u.setEmail("johnS@gmail.com");
//        u.setTelephone("01-84343847");
//        
//        int lastUserID = userDAO.save(u, loginBean);
//        
//        System.out.println("lastUserID  =" + lastUserID);
        
        
        
        //returs 11 -- perfect
        
        
        
//                List<User> users = userDAO.getAll();
//        
//        for(User u: users){
//            System.out.println(u.toString());
//        }



        
//        System.out.println("test Address.save();");
//        
//         Address a = new Address();
//        
//        int lastUserID = 11; //hardcoded for the moment
//        a.setStreet("Lane");
//        a.setHouseNumber("47a");
//        a.setCity("Limerick");
//        a.setPostcode("Testtest");
//        
//        
//        int status = addressDAO.save(a,lastUserID);
//        
//        System.out.println("Status after insert = " + status);
//        
        
        
        
//        System.out.println("testing User.delete()");
//        
//        User u = userDAO.get(10).get();
//        
//        System.out.println(u.toString());
//        
//        int status = userDAO.delete(u);
//        
//        System.out.println("User.delete() status = " + status);

//        
//        //testing User update  - ----   WORKIN
//        User u = new User();
//        LoginBean loginBean = new LoginBean();
//        UserRole ur = userRoleDAO.get(6).get();
//        
//        loginBean.setPassword("1234567"); //added a 7
//        
//        u.setUserRole(ur);
//        u.setName("Johan");
//        u.setSurname("Chameau");
//        u.setEmail("johnC@gmail.com");
//        u.setTelephone("01-7774847");
//        u.setId(10);
//        
//        int status = userDAO.update(u, loginBean);
        
//        System.out.println("user insert status =" + status);
        //testing user insert
//        User u = new User();
//        LoginBean loginBean = new LoginBean();
//        UserRole ur = userRoleDAO.get(4).get();
//        
//        loginBean.setPassword("123456");
//        
//        u.setUserRole(ur);
//        u.setName("John");
//        u.setSurname("Archer");
//        u.setEmail("johnA@gmail.com");
//        u.setTelephone("01-8314847");
//        
//        int status = userDAO.save(u, loginBean);
//        
//        System.out.println("user insert status =" + status);
        
        
//        User u = userDAO.get(3).get();
//        
//        System.out.println(u.toString());
//        
//        List<User> users = userDAO.getAll();
//        
//        for(User u: users){
//            System.out.println(u.toString());
//        }
//        
//        
        
        
//        String unhashed = "12345678";
//        
//        String hashed = "$2a$12$SpK5iPOAjM3ChjYNkDr6/OICj7IxoKbVyWEKV4KPlwQ3YNQCKpVru";
//        
//        boolean check = BCrypt.BCrypt.checkpw(unhashed, hashed);
//        
//        
//        
//        
//        
//        System.out.println("the hashed password is the same as the plaintext: "+ check);
        
//        String originalString = "123456"; //this is the pasword for student
//          MessageDigest digest = null;
//        try {
//            digest = MessageDigest.getInstance("SHA-256");
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           byte[] hash = digest.digest(
//  originalString.getBytes(StandardCharsets.UTF_8));
//           
//           String result = null;
//           
//            StringBuilder hexString = new StringBuilder(2 * hash.length);
//            
//            for (int i = 0; i < hash.length; i++) {
//        String hex = Integer.toHexString(0xff & hash[i]);
//        if(hex.length() == 1) {
//            hexString.append('0');
//        }
//        hexString.append(hex);
//        
//        result = hexString.toString();
//        
//                
//          
//            }
//        
//        System.out.println("result = "+ result);
        
//      Discount d = discountDAO.get(4).get();
//      
//      int status = discountDAO.delete(d);
//      
//        System.out.println("delete discount status = " +status);
//        
//        System.out.println(d.toString());
//        
//        UserRole ur = userRoleDAO.get(6).get();
//        
//        d.setName("Spring student discount - 10% off");
//        d.setPercent(20);
//        d.setUserRole(ur);
//        
//        int status = discountDAO.update(d);
//        
//        System.out.println("update discount statis = " + status);
//        
        
//       Discount d = new Discount();
//       UserRole ur = userRoleDAO.get(5).get();
//       d.setName("Autumn student discount - 10% off");
//       d.setPercent(10);
//       d.setUserRole(ur);
//       
//       int status = discountDAO.save(d);
//       
//        System.out.println("discount save status = " + status);
        
        
        
//        List<Discount> discounts = discountDAO.getAll();
//        
//        for(Discount d: discounts){
//            
//            System.out.println(d.toString());
//        }
//        

//        Training t  = trainingDAO.get(6).get();
//        
//        int status = trainingDAO.delete(t);
//        
//        System.out.println("delete status = " + status);



//
//        System.out.println("testing training update"); // no int id
//        
//        Training tr = new Training();
//        Price p = priceDAO.get(1).get();
//        tr.setId(6);
//        tr.setCapacity(40);
//        tr.setDuration(14);
//        tr.setTitle("Advanced Spring");
//        tr.setPrice(p);
//        tr.setDescription("This course on the open-source application framework that provides infrastructure support for developing Java applications.");
//        
//        int status = trainingDAO.update(tr);
//        
//        System.out.println("training save int status = " + status);
//        

                      
//        Address a = new Address();
//        
//        a.
//        a.setStreet("Lane");
//        a.setHouseNumber("47a");
//        a.setCity("Limerick");
//        a.setPostcode("Testtest");
//        
//        
//        int status = addressDAO.save(a);
//        
//        System.out.println("Status after insert = " + status);
        


        
        
        



//        List<Price> prices = priceDAO.getAll();
//        for(Price p: prices){
//            System.out.println(p.toString());
//        }
        


//        System.out.println("testing update method of userRoleDAO");
        
//        UserRole teacher = userRoleDAO.get(3).get();
//        
//        teacher.setName("teacher space");
//        
//        int t = userRoleDAO.update(teacher);
//        
//        System.out.println("update int = " +t);
        
        
        
        
//        
//        System.out.println("User role ur.getName() = " + ur.getName());
//        
//       if(o.isPresent()){
//           System.out.println("o is present");
//           System.out.println(o.toString());
//       }else{
//           System.out.println("uh oh,, o is not present");
//       }
       
       

       
       
       
       
       
        
        
        
        
        
    }
    
}

