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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Payment;
import model.dao.AbstractDAOFactory;
import model.dao.PaymentDAO;

/**
 *
 * @author Cathal
 */
public class DeleteMyPaymentServlet extends HttpServlet {

   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Long sessionId = Long.parseLong(request.getParameter("sessionid"));
        
        int userId = Integer.parseInt(request.getParameter("userid"));
        
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        PaymentDAO paymentDAO = factory.createPaymentDAO();
        
        List<Payment> payments = new ArrayList<Payment>();
        
        payments = paymentDAO.getAll();
        
        //delete payments containng both sessionId + userId
        
        Payment paymentToDelete = new Payment();
        
        for(Payment p: payments){
            
            if(p.getSession().getId().equals(sessionId) && p.getUser().getId()==userId){
                paymentToDelete = p;
            }
            
        }
        
        try{
            paymentDAO.delete(paymentToDelete);
        }catch(Exception e){
            
            this.error(request,response);
        }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void error(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
