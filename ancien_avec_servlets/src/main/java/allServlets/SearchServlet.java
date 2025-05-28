package allServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import classes.DataBase;
import classes.Offer;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		
		int dep = Integer.parseInt(request.getParameter("departure"));
		int des =Integer.parseInt(request.getParameter("destination"));
		String dateString = request.getParameter("datesortie");
		int nbPlaces= Integer.parseInt(request.getParameter("nbplace"));
		
		System.out.println(dep+" "+des +" "+ dateString + " " +nbPlaces);
		
		 Date date = null;
		    if (dateString != null && !dateString.isEmpty()) {
		        try {
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		            java.util.Date parsedDate = dateFormat.parse(dateString);
		            date = new java.sql.Date(parsedDate.getTime());
		        } catch (ParseException e) {
		            e.printStackTrace();
		            // Handle parsing exception
		        }
		    }
		    
		    // Call getFilteredOffers with the correct parameters
		    List<Offer> filteredOffers = DataBase.getFilteredOffers(dep, des, nbPlaces, date);
		    System.out.println(filteredOffers.toString());
		    request.setAttribute("offers", filteredOffers);
		    request.getRequestDispatcher("user.jsp").forward(request, response);
	}

}
