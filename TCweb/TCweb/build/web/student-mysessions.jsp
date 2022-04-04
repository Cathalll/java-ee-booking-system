<%-- 
    Document   : student-sessions
    Created on : 01-Jan-2022, 11:35:33
    Author     : Cathal
--%>




<html>
    <head>

        <%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%@ taglib prefix="myfn" uri="http://samplefn"%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.css">  

    <title>Learning Dashboard - Student</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%@ include file="header.jsp" %> 

</head>
<body>

    <h1>Learning Dashboard - Student</h1>

    <h2>Autumn Sessions</h2>
    <table class="table" id="autumn">
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
                <th scope="col">Teacher</th>
                <th scope="col">Cancel inscription</th>

                <th scope="col"> </th>
            </tr>

        </thead>
        <tbody>

            <c:forEach items="${stAutumnSessions}" var="autumnSess" varStatus="loop">


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
                        ${autumnSess.teacher.name} ${autumnSess.teacher.surname}<br>
                        <a href="mailto:${autumnSess.teacher.email}">${autumnSess.teacher.email}</a>

                    </td>
                    <td>
                        <button class ="button" onclick="deletePayment(${autumnSess.id},${user.id}); this.disabled = true;">Unenroll from session</button>
                    </td>
                </tr>

            </c:forEach>  


        </tbody>

    </table>

    <p></p>
    <p></p>




    <h2> Spring Sessions</h2>
    <p></p>
    <table class="table" id="spring">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Begins</th>
                <th scope="col">Ends</th>
                <th scope="col">Day of Week</th>
                <th scope="col">Start time</th>
                <th scope="col">Duration</th>
                <th scope="col">Price</th>
                <th scope="col">Teacher </th>
                <th scope="col">Cancel inscription</th>
            </tr>

        </thead>
        <tbody>

            <c:forEach items="${stSpringSessions}" var="springSess" varStatus="loop">


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

                        ${springSess.teacher.name} ${springSess.teacher.surname}<br>
                        <a href="mailto:${springSess.teacher.email}">${springSess.teacher.email}</a>
                    </td>
                    <td>
                         <button class ="button" onclick="deletePayment(${springSess.id},${user.id}); this.disabled = true;">Unenroll from session</button>

                    </td>
                </tr>

            </c:forEach>  


        </tbody>

    </table>







    <div class ="container" align="right">







    </div>


    <script type="text/javascript">
        $(document).ready(function () {
            $('#autumn').DataTable();
            $('#spring').DataTable();
        });
        
       function deletePayment(sessionId, userId){
           
                     if (confirm('Delete your inscription for this Session?'))
            {
                $.ajax({
                    
                    
                    url: "${pageContext.request.contextPath }/deletemypayment?sessionid=" + sessionId +"&userid="+userId,
                    dataType: "JSON",
                    success: function (data)
                    {
                       alert("Your inscription to this Session has been deleted!");
                       location.reload();


                    }
                });
            }
        
           
           
       } 

    </script>
</body>

<%@ include file="footer.jsp" %>  
</html>
