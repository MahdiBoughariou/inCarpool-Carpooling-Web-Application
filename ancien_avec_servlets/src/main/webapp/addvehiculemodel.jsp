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
	        
	            <form action="AddVehicleServlet" method="post">
	            <input type="hidden" name="currentPage" value="addVehiculeModel"/>
	                <div class="container-form">
	                    <label class="aa">What's your model's model?</label>
	                    <input  type="text" id="model" name="model" placeholder="Model"required>
	                    <div id="modelFeedback" class="text-danger"></div>
	                </div>
	                
	                <div class="boutons">
	                    <div class="boutonR">
	                        <a href="addvehiculemarque.jsp">
	                            <div class="backbtn ">
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
	            </form>
	            
	        </div>
	    </section>
<script>
document.addEventListener('DOMContentLoaded', function() {
    console.log('Script loaded');

    // Function to validate license plate
    function validateModel() {
        var model = document.getElementById('model').value;
        var modelFeedback = document.getElementById('modelFeedback');

        // Regular expression pattern for XXX-TN-XXXX format
       

        // Check if the license plate input is empty
        if (model.trim() === '') {
            // Display error message and disable the button
            console.log('Model is empty');
            modelFeedback.innerText = 'Model cannot be empty.';
            document.getElementById('nextButton').disabled = true;
            return;
        } else {
            // Clear any previous error messages
            document.getElementById('nextButton').disabled = false;
            modelFeedback.innerText = '';
        }

        // Proceed with AJAX request to check if license plate exists
       
    }

    // Event listener for the Next button
    document.getElementById('nextButton').addEventListener('click', function(event) {
        console.log('Next button clicked');
        validateModel(); // Validate license plate when Next button is clicked
    });

    // Event listener for license plate input change
    document.getElementById('model').addEventListener('input', function(event) {
        console.log('Input value changed');
        validateModel(); // Validate license plate on change
    });

});
</script>
	    
	</body>
</html>