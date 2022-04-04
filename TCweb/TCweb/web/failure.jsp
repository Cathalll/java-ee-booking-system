<%-- 
    Document   : failure
    Created on : 04-Oct-2021, 18:50:08
    Author     : Cathal
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search did not return any results</title>
    </head>
    <body>
        <h1>Oh dear</h1>
<!--        <p>Your search term "<c:out value="${searchString}"/>" did not return any results</p>
        <p>Your search term "<%= request.getAttribute("searchString") %>" did not return any results</p>
        <p><a href="<c:url value = "/"/>">Try another search</a>, or <a href="<c:url value = "/View2"/>">view full list of users</a>.</p>-->
        <p> sorry login details incorrect - <a href="<c:url value = "/"/>">please try again</a>.
       
    </body>
</html>
