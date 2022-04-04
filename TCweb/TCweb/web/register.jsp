<%-- 
    Document   : registration
    Created on : 15-Nov-2021, 14:54:17
    Author     : Cathal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="bootstrap-4.3.1/bootstrap.css">

        <script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>



        <title>Register </title>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%@ include file="header.jsp" %>  
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-12 col-sm-8 offset-sm-2 col-md-6 offset-md-3 mt-5 pt-3 pb-3 bg-white from-wrapper">
                    <div class="container">
                        <h1>Not registered yet?</h1>
                        <br>
                        <h3>Please fill in the form below to register as a student, and sign up to a course!</h3>
                        <form action="register" method="post">
                            <div class="form-group">
                                <label for="name">Name:</label>
                                <input type="text" name="name" class="form-control" id="name" placeholder="John"/>

                            </div>
                            <div class="form-group">
                                <label for="name">Surname:</label>
                                <input type="text" name="surname" class="form-control" id="surname" placeholder="Smyth"/>

                            </div>
                            <div class="form-group">
                                <label for="name">Email:</label>
                                <input type="email" name="email" class="form-control" id="email" placeholder="youremail@mail.com"/>

                            </div>
                            <div class="form-group">
                                <label for="name">Telephone:</label>
                                <input type="text" name="telephone" class="form-control" id="telephone" placeholder="04-12345567"/>

                            </div>
                            <div class="form-group">

                                <label for="password">Password:</label>
                                <input type="password" name="password" class="form-control" id="password" aria-describedby="passwordHelp" placeholder="password"/>

                                <small id="passwordHelp" class="form-text text-muted">Password must contain one number and one special character</small> 
                            </div>
                            <div class="form-group">
                                <label for="name">Street:</label>
                                <input type="text" name="street" class="form-control" id="street" placeholder="Zola Street"/>

                            </div>
                            <div class="form-group">
                                <label for="name">House/apartment no.:</label>
                                <input type="text" name="houseNumber" class="form-control" id="houseNumber" placeholder="31b"/>

                            </div>
                            <div class="form-group">
                                <label for="name">City:</label>
                                <input type="text" name="city" class="form-control" id="city" placeholder="Brussels"/>

                            </div>
                            <div class="form-group">
                                <label for="name">Postcode:</label>
                                <input type="text" name="postCode" class="form-control" id="postCode" placeholder="B-1034"/>

                            </div>
                            <div class="form-group">
                                <label for="status">Select your status:</label>
                                 <select name ="studentStatus" class ="form-select"> 
                                     <option value="" disabled selected>Select your status</option>
                                     <c:forEach items="${statuses}" var="studentStatus" varStatus="loop">
                                           
                                         <c:if test="${studentStatus.id != 5}">
                                             <option value ="${studentStatus.id}">
                                                 ${studentStatus.name}
                                             </option>
                                         </c:if>
   
                                     </c:forEach>
                                                  </select>
                                     

                            </div>
                            
                            <div class="row">
                                <div class="col-12 col-sm-4">
                                    <button type="submit" class="btn btn-primary"> Register</button> 
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>






    </body>
    <%@ include file="footer.jsp" %>  

    <script type="text/javascript">
        $(function () {
            $("form[name='register']").validate({
                rules: {
                    email: {
                        required: true,

                        email: true
                    },
                    password: {
                        required: true,
                        minlength: 5,
                        regx: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/




                    }
                },
                messages: {
                    password: {
                        required: "Please provide a password",
                        minlength: "Your password must be at least 6 characters long",
                        regx: "Your password must contain numbers and special characters"
                    },
                    email: "Please enter a valid email address"
                },
                submitHandler: function (form) {
                    form.submit();
                }
            });
        });






    </script>

</html>
