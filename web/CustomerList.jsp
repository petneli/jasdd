<%@ page import="DSEshop.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="DSEshop.Customer" %><%--
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

<title>Customer List</title>
<ul>
    <%
        List<Customer> customerList = (List<Customer>)session.getAttribute("customerList");

        if(customerList == null) {
            System.out.println("No customers yet.");
        }
        else {
    %>

    <%
        for(Customer c : customerList) {
    %>

    <%= c.toString() + "  " %>
    <div style="display: inline-block;">

        <form action="../jasdd_war_exploded/rest/onlineShop/removeCustomer" style="display: inline-block;" method="GET">
            <input type="hidden" name="username" value="<%=c.getUserName()%>">
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
