<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student Page</title>
</head>
<% //In case, if User session is not set, redirect to Login page.
if((request.getSession(false).getAttribute("user")== null) )
{
%>
<jsp:forward page="/JSP/login.jsp"></jsp:forward>
<%} %>
<body>
    <center><h2>Student Home</h2></center>
    Welcome <c:out value="${user.name}"/>
 
    <div style="text-align: right"><a href="<%=request.getContextPath()%>/logout">Logout</a></div>
 
</body>
</html>