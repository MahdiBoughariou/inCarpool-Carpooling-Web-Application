<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="security.jsp" %>
<!DOCTYPE html>
<html lang="en">

	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
	    
	
		<link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">
	    <link  rel="stylesheet" href="styleride.css">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Date</title>
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
	    
	    <section div="container">
	        <div class="form">
	            <form action="AddOfferServlet" method="post">
	            <input type="hidden" name="currentPage" value="addRideDate">
	                <div class="container-form">
	                    <label class="aa">When are you going?</label>
	                    <input  type="datetime-local" id="datedesortie" name="datedesortie" required>
	                </div>
	                
	                <div class="boutons">
	                    <div class="boutonR">
	                        <a href="publisharidelocation.jsp">
	                            <div class="backbtn ">
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
    <script>
    document.addEventListener('DOMContentLoaded', function () {   
    	var currentDate = new Date();
        currentDate.setHours(currentDate.getHours() + 8 - (currentDate.getTimezoneOffset() / 60)); // Adjust for timezone offset
        var formattedDate = currentDate.toISOString().slice(0, 16);
        document.getElementById("datedesortie").value= formattedDate;
    
    document.getElementById("datedesortie").addEventListener("blur", function () {
            var inputDate = this.value;
            var currentDate = new Date();
            currentDate.setHours(currentDate.getHours() + 8 - (currentDate.getTimezoneOffset() / 60)); // Adjust for timezone offset

            var selectedDate = new Date(inputDate);
            
            
            if (!inputDate || selectedDate < currentDate) {
                // Set date 8 hours ahead if empty or earlier than current date + 8 hours
                
                var formattedDate = currentDate.toISOString().slice(0, 16);
                this.value = formattedDate;
            }
        });
    });
    </script>
	</body>
</html>