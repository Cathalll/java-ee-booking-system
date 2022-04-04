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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Payment;
import model.Session;
import model.Student;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.PaymentDAO;
import model.dao.SessionDAO;

/**
 *
 * @author Cathal
 */
//@WebServlet(name = "MySessionsServlet", urlPatterns = {"/mysessions"})
public class MySessionsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

     
        HttpSession session = request.getSession();

        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();

        SessionDAO sessionDAO = factory.createSessionDAO();
        
        //create empty ArrayLists for Students, Admins, Teachers, etc
        
         List<Session> studentSpringSessions = new ArrayList<Session>();
                    List<Session> studentAutumnSessions = new ArrayList<Session>();

                    List<Session> studentSessions = new ArrayList<Session>();


        User user = new User();

        try {

            user = (User) session.getAttribute("user");

        } catch (Exception e) {

            this.error(request, response);

        }

        try {

            switch (user.getUserRole().getId()) {
                case 4:
                    {
                        studentSessions = (List<Session>) sessionDAO.sessions(user);
                        //divide studentSessions into two lists for presentation purposes
                        
                        for (Session s : studentSessions) {
                            if (s.isAutumnSemester()) {
                                studentAutumnSessions.add(s);
                            } else if (!s.isAutumnSemester()) {
                                studentSpringSessions.add(s);
                            }
                        }   
                        
                        request.setAttribute("studentSessions", studentSessions);
                        request.setAttribute("stAutumnSessions",  studentAutumnSessions);
                        request.setAttribute("stSpringSessions",  studentSpringSessions);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("student-mysessions.jsp");
                        dispatcher.forward(request, response);
                        break;
                    }
                case 3:
                    {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-mysessions.jsp");
                        dispatcher.forward(request, response);
                        break;
                    }
                case 2:
                    {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-mysessions.jsp");
                        dispatcher.forward(request, response);
                        break;
                    }
                default:
                    this.error(request, response);
                    break;                    
            }
        } catch (Exception e) {
            this.error(request, response);

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<Session> sessions = (List<Session>) session.getAttribute("sessions");

        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        PaymentDAO paymentDAO = factory.createPaymentDAO();

        List<Payment> allPayments = paymentDAO.getAll();

        //List of all Payments related to a particlar Session
        List<Payment> payments = new ArrayList<Payment>();

        Session sessionSelected = null;

        List<Student> studentsSelected = null;

        for (Session s : sessions) {
            if (s.getId().equals(Long.parseLong(request.getParameter("id")))) {
                sessionSelected = s;
                studentsSelected = sessionSelected.getStudents();

                for (Payment p : allPayments) {
                    if (p.getSession() == sessionSelected) {
                        payments.add(p);
                    }
                }

                request.setAttribute("session", sessionSelected);
                request.setAttribute("payments", payments);
                request.setAttribute("students", studentsSelected);

            } else {
                this.error(request, response);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
        dispatcher.forward(request, response);

    }

    public void error(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
