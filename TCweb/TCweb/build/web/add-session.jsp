<%-- 
    Document   : add-session
    Created on : 20-Jan-2022, 23:20:30
    Author     : Cathal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a new Session</title>
        <%@ include file="header.jsp" %>  
    </head>
    <body>

        <div class="container">
            <div class="row">

                <div class="col-sm offset-sm-2 col-md-6 offset-md-3 mt-5 pt-3 pb-3 bg-white from-wrapper">

                    <div class="container">
                         <a href ="${pageContext.request.contextPath }/training" class="btn btn-secondary">&laquo; back to Training page</a>
<!--                         <a href="${pageContext.request.contextPath }/training" class="previous">&laquo; back to Training page</a>-->
                        <h1>Add a new Session</h1>



                        <p> </p>


                        <h3>Add a title for your Session</h3>
                        <div class="form-group mt-5" >
                            <form id="AddTitleAndDates" action="AddTitleAndDates" method="post">
                                <div class="form-group">


                                    <label for="title">Add a title for your Session</label>
                                    <input type="text" name="title" class="form-control" id="title" placeholder="Java 101 - spring Session"/>
                                </div>
                                <div class="form-group">
                                    <label for ="startDateAutumn">Start date(if autumn Session)</label>
                                    <input type ="date" name="startDateAutumn" class="form-control" id="startDateAutumn" value="2022-09-02" min="2022-09-01" max="2022-09-30"/>




                                </div>
                                <div class="form-group">
                                    <label for ="startDateSpring">Start date(if spring Session)</label>
                                    <input type ="date" name="startDateSpring" class="form-control" id="startDateSpring" value="2023-01-04" min="2023-01-01" max="2023-01-31"/>

<!--                                    <input type="hidden" name="endDate" id="endDate" />-->


                                </div>
                                <input type="hidden" name="startDate" id="startDate" />
                                <input type="hidden" name="endDate" id="endDate" />
                                <c:if test="${newSession.title eq null}">

                                    <div class="row">
                                        <div class="col-12 col-sm-4">
                                            <button class="btn btn-primary" id= "submitButtonId" type = "submit" onclick="this.disabled = true;">Add to Session</button> 
                                        </div>
                                    </div>

                                </c:if>
                            </form>


                        </div>




                        <p> </p>
                        <p> </p>



                        <div class="form-group mt-5">       
                            <form id = "setClassroom" action ="SetClassroomSession" method = "post">

                                <label for="classroom">Select classroom:</label>
                                <select name="classroomId" class ="form-select">
                                    <option value="" disabled selected>Select classroom</option>
                                    <c:forEach items="${availableClassrooms}" var = "availableClassroom" varStatus ="loop">
                                        <option value ="${availableClassroom.id}">
                                            Classroom ${availableClassroom.name}, capacity: ${availableClassroom.capacity}

                                        </option>
                                    </c:forEach>


                                </select>
                                <c:if test="${newSession.classroom eq null}">

                                    <div class="row">
                                        <div class="col-12 col-sm-4">
                                            <button class="btn btn-primary" id= "classroomSubmit" type = "submit" onclick="this.disabled = true;">Add classroom to Session</button> 
                                        </div>
                                    </div>
                                </c:if>
                            </form>  
                        </div>


                        <p> </p>
                        <p> </p>
                        <div class ="form-group mt-5">
                            <form id ="setTeacher" action ="SetTeacherSession" method ="post">
                                <label for="teacher">Select teacher:</label>
                                <select name="teacherId" class ="form-select">
                                    <option value="" disabled selected>Select available teacher</option>
                                    <c:forEach items="${availableTeachers}" var = "availableTeacher" varStatus ="loop">
                                        <option value ="${availableTeacher.id}">
                                            ${availableTeacher.name} ${availableTeacher.surname}
                                        </option>
                                    </c:forEach>
                                </select>

                                <c:if test="${newSession.teacher eq null}">

                                    <div class="row">
                                        <div class="col-12 col-sm-4">
                                            <button class="btn btn-primary" id="teacherSubmit" type ="submit" onclick="this.disabled = true;">Add teacher to Session</button> 
                                        </div>
                                    </div>

                                </c:if>

                            </form>

                        </div>

                        <p> </p>
                        <p> </p>




                    </div>
                </div>
                <div class="col-sm mt-5 pt-3 pb-3 bg-white from-wrapper">
                    <div class="container">
                        <h3>Session to be added:</h3>
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

                        <p><strong>Capacity:</strong><br>

                            ${newSession.training.capacity} 

                        </p>
                        <p><strong>Recurs:</strong><br>

                            Each  ${newSession.recurs} days (weekly)

                        </p>

                        <p>
                            <strong>Length of class:</strong><br>
                            ${newSession.duration} hours
                        </p>

                        <p><strong>Title:</strong><br>

                            ${newSession.title}

                        </p>
                        <p><strong>Start date:</strong><br>
                            <c:if test="${newSession.startDate ne null}">

                                <fmt:formatDate type = "date" value = "${newSession.startDate}" />
                            </c:if>

                        </p>
                        <p><strong>End date:</strong><br>
                            <c:if test="${newSession.endDate ne null}">

                                <fmt:formatDate type = "date" value = "${newSession.endDate}" />

                            </c:if>

                        </p>



                        <p><strong>Classroom:</strong><br>

                            ${newSession.classroom.name} 

                        </p>
                        <p><strong>Teacher:</strong><br>

                            ${newSession.teacher.name} ${newSession.teacher.surname}<br>


                        </p>

                        <div class ="form-group">
                            <form id ="saveSession" action ="SaveSession" method ="post">

                                <c:if test="${newSession.teacher ne null}">
                                    <div class="row">
                                        <div class="col-12 col-sm-4">
                                            <button class="btn btn-primary" id="sessionSubmit" type ="submit" onclick="this.disabled = true;">Save session</button> 
                                        </div>
                                    </div>
                                </c:if>

                            </form>

                        </div>





                    </div>
                </div>
            </div>
        </div>

    </body>
    <%@ include file="footer.jsp" %>  

    <script type="text/javascript">
        var durationWeeksString = '${newSession.training.duration}';
        var durationWeeks = parseInt(durationWeeksString);

//        alert("value f duration weeks =" + durationWeeks)
        var endDate = new Date();
//        var startDateAutumn;
//        var startDateSpring;

        var startDate;

        function addDays(date, numberOfWeeks) {
            var result = new Date(date);
            result.setDate(date.getDate() + numberOfWeeks * 7);
            return result;
        }
        
//        document.getElementById("startDateAutumn").addEventListener("change", function () {
//            var startInput = this.value;
//
//            //we reassign the startDate value as startDateAutumn
//            document.getElementById('startDate').value = startInput;
//
//            var startDate = new Date(startInput);
//
//            alert("value f startInput = " + startInput);
//
//            endDate = addDays(startDate, durationWeeks);
//            alert("value of endDate = " + endDate);
//
//
//
//            document.getElementById('endDate').value = endDate.getTime();
//
//
//
//
//
//        });

               const picker1 = document.getElementById('startDateAutumn');
        picker1.addEventListener('input', function (e) {
            var day = new Date(this.value).getUTCDay();
            if ([6, 0].includes(day)) {
                e.preventDefault();
                this.value = '';
                alert('Weekends not allowed');
            }else{
                
                var startInput = this.value;

            //we reassign the startDate value as startDateAutumn
            document.getElementById('startDate').value = startInput;

            var startDate = new Date(startInput);

            //alert("value f startInput = " + startInput);

            endDate = addDays(startDate, durationWeeks);
            //alert("value of endDate = " + endDate);



            document.getElementById('endDate').value = endDate.getTime();


            }
        });



        const picker = document.getElementById('startDateSpring');
        picker.addEventListener('input', function (e) {
            var day = new Date(this.value).getUTCDay();
            if ([6, 0].includes(day)) {
                e.preventDefault();
                this.value = '';
                alert('Weekends not allowed');
            }else{
                
                  var startInput = this.value;

                document.getElementById('startDate').value = startInput;

                var startDate = new Date(startInput);

                //alert("value f startInput = " + startInput);

                endDate = addDays(startDate, durationWeeks);
               // alert("value of endDate = " + endDate);



                document.getElementById('endDate').value = endDate.getTime();
            }
        });

//        document.getElementById("startDateSpring").addEventListener("change", function () {
//            //check for weekend dates
//
// 
//
//                var startInput = this.value;
//
//                document.getElementById('startDate').value = startInput;
//
//                var startDate = new Date(startInput);
//
//                alert("value f startInput = " + startInput);
//
//                endDate = addDays(startDate, durationWeeks);
//                alert("value of endDate = " + endDate);
//
//
//
//                document.getElementById('endDate').value = endDate.getTime();
//
//            
//
//
//        });

        $("#submitButtonId").click(function () {

            var url = "${pageContext.request.contextPath}/AddTitleAndDates"; // the script where you handle the form input.

            $.ajax({
                type: "POST",
                url: url,
                data: $("#AddTitleAndDates").serialize(), // serializes the form's elements.
                success: function (data)
                {
                    //alert(data); // show response from the php script.
                    location.reload();
                }
            });

            return false; // avoid to execute the actual submit of the form.

        });


        $("#classroomSubmit").click(function () {

            var url = "${pageContext.request.contextPath}/SetClassroomSession"; // the script where you handle the form input.

            $.ajax({
                type: "POST",
                url: url,
                data: $("#setClassroom").serialize(), // serializes the form's elements.
                success: function (data)
                {
                    //alert(data); // show response from the php script.
                    location.reload();
                }
            });

            return false; // avoid to execute the actual submit of the form.
        });


        $("#teacherSubmit").click(function () {

            var url = "${pageContext.request.contextPath}/SetTeacherSession"; // the script where you handle the form input.

            $.ajax({
                type: "POST",
                url: url,
                data: $("#setTeacher").serialize(), // serializes the form's elements.
                success: function (data)
                {
                    //alert(data); // show response from the php script.
                    location.reload();
                }
            });

            return false; // avoid to execute the actual submit of the form.
        });









    </script>
</html>
