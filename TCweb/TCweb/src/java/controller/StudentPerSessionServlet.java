/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cathal
 */
//@WebServlet(name = "StudentPerSessionServlet", urlPatterns = {"/StudentPerSessionServlet"})
public class StudentPerSessionServlet extends HttpServlet {

  


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
       
    }


    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
