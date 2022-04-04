<%-- 
    Document   : full-calendar-3
    Created on : 29-Nov-2021, 19:37:23
    Author     : Cathal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
<link href='https://fullcalendar.io/releases/fullcalendar/3.9.0/fullcalendar.min.css' rel='stylesheet' />
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/
bootstrap.min.css'>
<link href='https://fullcalendar.io/releases/fullcalendar/3.9.0/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='https://fullcalendar.io/releases/fullcalendar/3.9.0/lib/moment.min.js'></script>
<script src='https://fullcalendar.io/releases/fullcalendar/3.9.0/lib/jquery.min.js'></script>
<script src='https://fullcalendar.io/releases/fullcalendar/3.9.0/fullcalendar.min.js'></script>
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>

        <script>  
        $(document).ready(function() {
        $('#calendar').fullCalendar({
            theme: true,
            editable: false,
            events: "./calendar"
        });
//        $('#calendar').fullCalendar('updateEvents', "./calendar");
//$('#calendar').fullCalendar('addEventSource', "./calendar");
//$('#calendar').fullCalendar('refetchEvents');
$("#calendar").fullCalendar('rerenderEvents');
 
    });

</script>


    </head>
    
     <body>




<div id="calendar"></div>
    
    </body>
    
    
</html>
