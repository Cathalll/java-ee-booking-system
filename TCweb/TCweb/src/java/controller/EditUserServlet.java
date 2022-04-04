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
public class EditUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditUserServlet() {
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

        ///dispatch to the various types of User
        switch (user.getUserRole().getId()) {
            case 4:
                AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
                StatusDAO statusDAO = factory.createStatusDAO();
                List<Status> statuses = new ArrayList<Status>();
                statuses = statusDAO.getAll();
                request.setAttribute("statuses", statuses);

                request.getRequestDispatcher("student-edit-user.jsp").include(request, response);
                break;
//            case 3: /// might be added at some later date
//                request.getRequestDispatcher("teacher-edit-user.jsp").include(request, response);
//                break;
//            case 2:
//                request.getRequestDispatcher("teacher-edit-user.jsp").include(request, response);
//                break;
            default:
                RequestDispatcher dispatcher = request.getRequestDispatcher("failure.jsp");
                dispatcher.include(request, response);
                break;
        }

    }

    @Override
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

    public void error(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("error-permission.jsp");
        dispatcher.forward(request, response);

    }
}
