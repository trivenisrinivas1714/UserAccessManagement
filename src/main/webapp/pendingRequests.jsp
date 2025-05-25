<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, com.project.uams.DBUtil" %>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");

    if (username == null || !"Manager".equalsIgnoreCase(role)) {
        response.sendRedirect("login.jsp");
        return;
    }

    Connection conn = DBUtil.getConnection();
    String sql = "SELECT r.id, u.username, s.name AS software_name, r.access_type, r.reason " +
                 "FROM requests r JOIN users u ON r.user_id = u.id " +
                 "JOIN software s ON r.software_id = s.id WHERE r.status = 'Pending'";
    PreparedStatement stmt = conn.prepareStatement(sql);
    ResultSet rs = stmt.executeQuery();
%>
<html>
<head>
    <title>Pending Requests</title>
    <style>
        body {
            font-family: Arial;
            background-color: #f4f4f4;
            padding: 40px;
        }
        table {
            width: 90%;
            border-collapse: collapse;
            margin: auto;
            background-color: white;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ccc;
        }
        th {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
    <h2 align="center">Pending Access Requests</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Employee</th>
            <th>Software</th>
            <th>Access Type</th>
            <th>Reason</th>
            <th>Action</th>
        </tr>
        <% while(rs.next()) { %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("username") %></td>
            <td><%= rs.getString("software_name") %></td>
            <td><%= rs.getString("access_type") %></td>
            <td><%= rs.getString("reason") %></td>
            <td>
                <form action="approveRequest" method="post">
                    <input type="hidden" name="requestId" value="<%= rs.getInt("id") %>">
                    <button type="submit" name="action" value="Approve">Approve</button>
                    <button type="submit" name="action" value="Reject">Reject</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
