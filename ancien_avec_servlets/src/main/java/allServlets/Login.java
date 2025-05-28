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

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*Connexion à la base*/
	 public static final String URL="jdbc:mysql://localhost:3306/covoiturage";
	 public static final String USER="root";
	 public static final String PASSWORD="";
	 Connection con=null;
	 /*---------------------------------------------------------------------------*/
	 
	 /*------------Tous les fonctions------------*/

	 /*Fonction qui vérifie le compte existe ou pas*/
	 
	 public boolean verifPassword(String password, String passwordBD){
		 return password.equals(passwordBD);
	 }
	 
	 /*-----------------------------------------------------------------------------------------------------------*/

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		response.setContentType("text/html;charset=UTF-8");

        // Récupération des paramètres du formulaire login
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
		 try {        	
	            con = DriverManager.getConnection(URL, USER, PASSWORD);

	            // Insertion des données du premier formulaire dans la base de données
	            PreparedStatement pstmt = con.prepareStatement("SELECT id, password, state FROM user WHERE username = ?");
	            pstmt.setString(1, username);
	            
	            try (ResultSet resultSet = pstmt.executeQuery()) {
	                if (resultSet.next()) {
	                	//récupérer les informations de resultSet
	                	int idBD= resultSet.getInt("id");
	                	String passwordBD= resultSet.getString("password");
	                	String stateBD= resultSet.getString("state");
	            	
	                	//vérifier si les données récupérées sont les mêmes entrées par le user ou pas
	                	if(!verifPassword(password, passwordBD)) {		
		                    // Informations d'identification incorrectes, renvoyer un message d'erreur à index.jsp
		                    request.setAttribute("checkMessage", "Password incorrect !");
		                    request.getRequestDispatcher("index.jsp").forward(request, response);
	                	}
		                else {
	                		//stocker l'id du user dans une session
	                        HttpSession session= request.getSession();
	                        session.setAttribute("idBD", idBD);
	                        session.setAttribute("stateBD", stateBD);
	                        
	            	        //Redirection vers la page user
	            	        response.sendRedirect("/projet_coVoiturage/user.jsp");
		                }
	                }
	                else {
	                    // Informations d'identification incorrectes, renvoyer un message d'erreur à index.jsp
	                    request.setAttribute("accountError", "Please create an account !");
	                    request.getRequestDispatcher("index.jsp").forward(request, response);
	                }
	            }
		        } catch (SQLException e) {
		        	 e.printStackTrace();
		        }
	}
}
