<%-- 
    Document   : sout-training-from-session
    Created on : 22-Jan-2022, 12:25:04
    Author     : Cathal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
         <p><strong>Training:</strong><br>
                            ${newSession.training.title}
                        </p>
                        <p>
                            <strong>Capacity:</strong><br>
                            ${newSession.training.capacity}
                        </p>

                        <p>
                            <strong>Duration:</strong><br>
                            ${newSession.training.duration} weeks
                        </p>
                         <p>
                            <strong>Session title:</strong><br>
                            ${newSession.title} 
                        </p>
                                                <p><strong>Start date:</strong><br>
                            
                                ${newSession.startDate.toString()}
                            
                        </p>
                        <p><strong>End date:</strong><br>
                            <c:if test=" ${newSession.endDate !=null}">
                                    <fmt:formatDate type = "date" value = "${newSession.endDate}" />
                            </c:if>
                        </p>
    </body>
</html>
