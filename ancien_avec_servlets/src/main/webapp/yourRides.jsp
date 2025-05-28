<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ include file="security.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="classes.Ride" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   	
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">

	<link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">
    <link  rel="stylesheet" href="styleuser.css">
    <title>inCarpool</title>
    <style>
        select option {
            background: #4f4587;
        }

        .postcard-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
            max-height: 100%;
            overflow-y: auto;
            margin: 20px;
        }

        .postcard {
            background-color: #f0f0f0;
            border-radius: 20px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 100%; /* Adjusted to fit the container */
            max-width: 500px; /* Added to limit maximum width */
            margin-bottom: 20px;
        }

        .postcard .upper-left {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            border-bottom: 2px solid #ddd;
        }

        .postcard .poster-info {
            display: flex;
            flex-direction: column;
        }

        .postcard .poster-info .name {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .postcard .phone {
            margin-top: auto;
        }

        .postcard .content {
            padding: 20px; /* Adjusted padding */
        }

        .postcard .details {
            margin-top: 20px;
        }

        .postcard .details .location {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .postcard .details .location p {
            margin: 0;
            margin-right: 5px;
        }

        .postcard .comment {
            margin-top: 20px;
            text-align: center;
        }

        .postcard .confirm-button {
            margin-top: 20px;
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 15px 30px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 20px;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .postcard .confirm-button:hover {
            background-color: #45a049;
        }

        .postcard .price-tag {
            background-color: #4CAF50;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
        }

        .postcard .details .location p,
        .postcard .details p,
        .postcard .content p {
            font-size: 1.2em; /* Adjusted font size */
        }
.registration-ui {
    font-weight: bold;
    font-size: 1em;
    position: relative;
    font-family: helvetica, ariel, sans-serif;
    display: inline-block; /* Adjusted to fit content */
    margin-top: 2px;
}



    </style>
</head>
<body>
    
    
    <nav class="navbar">  
        <div class="brand-title">
            <img class="logo" src="photo/logov2.png">
        </div>
        <div class="navbar-links">
            <ul>
            	<%
            	if(session.getAttribute("stateBD")!=null){
            	String state= (String)session.getAttribute("stateBD");     
 				%>
            	
            	
            	
			   
			    <%  if (state.equals("approved")) { %>
			        <li class="add_offer">
			            <a href="publisharidelocation.jsp">
			                <i class="fa-light fa-plus"></i>
			                <span>Add Offer</span>
			            </a>
			        </li>
			    <% }} %>
            </ul>
        </div>
        
        <div class="action">
            <div class="profile" onclick="menuToggle();">
                <img src="photo/user.png" alt="">
            </div>
                <div class="menu">
                
                <ul>
                    <li>
                        <span class="material-icons icons-size">person</span>
                        <!-- envoyer l'id du user à la page profileuser.jsp -->
                        <a href='/projet_coVoiturage/profileuser.jsp'>Profile</a>
                    </li>
                    <li>
                        <span class="material-icons icons-size">directions_car</span>
                        <a href="#">Your rides</a>
                    </li>
                    <li>
                        <span class="material-icons icons-size">account_balance_wallet</span>
                        <a href="wallet.jsp">Wallet</a>
                    </li>
                    <li>
                        <span class="material-icons icons-size">logout</span>
                        <a href="/projet_coVoiturage/Logout">Logout</a>
                    </li>
                </ul>
        </div>

        <script>
            function menuToggle(){
                const toggleMenu = document.querySelector('.menu');
                toggleMenu.classList.toggle('active')
            }
        </script>

      </nav>
      	
      	
      
      	
		<div class="postcard-container">
		
						<%
						    // Récupération de la liste des rides depuis la servlet
						    List<Ride> ListeRides = (List<Ride>) request.getAttribute("ListeRides");
						    for (Ride ride : ListeRides) {
						%>
						
		                <div class="postcard">
		                    <div class="upper-left">
		                    
		                        <div class="poster-info">
		                            <p class="name"><%= ride.getLastname() %> <%= ride.getFirstname() %></p>
		                            <p class="phone"><%= ride.getPhone() %></p>
		                        </div>
		                        
		                        <div class="price-tag">
		                            <p><%= ride.getPrice()%> DT</p>
		                        </div>
		                        
		                    </div>
		 					<div class="content">
		    					<div class="details">
		    					
		        					<div class="location">
							            <p><i class="fas fa-map-marker-alt"></i> <%= ride.getDeparture() %></p>
							            <i class="fas fa-arrow-right"></i>
							            <p><i class="fas fa-map-marker-alt"></i> <%= ride.getDestination() %></p>							            						            
		        					</div>
		        					
		        					<br/>
		        					<p>Time  : <%= ride.getDate() %>  </p>
							        <br/>
							        <p>Time : <%= ride.getTime() %></p>
									<br/>
		        					<p><i class="fa-solid fa-car"></i> <%= ride.getBrand() %>  <%= ride.getModel() %></p>
									<br/>
		        					<p ><%= ride.getStateCar() %> </p>
		        
							        <div class="comment">
							            <p><%= ride.getComment() %></p>
							        </div>
							        
							        <h5><%= ride.getState() %></h5>
		        					<button class="confirm-button"> <a href="/projet_coVoiturage/CancelRide?id=<%= ride.getId() %>"> Confirmed ! </a> </button>
		    					</div>
							</div>
		                </div>
		                <% } %>
		</div>
      	  	
        <footer id="about">
            <div class="row_footer">
                <div class="col_footer">
                    <img class="logo_footer" src="photo/logov2.png">
                    <p class="description"> 
                        
                        inCarpool connects drivers with students or adults, in general, using other means of transport to carpool to their university or destination.
                        
                        Carpooling has seen an exponential increase in recent years across the globe without there being any remarkable growth in Tunisia.
                </div>
                <div class="col_footer">
                    <h3>Office <div class="underline"><span></span></div></h3>
                    <p class="link">ONS City</p>
                    <p class="links">Tunis Road  Km 11</p>
		            <p class="email-id">idham.boughariou@gmail.com</p>
		            <p class="email-id">chellytaha@gmail.com</p>
		            <h4 class="links">+216 50661094</h4>
                </div>
                <div class="col_footer">
                    <h3>Links <div class="underline"><span></span></div></h3>
                    <ul class="links">
                        <li><a href="#home">Home</a></li>
                        <li><a href="#services">Services</a></li>
                        <li><a href="#about">About</a></li>
                        <li><a href="#Register">Register</a></li>
                    </ul>
                </div>
                <div class="col_footer">
                    <h3>Newsletter <div class="underline"><span></span></div></h3>
                    <form>
                        <i class="far fa-envelope"></i>
                        <input type="email" placeholder="Enter yout email" required>
                        <button type="submit"><i class="fas fa-arrow-right"></i></button>
                    </form>
                    <div class="social-icons">
                        <i class="fab fa-facebook-f"></i>
                        <i class="fab fa-twitter"></i>
                        <i class="fab fa-whatsapp"></i>
                        <i class="fab fa-pinterest"></i>
                    </div>
                </div>
            </div>
            <hr>
            <p class="copyright">inCarpool © 2024 -All Rights Reserved</p>
    
        </footer>

    </body>
    </html>