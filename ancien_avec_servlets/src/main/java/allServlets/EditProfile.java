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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class EditProfile
 */
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*Connexion à la base*/
	 public static final String URL="jdbc:mysql://localhost:3306/covoiturage";
	 public static final String USER="root";
	 public static final String PASSWORD="";
	 Connection con=null;
	 /*---------------------------------------------------------------------------*/
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		//Récupérer l'id envoyer par profileuser.jsp
		String id = String.valueOf(session.getAttribute("idBD"));
		
		 try {        	
	            con = DriverManager.getConnection(URL, USER, PASSWORD);

	            // Récupération de données à travers l'id
	            PreparedStatement pstmt = con.prepareStatement("SELECT firstname, lastname, cin, email, birthdate, phone, username FROM user WHERE id = ?");
	            pstmt.setString(1, id);
	            
	            try (ResultSet resultSet = pstmt.executeQuery()) {
	                if (resultSet.next()) {
	                
	                	 // Récupérer les informations de l'utilisateur depuis le résultat de la requête
		                String firstName = resultSet.getString("firstname");
		                String lastName = resultSet.getString("lastname");
		                String cin = resultSet.getString("cin");
		                String email = resultSet.getString("email");
		                String birthDate = resultSet.getString("birthDate");
		                String phone = resultSet.getString("phone");
		                String username = resultSet.getString("username");

		                // Placer les informations dans les attributs de la requête pour les utiliser dans JSP
		                request.setAttribute("id", id);
		                request.setAttribute("firstName", firstName);
		                request.setAttribute("lastName", lastName);
		                request.setAttribute("cin", cin);
		                request.setAttribute("email", email);
		                request.setAttribute("birthDate", birthDate);
		                request.setAttribute("phone", phone);
		                request.setAttribute("username", username);
		                
		                // Rediriger vers la page EditProfile.jsp
		                request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
	                }
	            }
	            
		 }
		 catch (SQLException e) {
        	 e.printStackTrace();
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
