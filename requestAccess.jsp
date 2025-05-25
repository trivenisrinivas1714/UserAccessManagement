<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, com.project.uams.DBUtil" %>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");

    if (username == null || !"Employee".equalsIgnoreCase(role)) {
        response.sendRedirect("login.jsp");
        return;
    }

    Connection conn = DBUtil.getConnection();
    PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM software");
    ResultSet rs = stmt.executeQuery();
%>
<html>
<head>
    <title>Request Access</title>
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
    <h2>Submit Access Request</h2>
    <form action="requestAccess" method="post">
        Software:
        <select name="softwareId" required>
            <% while(rs.next()) { %>
                <option value="<%= rs.getInt("id") %>"><%= rs.getString("name") %></option>
            <% } %>
        </select><br><br>

        Access Type:
        <select name="accessType" required>
            <option value="Read">Read</option>
            <option value="Write">Write</option>
            <option value="Admin">Admin</option>
        </select><br><br>

        Reason:<br>
        <textarea name="reason" rows="4" cols="45" required></textarea><br><br>

        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
