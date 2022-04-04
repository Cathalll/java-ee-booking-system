<%-- 
    Document   : header
    Created on : 01-Dec-2021, 22:50:56
    Author     : Cathal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="en_IE" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<!--        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>-->
<script
    src="https://code.jquery.com/jquery-3.6.0.js"
    integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" type="text/javascript"></script>


<style>
    .footer {
        margin-top: 30px; 

    }


    .container {
        padding: 8px;
    }
    
    
/*    .table*/
</style>



<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Training Centre</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
                </li>

                <c:choose>
                    <c:when test= "${sessionScope.user.getUserRole().getId() ==4 }"> 



                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/student">My sessions</a>

                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/edituser">User settings</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/session">Session sign up</a>
                        </li>






                    </c:when>
                    <c:when test= "${sessionScope.user.getUserRole().getId() ==3 }">

                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/teacher">My sessions</a>

                        </li>



                    </c:when>
                    <c:when test= "${sessionScope.user.getUserRole().getId() ==2 }">


                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/admin">All sessions</a>

                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/training">Trainings</a>

                        </li>
                                                <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/studentlistall">Student list</a>

                        </li>




                    </c:when>
                    <c:otherwise>


                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/session">Session signup</a>
                        </li>
                          <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/register">Register</a>
                        </li>




                    </c:otherwise>


                </c:choose>


            </ul>
            <ul class="navbar-nav">
                <c:choose>
                    <c:when test= "${sessionScope.user.getUserRole().getId() ==4 }"> 
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/student">${sessionScope.user.name} - Student</a>
                        </li>  
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/logout">Logout</a>
                        </li>

                    </c:when>
                    <c:when test= "${sessionScope.user.getUserRole().getId() ==3 }">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/teacher">${sessionScope.user.name} - Teacher</a>
                        </li>        
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/logout">Logout</a>
                        </li>

                    </c:when>

                    <c:when test= "${sessionScope.user.getUserRole().getId() ==2 }">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/admin">${sessionScope.user.name} - Admin</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath }/logout">Logout</a>
                        </li>

                    </c:when>
                    <c:otherwise>
                        <li class="nav-item active">
                            <a class="nav-link" href="${pageContext.request.contextPath }/login">Login</a>
                        </li>

                    </c:otherwise>



                </c:choose>




            </ul>

        </div>
    </div>
</nav>


