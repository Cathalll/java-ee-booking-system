<%-- 
    Document   : training-sout
    Created on : 20-Jan-2022, 00:12:47
    Author     : Cathal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
             <%@ include file="header.jsp" %>  
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <P> ${training.description}</P>
        <P> ${training.title}</P>
    </body>
</html>
