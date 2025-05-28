package allServlets;
import classes.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

public class AddVehicleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        VehicleData vehicleData = (VehicleData) session.getAttribute("vehicleData");

        // If vehicleData is null, create a new instance
        if (vehicleData == null) {
            vehicleData = new VehicleData();
        }

        // Retrieve data from the request and set it in the bean
        String currentPage = request.getParameter("currentPage");
        switch (currentPage) {
            case "addVehicule":
                vehicleData.setLicensePlate(request.getParameter("vehicle"));
                vehicleData.setId(Integer.valueOf(String.valueOf(session.getAttribute("idBD"))));
                break;
            case "addVehiculeMarque":
                vehicleData.setBrand(new Brand (Integer.parseInt(request.getParameter("brand"))));
                break;
            case "addVehiculeModel":
                vehicleData.setModel(request.getParameter("model"));
                break;
            case "addVehiculeEtat":
                vehicleData.setState(request.getParameter("state"));
                break;
            default:
                // Handle invalid page
                break;
        }

        // Store the updated vehicleData in session
        session.setAttribute("vehicleData", vehicleData);

        // Redirect the user to the next page
        switch (currentPage) {
            case "addVehicule":
                response.sendRedirect("addvehiculemarque.jsp");
                break;
            case "addVehiculeMarque":
                response.sendRedirect("addvehiculemodel.jsp");
                break;
            case "addVehiculeModel":
                response.sendRedirect("addvehiculeetat.jsp");
                break;
            case "addVehiculeEtat":
                // Now all data is collected, you can proceed to insert into database
                response.sendRedirect("ProcessVehicleDataServlet");
                break;
            default:
                // Handle invalid page
                break;
        }
    }
}
