package allServlets;
import classes.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.sql.*;

public class ProcessVehicleDataServlet extends HttpServlet {
    
    // Method to process data based on the current step
	private void processData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		VehicleData vehicleData = (VehicleData) session.getAttribute("vehicleData");
		System.out.println(vehicleData.toString());

	    // Perform database operations using the data from the VehicleData bean
	    
	    PreparedStatement stmt = null;

	    String url = "jdbc:mysql://localhost:3306/covoiturage";
        String utilisateur = "root";
        String motDePasse = "";
        
      
        try (Connection conn = DriverManager.getConnection(url, utilisateur, motDePasse)) {
           
	        String sql = "INSERT INTO car (id_user, platenumber, id_brand, model, state) VALUES (?, ?, ?, ?, ?)";

	               
	        System.out.println(vehicleData.getLicensePlate());
	        // Prepare and execute SQL statement
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, vehicleData.getId());
	        stmt.setString(2, vehicleData.getLicensePlate()); // Assuming serie is a property in VehicleData
	       
	       
	        stmt.setInt(3, vehicleData.getBrand().getId());
	        stmt.setString(4, vehicleData.getModel());
	        stmt.setString(5, vehicleData.getState());
	        

	        stmt.executeUpdate();

	        // Redirect to the next step
	        response.sendRedirect("/projet_coVoiturage/VerifState");

            }
         
        
        catch (SQLException e) {
            e.printStackTrace(); // Gestion de l'exception (à personnaliser)
        }

	}

	        
	 


    
    // Method to determine the next step based on the current step
    private String getNextStep(String currentStep) {
        switch (currentStep) {
            case "addVehicule":
                return "addVehiculeMarque.jsp";
            case "addVehiculeMarque":
                return "addVehiculeModel.jsp";
            case "addVehiculeModel":
                return "addVehiculeEtat.jsp";
            case "addVehiculeEtat":
                return "profileuser.jsp"; // Assuming this is the final step
            default:
                // Handle unexpected step
                return "profileuser.jsp";
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
