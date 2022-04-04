/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Session;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.SessionDAO;

/**
 *
 * @author Cathal
 */
public class StudentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        
        //check to ensure 
         if(session.getAttribute("user") == null){
             this.error(request, response);
         }

        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();

        SessionDAO sessionDAO = factory.createSessionDAO();
        Collection<Session>  studentSessions = new ArrayList<Session>();
        
        //create empty ArrayLists for autumn and spring sessions
        
         List<Session> studentSpringSessions = new ArrayList<Session>();
        List<Session> studentAutumnSessions = new ArrayList<Session>();

                     


        User user = new User();

        try {

            user = (User) session.getAttribute("user");

        } catch (Exception e) {

            this.error(request, response);

        }
        
        //check user.UserRole == Student
        if(user.getUserRole().getId() !=4){
            this.error(request, response);
        }
        
        studentSessions = sessionDAO.sessions(user);
                        //divide studentSessions into two lists for presentation purposes
                        
                        for (Session s : studentSessions) {
                            if (s.isAutumnSemester()) {
                                studentAutumnSessions.add(s);
                            } else if (!s.isAutumnSemester()) {
                                studentSpringSessions.add(s);
                            }
                        }   
                        
                        request.setAttribute("studentSessions", studentSessions);
                        request.setAttribute("stAutumnSessions",  studentAutumnSessions);
                        request.setAttribute("stSpringSessions",  studentSpringSessions);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("student-mysessions.jsp");
                        dispatcher.forward(request, response);
        
        
        
        
       
    }
    
    public void error(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
