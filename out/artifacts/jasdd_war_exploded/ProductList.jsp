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
    <li><a href="http://localhost:8080/jasdd_war_exploded/CustomerList.jsp">Customer List</a></li>
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

                <%
            for(Product p : productList) {
                %>

                <%= p.toString() + "  " %>
            <div style="display: inline-block;">
                <form action="../jasdd_war_exploded/rest/onlineShop/addToWishList" style="display: inline-block;" method="GET">
                    <input type="hidden" name="id" value="<%=p.getProductID()%>">
                    <input type="submit" style="display: inline;" value="Add To Wishlist">
                </form>
                <form action="../jasdd_war_exploded/rest/onlineShop/editProduct" style="display: inline-block;"  method="GET">
                    <input type="hidden" name="id" value="<%=p.getProductID()%>">
                    <input type="submit" style="display: inline;" value="Edit"><br>
                </form>
                <form action="../jasdd_war_exploded/rest/onlineShop/removeProduct" style="display: inline-block;" method="GET">
                    <input type="hidden" name="id" value="<%=p.getProductID()%>">
                    <input type="submit" style="display: inline;" value="Delete">
                </form>
            </div>
        <br>
                <%
            }
            %>

         <%
        }
    %>
    </ul>

</body>
</html>
