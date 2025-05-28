package allServlets;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckLicensePlateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String licensePlate = request.getParameter("licensePlate");
        boolean exists = checkLicensePlateInDatabase(licensePlate);
        
        // Set response content type
        response.setContentType("text/plain");
        
        // Write response
        PrintWriter out = response.getWriter();
        out.print(exists);
        out.flush();
    }
    
    // Method to check if license plate exists in the database
    private boolean checkLicensePlateInDatabase(String licensePlate) {
        // Perform database operations here
        // Return true if license plate exists, false otherwise
        // Sample code:
        // Assuming you have a connection to your database
        // You need to replace this with your actual database code
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covoiturage", "root", "");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM car WHERE platenumber = ?");
            stmt.setString(1, licensePlate);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if there's at least one row, indicating the license plate exists
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
