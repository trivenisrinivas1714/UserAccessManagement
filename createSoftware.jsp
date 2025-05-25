<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");

    if (username == null || !"Admin".equalsIgnoreCase(role)) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Add Software</title>
    <style>
        body {
            font-family: Arial;
            background-color: #eef;
            display: flex;
            justify-content: center;
            padding-top: 50px;
        }
        .box {
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 10px gray;
            width: 400px;
        }
    </style>
</head>
<body>
<div class="box">
    <h2>Add New Software</h2>
    <form action="addSoftware" method="post">
        Name: <input type="text" name="name" required><br><br>
        Description:<br>
        <textarea name="description" rows="4" cols="45" required></textarea><br><br>
        Access Levels (e.g. Read,Write,Admin):<br>
        <input type="text" name="accessLevels" required><br><br>
        <input type="submit" value="Add Software">
    </form>
</div>
</body>
</html>
