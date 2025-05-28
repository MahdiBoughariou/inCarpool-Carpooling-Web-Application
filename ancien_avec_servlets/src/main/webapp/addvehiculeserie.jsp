<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="security.jsp" %>
<!DOCTYPE html>
<html lang="en">

	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">	    
		<link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">
	    <link  rel="stylesheet" href="styleride.css">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">	    
 		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
   		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Add Vehicule</title>

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
	    
	    <section class="container">
	        <div class="form" >
	            <form action="AddVehicleServlet" method="post" id="addVehicleForm">
	            <input type="hidden" name="currentPage" value="addVehicule">
	                <div class="container-form">
	                    <label class="aa">What is your license plate number?</label>
	                    <input type="text" id="vehicle" name="vehicle" placeholder="XXX-TN-XXXX" required class="form-control">
						<div id="licensePlateFeedback" class="text-danger"></div>
	                </div>
	                
	                <div class="boutons">
	                    <div class="boutonR">
	                        	<a href="profileuser.jsp">
	                            <div class="backbtn">
	                                <i class="uil uil-navigator" style="transform: rotate(180deg )" ></i>
	                                <span class="btntext">Back</span>
	                            
	                            </div>
	                        	</a>
	                    </div>
	                    <div class="bouton">
	                        
	    			<button id="nextButton" type="submit" class="nextbtn"> <!-- Change <a> to <button> -->
                        <span class="btntext">Next</span>
                        <i class="uil uil-navigator"></i>
                    </button>
	                        
	                    </div>
	                    
	                </div>
	                <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
	            </form>
	            
	        </div>
	    </section>


<script>
document.addEventListener('DOMContentLoaded', function() {
    console.log('Script loaded');

    // Function to validate license plate
    function validateLicensePlate() {
        var licensePlate = document.getElementById('vehicle').value;
        var licensePlateFeedback = document.getElementById('licensePlateFeedback');

        // Regular expression pattern for XXX-TN-XXXX format
        var pattern = /^[A-Za-z0-9]{3}-[A-Za-z0-9]{2}-[A-Za-z0-9]{4}$/;

        // Check if the license plate input is empty
        if (licensePlate.trim() === '') {
            // Display error message and disable the button
            console.log('License plate is empty');
            licensePlateFeedback.innerText = 'License plate number cannot be empty.';
            document.getElementById('nextButton').disabled = true;
            return;
        } else if (!pattern.test(licensePlate)) {
            // Display error message if the format is incorrect
            console.log('Invalid license plate format');
            licensePlateFeedback.innerText = 'License plate format must be XXX-TN-XXXX.';
            document.getElementById('nextButton').disabled = true;
            return;
        } else {
            // Clear any previous error messages
            licensePlateFeedback.innerText = '';
        }

        // Proceed with AJAX request to check if license plate exists
        fetch('CheckLicensePlateServlet?licensePlate=' + licensePlate)
            .then(response => response.text())
            .then(data => {
                // Update UI based on response
                if (data.trim() === "true") {
                    // License plate exists
                    console.log('License plate already exists');
                    licensePlateFeedback.innerText = 'This license plate already exists.';
                    document.getElementById('nextButton').disabled = true;
                } else {
                    // License plate doesn't exist
                    console.log('License plate is valid');
                    document.getElementById('nextButton').disabled = false;
                }
            })
            .catch(error => {
                // Handle error
                console.error('Error:', error);
            });
    }

    // Event listener for the Next button
    document.getElementById('nextButton').addEventListener('click', function(event) {
        console.log('Next button clicked');
        validateLicensePlate(); // Validate license plate when Next button is clicked
    });

    // Event listener for license plate input change
    document.getElementById('vehicle').addEventListener('input', function(event) {
        console.log('Input value changed');
        validateLicensePlate(); // Validate license plate on change
    });

});
</script>









	    
	</body>
</html>