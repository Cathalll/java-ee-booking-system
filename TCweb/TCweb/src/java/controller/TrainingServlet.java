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
import javax.servlet.http.HttpSession;
import model.Price;
import model.Training;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.PriceDAO;
import model.dao.TrainingDAO;

/**
 *
 * @author Cathal
 */
public class TrainingServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
        public TrainingServlet() {
        super();
    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          HttpSession session = request.getSession();
          
                //check to ensure User is signed in as Admin
        if (session.getAttribute("user") == null) {
             response.sendRedirect(request.getContextPath() + "/permission-error");
        }

        User user = new User();

        try {

            user = (User) session.getAttribute("user");

        } catch (Exception e) {

          response.sendRedirect(request.getContextPath() + "/permission-error");

        }

        //check user.UserRole == Admin
        if (user.getUserRole().getId() != 2) {
             response.sendRedirect(request.getContextPath() + "/permission-error");
        }
        
          
        
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
         TrainingDAO trainingDAO = factory.createTrainingDAO();
         
         
         
         List<Training> trainings = new ArrayList<Training>();
         trainings = trainingDAO.getAll();
         request.setAttribute("trainings", trainings);
         
         
         //get Prices
         List<Price> prices = new ArrayList<Price>();
         PriceDAO priceDAO = factory.createPriceDAO();
         prices = priceDAO.getAll();
         request.setAttribute("prices", prices);
         
         
         request.getRequestDispatcher("trainings.jsp").forward(request,response);
         
         
         
       
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             //get details of new Training entered by Admin from form in doGet method
       String title = request.getParameter("title");
       String description = request.getParameter("description");
       
       Price price = new Price();
       price.setId(Integer.parseInt(request.getParameter("price")));
       int duration = Integer.parseInt(request.getParameter("duration"));
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        
        // add attributes to new Training
        Training training = new Training();
        training.setTitle(title);
        training.setDescription(description);
        training.setPrice(price);
        training.setDuration(duration);
        training.setCapacity(capacity);
        
     
        
        //instatiate TrainingDAO for insert 
        
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
         TrainingDAO trainingDAO = factory.createTrainingDAO();
         
         //status of insert
         int status = 0;
         
         
  
         
         try{
         status = trainingDAO.save(training);
        
         }catch(Exception e){
             
             request.getRequestDispatcher("training-failure.jsp").include(request, response);
             
         }
        
          if(status ==1){
            request.getRequestDispatcher("registration-success.jsp").include(request, response);
        }else{
             request.getRequestDispatcher("registration-failure.jsp").include(request, response);
        }

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
