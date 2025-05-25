package com.project.uams;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String action = request.getParameter("action");

        String status = null;
        if ("Approve".equalsIgnoreCase(action)) {
            status = "Approved";
        } else if ("Reject".equalsIgnoreCase(action)) {
            status = "Rejected";
        } else {
            response.getWriter().println("Invalid action");
            return;
        }

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "UPDATE requests SET status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, requestId);
            stmt.executeUpdate();

            response.sendRedirect("pendingRequests.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error updating request: " + e.getMessage());
        }
    }
}
