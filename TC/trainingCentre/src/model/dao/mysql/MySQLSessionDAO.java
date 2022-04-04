package model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import model.dao.SessionDAO;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;
import model.Classroom;
import model.Discount;
import model.Price;
import model.Session;
import model.Status;
import model.Student;
import model.Teacher;
import model.Training;
import model.User;
import model.UserRole;

/**
 *
 */
public class MySQLSessionDAO implements SessionDAO {

    /**
     * Default constructor
     */
    public MySQLSessionDAO() {
    }

    private static MySQLSessionDAO instance;

    static {
        instance = new MySQLSessionDAO();
    }

    public static MySQLSessionDAO getInstance() {
        return instance;
    }

    private static final String GETALL = "SELECT idSession,session.training,startDate,endDate,sessionDuration,recurs, session.classroom,session.teacher,capacitySession, titleSession, enabledSession, idTraining, titleTraining, description, training.price,duration,trainingCapacity, enabledTraining, idPrices, amount, namePrices, enabledPrices, idClassroom, nameClassroom, capacityClassroom, enabledClassroom,student.idUsers as idStudent, student.name as nameStudent, student.surname as surnameStudent, student.telephone, student.email as emailStudent, student.status, student.enabled as enabledStudent,teacher.idUsers as idTeacher, teacher.role as roleTeacher, teacher.name AS nameTeacher, teacher.surname AS surnameTeacher, teacher.email AS emailTeacher, teacher.enabled AS enabledTeacher,idAddress, street, houseNumber, city, postcode, enabledAddress, teacher.role as roleTeacher, teacherRole.idUserRoles as t_idUserRoles, teacherRole.nameUserRoles as t_nameUserRoles, teacherRole.enabledUserRoles as t_enabledUserRoles,student.role as roleStudent, student.status, idStatus, nameStatus, discount, enabledStatus, studentRole.idUserRoles AS s_idUserRoles, studentRole.nameUserRoles AS s_nameUserRoles, studentRole.enabledUserRoles AS s_enabledUserRoles FROM session LEFT JOIN training ON session.training = idTraining LEFT JOIN prices ON training.price = idPrices LEFT JOIN classroom ON session.classroom=idClassroom LEFT JOIN payments ON idSession = payments.session LEFT JOIN users student ON payments.user = student.idUsers LEFT JOIN users teacher ON session.teacher = teacher.idUsers LEFT JOIN address on address.idUser = student.idUsers LEFT JOIN userRoles teacherRole ON teacherRole.idUserRoles = teacher.role LEFT JOIN userRoles studentRole ON studentRole.idUserRoles = student.role LEFT JOIN status ON student.status = idStatus where enabledSession = 1";
    private static final String GETBYID = "";
    private static final String DELETE = "";
    private static final String SAVE = "INSERT INTO session(training,startDate,endDate,sessionDuration,recurs,classroom,teacher,capacitySession,titleSession) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "";
    private static final String GETBYUSER = "SELECT idSession,session.training,startDate,endDate,sessionDuration,recurs, session.classroom,session.teacher,capacitySession, titleSession, enabledSession, idTraining, titleTraining, description, training.price,duration,trainingCapacity, enabledTraining, idPrices, amount, namePrices, enabledPrices, idClassroom, nameClassroom, capacityClassroom, enabledClassroom,student.idUsers as idStudent, student.name as nameStudent, student.surname as surnameStudent, student.telephone, student.email as emailStudent, student.status, student.enabled as enabledStudent,teacher.idUsers as idTeacher, teacher.role as roleTeacher, teacher.name AS nameTeacher, teacher.surname AS surnameTeacher, teacher.email AS emailTeacher, teacher.enabled AS enabledTeacher,idAddress, street, houseNumber, city, postcode, enabledAddress, teacher.role as roleTeacher, teacherRole.idUserRoles as t_idUserRoles, teacherRole.nameUserRoles as t_nameUserRoles, teacherRole.enabledUserRoles as t_enabledUserRoles,student.role as roleStudent, student.status, idStatus, nameStatus, discount, enabledStatus, studentRole.idUserRoles AS s_idUserRoles, studentRole.nameUserRoles AS s_nameUserRoles, studentRole.enabledUserRoles AS s_enabledUserRoles FROM session LEFT JOIN training ON session.training = idTraining LEFT JOIN prices ON training.price = idPrices LEFT JOIN classroom ON session.classroom=idClassroom LEFT JOIN payments ON idSession = payments.session LEFT JOIN users student ON payments.user = student.idUsers LEFT JOIN users teacher ON session.teacher = teacher.idUsers LEFT JOIN address on address.idUser = student.idUsers LEFT JOIN userRoles teacherRole ON teacherRole.idUserRoles = teacher.role LEFT JOIN userRoles studentRole ON studentRole.idUserRoles = student.role LEFT JOIN status ON student.status = idStatus where enabledSession = 1 AND student.idUsers = ? and payments.user = ? and payments.enabled = 1";
    /**
     * @param Teacher teacher
     * @return
     */
//    public List<Session> getAll(void Teacher teacher) {
//        // TODO implement here
//        return null;
//    }
//
//    /**
//     * @param Training training 
//     * @return
//     */
//    public List<Session> getAll(void Training training) {
//        // TODO implement here
//        return null;
//    }
//
//    /**
//     * @param Student student 
//     * @return
//     */
//    @Override
//    public List<Session> getAll(void Student student) {
//        // TODO implement here
//        return null;
//    }
    @Override
    public Optional<Session> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        @Override
    public List<Session> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public int save(Session t) {
       int status = 0;
       Connection c = null;
       PreparedStatement ps = null;
       
        try {
            c= CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps = c.prepareStatement(SAVE);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(1, t.getTraining().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setTimestamp(2, t.getStartDate());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setTimestamp(3, t.getEndDate());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(4, t.getDuration());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(5, t.getRecurs());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(6, t.getClassroom().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(7, t.getTeacher().getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setInt(8, t.getTraining().getCapacity());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.setString(9, t.getTitle());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        }
        
        
        return status;
        
        
       
    }

    @Override
    public int update(Session t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Session t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Session> getAll(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Session> sessions() {
        Map<Long, Session> sessionsByID = new HashMap<>();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        Classroom cl = null;
        Teacher t = null;
        Training tr = null;
        Price p = null;
        //UserRole ur = null;
        Address a = null;
        Student st = null;
        Status stat = null;
        UserRole teacherRole = null;
//        int studentInt = 4;
//        int teacherInt = 3;
        UserRole studentRole = null;
//        Discount d = null;
        //ArrayList<Student> students = new ArrayList<Student>();
        Session s = null;

        try {
            c = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ps = c.prepareCall(GETALL);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(rs.next()){
                
                a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
                rs.getBoolean("enabledAddress"));
                
              teacherRole = new UserRole(rs.getInt("t_idUserRoles"),rs.getString("t_nameUserRoles"), rs.getBoolean("t_enabledUserRoles"));
              
              studentRole = new UserRole(rs.getInt("s_idUserRoles"),rs.getString("s_nameUserRoles"), rs.getBoolean("s_enabledUserRoles"));
              
              stat = new Status(rs.getInt("idStatus"), rs.getString("nameStatus"), rs.getInt("discount"),rs.getBoolean("enabledStatus"));
              
              cl = new Classroom(rs.getInt("idClassroom"),rs.getString("nameClassroom"),rs.getInt("capacityClassroom"),rs.getBoolean("enabledClassroom"));
              
               st = new Student(stat,rs.getInt("idStudent"), studentRole, rs.getString("nameStudent"), rs.getString("surnameStudent"), rs.getString("telephone"), rs.getString("emailStudent"),rs.getBoolean("enabledStudent"), a);
               
               t= new Teacher(rs.getInt("idTeacher"), teacherRole, rs.getString("nameTeacher"), rs.getString("surnameTeacher"), rs.getString("emailTeacher"),rs.getBoolean("enabledTeacher"));
               
//               testTeach = new Teacher
               
               p = new Price(rs.getInt("idPrices"),rs.getDouble("amount"),rs.getString("namePrices"),rs.getBoolean("enabledPrices"));
               
               tr =  new Training(rs.getInt("idTraining"),rs.getString("titleTraining"),rs.getString("description"),p,rs.getInt("duration"),rs.getInt("trainingCapacity"),
                        rs.getBoolean("enabledTraining"));
               
               Long id = rs.getLong("idSession");
               Training training = tr;
               Timestamp startDate = rs.getTimestamp("startDate");
               Timestamp endDate = rs.getTimestamp("endDate");
               int duration = rs.getInt("sessionDuration");
               int recurs = rs.getInt("recurs");
               Classroom classroom = cl;
               Teacher teacher =t;
               Student student = st;
               int capacity = rs.getInt("capacitySession");
               String title = rs.getString("titleSession");
               boolean enabled = rs.getBoolean("enabledSession");
               Session session = sessionsByID.get(id);
               if(session == null){
//                   Teacher teacher = new Teacher();
//                   teacher = t;
                   ArrayList<Student> students = new ArrayList<Student>();
                   session = new Session(id,training,startDate,endDate,duration,recurs,
                   classroom,teacher,capacity,title,enabled,students); /// maybe here? don't you have a constructor without students??
                   sessionsByID.put(session.getId(), session);
  
               }

               
                //session.add(t);
                session.addStudent(student);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       
        
        }//end of finally loop
        
        
        Collection<Session> sessions = sessionsByID.values();
        
        
        return sessions;

    }

    @Override
    public Collection<Session> sessions(User user) {
        
        Map<Long, Session> sessionsByID = new HashMap<>();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        Classroom cl = null;
        Teacher t = null;
        Training tr = null;
        Price p = null;
        //UserRole ur = null;
        Address a = null;
        Student st = null;
        Status stat = null;
        UserRole teacherRole = null;
//        int studentInt = 4;
//        int teacherInt = 3;
        UserRole studentRole = null;
//        Discount d = null;
        //ArrayList<Student> students = new ArrayList<Student>();
        Session s = null;

        try {
            c = CheckProperties.checkConnectionDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ps = c.prepareStatement(GETBYUSER);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        try {
            ps.setInt(1, user.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
        try {
            ps.setInt(2, user.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(rs.next()){
                
                a = new Address(rs.getInt("idAddress"),rs.getString("street"),rs.getString("houseNumber"),rs.getString("city"),rs.getString("postcode"),
                rs.getBoolean("enabledAddress"));
                
              teacherRole = new UserRole(rs.getInt("t_idUserRoles"),rs.getString("t_nameUserRoles"), rs.getBoolean("t_enabledUserRoles"));
              
              studentRole = new UserRole(rs.getInt("s_idUserRoles"),rs.getString("s_nameUserRoles"), rs.getBoolean("s_enabledUserRoles"));
              
              stat = new Status(rs.getInt("idStatus"), rs.getString("nameStatus"), rs.getInt("discount"),rs.getBoolean("enabledStatus"));
              
              cl = new Classroom(rs.getInt("idClassroom"),rs.getString("nameClassroom"),rs.getInt("capacityClassroom"),rs.getBoolean("enabledClassroom"));
              
               st = new Student(stat,rs.getInt("idStudent"), studentRole, rs.getString("nameStudent"), rs.getString("surnameStudent"), rs.getString("telephone"), rs.getString("emailStudent"),rs.getBoolean("enabledStudent"), a);
               
               t= new Teacher(rs.getInt("idTeacher"), teacherRole, rs.getString("nameTeacher"), rs.getString("surnameTeacher"), rs.getString("emailTeacher"),rs.getBoolean("enabledTeacher"));
               
//               testTeach = new Teacher
               
               p = new Price(rs.getInt("idPrices"),rs.getDouble("amount"),rs.getString("namePrices"),rs.getBoolean("enabledPrices"));
               
               tr =  new Training(rs.getInt("idTraining"),rs.getString("titleTraining"),rs.getString("description"),p,rs.getInt("duration"),rs.getInt("trainingCapacity"),
                        rs.getBoolean("enabledTraining"));
               
               Long id = rs.getLong("idSession");
               Training training = tr;
               Timestamp startDate = rs.getTimestamp("startDate");
               Timestamp endDate = rs.getTimestamp("endDate");
               int duration = rs.getInt("sessionDuration");
               int recurs = rs.getInt("recurs");
               Classroom classroom = cl;
               Teacher teacher =t;
               Student student = st;
               int capacity = rs.getInt("capacitySession");
               String title = rs.getString("titleSession");
               boolean enabled = rs.getBoolean("enabledSession");
               Session session = sessionsByID.get(id);
               if(session == null){
//                   Teacher teacher = new Teacher();
//                   teacher = t;
                   ArrayList<Student> students = new ArrayList<Student>();
                   session = new Session(id,training,startDate,endDate,duration,recurs,
                   classroom,teacher,capacity,title,enabled,students); /// maybe here? don't you have a constructor without students??
                   sessionsByID.put(session.getId(), session);
  
               }

               
                //session.add(t);
                session.addStudent(student);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
        
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }//end of finally loop
        
        
        Collection<Session> sessions = sessionsByID.values();
        
        
        return sessions;

    
    }



}
