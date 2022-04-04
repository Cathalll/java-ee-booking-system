package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Session;
import model.dao.AbstractDAOFactory;
import model.dao.SessionDAO;

/**
 *
 * @author Cathal
 */
public class CalendarJsonServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //create List of Sessions
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
         SessionDAO sessionDAO = factory.createSessionDAO();
          Collection<Session> sessions = sessionDAO.sessions();
          MyClass mc = new MyClass();
          mc.setEvents(sessions);
          ///attemppting without any of the formatting
          
          response.setContentType("application/json");
//           response.setCharacterEncoding("UTF-8");
 PrintWriter out = response.getWriter();
 //out.write(new Gson().toJson(sessions));
 out.write(new Gson().toJson(mc));
         
        
         
         
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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

    private class MyClass{
        Collection <Session> events;

        public Collection<Session> getEvents() {
            return events;
        }

        public void setEvents(Collection<Session> events) {
            this.events = events;
        }
        
    }
}
