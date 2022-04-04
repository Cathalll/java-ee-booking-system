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
                <div class="col-12 col-sm-6 offset-sm-2 offset-md-3 mt-5 pt-3 pb-3 bg-white from-wrapper">
                    <div class="container">
                        <h1>Your order total:</h1>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Begins</th>
                                    <th scope="col">Ends</th>
                                    <th scope="col">Day of Week</th>
                                    <th scope="col">Price</th>
                                    <th scope="col"> </th>
                                    <th scope="col"> </th>
                                </tr>

                            </thead>
                            <tbody>
                                <c:set var="total" value="0"></c:set>
                                <c:forEach items="${sessionScope.cart}" var="item" varStatus="loop">

                                    <c:set var="total" value="${total + item.finalPrice }"></c:set>

                                        <tr>
                                            <th scope="row">${item.session.id}</th>
                                        <td>${item.session.title}</td>
                                        <td><fmt:formatDate type = "date" value = "${item.session.startDate}" /></td>
                                        <td><fmt:formatDate type = "date" value = "${item.session.endDate}" /></td>
                                        <td><fmt:formatDate type = "date" pattern="EEEE" value ="${item.session.startDate}" /></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.finalPrice < item.session.training.price.amount}">
                                                    <s>${item.session.training.price.name}</s>
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${item.session.training.price.name}
                                                    </c:otherwise>
                                                </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.finalPrice < item.session.training.price.amount}">
                                                    <fmt:formatNumber value = "${item.finalPrice}" type = "currency" /> discount!
                                                </c:when>
                                                <c:otherwise>

                                                </c:otherwise>

                                            </c:choose>
                                        </td>
                                        <td><a href="${pageContext.request.contextPath }/cart?action=remove&id=${item.session.id}" onclick="return confirm('Are you sure?')">Remove</a></td>



                                    </tr>


                                </c:forEach>  


                            </tbody>
                        </table>
                    </div>
                    <div class="form-group">
                        <form action="purchase" name ="purchase" method="post">

                            <label for="paymentType">Select a payment type:</label>
                            <select name ="paymentType" class ="form-select"> 
                                <c:forEach items="${paymentTypes}" var="paymentType" varStatus="loop">

                                    <option value ="${paymentType.id}">
                                        ${paymentType.name}  
                                    </option>


                                </c:forEach>  
                            </select>



                            <div class="row">
                                <div class="col-12 col-sm-4">


                                    <h3>Your total:<fmt:formatNumber value = "${total}" type = "currency" /></h3>

                                    <p><a href="${pageContext.request.contextPath}/session">Go back to Session page</a></p>

                                    <button type="submit" class="btn btn-primary"> Purchase</button> 


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