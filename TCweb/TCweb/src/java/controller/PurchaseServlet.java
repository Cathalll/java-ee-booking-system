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
import model.Item;
import javax.servlet.http.HttpSession;
import model.Payment;
import model.PaymentType;
import model.Session;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.PaymentDAO;
import model.dao.PaymentTypeDAO;

/**
 *
 * @author Cathal
 */
public class PurchaseServlet extends HttpServlet {
    

    
     private static final long serialVersionUID = 1L;
     
         public PurchaseServlet(){
             super();
    }
    


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
           PaymentTypeDAO paymentTypeDAO = factory.createPaymentTypeDAO();
           PaymentDAO paymentDAO = factory.createPaymentDAO();
        
           
           //retreive variables from cart.jsp
        HttpSession session = request.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        
       int idPaymentTypeSelected = Integer.parseInt(request.getParameter("paymentType"));
       
       
       ///create List of Payments and populate it from the above
       
       //List<Payment> payments = new ArrayList<Payment>();
       
       int numberOfSessionsPaid = 0;
       
       for(Item i: cart){
           Payment payment = new Payment();
           Session s = i.getSession();
           double paid = i.getFinalPrice();
           PaymentType paymentTypeSelected = new PaymentType(); 
           
           paymentTypeSelected.setId(idPaymentTypeSelected);
//           s.setId(i.getSession().getId());
           
           payment.setPaymentType(paymentTypeSelected);
           payment.setAmount(paid);
           payment.setUser(user);
           payment.setSession(s);
           
           int success = paymentDAO.save(payment);
           
           if(success > 0){
               numberOfSessionsPaid++;
               //empty cart 
               session.removeAttribute("cart");
           }else{
               
                RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp"); // not sure i need to make this
           }
   
          
       }
       
       
           request.setAttribute("numberOfSessionsPaid", numberOfSessionsPaid);
           request.getRequestDispatcher("payment-success.jsp").forward(request, response); 
           
       
       
       
       
       
       //find  correspoding PaymentType
//        List<PaymentType> pts = paymentTypeDAO.getAll();
//       
//       PaymentType paymentTypeSelected = null;
//                    
//        
//        for(PaymentType p: pts ){
//            if(p.getId() == idPaymentTypeSelected){
//                 paymentTypeSelected= p;
//            }
//        }
//        

        
        
        
         
         
       
        
        
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

}
