<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
</head>
<body>
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