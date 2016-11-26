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
    <title>Productcatalogue</title>
    <ul>
    <%
        List<Product> productList = (List<Product>)session.getAttribute("productList");

        if(productList == null) {
            System.out.println("No products yet.");
        }
        else {
            for(Product p : productList) {
                %>
                <form action="../jasdd_war_exploded/rest/onlineShop/addToWishList" method="GET">
                            <input type="hidden" name="id" value="<%=p.getProductID()%>">
                            <%= p.toString() %>
                    <input type="submit" value="Add To Wishlist"><br>
                </form>
                <%
            }
        }
    %>
    </ul>
</head>
<body>

</body>
</html>
