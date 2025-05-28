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
	    <title>Time</title>
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

	            <input name="time" id="time" type="hidden" value=${sessionScope.time}">
	    <section div="container">
	        <div class="form">
	            <form action="AddOfferServlet" method="post">
	            <input type="hidden" name="currentPage" value="addRideTime">
	                <div class="container-form time">
	                    <label class="aa">At what time will you pick passengers up?</label>
	                    <input  type="time" id="timeride" name="timeride" required>
	                </div>
	                
	                <div class="boutons">
	                    <div class="boutonR">
	                        <a href="publisharidedate.jsp">
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
    document.addEventListener("DOMContentLoaded", function () {
        var timeInput = document.getElementById("timeride");
        var hiddenTime = document.getElementById("time").value;
        var dateArray = hiddenTime.split(":");
        var hours = parseInt(dateArray[0]);
        var minutes = parseInt(dateArray[1]);
        var hiddenDate = new Date();
        hiddenDate.setHours(hours);
        hiddenDate.setMinutes(minutes);

        // Subtract 30 minutes from the hidden time
        var newTime = new Date(hiddenDate.getTime() - 30 * 60000); // 30 minutes in milliseconds
        var formattedTime = ('0' + newTime.getHours()).slice(-2) + ':' + ('0' + newTime.getMinutes()).slice(-2);
        timeInput.value = formattedTime;

        timeInput.addEventListener("blur", function () {
            var inputTime = this.value;
            var timeParts = inputTime.split(":");
            var hours = parseInt(timeParts[0]);
            var minutes = parseInt(timeParts[1]);

            // Add 30 minutes to the provided time
            minutes += 30;
            if (minutes >= 60) {
                hours += 1;
                minutes -= 60;
            }


            // Compare newTime with hiddenDate - 30 minutes
            var hiddenMinus30 = new Date(hiddenDate.getTime());
            var newTimeDate = new Date();
            newTimeDate.setHours(hours);
            newTimeDate.setMinutes(minutes);
            

            // Set timeInput to hiddenDate - 30 minutes if it's later
            if (newTimeDate > hiddenMinus30) {
                timeInput.value = formattedTime;
            } 
        });
    });
</script>




	    
	</body>
	
</html>