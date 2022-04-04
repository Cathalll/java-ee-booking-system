/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.LoginBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Session;

import model.User;
import model.Status;
import model.UserRole;
import model.dao.AbstractDAOFactory;
import model.dao.AddressDAO;
import model.dao.LoginDAO;
import model.dao.SessionDAO;
import model.dao.StatusDAO;
import model.dao.UserDAO;
import model.dao.UserRoleDAO;

/**
 *
 * @author Cathal
 */
public class LoginServlet extends HttpServlet {
    
     private static final long serialVersionUID = 1L;
     


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }



  
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
         UserDAO userDAO = factory.createUserDAO();
         StatusDAO statusDAO = factory.createStatusDAO();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
       
        LoginBean loginBean = new LoginBean();
       loginBean.setEmail(email);
        loginBean.setPassword(password);
        
        
         LoginDAO loginDAO = factory.createLoginDAO();
         
         User user = new User();
    Optional userOptional = loginDAO.login(loginBean);
    
    if(userOptional.isPresent()){
           user = (User) userOptional.get();
           
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
           request.setAttribute("user",user); /// no need; do I need 
           
              
            SessionDAO sessionDAO = factory.createSessionDAO();
            
            //getAll of the logged-in User's Sessions and add them to session object, divided into spring and autumn semesters
            // this only works for a student now, not a teacher
           
            Collection <Session> enrolledSessions = sessionDAO.sessions(user);
        
        //List of session in spring semester
        ArrayList<Session> springSessions = new ArrayList<Session>();
        
         //List of session in autumn semester
        ArrayList<Session> autumnSessions = new ArrayList<Session>();
        
        for(Session s: enrolledSessions){
            if(s.isAutumnSemester()){
                autumnSessions.add(s);
                
            }else{
                springSessions.add(s);
            }//end if loop
        }
        
        session.setAttribute("springSesssions", springSessions);
        session.setAttribute("autumnSessions", autumnSessions);
        
        
            /// determine if the Optional returned is an Admin, Student, or Teacher
            switch (user.getUserRole().getId()) {
                case 4:
                    session.setMaxInactiveInterval(10*60);
                    Status status = statusDAO.getByUser(user).get();
                    session.setAttribute("status", status);
                    response.sendRedirect(request.getContextPath() + "/student");
                    
//                    request.getRequestDispatcher("student.jsp").forward(request, response); ////this can be changed to URL, probably
                    break;
                case 3:
                    
                    response.sendRedirect(request.getContextPath() + "/teacher");
                    break;
                case 2:
                      response.sendRedirect(request.getContextPath() + "/admin");
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("failure.jsp");
        dispatcher.forward(request, response);
                    
                    break;
            }
           
           
            
            
        }else{
        RequestDispatcher dispatcher = request.getRequestDispatcher("failure.jsp");
        dispatcher.forward(request, response);
        
        }
       


    }

    

    
    
}
