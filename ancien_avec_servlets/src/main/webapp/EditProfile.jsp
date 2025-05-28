<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ include file="security.jsp" %>
<!DOCTYPE html>
<html lang="en">

	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link  rel="stylesheet" href="styleR.css">
	    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
	    <title>Edit</title>
	    
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
	    
	    <!--fonction ajax qui vérifie le password sans réfréchir la page-->
	    <script>
	        $(document).ready(function() {
	            $('#password').on('blur', function() {
	                var password = $(this).val();
	                var id = $('#id').val();
	                $.ajax({
	                    type: 'GET',
	                    url: '/projet_coVoiturage/checkPassword', // endpoint sur le serveur pour vérifier la disponibilité
	                    data: {'password': password, 'id':id},
	                    success: function(response) {
	                    	console.log(response)
	                        if(response.indexOf('T') !== -1) {
	                            $('#password-message').text('Correct.').css('color', 'green');
	                        }
	                        else {
	                            $('#password-message').text('Password incorrect !').css('color', 'red');
	                        }
	                    }
	                });
	            });
	        });
	    </script>
	    
	</head>
	
	<body>
	    <div class="container">
	        <header> Edit</header>
	        <form action="/projet_coVoiturage/EditProfil_update" method="post">
	            <div class="form first">
	                <div class="details personal">
	                    <span class="title">Personal Details</span>
	                    <div class="fields">
	                        <div class="input-fields">
	                            <label>First Name</label>
	                            <input type="text" id="firstName" name="firstName" value="<%= request.getAttribute("firstName") %>" placeholder="Enter your First Name">
	                        </div>
	                        <div class="input-fields">
	                            <label>Last Name</label>
	                            <input type="text" id="lastName" name="lastName"  value="<%= request.getAttribute("lastName") %>" placeholder="Enter your Last Name">
	                        </div>
	                        <div class="input-fields">
	                            <label>Identity Card Number</label>
	                            <input type="text" id="cin" name="cin" value="<%= request.getAttribute("cin") %>" placeholder="Enter your ID Card Number">
	                            <% if (request.getAttribute("cinError") != null) { %>
        							<span style="color: red;"><%= request.getAttribute("cinError") %></span>
    							<% } %>
	                        </div>
	                        <div class="input-fields">
	                            <label>E-mail</label>
	                            <input  type="text" id="email" name="email" value="<%= request.getAttribute("email") %>" placeholder="Enter your Email">
	                            <% if (request.getAttribute("emailError") != null) { %>
        							<span style="color: red;"><%= request.getAttribute("emailError") %></span>
    							<% } %>
	                        </div>
	                        
	                        <div class="input-fields">
	                            <label>Birthday Date</label>
	                            <input type="date" id="birthDate" name="birthDate" value="<%= request.getAttribute("birthDate") %>" placeholder="Enter your Birthday Date">
	                        </div>
	                        
	                        <div class="input-fields">
	                            <label>Mobile Number</label>
	                            <input type="text" id="phone" name="phone" value="<%= request.getAttribute("phone") %>" placeholder="Enter your mobile number">
	                            <% if (request.getAttribute("phoneError") != null) { %>
        							<span style="color: red;"><%= request.getAttribute("phoneError") %></span>
    							<% } %>
	                        </div>		
	                    </div> 
	                    
	                    <div class="details keys">
		                    <span class="title">Personal keys</span>
		                    <div class="fields">
		                        <div class="input-fields">
		                            <label>USERNAME</label>
		                            <input type="text" id="username" name="username" value="<%= request.getAttribute("username") %>" placeholder="Enter your USERNAME">
		                            <span id="username-message"></span> <!-- pour afficher les messages de disponibilité -->
		                        </div>
		                        
		                        <div class="input-fields">
		                            <label>OLD PASSWORD</label>
		                            <input type="password" id="password" name="password" placeholder="Enter your old PASSWORD">
		                            <span id="password-message"></span> <!-- pour afficher les messages  -->
		                        </div>
		                        
		                        <div class="input-fields">
		                            <label>NEW Password</label>
		                            <input  type="password" id="password2" name="password2" placeholder="Repeat your new Password">
		                            <% if (request.getAttribute("nullError") != null) { %>
	        							<span style="color: red;"><%= request.getAttribute("nullError") %></span>
	    							<% } %>
		                            <% if (request.getAttribute("bothPasswordsError") != null) { %>
	        							<span style="color: red;"><%= request.getAttribute("bothPasswordsError") %></span>
	    							<% } %>
		                        </div>
	                        </div>
	                        
	                        <input type="hidden" id="id" name="id" value="<%= session.getAttribute("idBD") %>">	                        
                     	                        
	                        <button class="nextbtn" >	
	                            	<span class="btntext">Modify</span>
	                        </button>
	                    
	                </div>
	            </div>
	        </form> 
	    </div>
	</body>
	
</html>