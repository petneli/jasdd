<%@ page import="DSEshop.*" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nelipetkova
  Date: 26.11.16
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
</ul>

<title>Wishlist</title>
<ul>
    <%
        WishList wishList = (WishList) session.getAttribute("wishList");

        if(wishList == null) {
            System.out.println("No products yet.");
        }
        else {
            List<Product> list = wishList.getWishList();

            for(Product p : list) {
        %>
                <%= p.toString() %>
            <form action="../jasdd_war_exploded/rest/onlineShop/removeProductFromWishlist" style="display: inline-block;" method="GET">
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
