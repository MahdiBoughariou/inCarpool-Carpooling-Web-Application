<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="security.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styleridevehicule.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="styleaddvehiculeserie.css">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <title>Vehicle</title>
    <style>
        .alert-danger {
            text-align: center; /* Center align the text */
            width: 70%; /* Set width */
            margin: 6% auto; /* Center vertically and horizontally */
            padding: 10px 20px; /* Adjust padding */
        }
        .novehicle {
            width: 70%;
            font-size: 25px;
            margin-top: 6%;
            font-weight: 400;
            margin-left: 15%;
        }
    </style>
</head>

<body>
    <nav>
        <div class="logo">
            <a href="#" style="text-decoration:none;">
                <img class="imglogo" src="photo/logovR0.png">
            </a>
            <span>inCarpool</span>
        </div>
    </nav>
    <%@ page import="classes.VehicleData"
    import="classes.DataBase" %>


    <div class="container">
<%
				int id_user = (int) session.getAttribute("idBD");
				//int id_user = (int) session.getAttribute("idBD");
                java.util.List<VehicleData> vehicles = DataBase.getAllVehicles(id_user);
                if (vehicles.isEmpty()) {
                %>
                                   <div class="novehicle"> <div class="alert alert-danger" role="alert">
                        <p>No vehicles available. <a href="addvehiculeserie.jsp">Please add a vehicle.</a></p>
                    </div>
                    </div>
                

                <%
                } else {
                %>
                
        <form action="AddOfferServlet" method="post">
            <input type="hidden" name="currentPage" value="addRideVehicle">
            <div class="row justify-content-center">
                <center>
                    <h2>Which car will you take?</h2>
                </center>
				<% 
                    for (VehicleData vehicle : vehicles) {
                %>
                <a href="javascript:void(0);" onclick="submitForm(<%= vehicle.getId() %>)" style="text-decoration:none;color:black;">
                    <div class="vehicule">
                        <span><%= vehicle.getBrand().getName() + " " + vehicle.getModel() %></span>
                        <span class="material-symbols-outlined">
                            navigate_next
                        </span>
                        <div class="separateur"></div>

                    </div>
                </a><%} %>
                <input type="hidden" id="vehicle" name="vehicle">
   


                <div class="boutons">
                    <div class="boutonR">
                        <a href="user.jsp">
                            <div class="backbtn">
                                <i class="uil uil-navigator" style="transform: rotate(180deg)"></i>
                                <span class="btntext">Back</span>
                            </div>
                        </a>
                    </div>

                    </div>
                </div>
            </div>
        </form>
                     <%
                    }               
                %>
    </div>

    <script>
        function submitForm(vehicle) {
            document.getElementById('vehicle').value = vehicle;
            document.forms[0].submit();
        }
    </script>
</body>

</html>
