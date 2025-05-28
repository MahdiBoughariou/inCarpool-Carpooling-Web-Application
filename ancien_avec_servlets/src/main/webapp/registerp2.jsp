<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link  rel="stylesheet" href="styleR.css">
	    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
	    <title>Registration</title>
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	    <!--fonction ajax qui vérifie l'existance de username sans réfréchir la page-->
	    <script>
	        $(document).ready(function() {
	            $('#username').on('blur', function() {
	                var username = $(this).val();
	                $.ajax({
	                    type: 'GET',
	                    url: '/projet_coVoiturage/CheckUsernameAvailability', // endpoint sur le serveur pour vérifier la disponibilité
	                    data: {'username': username},
	                    success: function(response) {
	                    	console.log(response)
	                        if(response.indexOf('T') !== -1) {
	                            $('#username-message').text('Username available.').css('color', 'green');
	                        }
	                        else {
	                            $('#username-message').text('Username not available, try another one !').css('color', 'red');
	                        }
	                    }
	                });
	            });
	        });
	    </script>
	    
	</head>
	
	<body>
	    <div class="container">
	        <form action="/projet_coVoiturage/Registration2" method="post">
	            <div class="form second">
	                <div class="details keys">
	                    <span class="title">Personal keys</span>
	                    <div class="fields">
	                        <div class="input-fields">
	                            <label>USERNAME</label>
	                            <input type="text" id="username" name="username" placeholder="Enter your USERNAME">
	                            <span id="username-message"></span> <!-- pour afficher les messages de disponibilité -->
	                            <% if (request.getAttribute("usernameError") != null) { %>
        							<span style="color: red;"><%= request.getAttribute("usernameError") %></span>
    							<% } %>
	                        </div>
	                        
	                        <div class="input-fields">
	                            <label>PASSWORD</label>
	                            <input type="password" id="password" name="password" placeholder="Enter your PASSWORD">
	                        </div>
	                        
	                        <div class="input-fields">
	                            <label>Repeat Password</label>
	                            <input  type="password" id="password2" name="password2" placeholder="Repeat your Password">
	                            <% if (request.getAttribute("nullError") != null) { %>
        							<span style="color: red;"><%= request.getAttribute("nullError") %></span>
    							<% } %>
	                            <% if (request.getAttribute("passwordError") != null) { %>
        							<span style="color: red;"><%= request.getAttribute("passwordError") %></span>
    							<% } %>
	                        </div>     
	                    </div> 
	                    
	                    <div class="buttons">
	                        <div class="backbtn">
	                            <a href="register.jsp" class="backbtn">
	                                <i class="uil uil-navigator" style="transform: rotate(180deg )"></i>
	                                <span class="btntext">Back</span>
	                            </a>
	                        </div>                        
	                        
	                        <button class="nextbtn" >	
	                            	<span class="btntext">Submit</span>
	                        </button>	                                                             
	                    </div>         
	                </div>
	            </div>
	        </form>
	    </div>
	</body>
</html>