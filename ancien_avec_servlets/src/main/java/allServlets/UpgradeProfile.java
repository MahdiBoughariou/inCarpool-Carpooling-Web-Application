package allServlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class UpgradeProfile
 */
public class UpgradeProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*Connexion à la base*/
	 public static final String URL="jdbc:mysql://localhost:3306/covoiturage";
	 public static final String USER="root";
	 public static final String PASSWORD="";
	 Connection con=null;
	 /*---------------------------------------------------------------------------*/
	 
	 /*------------Tous les fonctions------------*/
	 
	 /*Fonction qui vérifie si l'extension est pdf ou pas*/
	 
	 public static boolean isPDF(String fileName) {		 
		 return fileName.toLowerCase().endsWith(".pdf");
	 }
	 
	 /*----------------------------------------------------------------------------------*/
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpgradeProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
			
		HttpSession session = request.getSession();
		int id = Integer.parseInt(String.valueOf(session.getAttribute("idBD")));
		
		// Récupération des paramètres du premier formulaire
        String id_img = request.getParameter("id_img");
        String lic_img = request.getParameter("lic_img");
        
        if( !(isPDF(id_img)) ||  !(isPDF(id_img)) ) {
            request.setAttribute("pdfError", "The file extension must be PDF !");
            request.getRequestDispatcher("UpgradeProfile.jsp").forward(request, response);
            return;
        }
        else {
        	
        	try (Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
                // Requête SQL pour mettre à jour les informations de l'utilisateur
                String sql = "UPDATE user SET img_licence = ?, img_cin = ?, state = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, lic_img);
                statement.setString(2, id_img);
                //le user devient en attente
                statement.setString(3, "pending");
                statement.setInt(4, id);

                
                // Exécuter la requête
                int rowsUpdated = statement.executeUpdate();
                
                if (rowsUpdated > 0) {
                    // Les informations de l'utilisateur ont été mises à jour avec succès
                    // Vous pouvez rediriger l'utilisateur vers la page user.jsp
                    response.sendRedirect("/projet_coVoiturage/user.jsp");
                }
               
            }
	   		 catch (SQLException e) {
	        	 e.printStackTrace();
	        }
        }
	}

}
