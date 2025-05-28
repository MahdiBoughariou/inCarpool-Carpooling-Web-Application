<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ include file="security.jsp" %>
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
                        <a href="/projet_coVoiturage/YourRides">Your rides</a>
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
      	<%@ page import="classes.City"
		import="classes.DataBase"
		import="classes.Offer"
		import="java.util.List"
		import="classes.User"
		import="classes.Car"%>
        <section class="main">
            <div class="container">
                <div class="background">
                    <img  src="photo/back10.png" >
                    
                    <div class="container-form">
                        <div class="title-main">
                            <h1>Your pick of rides at low prices</h1>
                        </div>
                        <br/>
                        <form class="form_user" id="filterForm" action="SearchServlet" method="post">
                            <div class="col_search">
                                <select name="departure" id="departure">
                       				<% 
                    					java.util.List<City> cities = DataBase.getAllCities();
                    					for (City city : cities) {
                    				%>
                    				<option value="<%= city.getId() %>"><%= city.getName() %></option>
                    					<% } %>
                          
                                </select>  
                            </div>
                            <div class="col_search">
                				<select name="destination" id="destination">
               					</select> 
                            </div>
                            <div class="col_search">
                                <input name="datesortie" id="datesortie" type="date" >
                            </div>
                            <div class="col_search">

                                <input type="number" name="nbplace" value="1" min="1" max="4"> 
                            </div>

                            <div class="col_search">
                                
                                <a href="rides.png">
                                    <button  class="search_button" type="submit" value="Search">Search
                                    </button>
                                </a>

                            </div>
                         </form>
                    </div>
                </div>
                
            </div>
        </section>
   		  

    <div class="postcard-container">
        <% 
        if(request.getAttribute("offers")!=null){
            List<Offer> offers = (List<Offer>) request.getAttribute("offers");
            for(Offer offer: offers ) { %>
                <div class="postcard">
                    <div class="upper-left">
                        <div class="poster-info">
                            <% User user = DataBase.getUserInfoForCar(offer.getId_car()); %>
                            <p class="name"><%= user.getFirstName() + " " + user.getLastName() %></p>
                            <p class="phone"><%= user.getPhone() %></p>
                        </div>
                        <div class="price-tag">
                            <p><%= (int)offer.getPrice()+" TND" %></p>
                        </div>
                    </div>
 						<div class="content">
    			<div class="details">
        			<div class="location">
            <p><i class="fas fa-map-marker-alt"></i> <%=DataBase.getCityName(offer.getDeparture()) %></p>
            <i class="fas fa-arrow-right"></i>
            <p><i class="fas fa-map-marker-alt"></i> <%= DataBase.getCityName(offer.getDestination()) %></p>
        </div>
        
        <% Car car = DataBase.getCarInfo(offer.getId_car());
        String brand = DataBase.getBrandName(car.getBrand_id()); %>
        <p><i class="fa-solid fa-car"></i> <%=brand +" "+ car.getModel() %></p>
        <span class="registration-ui">
           
            <%=car.getPlatenumber() %>
        </span>
        <p ><%=car.getState() %> </p>
        
        <div class="comment">
            <p><%= offer.getComment() %></p>
        </div>
        <button class="confirm-button">Confirm Ride</button>
    </div>
</div>
                </div>
            <% }} %>
    </div>
	
	
        <section class="service">
            <div class="container-services">
                <div class="card">
                    <i class="fa fa-coins"></i>  
                    <h2>Your pick of rides at low prices</h2>
                    <p>No matter where you're going, by bus or carpool, find the perfect ride from our wide range of destinations and routes at low prices.</p>
                </div>
                <div class="card">

                    <i class="fa fa-address-card" aria-hidden="true"></i>
                    <h2>Trust who you travel with</h2>
                    <p>We take the time to get to know each of our members and bus partners. We check reviews, profiles and IDs, so you know who you’re travelling with and can book your ride at ease on our secure platform.</p>
                </div>
                <div class="card">
                    <i class="fa fa-bolt" aria-hidden="true"></i>
                    <h2>Scroll, click, tap and go!</h2>
                    <p>Booking a ride has never been easier! Thanks to our simple app powered by great technology, you can book a ride close to you in just minutes.                    </p>
                </div>
        </section>

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
        
        <script>
document.addEventListener('DOMContentLoaded', function() {
    // Get the default value of departure
    var departureId = document.getElementById('departure').value;
    
    // Populate the destination select element
    populateDestination(departureId);
});

document.getElementById('departure').addEventListener('change', function() {
    var departureId = this.value;
    populateDestination(departureId);
});

function populateDestination(departureId) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'getDestinationCities.jsp?departureId=' + departureId, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var cities = JSON.parse(xhr.responseText);
            populateDestinationSelect(cities);
        }
    };
    xhr.send();
}

function populateDestinationSelect(cities) {
    var destinationSelect = document.getElementById('destination');
    destinationSelect.innerHTML = ''; // Clear existing options
    cities.forEach(function(city) {
        var option = document.createElement('option');
        option.value = city.id;
        option.textContent = city.name;
        destinationSelect.appendChild(option);
    });
}

</script>
    </body>
    </html>