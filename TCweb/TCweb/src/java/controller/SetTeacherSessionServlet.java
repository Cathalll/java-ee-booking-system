/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Session;
import model.Teacher;
import model.dao.AbstractDAOFactory;
import model.dao.TeacherDAO;

/**
 *
 * @author Cathal
 */
public class SetTeacherSessionServlet extends HttpServlet {
    
    

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          HttpSession session = request.getSession();
        
   int teacherID = Integer.parseInt(request.getParameter("teacherId"));
   
    AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        
    TeacherDAO teacherDAO = factory.createTeacherDAO();
                
    
    Teacher teacherSelected =  new Teacher();
    
    teacherSelected =  teacherDAO.get(teacherID).get();
    
    
     Session newSession = new Session();
     
       newSession =(Session) session.getAttribute("newSession");
       
       newSession.setTeacher(teacherSelected);
       
        session.setAttribute("newSession", newSession);


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
