package com.project.uams;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");

        int softwareId = Integer.parseInt(request.getParameter("softwareId"));
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        try (Connection conn = DBUtil.getConnection()) {
            // get user ID
            PreparedStatement userStmt = conn.prepareStatement("SELECT id FROM users WHERE username = ?");
            userStmt.setString(1, username);
            ResultSet userRs = userStmt.executeQuery();
            if (!userRs.next()) {
                response.getWriter().println("User not found.");
                return;
            }
            int userId = userRs.getInt("id");

            // insert request
            PreparedStatement insertStmt = conn.prepareStatement(
                "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, 'Pending')");
            insertStmt.setInt(1, userId);
            insertStmt.setInt(2, softwareId);
            insertStmt.setString(3, accessType);
            insertStmt.setString(4, reason);
            insertStmt.executeUpdate();

            response.getWriter().println("✅ Access request submitted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Error: " + e.getMessage());
        }
    }
}
