<%-- 
    Document   : student-list
    Created on : 02-Jan-2022, 17:33:16
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

    <title>Sign up for a session</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%@ include file="header.jsp" %> 

</head>
<body>

    <h2>Students - #${session.id}, ${session.title} </h2>



    <p> <br>

        <fmt:formatDate type = "date" value = "${session.startDate}" /> - <fmt:formatDate type = "date" value = "${session.endDate}" />
        <fmt:formatDate type = "date" pattern="EEEE" value ="${session.startDate}" /> - <fmt:formatDate type = "time" value ="${session.startDate}" /> 

    </p>
    
    <a href ="${pageContext.request.contextPath }/teacher" class="btn btn-secondary">&laquo; back to Dashboard</a>
    
    <table class="table" id="students">
        <p></p>
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Email</th>
                <th scope="col">Status</th>
                <th scope="col">Address</th>
                <th scope="col">Date registered</th>
                <th scope="col">Payment ID & amount</th>
  
            </tr>

        </thead>
        <tbody>

            <c:forEach items="${students}" var="student" varStatus="loop">




                <tr>
                    <th scope="row">${student.id}</th>
                    <td>${student.name}</td>
                    <td>${student.surname}</td>  
                    <td>${student.email}</td>
                    <td>${student.status.name}</td>
                    <td>Address ID #${student.address.id}<br>
                        ${student.address.houseNumber} ${student.address.street} <br>
                        ${student.address.city}, ${student.address.postcode}  <br>
                        
                        
                    </td>
                      <c:forEach items="${payments}" var="payment" varStatus="loop"> 
                    <c:if test="${student.id eq payment.user.id}">
                          <td><fmt:formatDate type = "date" value = "${payment.dateOfPayments}" /></td>  
                          <td>#${payment.id},  <fmt:formatNumber value = "${payment.amount}" type = "currency" /> </td>
                               
                             </c:if>
                    </c:forEach> 
                    
                    

                </tr>

            </c:forEach>  



        </tbody>

    </table>
    
      <a href ="${pageContext.request.contextPath }/teacher" class="btn btn-secondary">&laquo; back to Dashboard</a>

    
      



 
</p>

</body>
<script type="text/javascript">
    $(document).ready(function () {
        $('#students').DataTable();

    });

</script>

<%@ include file="footer.jsp" %>  
</html>
