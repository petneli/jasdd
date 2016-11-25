<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
<form action="../jasdd_war_exploded/rest/onlineShop/register" method="POST">
    <label>Name: </label>
    <input name="customerName" />
    <br/>
    <label>Password: </label>
    <input name="customerPass" />
    <br/>
    <button id="submit" value="Register">Register</button>
</form>

</body>
</html>