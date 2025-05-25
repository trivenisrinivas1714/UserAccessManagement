package com.project.uams;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SoftwareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String accessLevels = request.getParameter("accessLevels");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, accessLevels);
            stmt.executeUpdate();

            response.getWriter().println("✅ Software added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Error: " + e.getMessage());
        }
    }
}
