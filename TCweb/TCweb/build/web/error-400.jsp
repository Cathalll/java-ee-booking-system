<%-- 
    Document   : error-401
    Created on : 07-Jan-2022, 12:33:10
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
        <h1>401</h1>
        
        <p>I'm afraid your client has issued a bad request.</p>
        <p></p>
        <p><a href="${pageContext.request.contextPath}/">Take me back to the homepage</a>.</p>
    </body>
    
     <%@ include file="footer.jsp" %>  
</html>
