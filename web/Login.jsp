<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<form action="../jasdd_war_exploded/rest/onlineShop/login" method="POST">
    <label>Name: </label>
    <input name="customerName" />
    <br/>
    <label>Password: </label>
    <input name="customerPass" />
    <br/>
    <button id="submit" value="Register">Login</button>
</form>

</body>
</html>