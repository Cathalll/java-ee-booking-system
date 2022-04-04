/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.LoginBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Address;
import model.Status;
import model.User;
import model.UserRole;
import model.dao.AbstractDAOFactory;
import model.dao.AddressDAO;
import model.dao.StatusDAO;
import model.dao.UserDAO;
import model.dao.UserRoleDAO;
import model.dao.mysql.MySQLAddressDAO;
import model.dao.mysql.MySQLUserDAO;
import model.dao.mysql.MySQLUserRoleDAO;

/**
 *
 * @author Cathal
 */
//@WebServlet(name = "RegisterServlet", urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {
    
      private static final long serialVersionUID = 1L;

     private static  AddressDAO addressDAO;
     private static UserDAO userDAO;
     private static LoginBean loginBean;
     private static UserRole ur;
     private static UserRoleDAO userRoleDAO;
     private static User user;
     private static Address address;
     private static int status;
     private static String message;
     private static boolean userAlreadyExists;
     private static LoginBean emailCheckLoginBean;
     
     
     public void init(){
        addressDAO = new MySQLAddressDAO();
        userDAO = new MySQLUserDAO();
        loginBean = new LoginBean();
        ur = new UserRole();
        userRoleDAO =  new MySQLUserRoleDAO();
        user = new User();
        status = 0;
        message ="";
        userAlreadyExists = false;
        emailCheckLoginBean = new LoginBean();
        address = new Address();
         
     }
     
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
         StatusDAO statusDAO = factory.createStatusDAO();
         List<Status> statuses = new ArrayList<Status>();
         statuses = statusDAO.getAll();
         request.setAttribute("statuses", statuses);
        
        request.getRequestDispatcher("register.jsp").forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        
        //fields for user
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String plaintextPW = request.getParameter("password");
        
        //fields for address
        String street = request.getParameter("street");
        String houseNumber = request.getParameter("houseNumber");
        String city = request.getParameter("city");
        String postCode = request.getParameter("postCode");
        
        int selectedStatus = Integer.parseInt(request.getParameter("studentStatus"));

         //set User Status
         Status studentStatus = new Status();
         studentStatus.setId(selectedStatus);
      
         
         
        
      
        
        
        
        
        //new User registered role is Student by default
        ur = userRoleDAO.get(4).get();
        loginBean.setPassword(plaintextPW);
        
        
        
        //add fields to user prior to insert
        user.setUserRole(ur);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setTelephone(telephone);
        
        //add fields to address prior to insert
        address.setCity(city);
        address.setHouseNumber(houseNumber);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        
        
        //userDAO.save() returns the idUser of most recent insert, which is needed for the insert into the address table
        int lastUserID = userDAO.save(user, loginBean,studentStatus);
        
        //addressDAO.save() returns 1
        status = addressDAO.save(address,lastUserID);
        
        
        
        request.setAttribute("user",user);
        request.setAttribute("message", message);
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
