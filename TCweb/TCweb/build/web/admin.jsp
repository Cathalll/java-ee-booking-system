<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin Page</title>
</head>
<% //In case, if User session is not set, redirect to Login page.
if((request.getSession(false).getAttribute("user")== null) )
{
%>
<jsp:forward page="login.jsp"></jsp:forward>
<%} %>
<body>
    <center><h2>Admin Home</h2></center>
    Welcome <c:out value="${user.name}"/>
    
    This the landing page for <c:out value="${user.userRole.name}"/>
 
    <div style="text-align: right"><a href="<%=request.getContextPath()%>/logout">Logout</a></div>
 
</body>
</html>