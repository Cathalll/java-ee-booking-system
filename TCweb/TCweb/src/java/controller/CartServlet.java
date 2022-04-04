/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Item;
import model.PaymentType;
import model.Status;
import model.Session;
import model.dao.AbstractDAOFactory;
import model.dao.PaymentTypeDAO;
import model.dao.SessionDAO;
import model.dao.UserDAO;

/**
 *
 * @author Cathal
 */
public class CartServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    
    
    public CartServlet(){
        super();
    }

 


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
        String action = request.getParameter("action");
        if (action == null) {
			doGet_DisplayCart(request, response);
                        
    }else{
            if(action.equalsIgnoreCase("buy")){
                doGet_Buy(request, response);
            } else if (action.equalsIgnoreCase("remove")) {
				doGet_Remove(request, response);
        }
    
    
    }
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

    private void doGet_Remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = isExisting(request.getParameter("id"),cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        response.sendRedirect("cart");

    }



    private void doGet_DisplayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // HttpSession session = request.getSession();
        //List<Session> cart = (List<Session>) session.getAttribute("cart");// -- i think this was resetting the
        
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }



    private void doGet_Buy(HttpServletRequest request, HttpServletResponse response) throws IOException {
            // User selection counter
          Session sessionSelected = null; 
          Item itemSelected = null;
        
        //ArrayList of existing Session to loop selectedSession through
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
         SessionDAO sessionDAO = factory.createSessionDAO();
         PaymentTypeDAO paymentTypeDAO = factory.createPaymentTypeDAO();
         Collection<Session> sessions = sessionDAO.sessions();
         Status status = new Status();
          HttpSession session = request.getSession(); 
          status = (Status) session.getAttribute("status");
         
         
      
        //Cart for selections selected by User
         List<Item> cart = new ArrayList<Item>(); // @catha;
         
         List<PaymentType> paymentTypes = paymentTypeDAO.getAll();
         session.setAttribute("paymentTypes", paymentTypes);
        
        
  
        if(session.getAttribute("cart") == null){ // if no Items have been added to the cart
          
           for(Session s: sessions){
               if(s.getId().equals(Long.parseLong(request.getParameter("id")))){
                sessionSelected =s;
                itemSelected = new Item();
                itemSelected.setSession(sessionSelected);
                //update the final price to be paid based on the Status of the User
               itemSelected.updatefinalPrice(status);
                
                cart.add(itemSelected);
            }// end of if(s.getID()... ) loop
           }//end of Session s: sessions loop
           
            session.setAttribute("cart", cart);

        }else{ // end of if(getAttribute("cart") == null)loop
             cart = (List<Item>) session.getAttribute("cart");
             int index = isExisting(request.getParameter("id"), cart);
             
             //control for if Item is already in cart - User can add a Session as many times as they like, but
             // the cart will only ever have one
            if (index == -1) {
             for(Session s: sessions){
               if(s.getId().equals(Long.parseLong(request.getParameter("id")))){
                sessionSelected =s;
                  itemSelected = new Item();
                 itemSelected.setSession(sessionSelected);
                 itemSelected.updatefinalPrice(status);
                
                cart.add(itemSelected);
            }
             
            session.setAttribute("cart", cart);
        }//end of else 

            }else{ //item is already in cart
        response.sendRedirect("cart");
            }
          
    }
        
        response.sendRedirect("cart");  
        
    }

    private int isExisting(String id, List<Item> cart) {
        for(int i = 0; i < cart.size(); i++) {
            
            if(cart.get(i).getSession().getId() == Integer.parseInt(id)){
                return i;
            }
        }

            return -1;
    }

}