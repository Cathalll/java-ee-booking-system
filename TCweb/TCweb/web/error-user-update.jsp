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
        <title>Problem updating you profile</title>
        
          <%@ include file="header.jsp" %>  
        
       
    </head>
    <body>
        <h1>500</h1>
        
        <p>Oops - I'm afraid that we have encountered a problem and cannot carry out requested update</p>
        <p></p>
        <p><a href="${pageContext.request.contextPath}/student">Take me back to previous page</a>.</p>
    </body>
    
     <%@ include file="footer.jsp" %>  
</html>
