<%@ page import="DSEshop.Product" %>
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
</ul>

    <title>Productcatalogue</title>
    <ul>
    <%
        List<Product> productList = (List<Product>)session.getAttribute("productList");

        if(productList == null) {
            System.out.println("No products yet.");
        }
        else {
            %>
            <form action="../jasdd_war_exploded/rest/onlineShop/addToWishList" method="GET">
                <%
            for(Product p : productList) {
                %>
                <input type="checkbox" name="id" value="<%=p.getProductID()%>">
                <%= p.toString() %><br>
                <%
            }
            %>
                <input type="submit" value="Add To Wishlist"><br>
            </form>
         <%
        }
    %>
    </ul>

</body>
</html>
