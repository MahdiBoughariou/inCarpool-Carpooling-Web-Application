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
 * Servlet implementation class Registration2
 */
public class Registration2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*Connexion à la base*/
	 public static final String URL="jdbc:mysql://localhost:3306/covoiturage";
	 public static final String USER="root";
	 public static final String PASSWORD="";
	 Connection con=null;
	 /*---------------------------------------------------------------------------*/
	 
	 /*------------Tous les fonctions------------*/
	 
	 /*Fonction qui vérifie si les deux mots de passes entrés sont les même ou pas*/
	 
	 public boolean verifSamePassword(String password, String password2) {
	     return password.equals(password2);
	 }
	 
	 /*Fonction qui vérifie si tous les champs sont remplis*/
	    
	 public boolean checkNullValues(String username, String password, String password2) {
		 // Vérifie si l'une des valeurs est nulle
		 if (username == "" || password == "" || password2 == "") {
			 return false;
		 }
		 return true;
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
	    
	 /*------------------------------------------------------------------------------------------------------------------*/
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration2() {
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
		
		//Récupération des anciens paramètres (formulaire 1)
		HttpSession session=request.getSession();
        String firstName=(String) session.getAttribute("firstName");
        String lastName=(String) session.getAttribute("lastName");
        String cin=(String) session.getAttribute("cin");
        String email=(String) session.getAttribute("email");
        String birthDate=(String) session.getAttribute("birthDate");
        String phone=(String) session.getAttribute("phone");
		
        // Récupération des paramètres du deuxième formulaire
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        
        //S'il y a un champ vide
        if(!checkNullValues(username,password,password2)) {
            request.setAttribute("nullError", "Please fill in all the required fields !");
            request.getRequestDispatcher("registerp2.jsp").forward(request, response);
            return;
        }

        if(!verifSamePassword(password,password2)) {
        	//si les mots de passes ne sont pas les même envoyer une message d'erreur
            request.setAttribute("passwordError", "Please enter same passwords !");
            request.getRequestDispatcher("registerp2.jsp").forward(request, response);
            return;
        }
        if(!isUsernameAvailable(username)) {
        	request.setAttribute("usernameError", "Username not available, try another one !");
            request.getRequestDispatcher("registerp2.jsp").forward(request, response);
            return;
        }
        else {
        	// (assuming proper error handling and database interaction)
            Connection con = null;
            try {
            	
                con = DriverManager.getConnection(URL, USER, PASSWORD);

                // Insertion des données du premier formulaire dans la base de données
                PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO user (firstname, lastname, cin, email, birthdate, phone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt1.setString(1, firstName);
                pstmt1.setString(2, lastName);
                pstmt1.setString(3, cin);
                pstmt1.setString(4, email);
                pstmt1.setString(5, birthDate);
                pstmt1.setString(6, phone);
                pstmt1.setString(7, username);
                pstmt1.setString(8, password);
                pstmt1.executeUpdate();
                pstmt1.close();

    	        
                //Envoyer un message de succès
                request.setAttribute("accountCreated", "Account created successfully.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                
            } catch (SQLException e) {
                
            }       	
        }      
	}
}
