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
 * Servlet implementation class LoginA
 */

public class LoginA extends HttpServlet {
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
    public LoginA() {
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
		
		//récupérer les informations du formulaire
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
			 con = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement("SELECT username,password FROM admin WHERE username = ?");
	         pstmt.setString(1, username);
	         
	         try (ResultSet resultSet = pstmt.executeQuery()) {
	                if (resultSet.next()) {
	                	
	                	//récupérer les informations de resultSet	 
	                	String usernameBD= resultSet.getString("username");
	                	String passwordBD= resultSet.getString("password");	                	
	                		            	
	                	//vérifier si les données récupérées sont les mêmes entrées par le user ou pas
	                	if(!verifPassword(password, passwordBD)) {		
		                    // Informations d'identification incorrectes, renvoyer un message d'erreur à indexA.jsp
		                    request.setAttribute("checkMessage", "Password incorrect !");
		                    request.getRequestDispatcher("loginA.jsp").forward(request, response);
	                	}
	                	else {
	                		//redirectionner vers la pageindexA.jsp
	                		response.sendRedirect("/projet_coVoiturage/RecentCustomers");
	                	}
	                }
		            else {
			            // Informations d'identification incorrectes, renvoyer un message d'erreur à indexA.jsp
			            request.setAttribute("accountError", "Please create an account !");
			            request.getRequestDispatcher("loginA.jsp").forward(request, response);
		            }
	         }
	                         	         	         
	         }	         
     
        catch (SQLException e) {
         	 e.printStackTrace();
        }
	}
}
        
	


