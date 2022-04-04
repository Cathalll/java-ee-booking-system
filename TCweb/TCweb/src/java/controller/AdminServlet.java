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
public class AdminServlet extends HttpServlet {
    
            private static final long serialVersionUID = 1L;
    
    
    
    public AdminServlet(){
        super();
    }

    



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
        
        //load Sessions - Admin has access to all Sessions
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        SessionDAO sessionDAO = factory.createSessionDAO();
        
        Collection<Session> sessions = sessionDAO.sessions();
        
        //divide Sessions into spring and autumn semesters for preentation purposes
        
        List<Session> springSessions = new ArrayList<Session>();
        List<Session> autumnSessions =  new ArrayList<Session>();
        
        for(Session s: sessions){
            if(s.isAutumnSemester()){
                autumnSessions.add(s);
            }else if(!s.isAutumnSemester()){
                springSessions.add(s);
            }
        }//end of Session s: sessions loop
        
        
         session.setAttribute("sessions",sessions); 
         session.setAttribute("autumnSessions", autumnSessions);
        session.setAttribute("springSessions", springSessions);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-mysessions.jsp");
        dispatcher.forward(request, response);
       
        
        
        
      
        
        
    }


      public void error(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("error-permission.jsp");
        dispatcher.forward(request, response);

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
