/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PaymentType;
import model.Session;
import model.Status;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.PaymentTypeDAO;
import model.dao.SessionDAO;
import model.dao.StatusDAO;

/**
 *
 * @author Cathal
 */
public class SessionServlet extends HttpServlet {
    
     private static final long serialVersionUID = 1L;




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        SessionDAO sessionDAO = factory.createSessionDAO();
        PaymentTypeDAO paymentTypeDAO = factory.createPaymentTypeDAO();
        StatusDAO statusDAO = factory.createStatusDAO();
        
        
        
        HttpSession session = request.getSession();

        
        //objects of business required to present Student with options to sign up for a Session 
       Collection<Session> sessions = sessionDAO.sessions();
       
       
        //divide Sessions into spring and autumn semesters
        
        List<Session> springSessions = new ArrayList<Session>();
        List<Session> autumnSessions =  new ArrayList<Session>();
        
        for(Session s: sessions){
            if(s.isAutumnSemester()){
                autumnSessions.add(s);
            }else if(!s.isAutumnSemester()){
                springSessions.add(s);
            }
        }//end of Session s: sessions loop
        
        
//        request.setAttribute("sessions",sessions); 
//        request.setAttribute("autumnSessions", autumnSessions);
//        request.setAttribute("springSessions", springSessions);
//        
        session.setAttribute("sessions",sessions); 
         session.setAttribute("autumnSessions", autumnSessions);
        session.setAttribute("springSessions", springSessions);
        
         // if User is not signed in, they are presented with a list of Sessions, but with no options other than to sign in       
        if(session.getAttribute("user") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("session-list.jsp");
        dispatcher.forward(request, response);
              
            
        }else{ //User is signed in,so they are presented with full payment options, prices based on their status, etc
            
       
       
       
        List<PaymentType> paymentTypes = paymentTypeDAO.getAll();
        User user = new User();
        user = (User) request.getSession(false).getAttribute("user"); // i don't need to pass this one though
       
        Status status = statusDAO.getByUser(user).get();
        

        
        request.setAttribute("paymentTypes", paymentTypes);
        session.setAttribute("status", status);
        
         RequestDispatcher dispatcher = request.getRequestDispatcher("session-signup.jsp");
        dispatcher.forward(request, response);
        }//end of else (user !null) 
        
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        doGet(request, response);  //nb have removed this line
    }
    
    public void listSession (HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
       
        
        
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
