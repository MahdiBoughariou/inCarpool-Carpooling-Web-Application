package allServlets;
import classes.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class OfferServlet
 */
public class AddOfferServlet extends HttpServlet {
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        Offer offerData = (Offer) session.getAttribute("offerData");

	        // If vehicleData is null, create a new instance
	        if (offerData == null) {
	        	offerData = new Offer();
	        }

	        // Retrieve data from the request and set it in the bean
	        String currentPage = request.getParameter("currentPage");
	        switch (currentPage) {
	            case "addRideLocation":
	            	offerData.setDeparture(Integer.parseInt(request.getParameter("departure")));
	            	offerData.setDestination(Integer.parseInt(request.getParameter("destination")));
	            	
	                break;
	            case "addRideDate":
	                String datetimeStr = request.getParameter("datedesortie");
	                SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	                java.util.Date utilDatetime;
	                try {
	                	utilDatetime = datetimeFormat.parse(datetimeStr);



	                    // Convert java.util.Date to java.sql.Timestamp
	                    java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDatetime.getTime());

	                    // Set the timestamp in your offerData
	                    offerData.setDate(sqlTimestamp);
	                } catch (ParseException e) {
	                    e.printStackTrace(); // Handle parsing exception appropriately
	                }
	                break;
	            case "addRideTime":
	            	System.out.println("time received");
	                String timeStr = request.getParameter("timeride");
	                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	                java.util.Date utilTime;
	                try {
	                    utilTime = timeFormat.parse(timeStr);

	                    // Convert java.util.Date to java.sql.Time
	                    Time sqlTime = new Time(utilTime.getTime());

	                    // Set the time in your offerData
	                    offerData.setTime(sqlTime);
	                    System.out.println("time set");
	                } catch (ParseException e) {
	                	System.out.println("errrrrrrrrrrrrrrrrrrorrrrrrrrrrrrr");
	                    e.printStackTrace(); // Handle parsing exception appropriately
	                }
	                break;

	            case "addRideVehicle":
	            	offerData.setId_car(Integer.parseInt(request.getParameter("vehicle")));
	                break;
	            case "addRideNbPlace":
	            	offerData.setNbplaces(Integer.parseInt(request.getParameter("nbplace")));
	            	break;
	            case "addRidePrice":
	            	offerData.setPrice(Integer.parseInt(request.getParameter("numeric_price")));
	            	break;
	            case "addRideComment":
	            	offerData.setComment(request.getParameter("comment"));
	            	break;      	
	            default:
	                // Handle invalid page
	                break;
	        }

	        // Store the updated vehicleData in session
	        session.setAttribute("offerData", offerData);

	        // Redirect the user to the next page
	        switch (currentPage) {
	            case "addRideLocation":
	                response.sendRedirect("publisharidedate.jsp");
	                break;
	            case "addRideDate":
	                String datetimeStr = request.getParameter("datedesortie");
	                SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	                java.util.Date utilDatetime;
				try {
					utilDatetime = datetimeFormat.parse(datetimeStr);
				
                    // Extract the time part from the parsed java.util.Date
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(utilDatetime);
                    int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);

                    // Format the time as "HH:mm"
                    String timeString = String.format("%02d:%02d", hourOfDay, minute);
                    System.out.println(timeString);


                    // Or if you want to set it in session:
                    session.setAttribute("time", timeString);

				} catch (ParseException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
				System.out.println("received");
	                response.sendRedirect("publisharidetime.jsp");
	                break;
	            case "addRideTime":
	            	
	                response.sendRedirect("publisharidevehicule.jsp");
	                break;
	            case "addRideVehicle":
	                // Now all data is collected, you can proceed to insert into database
	                response.sendRedirect("publisharidenbplace.jsp");
	                break;
	            case "addRideNbPlace":
	                // Now all data is collected, you can proceed to insert into database
	                response.sendRedirect("publisharideprix.jsp");
	                break;
	            case "addRidePrice":
	                // Now all data is collected, you can proceed to insert into database
	                response.sendRedirect("publisharidecomment.jsp");
	                break;
	            case "addRideComment":
	                // Now all data is collected, you can proceed to insert into database
	                response.sendRedirect("ProcessOfferDataServlet");
	                break;	                
	            default:
	                // Handle invalid page
	                break;
	        }
	    }
	}