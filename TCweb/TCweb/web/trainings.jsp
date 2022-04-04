<%-- 
    Document   : trainings
    Created on : 17-Jan-2022, 21:00:55
    Author     : Cathal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trainings</title>
        <%@ include file="header.jsp" %>  
    </head>
    <body>
        <h1>Trainings</h1>
        <div class="container">
            <div class="row">
                
                    <div class="container">
                        <table class="table" id="trainings" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Duration</th>
                                    <th scope="col">Capacity</th>
                                    <th scope="col">Create Session</th>
                                </tr>

                            </thead>
                            <tbody>
                                <c:forEach items="${trainings}" var="training" varStatus="loop">
                                    <tr>
                                        <th scope="row">${training.id}</th>
                                        <td>${training.title}</td>
                                        <td>${training.description}</td>
                                        <td>${training.price.name}</td>
                                        <td>${training.duration} weeks</td>
                                         <td>${training.capacity}</td>
                                        <td> <a href="${pageContext.request.contextPath }/createsession?id=${training.id}">Create a Session</a></td>
                                    </tr>


                                </c:forEach>

                            </tbody>


                        </table>
                    </div>


               
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-12 col-sm-8 offset-sm-2 col-md-6 offset-md-3 mt-5 pt-3 pb-3 bg-white from-wrapper">
                    <div class="container">
                        <h3>Create a new training</h3>
                        <form action="training" method="post">
                            <div class="form-group">
                                <label for="title">Title:</label>
                                <input type="text" name="title" class="form-control" id="title" placeholder="Java 101"/>
                            </div>
                            <div class="form-group">
                                <label for="description">Description:</label>
                                <input type="text" name="description" class="form-control" id="description" placeholder="Description of the training"/>
                            </div>
                            <div class="form-group">
                                <div class="form-group">
                                    <label for="price">Price:</label>
                                    <select name ="price" class ="form-select"> 
                                        <option value="" disabled selected>Select price</option>
                                        <c:forEach items="${prices}" var="price" varStatus="loop">

                                            <option value ="${price.id}">
                                                ${price.name}
                                            </option>

                                        </c:forEach>
                                    </select>


                                </div>
                                <div class="form-group">
                                    <label for="duration">Duration (weeks):</label>
                                    <input type="number" name="duration" class="form-control numeric" id="duration" placeholder="12"  min="10" max="15"/>
                                     <small id="durationHelp" class="form-text text-muted">Please enter a number between 10 and 15</small>
                                </div>
                                <div class="form-group">
                                    <label for="Capacity">Capacity:</label>
                                    <input type="number" name="capacity" class="form-control numeric" id="capacity" placeholder="40"  min="25" max="60"/>
                                    <small id="capacityHelp" class="form-text text-muted">Please enter a number between 25 and 40</small>
                                </div>
                                                            
                            <div class="row">
                                <div class="col-12 col-sm-4">
                                    <button type="submit" class="btn btn-primary">Add training</button> 
                                </div>
                            </div>



                        </form>


                    </div>


                </div>
            </div>
        </div>
        </div>


    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#trainings').DataTable();

        });
        //control for input need do be numeric
        $(".numeric").numeric();

        $(".integer").numeric(false, function () {
            alert("Integers only");
            this.value = "";
            this.focus();
        });

        $(".positive").numeric({negative: false},
                function () {
                    alert("No negative values");
                    this.value = "";
                    this.focus();
                });

        $(".positive-integer").numeric({decimal: false, negative: false},
                function () {
                    alert("Positive integers only");
                    this.value = "";
                    this.focus();
                });

        $("#remove").click(
                function (e)
                {
                    e.preventDefault();
                    $(".numeric,.integer,.positive").removeNumeric();
                }
        );

    </script>
    <%@ include file="footer.jsp" %>  
</html>
