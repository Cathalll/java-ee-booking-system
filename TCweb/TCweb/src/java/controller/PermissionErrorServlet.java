/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cathal
 */
public class PermissionErrorServlet extends HttpServlet {

    
    private static final long serialVersionUID = 1L;
    
  public PermissionErrorServlet(){
        super();
    }
  


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         request.getRequestDispatcher("error-permission.jsp").forward(request,response);
        
    }

   

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
