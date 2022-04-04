<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>


        <link rel="stylesheet" href="bootstrap-4.3.1/bootstrap.css">

        <script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>


        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%@ include file="header.jsp" %>  
    </head>

    <body>
        <div class="container">
            <div class="row">
                <div class="col-12 col-sm-8 offset-sm-2 col-md-6 offset-md-3 mt-5 pt-3 pb-3 bg-white from-wrapper">
                    <div class="container">
                        <h3>Please enter your email and password to login:</h3>


                        <!--        <form id ="login" -->
                        <form action="login" name ="login" method="post">

                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" name="email" class="form-control" id="email" placeholder="youremail@mail.com"/>
                            </div>
                            <div class="form-group">

                                <label for="password">Password:</label>
                                <input type="password" name="password" class="form-control" id="password" aria-describedby="passwordHelp" placeholder="password"/>

                                <small id="passwordHelp" class="form-text text-muted">Password must contain one number and one special character</small> 
                            </div>  
                            <div class="row">
                                <div class="col-12 col-sm-4">
                                    <button type="submit" class="btn btn-primary"> Login</button> 
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
            $("form[name='login']").validate({
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
