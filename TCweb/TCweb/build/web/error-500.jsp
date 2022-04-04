<%-- 
    Document   : error-404
    Created on : 07-Jan-2022, 12:33:10
    Author     : Cathal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>500 error</title>
        
          <%@ include file="header.jsp" %>  
        
       
    </head>
    <body>
        <h1>500</h1>
        
        <p>Oops - I'm afraid that we have encountered a problem and cannot find the requested resource</p>
        <p></p>
        <p><a href="${pageContext.request.contextPath}/">Take me back to the homepage</a>.</p>
    </body>
    
     <%@ include file="footer.jsp" %>  
</html>
