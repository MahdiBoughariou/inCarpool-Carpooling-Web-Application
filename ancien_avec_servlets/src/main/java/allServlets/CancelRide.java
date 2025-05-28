package allServlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Servlet implementation class CancelRide
 */
public class CancelRide extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*Connexion à la base*/
	 public static final String URL="jdbc:mysql://localhost:3306/covoiturage";
	 public static final String USER="root";
	 public static final String PASSWORD="";
	 Connection conn=null;
	 /*---------------------------------------------------------------------------*/
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelRide() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//récupérer l'id envoyer d'aprés le bouton id_offer_courant
		String rideId = request.getParameter("id");
		System.out.println(rideId);

        // Exécutez les actions de suppression et de mise à jour dans la base de données
        try{
        	conn = DriverManager.getConnection(URL, USER, PASSWORD);
        	
            // Suppression de la ligne dans la table 'ride' avec l'ID spécifié
            String deleteRideQuery = "DELETE FROM ride WHERE id_offer = ?";
            PreparedStatement deleteRideStmt = conn.prepareStatement(deleteRideQuery);
            deleteRideStmt.setString(1, rideId);
            deleteRideStmt.executeUpdate();

            // Incrémentation du nombre de places disponibles dans la table 'offer'
            String updateOfferQuery = "UPDATE offer SET nbplaces = nbplaces + 1 WHERE id = ?";
            PreparedStatement updateOfferStmt = conn.prepareStatement(updateOfferQuery);
            updateOfferStmt.setString(1, rideId);
            updateOfferStmt.executeUpdate();

            // Vérification et mise à jour de l'état de l'offre si nécessaire
            String checkOfferStateQuery = "SELECT state FROM offer WHERE id = ?";
            PreparedStatement checkOfferStateStmt = conn.prepareStatement(checkOfferStateQuery);
            checkOfferStateStmt.setString(1, rideId);
            
         // Exécutez la requête et récupérez le résultat dans un ResultSet
            ResultSet resultSet = checkOfferStateStmt.executeQuery();
            if (resultSet.next()) {
                String offerState = resultSet.getString("state");
                System.out.println(offerState);
                
                if ("expired".equals(offerState)) {
                    // Mettre à jour l'état de l'offre de "expired" à "available"
                    String updateStateQuery = "UPDATE offer SET state = 'available' WHERE id = ?";
                    PreparedStatement updateStateStmt = conn.prepareStatement(updateStateQuery);
                    updateStateStmt.setString(1, rideId);
                    updateStateStmt.executeUpdate();
                }
            }

            // Redirigez l'utilisateur vers une page appropriée après l'annulation réussie
            response.sendRedirect("YourRides");
            
        } catch (SQLException e) {
            // Gérez les exceptions SQL appropriées
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
