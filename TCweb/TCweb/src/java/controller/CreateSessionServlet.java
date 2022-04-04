/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Classroom;
import model.Session;
import model.Teacher;
import model.Training;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.ClassroomDAO;
import model.dao.SessionDAO;
import model.dao.TeacherDAO;
import model.dao.TrainingDAO;

/**
 *
 * @author Cathal
 */
public class CreateSessionServlet extends HttpServlet {
    
      private static final long serialVersionUID = 1L;
      
      

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
          HttpSession session = request.getSession();
          
                  //check to ensure User is signed i
        if (session.getAttribute("user") == null) {
            this.error(request, response);
        }

        User user = new User();

        try {

            user = (User) session.getAttribute("user");

        } catch (Exception e) {

            this.error(request, response);

        }

        //check user.UserRole == Admin
        if (user.getUserRole().getId() != 2) {
            this.error(request, response);
        }
        
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
         
         //A Session's fields include Teacher, Training, and Classroom
       

        
        ///List of Trainings
        TrainingDAO trainingDAO = factory.createTrainingDAO();
        
        List<Training> trainings = new ArrayList<Training>();
        
       trainings = trainingDAO.getAll();
       
        Training trainingSelected = null;
        
        // the Training of the Session is selected from a 'get' from the Training page
        for(Training t: trainings){
            if(t.getId() == Integer.parseInt(request.getParameter("id"))){
                trainingSelected =  t;
            }
        }
        
        Session newSession = new Session();
        
        
        try{
            newSession.setTraining(trainingSelected);
        }catch(Exception e){
            this.error(request, response);
        }
        
        //hardcode recurs and duration values - these will not change for the purposes of this project.
        newSession.setCapacity(trainingSelected.getCapacity());
        newSession.setRecurs(7);
        newSession.setDuration(3); //refers to hours of the class - NOT the same as session.training.duration
        
        //create empty Arraylist of Classrooms, to be filled once the scheduling has been selected (in add-session.jsp)
        
         ArrayList<Classroom> classroomsForNewSession = new ArrayList<Classroom>();
          
          session.setAttribute("availableClassrooms", classroomsForNewSession);
          
          
         //create empty ArrayList Teachers to be filled once classroom has been selected
         
         ArrayList<Teacher> availableTeachers = new ArrayList<Teacher>();
         
         session.setAttribute("availableTeachers", availableTeachers);
        
        
        session.setAttribute("newSession", newSession);
        
         response.sendRedirect(request.getContextPath() + "/addsessiondetails");
        
        
//        request.getRequestDispatcher("add-session.jsp").forward(request,response);
        
        
        
       
//       //List of Teachers
//       
//       TeacherDAO teacherDAO = factory.createTeacherDAO();
//       List<Teacher> teachers = new ArrayList<Teacher>();
//       
//       teachers = teacherDAO.getAll();
//       
//       //List of Classrooms
//       
//       ClassroomDAO classroomDAO = factory.createClassroomDAO();
//       
//       List<Classroom> classrooms = new ArrayList<Classroom>();
//       
//       classrooms = classroomDAO.getAll();
//        
//        
        
        
    }
    
          public void error(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("error-permission.jsp");
        dispatcher.forward(request, response);

    }

  
}
