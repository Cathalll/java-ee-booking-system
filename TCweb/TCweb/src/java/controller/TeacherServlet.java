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
//@WebServlet(name = "TeacherServlet", urlPatterns = {"/teacher"})
public class TeacherServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;
    
    
    
    public TeacherServlet(){
        super();
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        //check to ensure 
        if (session.getAttribute("user") == null) {
            this.error(request, response);
        }

        User user = new User();

        try {

            user = (User) session.getAttribute("user");

        } catch (Exception e) {

            this.error(request, response);

        }

        //check user.UserRole == Teacher
        if (user.getUserRole().getId() != 3) {
            this.error(request, response);
        }

        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();

        SessionDAO sessionDAO = factory.createSessionDAO();
        Collection<Session> allSessions = sessionDAO.sessions();

        List<Session> teacherSessions = new ArrayList<Session>();

        //create empty ArrayLists for autumn and spring sessions
        List<Session> teacherSpringSessions = new ArrayList<Session>();
        List<Session> teacherAutumnSessions = new ArrayList<Session>();
        
        Session tSession = new Session();

        for (Session s : allSessions) {
            if (s.getTeacher().getId() == user.getId()) {
                tSession=s;
                teacherSessions.add(tSession);
            }
        }
        
        session.setAttribute("teacherSessions", teacherSessions);

        //divide teacherSessions into two lists for presentation purposes
        for (Session s : teacherSessions) {
            if (s.isAutumnSemester()) {
                teacherAutumnSessions.add(s);
            } else if (!s.isAutumnSemester()) {
                teacherSpringSessions.add(s);
            }

        }//end of Session s: teacherSessions loop

//        session.setAttribute("teacherSessions", teacherSessions); //// setting this further up to see taht it is not overwritten
        session.setAttribute("tAutumnSessions", teacherAutumnSessions);
        session.setAttribute("tSpringSessions", teacherSpringSessions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-mysessions.jsp");
        dispatcher.forward(request, response);

    }

    public void error(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
        dispatcher.forward(request, response);

    }

//  protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//      
//      Long requestID = Long.parseLong(request.getParameter("id"));
//
//        HttpSession session = request.getSession();
//
//        List<Session> sessions = (List<Session>) session.getAttribute("sessions");
//
//        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
//        PaymentDAO paymentDAO = factory.createPaymentDAO();
//
//        List<Payment> allPayments = paymentDAO.getAll();
//
//        //List of all Payments related to a particlar Session
//        List<Payment> payments = new ArrayList<Payment>();
//
//        Session sessionSelected = null;
//
//        List<Student> studentsSelected = null;
//
//        for (Session s : sessions) {
//            if (s.getId().equals(requestID)){
//                sessionSelected = s;
//                studentsSelected = sessionSelected.getStudents();
//
//                for (Payment p : allPayments) {
//                    if (p.getSession() == sessionSelected) {
//                        payments.add(p);
//                    }
//                }
//
//                request.setAttribute("session", sessionSelected);
//                request.setAttribute("payments", payments);
//                request.setAttribute("students", studentsSelected);
//
//            } else {
//                this.error(request, response);
//            }
//        }
//        RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
//        dispatcher.forward(request, response);
//
//    }

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
