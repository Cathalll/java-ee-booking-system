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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Classroom;
import model.Session;
import model.Teacher;
import model.dao.AbstractDAOFactory;
import model.dao.ClassroomDAO;
import model.dao.TeacherDAO;

/**
 *
 * @author Cathal
 */
public class SetClassroomSessionServlet extends HttpServlet {

  

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        
       int classroomId = Integer.parseInt(request.getParameter("classroomId"));
       
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        
        ClassroomDAO classroomDAO = factory.createClassroomDAO();
        
        Classroom classroomSelected = new Classroom();
        
        classroomSelected = classroomDAO.get(classroomId).get();
        
        Session newSession = new Session();
       
      newSession =(Session) session.getAttribute("newSession");
      
      newSession.setClassroom(classroomSelected);
      
        session.setAttribute("newSession", newSession);
        
        //fill ArrayList of available Teachers
        
        
        ArrayList<Teacher> availableTeachers = new ArrayList<Teacher>();
        
       TeacherDAO teacherDAO = factory.createTeacherDAO();
       
       List<Teacher> allTeachers = teacherDAO.getAll();
       
       //add available Teachers to session object
       for(Teacher t: allTeachers){
           if(!t.teacherHasAConflict(newSession)){
               availableTeachers.add(t);
           }
       }
       
       session.setAttribute("availableTeachers", availableTeachers);
        

       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
