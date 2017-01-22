<%@ page import="DSEshop.Product" %>
<%@ page import="java.security.ProtectionDomain" %>
<%@ page import="DSEshop.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
</head>
<body>
<ul>
    <li><a href="http://localhost:8080/jasdd_war_exploded/Login.jsp">Login</a></li>
    <li><a href="http://localhost:8080/jasdd_war_exploded/Register.jsp">Register</a></li>
    <li><a href="http://localhost:8080/jasdd_war_exploded/ProductList.jsp">Productcatalogue</a></li>
    <li><a href="http://localhost:8080/jasdd_war_exploded/rest/onlineShop">Hello page</a></li>
    <li><a href="http://localhost:8080/jasdd_war_exploded/AddProduct.jsp">Add Product</a></li>
    <li><a href="http://localhost:8080/jasdd_war_exploded/Wishlist.jsp">Wishlist</a></li>
    <li><a href="http://localhost:8080/jasdd_war_exploded/CustomerList.jsp">Customer List</a></li>
    <li><a href="http://localhost:8080/jasdd_war_exploded/ShoppingCart.jsp">Shopping Cart</a></li>
</ul>

<% Customer c = (Customer) session.getAttribute("customerToBeEdited"); %>
<form action="../jasdd_war_exploded/rest/onlineShop/edit_customer" method="POST">
    <label>Name: </label>
    <input name="userName" value="<%=c.getUserName()%>"/>
    <br/>
    <label>Price: </label>
    <input name="userPassword" value="<%=c.getPassword()%>"/>
    <br/>
    <label>Price: </label>
    <input name="userBilling" value="<%=c.getBillingAddress()%>"/>
    <br/>
    <label>Price: </label>
    <input name="userPayment" value="<%=c.getPaymentMethod()%>"/>
    <br/>
    <input name="userId" type="hidden" value="<%=c.getUserID()%>">
    <button id="submit" value="Submit">Edit</button>
</form>

</body>
</html>