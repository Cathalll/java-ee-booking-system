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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Payment;
import model.Session;
import model.Student;
import model.dao.AbstractDAOFactory;
import model.dao.PaymentDAO;
import model.dao.SessionDAO;

/**
 *
 * @author Cathal
 */
public class StudentListServlet extends HttpServlet {


 private static final long serialVersionUID = 1L;
    
  public StudentListServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        


        HttpSession session = request.getSession();


        
        ArrayList<Session> teacherSessions = (ArrayList<Session>) session.getAttribute("teacherSessions");

        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        PaymentDAO paymentDAO = factory.createPaymentDAO();
        SessionDAO sessionDAO = factory.createSessionDAO();
        
//        Collection<Session> sessions = sessionDAO.sessions(); // think i need to narrow this down to teacherSessions
        

        List<Payment> allPayments = paymentDAO.getAll();

        //List of all Payments related to a particlar Session
        List<Payment> payments = new ArrayList<Payment>();

        Session sessionSelected = null;

        List<Student> studentsSelected = null;

        for (Session s : teacherSessions) {
            if (s.getId().equals(Long.parseLong(request.getParameter("id")))){
                sessionSelected = s;
                studentsSelected = sessionSelected.getStudents();



                request.setAttribute("session", sessionSelected);
                
               
                request.setAttribute("students", studentsSelected);

//            } else {
//                this.error(request, response); // i think this is the problem
//            }
            
            
        }
            
        }
            
            
        
        for (Payment p : allPayments) {
                    if (p.getSession().getId().equals(sessionSelected.getId())) {
                        
                        payments.add(p);
                    }
                    
                   
                }//end of Payment p: loop
             
        request.setAttribute("payments", payments);
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
        dispatcher.forward(request, response);

       
    }
    
        public void error(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("student-list-error.jsp");
        dispatcher.forward(request, response);

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
