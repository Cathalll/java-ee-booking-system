<%-- 
    Document   : cart
    Created on : 19-Dec-2021, 17:50:15
    Author     : Cathal
--%>

<html>
    <head>

        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
        <fmt:setLocale value="en_IE" scope="session"/>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.css">  
    <!--           changed from relative link to local drive-->

    <script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>

    <title>Paid successfully!</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%@ include file="header.jsp" %> 



</head>

<body>
    <div class="align-items-center">
        <p class ="align-content-center">
            Thank you - your payment was a success! You have successfully signed up for <c:out value="${numberOfSessionsPaid}" /> sessions.
        </p>
        
        <p class ="align-content-center">
            View all of your sessions at your <b>dashboard</b>, or <a href = "${pageContext.request.contextPath }/session">sign up for another session</a>.
        </p>
        
    </div>
</body>
</html>