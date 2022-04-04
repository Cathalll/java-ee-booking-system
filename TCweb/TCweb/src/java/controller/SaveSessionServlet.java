/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Session;
import model.dao.AbstractDAOFactory;
import model.dao.SessionDAO;

/**
 *
 * @author Cathal
 */
public class SaveSessionServlet extends HttpServlet {





    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          HttpSession session = request.getSession();
          
          //retreive Session newSession from session objecct for insert into database
           Session newSession = new Session();
     
            newSession =(Session) session.getAttribute("newSession");
            
            
            
            //create a new Session to capture details of newSession to show to the User (newSession will be removed from session object once the insert has been successful
            
            Session newSessionDetails = new Session();
            
            newSessionDetails = newSession;
       

          
           AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
           
           SessionDAO sessionDAO = factory.createSessionDAO();
           
           int status = 0;
           
           
           try{
               status = sessionDAO.save(newSession);
               
           }catch(Exception e){
               request.getRequestDispatcher("session-failure.jsp").include(request, response);
           }
           
           if(status ==1){
            request.setAttribute("newSessionDetails", newSessionDetails);
            request.getRequestDispatcher("session-success.jsp").include(request, response);
             session.removeAttribute("newSession");
        }else{
             request.getRequestDispatcher("session-failure.jsp").include(request, response);
        }

           ///to do: destroy session.getAttribute(newSession);
        
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
