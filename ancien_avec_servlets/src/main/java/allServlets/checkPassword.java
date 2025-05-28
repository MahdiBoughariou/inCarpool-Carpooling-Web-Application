package allServlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class checkPassword
 */
public class checkPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*Connexion à la base*/
	 public static final String URL="jdbc:mysql://localhost:3306/covoiturage";
	 public static final String USER="root";
	 public static final String PASSWORD="";
	 Connection con=null;
	 /*---------------------------------------------------------------------------*/
	 
	 /*Fonction qui verifie si les deux password sont les même*/
	 public boolean isTheSame(String id, String oldPassword) {
		 try {        	
	            con = DriverManager.getConnection(URL, USER, PASSWORD);

	            // Insertion des données du premier formulaire dans la base de données
	            PreparedStatement pstmt = con.prepareStatement("SELECT password FROM user WHERE id = ?");
	            pstmt.setString(1, id);
	            
	            try (ResultSet resultSet = pstmt.executeQuery()) {
	                if (resultSet.next()) {
	                	String passwordBD = resultSet.getString("password");
	                	//System.out.println(passwordBD);
	                    return oldPassword.equals(passwordBD);
	                }
	            }

		        } catch (SQLException e) {
		        	 e.printStackTrace();
		        }
				// En cas d'erreur ou d'exception, retourne false par défaut
		         return false;
	 }
	 /*-----------------------------------------------------------------------------------------*/
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String oldPassword = request.getParameter("password");
		String id = request.getParameter("id");
		Boolean isTheSame = isTheSame(id,oldPassword);		
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(isTheSame ? "T" : "F");
        out.flush();
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
