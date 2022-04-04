/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.LoginBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Address;
import model.Status;
import model.User;
import model.UserRole;
import model.dao.AbstractDAOFactory;
import model.dao.AddressDAO;
import model.dao.UserDAO;
import model.dao.UserRoleDAO;

/**
 *
 * @author Cathal
 */
public class EditStudentPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

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

        //create UserRole fpor update
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        UserRoleDAO userRoleDAO = factory.createUserRoleDAO();
        UserRole ur = new UserRole();

        //UserRole = Student (which is 4)
        ur = userRoleDAO.get(4).get();

        //create LoginBean
        LoginBean loginBean = new LoginBean();
        loginBean.setPassword(plaintextPW);

        //retreive User from session sttributre
        User user = new User();

        user = (User) session.getAttribute("user");

        //add fields to user prior to update
        user.setUserRole(ur);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setTelephone(telephone);

        //retreive Address from User;
        Address address = new Address();

        address = user.getAddress();

        //add fields to address prior to insert
        address.setCity(city);
        address.setHouseNumber(houseNumber);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);

        ///intialise DAOs and execute updates
        UserDAO userDAO = factory.createUserDAO();

        try {

            int userUpdateStatus = userDAO.update(user, loginBean, studentStatus);

        } catch (Exception e) {

            request.getRequestDispatcher("error-user-update.jsp").include(request, response);

        }
        
        AddressDAO addressDAO = factory.createAddressDAO();
        
        
        int addressUpdateStatus = 0;
        
        try{
       addressUpdateStatus = addressDAO.update(address);
        }catch(Exception e){
            
            request.getRequestDispatcher("error-address-update.jsp").include(request, response);
            
        }
        
        if(addressUpdateStatus ==1){
            request.getRequestDispatcher("update-student-success.jsp").include(request, response);
        } else{
             request.getRequestDispatcher("error-address-update.jsp").include(request, response);
            
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
