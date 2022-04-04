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
import model.Student;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.StatusDAO;
import model.dao.StudentDAO;

/**
 *
 * @author Cathal
 */
public class StudentListAllServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    
    public StudentListAllServlet(){
        super();
    }
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session = request.getSession();
       
         

          
                  //check to ensure User is signed in
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
        
        //create List Students, populate from StudentDAO
        List<Student> students = new ArrayList<Student>();
        
            AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
            
            StudentDAO studentDAO = factory.createStudentDAO();
            
            students = studentDAO.getAll();
            
            request.setAttribute("students", students);
            
            
            
            //generate List Statuses
            
            StatusDAO statusDAO = factory.createStatusDAO();
                List<Status> statuses = new ArrayList<Status>();
                statuses = statusDAO.getAll();
                request.setAttribute("statuses", statuses);
                
                
                request.getRequestDispatcher("student-list-all.jsp").include(request, response);
                

        
        
        
        

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

             public void error(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("error-permission.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
