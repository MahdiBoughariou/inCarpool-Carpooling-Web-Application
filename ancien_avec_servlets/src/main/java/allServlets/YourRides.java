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
import java.util.ArrayList;
import java.util.List;

import classes.Ride;

/**
 * Servlet implementation class yourRides
 */
public class YourRides extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*Connexion à la base*/
	 public static final String URL="jdbc:mysql://localhost:3306/covoiturage";
	 public static final String USER="root";
	 public static final String PASSWORD="";
	 Connection con=null;
	 /*---------------------------------------------------------------------------*/
	 
	 /*Toutes les rêquettes sql*/
	 private static final String SELECT_ALL_YOUR_RIDES = "SELECT id_offer FROM ride WHERE id_passager = ?";
	 private static final String SELECT_ALL_OFFER="SELECT * From offer WHERE id = ?"; /*id = current offer  récupré d'aprés la req SELECT_ALL_YOUR_RIDES*/
	 private static final String SELECT_NAME_CITY = "SELECT name FROM city WHERE id = ?"; /*id = departure ou destination*/
	 private static final String SELECT_ALL_CAR = "SELECT * FROM car WHERE id = ?"; /*id = id_car*/
	 private static final String SELECT_USER = "SELECT firstname,lastname,phone FROM user WHERE id = ?"; /*id = id_user*/
	 private static final String SELECT_CAR_BRAND = "SELECT name FROM brands WHERE id = ?"; /*id = id_brand*/
	 /*--------------------------------------------------------------------------*/
	 
	 /*------------Tous les fonctions------------*/
	 
	 /*fonction qui récupère departure*/	 
	 public String dep(String id_dep) throws SQLException{
		 
		 con = DriverManager.getConnection(URL, USER, PASSWORD);
		 
		 /*partie departure*/
		 PreparedStatement pstmt_dep = con.prepareStatement(SELECT_NAME_CITY);
         pstmt_dep.setString(1, id_dep);
         try (ResultSet res_dep = pstmt_dep.executeQuery()) {
        	 if(res_dep.next()) {
        		//récupérer le departure
             	String departure_name = res_dep.getString("name");
             	return departure_name;
        	 }
         }
         return "true";
	 }
	 /*fonction qui récupère destination*/
	 public String des(String id_des) throws SQLException{
		 
		 con = DriverManager.getConnection(URL, USER, PASSWORD);
		 
		 /*partie destination*/
		 PreparedStatement pstmt_des = con.prepareStatement(SELECT_NAME_CITY);
         pstmt_des.setString(1, id_des);
         try (ResultSet res_des = pstmt_des.executeQuery()) {
        	 if(res_des.next()) {
        		//récupérer le departure
             	String destination_name = res_des.getString("name");
             	return destination_name;
        	 }
         }
         return "true";
	 }
	 /*-------------------------------------------------------------*/        
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YourRides() {
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
		//Récupérer l'id en utilisant la session
		int id = Integer.parseInt(String.valueOf(session.getAttribute("idBD")));
		
		List<Ride> ListeRides = new ArrayList<>();
		
		try {        	
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // Récupérer touts les offres confirmés par le user ao == allOffer
            PreparedStatement pstmt_ao = con.prepareStatement(SELECT_ALL_YOUR_RIDES);
            pstmt_ao.setInt(1,id);
            
            try (ResultSet res_ao = pstmt_ao.executeQuery()) {
                while(res_ao.next()) {
                	
                	//récupérer le current id_offer
                	int current_id_offer = res_ao.getInt("id_offer");
                	
                	// Récupérer toutes les informations de l'offre o == offre
                    PreparedStatement pstmt_o = con.prepareStatement(SELECT_ALL_OFFER);
                    pstmt_o.setInt(1, current_id_offer);
                    
                    try(ResultSet res_o = pstmt_o.executeQuery()){
                    	if (res_o.next()) {
		                	// Récupérer toutes les informations de l'offre
			                String id_dep = res_o.getString("departure");
			                String id_des = res_o.getString("destination");
			                String date = res_o.getString("date");
			                String time = res_o.getString("time");
			                String id_car = res_o.getString("id_car");
			                String price = res_o.getString("price");
			                String comment = res_o.getString("comment");
			                String state = res_o.getString("state");
			                String departure_name=dep(id_dep);
			                String destination_name=dep(id_des);
			                
			             // Récupérer toutes les informations du car c == car
		                    PreparedStatement pstmt_c = con.prepareStatement(SELECT_ALL_CAR);
		                    pstmt_c.setString(1, id_car);
		                    
		                    try(ResultSet res_c = pstmt_c.executeQuery()){
		                    	if (res_c.next()) {
		                    		String id_user = res_c.getString("id_user");
		                    		String id_brand = res_c.getString("id_brand");
		                    		String model = res_c.getString("model");
		                    		String state_car = res_c.getString("state");
		                    		
		                    		// Récupérer toutes les informations du car u == user
				                    PreparedStatement pstmt_u = con.prepareStatement(SELECT_USER);
				                    pstmt_u.setString(1, id_user);
				                    
				                    try(ResultSet res_u = pstmt_u.executeQuery()){
				                    	if (res_u.next()) {
				                    		String firstname = res_u.getString("firstname");
				                    		String lastname = res_u.getString("lastname");
				                    		String phone = res_u.getString("phone");
				                    	
				                    
				                    
				                 // Récupérer brand b == brnad
				                    PreparedStatement pstmt_b = con.prepareStatement(SELECT_CAR_BRAND);
				                    pstmt_b.setString(1, id_brand);
				                    
				                    try(ResultSet res_b = pstmt_b.executeQuery()){
				                    	if (res_b.next()) {
				                    		String car_brand = res_b.getString("name");
				                    		
				                    		/*affichage test
				                    		System.out.println(departure_name);
				                    		System.out.println(destination_name);
				                    		System.out.println(date);
				                    		System.out.println(time);
				                    		System.out.println(price);
				                    		System.out.println(comment);
				                    		System.out.println(state);
				                    		System.out.println(firstname);
				                    		System.out.println(lastname);
				                    		System.out.println(phone);
				                    		System.out.println(car_brand);
				                    		System.out.println(model);
				                    		System.out.println(state_car);
				                    		 */
				                    		
				    		                Ride ride = new Ride();
				    		                ride.setId(current_id_offer);
				    		                ride.setDeparture(departure_name);
				    		                ride.setDestination(destination_name);
				    		                ride.setDate(date);
				    		                ride.setTime(time);
				    		                ride.setPrice(price);
				    		                ride.setComment(comment);
				    		                ride.setState(state);
				    		                ride.setFirstname(firstname);
				    		                ride.setLastname(lastname);
				    		                ride.setPhone(phone);
				    		                ride.setBrand(car_brand);
				    		                ride.setModel(model);
				    		                ride.setStateCar(state_car);
				    		                ListeRides.add(ride);
				    		                System.out.println(ListeRides);
				    		                
				    		                
				    		                
				                    	}
				                    }
				                    	}
				                    }
		                    	}
		                    }
                    	}
                    }
                }
            }
          //envoyer la liste à la page youRides.jsp
            request.setAttribute("ListeRides", ListeRides);
            request.getRequestDispatcher("/yourRides.jsp").forward(request, response);
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
