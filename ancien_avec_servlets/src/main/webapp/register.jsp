<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link  rel="stylesheet" href="styleR.css">
	    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
	    <title>Registration</title>
	</head>
	
	<body>
	    <div class="container">
	        <header> Registration</header>
	        <form action="/projet_coVoiturage/Registration" method="post">
	            <div class="form first">
	                <div class="details personal">
	                    <span class="title">Personal Details</span>
	                    <div class="fields">
	                        <div class="input-fields">
	                            <label>First Name</label>
	                            <input type="text" id="firstName" name="firstName" placeholder="Enter your First Name">
	                        </div>
	                        <div class="input-fields">
	                            <label>Last Name</label>
	                            <input type="text" id="lastName" name="lastName" placeholder="Enter your Last Name">
	                        </div>
	                        <div class="input-fields">
	                            <label>Identity Card Number</label>
	                            <input type="text" id="cin" name="cin" placeholder="Enter your ID Card Number">
	                            <% if (request.getAttribute("cinError") != null) { %>
        							<span style="color: red;"><%= request.getAttribute("cinError") %></span>
    							<% } %>
	                        </div>
	                        <div class="input-fields">
	                            <label>E-mail</label>
	                            <input  type="text" id="email" name="email" placeholder="Enter your Email">
	                            <% if (request.getAttribute("emailError") != null) { %>
        							<span style="color: red;"><%= request.getAttribute("emailError") %></span>
    							<% } %>
	                        </div>
	                        
	                        <div class="input-fields">
	                            <label>Birthday Date</label>
	                            <input type="date" id="birthDate" name="birthDate" placeholder="Enter your Birthday Date">
	                            <% if (request.getAttribute("nullError") != null) { %>
        							<span style="color: red;"><%= request.getAttribute("nullError") %></span>
    							<% } %>
	                        </div>
	                        
	                        <div class="input-fields">
	                            <label>Mobile Number</label>
	                            <input type="text" id="phone" name="phone" placeholder="Enter your mobile number">
	                            <% if (request.getAttribute("phoneError") != null) { %>
        							<span style="color: red;"><%= request.getAttribute("phoneError") %></span>
    							<% } %>
	                        </div>		
	                    </div> 
	                    
	                    <button type="submit">
		                            <span class="btntext">Next</span>
		                            <i class="uil uil-navigator"></i>		                 
	                    </button>
	                    
	                </div>
	            </div>
	        </form> 
	    </div>
	</body>
	
</html>