<!--<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ include file="security.jsp" %>
-->
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link  rel="stylesheet" href="styleprofileuser.css">
		<link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">
	    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
	    <title>Profile</title>
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
	    
	    <section class="about you">
	    
	        <div class="container">
	        
	            <div class="container-personel">
	                <h2 id="Name-User">Profil</h2>	                
	            </div>
	            
	            <div class="container-edit">
	            	<!-- Récupérer l'id envoyé par la page user.jsp et l'envoyer à la servlet -->
	                <a href="/projet_coVoiturage/EditProfile" class="bio">
	                    <span class="material-symbols-outlined">add_circle</span>
	                    <p>Edit Personal Details</p>
	                </a>
	            </div>
	            
	            <div class="separateur-section"></div>
	            
	            <div class="container-profile">	            
	                <h2>Upgrade your profile</h2>	                
					<p style="font-size: 18px; line-height: 1.6; margin-top: 10px; color: #25294A;">
					Dear user, in order to ensure your safety and that of other passengers,
					 we kindly request that you verify your identity and your driving license by uploading them.</p>
					 <!-- Récupérer l'id envoyé par la page user.jsp et l'envoyer à la page UpgradeProfile.jsp -->	                 
	                <a href="/projet_coVoiturage/UpgradeProfile.jsp" class="bio">
	                    <span class="material-symbols-outlined">add_circle</span>
	                    <p>Upgrade</p>
	                </a>	                
	            </div>
	            
	        </div>
	
	    </section>
	     <%@ page import="classes.VehicleData"
    import="classes.DataBase" %>
	    <div class="separateur-section"></div> 
	    <section class="about vehicule">
	        <div class="container">

	            <div class="container-vehicule-Exist">
	                <h2>Vehicles</h2>
	                	        <%
	            
	            if (session.getAttribute("idBD")!=null){
				int id_user = (int) session.getAttribute("idBD");
				
                java.util.List<VehicleData> vehicles = DataBase.getAllVehicles(id_user);
                if (!vehicles.isEmpty()) {
                	for (VehicleData vehicle : vehicles) {
                %>
	                <div class="yourvehicule">
	                    <div class="Car-Vehicule">
	                        <span><h3 class="mark-vehicule"><%= vehicle.getBrand().getName()%></h3><h3 class="model-vehicule"><%=vehicle.getModel()%></h3></span>
	                        <p class="colorvehicule"><%= vehicle.getLicensePlate() %></p>
	                    </div>
	                    <span class="material-symbols-outlined">
	                        chevron_right
	                    </span>
	                </div>

	            </div>
	            <%
                }
                }
	            }
                %>
	            <div class="container-vehicule-add">
	                <a href="addvehiculeserie.jsp">
	                    <span class="material-symbols-outlined">
	                    add_circle
	                </span>
	                <p>Add Vehicule</p>
	                </a>
	            
	            </div>
	        </div>
	    </section>  
	</body>
</html>