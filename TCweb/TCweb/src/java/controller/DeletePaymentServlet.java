/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Payment;
import model.Session;
import model.dao.AbstractDAOFactory;
import model.dao.PaymentDAO;
import model.dao.SessionDAO;

/**
 *
 * @author Cathal
 */
public class DeletePaymentServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
//        HttpSession session = request.getSession(); // don't think i need this
        
      

        

        int paymentID = Integer.parseInt(request.getParameter("id"));
//        
//          //get url to reload once entry has been deleted
//        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
//        String queryString = request.getQueryString();
//        
//        String fullURL = requestURL.append('?').append(queryString).toString();
        
        Payment p = new Payment();
        
        p.setId(paymentID);
        
    
        

        

        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        PaymentDAO paymentDAO = factory.createPaymentDAO();
        
        paymentDAO.delete(p);

       
       
       
       
        
        
        //check to see update has been made

  
        
        
      
    }
    
    




    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void error (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        
                RequestDispatcher dispatcher = request.getRequestDispatcher("delete-student-error.jsp");
        dispatcher.forward(request, response);

        
//         request.setAttribute("errorMessage","You have selected an invalid payment");
//         RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath() + /"");
//            requestDispatcher.forward(request, response);
//                 
       
    }


}
