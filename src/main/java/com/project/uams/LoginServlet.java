package com.project.uams;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                switch (role.toLowerCase()) {
                    case "employee":
                        response.sendRedirect("requestAccess.jsp");
                        break;
                    case "manager":
                        response.sendRedirect("pendingRequests.jsp");
                        break;
                    case "admin":
                        response.sendRedirect("createSoftware.jsp");
                        break;
                    default:
                        response.getWriter().println("Unknown role.");
                }
            } else {
                response.getWriter().println("Invalid username or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Login failed: " + e.getMessage());
        }
    }
}
