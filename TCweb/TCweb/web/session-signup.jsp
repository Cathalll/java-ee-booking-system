<%-- 
    Document   : session-signup
    Created on : 06-Dec-2021, 14:54:16
    Author     : Cathal
--%>

<html>
    <head>

        <%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%@ taglib prefix="myfn" uri="http://samplefn"%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.css">  
    <!--           changed from relative link to local drive-->
    <!--
        <script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>-->

    <title>Sign up for a session</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%@ include file="header.jsp" %> 

</head>
<body>
    
        <div class ="container" align="right" mb-14 mt-2>



        
      

        <h2>Sessions in cart: <strong><span id="cartCounter" value="0"></span></strong>  - <a href="${pageContext.request.contextPath }/cart">Go to cart</a></h2>
        


    </div>
        <div class ="container" align="right" mb-14 mt-2>
    <h2>Autumn Sessions</h2>
    <table class="table" id="autumnSession">
        <p></p>
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Begins</th>
                <th scope="col">Ends</th>
                <th scope="col">Day of Week</th>
                <th scope="col">Start time</th>
                <th scope="col">Duration</th>
                <th scope="col">Price</th>
                <th scope="col"> </th>
            </tr>

        </thead>
        <tbody>

            <c:forEach items="${autumnSessions}" var="autumnSess" varStatus="loop">
                <c:set var="user" value="${user}" />
               
                <c:set var="placesRemaining" value="${autumnSess.placesRemaining()}" />

                <tr>
                    <th scope="row">${autumnSess.id}</th>
                    <td>${autumnSess.title}</td>
                    <td><fmt:formatDate type = "date" value = "${autumnSess.startDate}" /></td>  
                    <td><fmt:formatDate type = "date" value = "${autumnSess.endDate}" /></td>
                    <td><fmt:formatDate type = "date" pattern="EEEE" value ="${autumnSess.startDate}" /> </td>
                    <td><fmt:formatDate type = "time" value ="${autumnSess.startDate}" /> </td>
                    <td>${autumnSess.duration} hours</td>
                    <td>${autumnSess.training.price.name}</td>
                    <td>


                        <c:choose>



                            <c:when test="${autumnSess.studentIsEnrolled(user)}">
                                Already enrolled!
                            </c:when>
                                
                                <c:otherwise>
                                    
                                    <c:choose>
                                        
                                         <c:when test="${user.sessionIsInConflict(autumnSess)}">
                                                Conflicts with Session you are already enrolled in!
                                
                                         </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        
                                                         <c:when test="${autumnSess.placesRemaining() == 0}">
                                                                Sorry, this Session is full!
                                                            </c:when>
                                                                
                                                                <c:otherwise>
                                                                     <button class ="button" onclick="addToCart(<c:out value="${autumnSess.id}" />);cartPlusPlus();this.disabled = true;" >Add to Cart</button>
                                                                    
                                                                </c:otherwise>
                                                        
                                                        
                                                    </c:choose>
                                                    
                                                </c:otherwise>
                                        
                                        
                                    </c:choose> 
                                    
                                    
                                </c:otherwise>


                        </c:choose>


                    </td>
                </tr>

            </c:forEach>  


        </tbody>

    </table>
    
   
    
    <p></p>
    <p></p>
        
     </div>
    <div class ="container" align="right" mb-14 mt-2>

    <h2> Spring Sessions</h2>
    <p></p>
    <table class="table" id="springSession">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Begins</th>
                <th scope="col">Ends</th>
                <th scope="col">Day of Week</th>
                <th scope="col">Start time</th>
                <th scope="col">Duration</th>
                <th scope="col">Price</th>
                <th scope="col"> </th>
            </tr>

        </thead>
        <tbody>

            <c:forEach items="${springSessions}" var="springSess" varStatus="loop">

               
                <c:set var="placesRemaining" value="${springSess.placesRemaining()}" />

                <tr>
                    <th scope="row">${springSess.id}</th>
                    <td>${springSess.title}</td>
                    <td><fmt:formatDate type = "date" value = "${springSess.startDate}" /></td>  
                    <td><fmt:formatDate type = "date" value = "${springSess.endDate}" /></td>
                    <td><fmt:formatDate type = "date" pattern="EEEE" value ="${springSess.startDate}" /> </td>
                    <td><fmt:formatDate type = "time" value ="${springSess.startDate}" /> </td>
                    <td>${springSess.duration} hours</td>
                    <td>${springSess.training.price.name}</td>
                    <td>


                        <c:choose>


                            <c:when test="${user.sessionIsInConflict(springSess)}">
                                Conflicts with Session you are already enrolled in!
                            </c:when>

                            <c:when test="${springSess.studentIsEnrolled(user)}">
                                Already enrolled!
                            </c:when>

                            <c:when test="${springSess.placesRemaining() == 0}">
                                Sorry, this Session is full!
                            </c:when>
                            <c:otherwise>
                                <button class ="button" onclick="addToCart(<c:out value="${springSess.id}" />);cartPlusPlus();this.disabled = true;" >Add to Cart</button>

                            </c:otherwise>



                        </c:choose>


                    </td>
                </tr>

            </c:forEach>  


        </tbody>

    </table>

</div>


   





</body>
    <%@ include file="footer.jsp" %>  
    
    
    <script type="text/javascript">
        $(document).ready(function () {
            $('#autumnSession').DataTable();
            $('#springSession').DataTable();
        });
        var save_method; //for save method string
        var table;
        var cartCounter = 0;
        
    function cartPlusPlus() {
        cartCounter++;
        
        document.getElementById('cartCounter').innerHTML =cartCounter;
    }

        




        function addToCart(id)
        {
            if (confirm('Add to Cart?'))
            {
                $.ajax({
                    url: "${pageContext.request.contextPath }/cart?&action=buy&id=" + id,
                    dataType: "JSON",
                    success: function (data)
                    {
                       
                        location.reload();


                    }
                });
            }
        }
 
    </script>
</html>
