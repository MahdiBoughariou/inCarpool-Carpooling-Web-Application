<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css">
		<link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">
	    <link  rel="stylesheet" href="style.css">
	    <title>inCarpool</title>  
	</head>
	
	<body>
			<nav class="navbar">
		        <div class="brand-title">
		            <img class="logo" src="photo/logov2.png">
		        </div>
		        
		        <a href="#" class="toggle-button">
		          <span class="bar"></span>
		          <span class="bar"></span>
		          <span class="bar"></span>
		        </a>
	
		        <div class="navbar-links">
		          <ul>
		            <li><a href="#home">Home</a></li>
		            <li><a href="#services">Services</a></li>
		            <li><a href="#about">About</a></li>
		            <li><a href="register.jsp">Register</a></li>
		          </ul>
		        </div>
	
		        <script>
		            const toggleButton = document.getElementsByClassName('toggle-button')[0]
		            const navbarLinks = document.getElementsByClassName('navbar-links')[0]
		
		            toggleButton.addEventListener('click', () => {
		            navbarLinks.classList.toggle('active')
		            })
		        </script>
	    	</nav>
	
		    <section class="home" id="home">
		        <div class="content">
		            <div class="content-image">
		                <img class="background" src="photo/1.png"/>
		            </div>
		
		            <div class="box">
		                <div class="content-login">
		                	<form action="/projet_coVoiturage/Login" method="post">
			                    <div class="form">
				                        <h2>Login</h2>
				                        
				                        <div class="inputBox">
				                            <input type="text" id="username" name="username" required="required">
				                            <div class="input-text"></div>
				                            <span>Username</span>                        
				                        </div>
				                        
				                        <div class="inputBox">
				                            <input type="password" id="password" name="password" required="required">
				                            <div class="input-text"></div>
				                            <span>Password</span>	                            
				                        </div>
				                        
				                        <div class="aaa">
				                            <div class="Links">
					                            <a href="#">Forgot Password</a>
					                            <a href="register.jsp">Signup</a> 
				                        	</div>
				                        	
				                        	<!-- Affichage d'un message si le compte est cré -->
											<% String accountCreated = (String) request.getAttribute("accountCreated"); %>
											<% if (accountCreated != null) { %>
											<p style="color: green;"><%= accountCreated %></p>
											<% } %>
				                        	
				                        	<!-- Affichage d'un message d'erreur si le mot de passe est incorrect -->
				                        	<% String checkMessage = (String) request.getAttribute("checkMessage"); %>
											<% if (checkMessage != null) { %>
											<p style="color: red;"><%= checkMessage %></p>
											<% } %>
											
											<!-- Affichage d'un message si le compte n'existe pas -->
											<% String accountError = (String) request.getAttribute("accountError"); %>
											<% if (accountError != null) { %>
											<p style="color: red;"><%= accountError %></p>
											<% } %>

				                        	<input type="submit" value="Login">
			                     		</div>
			                     	</div>				                
		                     </form>
		                   </div>
		                </div>
		            </div>
		    </section>  
	
		    <section class="services" id="services">
		        <div class="container">
		        
		            <div class="servicestitle">
		                <h2>Share your ride</h2>
		                <div class="separateur"></div>
		            </div>
		
		            <div class="etape">
		                <h1 class="etapes-title">How it works</h1>
		                <div class="row">
		                    <div class="column">
			                     <h2 class="stepnumber"><i>1</i></h2>
			                     <p class="stepp"><i>Create account</i></p>
		                    </div>
		                    
		                    <div class="column">
		                        <h2 class="stepnumber"><i>2</i></h2>
		                        <p class="stepp"><i>Search or create <br> offers</i></p>
		                    </div>
		                    
		                    <div class="column">
		                        <h2 class="stepnumber"><i>3</i></h2>
		                        <p class="stepp"><i>Enjoy!</i></p>
		                    </div>
		                    
		                    <div class="column photo">
		                        <img class="phone" src="photo/phone.png">
		                    </div> 
		                </div>  
		            </div>
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
		                <ul class="links" >
		                    <li><a href="#home" style="text-decoration: none; color: white;">Home</a></li>
		                    <li><a href="#services" style="text-decoration: none; color: white;">Services</a></li>
		                    <li><a href="#about" style="text-decoration: none; color: white;">About</a></li>
		                    <li><a href="register.jsp" style="text-decoration: none; color: white;">Register</a></li>
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