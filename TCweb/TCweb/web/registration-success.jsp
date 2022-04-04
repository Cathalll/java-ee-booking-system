<%-- 
    Document   : registration-success
    Created on : 15-Nov-2021, 15:12:26
    Author     : Cathal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>You are registered! </title>
         <%@ include file="header.jsp" %> 
    </head>
    <body>
        <div class="container">
    <div class="row">
        <div class="col-12 col-sm-8 offset-sm-2 col-md-6 offset-md-3 mt-5 pt-3 pb-3 bg-white from-wrapper">
            <div class="container">


        <h1>Great - your registration was a success!</h1>
        <p>Please <a href="${pageContext.request.contextPath}/login">login</a> to sign up for a course or consult your account, or <a href = "${pageContext.request.contextPath}/session">click here to browse our current training sessions</a>.</p>
              </div>
        </div>
    </div>
</div>
    </body>
       <%@ include file="footer.jsp" %>  
</html>
