<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="security.jsp" %>
<!DOCTYPE html>
<html lang="en">

	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
		<link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">
	    <link  rel="stylesheet" href="styleaddvehiculeserie.css">

	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Add Vehicle</title>
		<style>
		#brand {
    width: 60%;
    font-size: 20px;
    display: flex;
    align-items: center;
    justify-content: center; 
    border-radius: 15px;
    color: rgb(73, 69, 69);
    border: 1px solid #aaa;
    padding: 0 15px;
    height: 42px; 
    margin: 8px 0;
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
	    <%@ page import="classes.Brand"
	    import="classes.DataBase" %>
	    
	    <section class="container">
	        <div class="form" >
	            <form action="AddVehicleServlet" method="post">
	            <input type="hidden" name="currentPage" value="addVehiculeMarque"/>
	                <div class="container-form">
	                    <label class="aa">What's your vehicle's brand?</label>
	                     <select id="brand" name="brand" required>
            <% 
             
                java.util.List<Brand> brands = DataBase.getAllBrands();
                for (Brand brand : brands) {
            %>
            <option value="<%= brand.getId() %>"><%= brand.getName() %></option>
            <% } %>
        </select>
	                </div>
	                
	                <div class="boutons">
	                    <div class="boutonR">
	                        <a href="addvehiculeserie.jsp">
	                            <div class="backbtn">
	                                <i class="uil uil-navigator" style="transform: rotate(180deg )" ></i>
	                                <span class="btntext">Back</span>
	                                
	                            </div>
	                        </a>
	                    </div>
	                    <div class="bouton">
	                       
	  <button type="submit" class="nextbtn" id="nextButton"> 
                        <span class="btntext">Next</span>
                        <i class="uil uil-navigator"></i>
                    </button>
	                            </div>
	                       
	                    </div>
	                    
	               
	            </form>
	            
	        </div>
	    </section>
	</body>
</html>