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
 * Servlet implementation class EditProfil_update
 */
public class EditProfil_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*Connexion à la base*/
	 public static final String URL="jdbc:mysql://localhost:3306/covoiturage";
	 public static final String USER="root";
	 public static final String PASSWORD="";
	 Connection con=null;
	 /*---------------------------------------------------------------------------*/
	 
	    /*------------Tous les fonctions------------*/

	    /*Fonction qui vérifie le format de l'email*/
	    
	    public boolean verifEmail(String email) {
	        // Expression régulière pour valider le format de l'adresse e-mail
	        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	        // Création d'un objet Pattern avec l'expression régulière
	        Pattern pattern = Pattern.compile(regex);

	        // Création d'un objet Matcher pour faire correspondre le motif avec l'adresse e-mail
	        Matcher matcher = pattern.matcher(email);

	        // Vérification si l'adresse e-mail correspond au format spécifié
	        return matcher.matches();
	    }
	       
	    /*Fonction qui vérifie la longeur du cin*/
	    
		    public boolean verifCin(String cin) {
		        // Vérifie si la longueur du CIN est égale à 8
		        return cin.length() == 8;
		    }
		    
		 /*Fonction qui vérifie le format du téléphone*/
		    
		    public boolean verifPhoneNumber(String phoneNumber) {
		        // Expression régulière pour valider le format du numéro de téléphone
		        String regex = "^\\+\\d{11}$"; // Le numéro doit commencer par '+' et comporter exactement 12 chiffres

		        // Création d'un objet Pattern avec l'expression régulière
		        Pattern pattern = Pattern.compile(regex);

		        // Création d'un objet Matcher pour faire correspondre le motif avec le numéro de téléphone
		        Matcher matcher = pattern.matcher(phoneNumber);

		        // Vérification si le numéro de téléphone correspond au format spécifié
		        return matcher.matches();
		    }
		    
		    /*Fonction qui vérifie si tous les champs sont remplis*/
		    
		    public boolean checkNullValues(String firstName, String lastName, String cin, String email, String birthDate, String phone, String username) {
		        // Vérifie si l'une des valeurs est nulle
		        if (firstName == "" || lastName == "" || cin == "" || email == "" || birthDate == "" || phone == "" || username == "") {
		        	return false;
		        }
				return true;
		    }
		    
		    /*fonction qui vérifie si le oldPassword entré est le même stocké dans la base*/
		    
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
		    
		    /*Fonction qui vérifie si on va changer le mot de passe ou pas*/
			 
		    public boolean changePassword(String id, String oldPassword, String newPassword) {
		    	if (oldPassword != "" && newPassword != "") {
		    		if(isTheSame(id,oldPassword)) {
		    			return true;
		    		}		    		
		    	}
		    	if( (oldPassword != "" && newPassword == "") || (oldPassword == "" && newPassword != "") ) {
		    		return false;
		    	}
		    	/*
		    	if((oldPassword == "" && newPassword == "")) {
		    		return false;
		    	}
		    	*/
		    	return false;
		    }
		    			 
	    /*------------------------------------------------------------------------------------------------------------------*/
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfil_update() {
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

        // Récupération des paramètres du premier formulaire
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String cin = request.getParameter("cin");
        String email = request.getParameter("email");
        String birthDate = request.getParameter("birthDate");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("password");
        String newPassword = request.getParameter("password2");
        
        //S'il y a un champ vide
        if(!checkNullValues(firstName,lastName,cin,email,birthDate,phone,username)) {
            request.setAttribute("nullError", "Please fill in all the required fields !");
            request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
            return;
        }
        
        // Si le cin n'est pas valide
        if(!verifCin(cin)) {
            request.setAttribute("cinError", "The number must contain exactly 8 digits !");
            request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
            return;
        }
        
        // Si l'email n'est pas valide
        if(!verifEmail(email)) {
            request.setAttribute("emailError", "Invalid email format (example@example.com) !");
            request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
            return;
        }
        
        // Si le téléphone n'est pas valide
        if(!verifPhoneNumber(phone)) {
            request.setAttribute("phoneError", "Invalid phone format. Use: +216-12345678 !");
            request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
            return;
        }
        
        //si on vas change le password 
        if(changePassword(id,oldPassword,newPassword)) {
        	try (Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
                // Requête SQL pour mettre à jour les informations de l'utilisateur
                String sqlPassword = "UPDATE user SET password = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sqlPassword);
                statement.setString(1, newPassword);
                statement.setString(2, id);
                
                // Exécuter la requête
                int rowsUpdated = statement.executeUpdate();
                
                if (rowsUpdated > 0) {
                    // Les informations de l'utilisateur ont été mises à jour avec succès
                    // Vous pouvez rediriger l'utilisateur vers une page de confirmation
                    response.sendRedirect("/projet_coVoiturage/user.jsp");
                }
               
            }
	   		 catch (SQLException e) {
	        	 e.printStackTrace();
	        }
        }
             
        else {
        	
            try (Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
                // Requête SQL pour mettre à jour les informations de l'utilisateur
                String sql = "UPDATE user SET firstname = ?, lastname = ?, cin = ?, email = ?, birthdate = ?, phone = ?, username = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, cin);
                statement.setString(4, email);
                statement.setString(5, birthDate);
                statement.setString(6, phone);
                statement.setString(7, username);
                statement.setString(8, id);
                
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
