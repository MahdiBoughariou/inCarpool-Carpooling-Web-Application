package allServlets;
import classes.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.sql.*;

public class ProcessOfferDataServlet extends HttpServlet {
    
    // Method to process data based on the current step
	private void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Offer offerData = (Offer) session.getAttribute("offerData");
		System.out.println(offerData.toString());

	    // Perform database operations using the data from the VehicleData bean
	    
	    PreparedStatement stmt = null;
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String url = "jdbc:mysql://localhost:3306/covoiturage";
        String utilisateur = "root";
        String motDePasse = "";
        
      
        try (Connection conn = DriverManager.getConnection(url, utilisateur, motDePasse)) {
           
	        String sql = "INSERT INTO offer (departure, destination, date, time, id_car, nbplaces, price, comment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	               
	        
	        // Prepare and execute SQL statement
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, offerData.getDeparture());
	        stmt.setInt(2, offerData.getDestination()); // Assuming serie is a property in VehicleData
	        stmt.setTimestamp(3, offerData.getDate());
	        stmt.setTime(4, offerData.getTime());
	        stmt.setInt(5, offerData.getId_car());
	        stmt.setInt(6, offerData.getNbplaces());
	        stmt.setFloat(7, offerData.getPrice());
	        stmt.setString(8, offerData.getComment());
	        

	        stmt.executeUpdate();	        

	        // Redirect to the next step
	        response.sendRedirect("/projet_coVoiturage/VerifState");

            }
         
        
        catch (SQLException e) {
            e.printStackTrace(); // Gestion de l'exception (à personnaliser)
        }

	}


    
    // Handle POST requests
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processData(request, response);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{doPost(request, response);
			}
		}
}
