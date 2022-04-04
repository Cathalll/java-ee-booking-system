<%-- 
    Document   : index.jsp
    Created on : 28-Nov-2021, 22:25:09
    Author     : Cathal
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        

        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/fullcalendar@3.5.1/dist/fullcalendar.css' />
        <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/jquery-ui.js'></script>
        
        <script src='http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js'></script>
        <script src='https://cdn.jsdelivr.net/npm/fullcalendar@3.5.1/dist/fullcalendar.js'></script>
        
        
<!--        <script src='https://cdn.jsdelivr.net/npm/fullcalendar@3.5.1/dist/fullcalendar.min.js'></script>-->

        <script>  
        $(document).ready(function() {
        $('#calendar').fullCalendar({
            theme: true,
            editable: false,
            events: "/controller/CalendarJsonServlet"
        });
 
    });

</script>
        
    </head>
    
     <body>





<div id="calendar"></div>
    
    </body>
    
    
    
</html>
