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
    <title>Session has been saved! </title>
         <%@ include file="header.jsp" %> 
    </head>
    <body>


        <div class="container">
    <div class="row">
        <div class="col-12 col-sm-8 offset-sm-2 col-md-6 offset-md-3 mt-5 pt-3 pb-3 bg-white from-wrapper">
            <div class="container">
                
                <h1>Your Session has been successfully added!</h1>
                <p></p>
                    
                
                 <h3>Details of your new Session:</h3>
                        <p><strong>Training:</strong><br>
                            ${newSessionDetails.training.title}
                        </p>
                        <p>
                            <strong>Capacity:</strong><br>
                            ${newSessionDetails.training.capacity}
                        </p>

                        <p>
                            <strong>Duration:</strong><br>
                            ${newSessionDetails.training.duration} weeks
                        </p>

                        <p><strong>Capacity:</strong><br>

                            ${newSessionDetails.training.capacity} 

                        </p>
                        <p><strong>Recurs:</strong><br>

                            Each  ${newSessionDetails.recurs} days (weekly)

                        </p>

                        <p>
                            <strong>Length of class:</strong><br>
                            ${newSessionDetails.duration} hours
                        </p>

                        <p><strong>Title:</strong><br>

                            ${newSessionDetails.title}

                        </p>
                        <p><strong>Start date:</strong><br>

                            ${newSessionDetails.startDate.toString()}

                        </p>
                        <p><strong>End date:</strong><br>

                            ${newSessionDetails.endDate.toString()}

                        </p>



                        <p><strong>Classroom:</strong><br>

                            ${newSessionDetails.classroom.name} 

                        </p>
                        <p><strong>Teacher:</strong><br>

                            ${newSessionDetails.teacher.name} ${newSessionDetails.teacher.surname}<br>
                           

                        </p>
                        
                         <div class="row">
                             <div class="col-12 col-sm-4"></div>
                                                             <p>
                             <a href ="${pageContext.request.contextPath }/training" class="btn btn-secondary">&laquo; back to Training page</a>
                        </p>
                             
                         </div>

       
              </div>
        </div>
    </div>
</div>
    </body>
       <%@ include file="footer.jsp" %>  
</html>
