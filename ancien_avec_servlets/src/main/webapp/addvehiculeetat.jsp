<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="security.jsp" %>
<!DOCTYPE html>
<html lang="en">

	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
		<link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">
	    <link  rel="stylesheet" href="styleaddvehiculeetat.css">
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
	    
	    <section div="container">
	        <div class="form" >
	            <form action="AddVehicleServlet" method="post">
	            <input type="hidden" name="currentPage" value="addVehiculeEtat">
	                <span class="aa">What's your vehicle's state?</span>
	                <div class="container-form">
	                    <label for="Confort">Comfort</label> 
	                    <input type="radio" id="Confort" name="state" value="comfortable" required>
	
	                </div>
	                <div class="container-form">
	                    
	                     <label for="css">Normal</label>
	                     <input type="radio" id="Normal" name="state" value="normal">
	                </div>
	                <div class="container-form">
	                    <label for="javascript">Poor</label>
	                    <input type="radio" id="Poor" name="state" value="poor">
	                </div>
	                <div id="stateFeedback" class="text-danger"></div>
	                <div class="boutons">
	                    <div class="boutonR">
	                                <a href="addvehiculemodel.jsp">	                        
	                            <div class="backbtn">
	                                <i class="uil uil-navigator" style="transform: rotate(180deg )" ></i>

	                                <span class="btntext">Back</span>
	                                
	                            </div>
	                        		</a>
	                    </div>
	                    <div class="bouton">
	       <button type="submit" class="nextbtn" id="nextButton"> <!-- Change <a> to <button> -->
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

    // Function to validate state radio inputs
    function validateState() {
        var stateRadios = document.getElementsByName('state');
        var stateFeedback = document.getElementById('stateFeedback');
        var isChecked = false;

        // Check if any radio input is checked
        for (var i = 0; i < stateRadios.length; i++) {
            if (stateRadios[i].checked) {
                isChecked = true;
                break;
            }
        }

        // If no radio input is checked, display error message
        if (!isChecked) {
            console.log('State is empty');
            stateFeedback.innerText = 'State cannot be empty.';
            document.getElementById('nextButton').disabled = true;
        } else {
            // Clear any previous error messages
            document.getElementById('nextButton').disabled = false;
            stateFeedback.innerText = '';
        }
    }

    // Event listener for the Next button
    document.getElementById('nextButton').addEventListener('click', function(event) {
        console.log('Next button clicked');
        validateState(); // Validate state radio inputs when Next button is clicked
    });

    // Event listener for state radio inputs change
    var stateRadios = document.getElementsByName('state');
    for (var i = 0; i < stateRadios.length; i++) {
        stateRadios[i].addEventListener('change', function(event) {
            console.log('State radio input changed');
            validateState(); // Validate state radio inputs when their value changes
        });
    }
});
</script>

	</body>
</html>