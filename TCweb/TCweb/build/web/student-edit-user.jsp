<%-- 
    Document   : student-edit-user
    Created on : 17-Jan-2022, 00:09:27
    Author     : Cathal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Student details</title>
        <%@ include file="header.jsp" %>  
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm offset-sm-2 col-md-6 offset-md-3 mt-5 pt-3 pb-3 bg-white from-wrapper">
                    <div class="container">
                        <h3>Current details</h3>
                        <p>
                            <strong>Id:</strong><br>
                            #${user.id}</p> 
                        <p>
                            <strong>Name:</strong><br>
                            ${user.name} </p> 
                        <p>   
                            <strong>Surname:</strong><br>
                            ${user.surname}</p> 
                        <p>   
                            <strong>Email:</strong><br>
                            ${user.email}</p> 
                        <p>   
                            <strong>Telephone:</strong><br>
                            ${user.telephone}</p> 
                       
                        <p>        
                            <strong>Password:</strong><br>
                            [hidden]</p> 
                        <p>   
                            <strong>Status:</strong><br>
                            ${status.name}<br>

                        </p>
                        <br>

                        <p>
                            <strong>Address ID:</strong> <br>
                            #${user.address.id}</p>
                        <p>
                            <strong>Street:</strong><br>
                            ${user.address.street} </p>
                        <p>
                            <strong>House/apartment no:</strong><br>
                            ${user.address.houseNumber} </p>
                        <p>
                            <strong>City:</strong><br> 
                            ${user.address.city}</p>
                        <p>
                            <strong>Postcode:</strong><br>
                            ${user.address.postcode}  <br>

                        </p>


                    </div>  
                </div>

                <div class="col-sm mt-5 pt-3 pb-3 bg-white from-wrapper">
                    <div class="container">
                        <h3>New details</h3>

                        <form action="editstudentpost" method="post">
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
                                <input type="password" name="password" class="form-control" id="password" data-rule-password="true" aria-describedby="passwordHelp" placeholder="password" required/>

                                <small id="passwordHelp" class="form-text text-muted">Password must contain one number and one special character</small> 
                            </div>
                            <div class="form-group">
                                <label for="passwordConfirm">Confirm new password:</label>
                                <input type="password" name="passwordConfirm" class="form-control" id="passwordConfirm" data-rule-password="true"  data-rule-equalTo="#password" aria-describedby="passwordHelp" placeholder="password" required/>

                                <small id="passwordHelp" class="form-text text-muted">Please reenter your password to cofirm</small> 
                            </div>

                            <div class="form-group">
                                <label for="status">Update your status:</label>
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
                            <div class="row">
                                <div class="col-12 col-sm-4">
                                    <button type="submit" class="btn btn-primary">Save your changes</button> 
                                </div>
                            </div>
                        </form>


                    </div>
                </div>
            </div>
        </div>



    </body>
    <%@ include file="footer.jsp" %>  
</html>
