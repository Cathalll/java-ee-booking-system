<%-- 
    Document   : person-list
    Created on : 04-Oct-2021, 16:34:43
    Author     : Cathal
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person list jsp</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
                <p><a href="<c:url value = "/"/>">Try another search</a></p>
        
         <table width='80%'>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Surname</th>
                                <th>email</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="person" items="${listPerson}">

                                <tr>
                                    <td>
                                        <c:out value="${person.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${person.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${person.surname}" />
                                    </td>
                                    <td>
                                        <c:out value="${person.email}" />
                                    </td>
                                   
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>
                                                <tfoot>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Surname</th>
                                <th>email</th>
                                
                            </tr>
                        </tfoot>

                    </table>
                
                        <p><a href="<c:url value = "/"/>">Try another search</a></p>
    </body>
</html>
