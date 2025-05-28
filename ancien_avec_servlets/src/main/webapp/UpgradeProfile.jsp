<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ include file="security.jsp" %>
<!DOCTYPE html>
<html lang="en">

	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link  rel="stylesheet" href="styleUpgrade.css">
	    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
	    <title>Registration</title>
	</head>
	
	<body>
	    <div class="container">
	        <header> Upgrade </header>
	        <form action="/projet_coVoiturage/UpgradeProfile" method="post">
	            <div class="form first">
	                <div class="details personal">
	                    <span class="title">Personal Details</span>
	                    <div class="fields">
	                    
	                    <p style="margin: 10px 0 10px 0;">
								Dear user,<br>


As part of our verification process, we kindly ask you to upload both sides of your identification and driver's license documents. Please ensure that the files are in PDF format to facilitate efficient processing. Your cooperation in providing these documents is greatly appreciated.
							</p>
	                    
	                        <div class="input-fields">
	                            <label for="id_img">Upload your identity card : </label>
	                            <input type="file" id="id_img" name="id_img" >
	                            <% if (request.getAttribute("pdfError") != null) { %>
        							<span style="color: red;"><%= request.getAttribute("pdfError") %></span>
    							<% } %>
	                        </div>
	                        
	                        <div class="input-fields">
	                            <label for="lic_img">Upload your driving license : </label>
	                            <input type="file" id="lic_img" name="lic_img" placeholder="Enter your Last Name">
	                        </div>
	
	                    </div> 
	                    
	                    <% String idBD =String.valueOf( session.getAttribute("idBD")); %>
	                    <input  type="hidden" id="id" name="id" value="<%= idBD %>">
	                    
	                    <button type="submit">
		                            <span class="btntext">Submit</span>
		                            <i class="uil uil-navigator"></i>		                 
	                    </button>
	                    
	                </div>
	            </div>
	        </form> 
	    </div>
	</body>
	
</html>