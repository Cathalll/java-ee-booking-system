/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Status;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.StatusDAO;

/**
 *
 * @author Cathal
 */
public class HomeServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /// check if session object is created yes; if not dispatch visitor to correct page

        HttpSession session = request.getSession(false);
        if (session == null) {
            // Not created yet. Now do so yourself.
            session = request.getSession();
             response.sendRedirect(request.getContextPath() + "/login");
            
        } else {
           session = request.getSession();
           User user = new User();
           user = (User) session.getAttribute("user");
           
            switch (user.getUserRole().getId()) {
                 case 4:
                     session.setMaxInactiveInterval(10*60);
                     
                     Status status = new Status();
                      AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
                      StatusDAO statusDAO = factory.createStatusDAO();
                     status = statusDAO.getByUser(user).get();
                    session.setAttribute("status", status);
                    response.sendRedirect(request.getContextPath() + "/student");
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
           
            }
           
           
           
            
        }

    }


