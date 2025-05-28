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
 * Servlet implementation class CheckUsernameAvailability
 */
public class CheckUsernameAvailability extends HttpServlet {
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
    public CheckUsernameAvailability() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /*Fonction qui vérifie si le username utilisé ou pas*/
    private boolean isUsernameAvailable(String username) {
    		try {        	
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // Insertion des données du premier formulaire dans la base de données
            PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE username = ?");
            pstmt.setString(1, username);
            
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    //System.out.println(count);
                    return count == 0; // Retourne true si count est égal à 0, sinon false
                }
            }

	        } catch (SQLException e) {
	        	 e.printStackTrace();
	        }
    		
            // En cas d'erreur ou d'exception, retourne false par défaut
            return false;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
        String username = request.getParameter("username");
        boolean isAvailable = isUsernameAvailable(username);
        //System.out.println(isAvailable);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(isAvailable ? "T" : "F");
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
