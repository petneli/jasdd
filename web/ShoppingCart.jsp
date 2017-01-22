<%@ page import="DSEshop.ShoppingCart" %>
<%@ page import="DSEshop.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sebastian
  Date: 22/01/2017
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart</title>
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
<ul>
    <%
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");

        if(shoppingCart == null) {
            System.out.println("No products in the cart.");
        }
        else {
            List<Product> list = shoppingCart.getShoppingCart();

            for(Product p : list) {
    %>
    <%= p.toString() %>
    <form action="../jasdd_war_exploded/rest/onlineShop/removeProductFromShoppingCart" style="display: inline-block;" method="GET">
        <input type="hidden" name="id" value="<%=p.getProductID()%>">
        <input type="submit" style="display: inline;" value="Delete">
    </form>
    <br>
    <%
            }
        }
    %>
</ul>


</body>
</html>
