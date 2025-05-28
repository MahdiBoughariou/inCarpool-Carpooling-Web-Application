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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
	    
	    public boolean checkNullValues(String firstName, String lastName, String cin, String email, String birthDate, String phone) {
	        // Vérifie si l'une des valeurs est nulle
	        if (firstName == "" || lastName == "" || cin == "" || email == "" || birthDate == "" || phone == "") {
	        	return false;
	        }
			return true;
	    }
    /*------------------------------------------------------------------------------------------------------------------*/
	    

    
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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String cin = request.getParameter("cin");
        String email = request.getParameter("email");
        String birthDate = request.getParameter("birthDate");
        String phone = request.getParameter("phone");

        //S'il y a un champ vide
        if(!checkNullValues(firstName,lastName,cin,email,birthDate,phone)) {
            request.setAttribute("nullError", "Please fill in all the required fields !");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Si le cin n'est pas valide
        if(!verifCin(cin)) {
            request.setAttribute("cinError", "The number must contain exactly 8 digits !");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Si l'email n'est pas valide
        if(!verifEmail(email)) {
            request.setAttribute("emailError", "Invalid email format (example@example.com) !");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Si le téléphone n'est pas valide
        if(!verifPhoneNumber(phone)) {
            request.setAttribute("phoneError", "Invalid phone format. Use: +216-12345678 !");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        else {
        	
        	//Mettre les données du premier formulaire dans une session afin de les récupérer après
            HttpSession session= request.getSession();
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);
            session.setAttribute("cin", cin);
            session.setAttribute("email", email);
            session.setAttribute("birthDate", birthDate);
            session.setAttribute("phone", phone);
            
	        //Redirection vers le deuxième formulaire
	        response.sendRedirect("/projet_coVoiturage/registerp2.jsp");
        }
	}
}
