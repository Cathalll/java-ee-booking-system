/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Classroom;
import model.Session;
import model.dao.AbstractDAOFactory;
import model.dao.ClassroomDAO;
import org.joda.time.LocalDate;

/**
 *
 * @author Cathal
 */
public class AddTitleAndDatesServlet extends HttpServlet {

    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
     
         String title = request.getParameter("title");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        
        Long getEndDate = Long.parseLong(endDate);
        
        Date dateEndDate = new Date(getEndDate);
        
        String endDateformatted = new SimpleDateFormat("yyyy-MM-dd").format(dateEndDate);
        
        ///add the start hours, min and seconds
        
        String tsStartDate = startDate + " 18:00:00.000";
        String tsEndDate =  endDateformatted + " 18:00:00.000";
        
        Timestamp tsStart =  Timestamp.valueOf(tsStartDate);
        
        Timestamp tsEnd = Timestamp.valueOf(tsEndDate);
        
        
       HttpSession session = request.getSession();
        //Session newSession = new Session();
        
        Session newSession = new Session();
       
      newSession =(Session) session.getAttribute("newSession");
       
       newSession.setTitle(title);
       newSession.setStartDate(tsStart);
       newSession.setEndDate(tsEnd);
       
      session.setAttribute("newSession", newSession);
      
      
      ///now that the the start time and endTime have been added to newSession, we can use newSession to generate a list of all of the available Classrooms
      
      //retreive empty list of Classrooms from session object
      List<Classroom> availableClassrooms = new ArrayList<Classroom>();
      
      availableClassrooms = (ArrayList<Classroom>) session.getAttribute("availableClassrooms");
      
          AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
          ClassroomDAO classroomDAO = factory.createClassroomDAO();
      
      List<Classroom> classrooms = classroomDAO.getAll();
      
      
      //test clasrooms for conflict with newSession
      for(Classroom cl: classrooms){
          if(!cl.hasConflicts(newSession)){
              availableClassrooms.add(cl);
          }
      }
      
      session.setAttribute("availableClassrooms", availableClassrooms);
      

       
    }  
 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
