<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
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

<form action="../jasdd_war_exploded/rest/onlineShop/add_product" method="POST">
    <label>Name: </label>
    <input name="productName" />
    <br/>
    <label>Price: </label>
    <input name="productPrice" />
    <br/>
    <button id="submit" value="Submit">Submit</button>
</form>

</body>
</html>