<%-- 
    Document   : error-page
    Created on : 02-Jan-2022, 14:40:09
    Author     : Cathal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>400 error</title>
        
          <%@ include file="header.jsp" %>  
        
       
    </head>
    <body>
        <h1>Student successfully removed from session</h1>
        
        <p>Student removed and payment refunded.</p>
        <p></p>
        <p><a href="${pageContext.request.contextPath}/">Take me back to the homepage</a>.</p>
    </body>
    
     <%@ include file="footer.jsp" %>  
</html>
