<%-- 
    Document   : teacher-sessions
    Created on : 01-Jan-2022, 11:35:33
    Author     : Cathal
--%>




<html>
    <head>

        <%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%@ taglib prefix="myfn" uri="http://samplefn"%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.css">  
    <!--           changed from relative link to local drive-->
    <!--
        <script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>-->

    <title>Dashboard - Teacher</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%@ include file="header.jsp" %> 

</head>
<body>
    
    <h1>Learning Dashboard - Teacher</h1>

    <h2>Autumn Sessions</h2>
    <table class="table" id="autumnSession">
        <p></p>
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Begins</th>
                <th scope="col">Ends</th>
                <th scope="col">Day of Week</th>
                <th scope="col">Start time</th>
                <th scope="col">Duration</th>
                <th scope="col">Classroom</th>
                <th scope="col">Students</th>
                
                <th scope="col"> </th>
            </tr>

        </thead>
        <tbody>

            <c:forEach items="${tAutumnSessions}" var="autumnSess" varStatus="loop">
               
               
                <c:set var="placesRemaining" value="${autumnSess.placesRemaining()}" />

                <tr>
                    <th scope="row">${autumnSess.id}</th>
                    <td>${autumnSess.title}</td>
                    <td><fmt:formatDate type = "date" value = "${autumnSess.startDate}" /></td>  
                    <td><fmt:formatDate type = "date" value = "${autumnSess.endDate}" /></td>
                    <td><fmt:formatDate type = "date" pattern="EEEE" value ="${autumnSess.startDate}" /> </td>
                    <td><fmt:formatDate type = "time" value ="${autumnSess.startDate}" /> </td>
                    <td>${autumnSess.duration} hours</td>
                    <td>${autumnSess.classroom.name}</td>
                    <td>
                
                        <strong>${fn:length(autumnSess.students)} enrolled </strong>/ ${autumnSess.capacity} total<br>
                        
                        <a href="${pageContext.request.contextPath }/studentlist?id=${autumnSess.id}">List students</a>
                        
                        

                    </td>
                </tr>

            </c:forEach>  


        </tbody>

    </table>
    
    <p></p>
    <p></p>
        
    
    

    <h2> Spring Sessions</h2>
    <p></p>
    <table class="table" id="springSession">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Begins</th>
                <th scope="col">Ends</th>
                <th scope="col">Day of Week</th>
                <th scope="col">Start time</th>
                <th scope="col">Duration</th>
                <th scope="col">Classroom</th>
                <th scope="col">Students</th>
            </tr>

        </thead>
        <tbody>

            <c:forEach items="${tSpringSessions}" var="springSess" varStatus="loop">
              
               
                <c:set var="placesRemaining" value="${springSess.placesRemaining()}" />

                <tr>
                    <th scope="row">${springSess.id}</th>
                    <td>${springSess.title}</td>
                    <td><fmt:formatDate type = "date" value = "${springSess.startDate}" /></td>  
                    <td><fmt:formatDate type = "date" value = "${springSess.endDate}" /></td>
                    <td><fmt:formatDate type = "date" pattern="EEEE" value ="${springSess.startDate}" /> </td>
                    <td><fmt:formatDate type = "time" value ="${springSess.startDate}" /> </td>
                    <td>${springSess.duration} hours</td>
                    <td>${springSess.classroom.name}</td>
                    <td>
                        <strong>${fn:length(springSess.students)} enrolled </strong>/ ${springSess.capacity} total<br>
                        
                        <a href="${pageContext.request.contextPath }/studentlist?id=${springSess.id}">List students</a>
                        
                    </td>
                    <td>
                        
                    </td>
                </tr>

            </c:forEach>  


        </tbody>

    </table>







    <div class ="container" align="right">







    </div>


    <script type="text/javascript">
        $(document).ready(function () {
            $('#autumnSession').DataTable();
            $('#springSession').DataTable();
        });
       
    </script>
    
     <%@ include file="footer.jsp" %>  
</body>
   
</html>
