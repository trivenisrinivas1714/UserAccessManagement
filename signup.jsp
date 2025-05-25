<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Sign Up</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            height: 100vh;
            align-items: center;
            justify-content: center;
        }
        .signup-box {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px gray;
        }
    </style>
</head>
<body>
    <div class="signup-box">
        <h2>Sign Up</h2>
        <form action="signup" method="post">
            Username: <input type="text" name="username" required><br><br>
            Password: <input type="password" name="password" required><br><br>
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>