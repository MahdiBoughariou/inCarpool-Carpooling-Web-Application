<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="security.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=Neo, initial-scale=1.0">
  <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">
  <link href='styleR4.css' rel='stylesheet'>
  <title>Price</title>
</head>
<style>
  /* Chrome, Safari, Edge, Opera */
  input::-webkit-outer-spin-button,
  input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }
  /* Firefox */
  input[type=number] {
    -moz-appearance: textfield;
  }
</style>
  
<body>
  <nav>
    <div class="logo">
      <a href="#" style="text-decoration:none;">
        <img class="imglogo" src="photo/logovR0.png">
      </a>
      <span>inCarpool</span>
    </div>  
  </nav>
  
  <section>
  <div class="form">
  <form action="AddOfferServlet" method="post">
  <input type="hidden" name="currentPage" value="addRidePrice">
    <h1>Set your price per seat</h1>
    <div class="container">
      <span class="minus">
        <span></span>
      </span>
      <input type="text" name="price" id="price" class="num" value="20DT" style="border: none;outline: none; text-align: center; line-height: 1;">
      <span class="plus">
        <span></span>
        <span></span>
      </span>
    </div>
          
    <!-- Hidden field to store numeric value of price -->
    <input type="hidden" name="numeric_price" id="numeric_price" value="20">
    
    <script src="script2.js"></script>
    <div>
      <div class="comment"> <span>Recommended price: 10DT - 30DT</span></div> 
      <p>Your ride is overpriced. There's a very high chance you'll end up travelling alone.</p>
    </div>
    <div class="boutons">
      <div class="boutonR">
        <a href="publisharidenbplace.jsp">
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
      const numDisplay = document.querySelector('.num');
      const plusBtn = document.querySelector('.plus');
      const minusBtn = document.querySelector('.minus');
      const numericPriceField = document.getElementById('numeric_price');
        
      plusBtn.addEventListener('click', function () {
        let value = parseInt(numDisplay.value);
        if (value < 50) {
          value++;
          numDisplay.value = value + "DT";
          numericPriceField.value = value; // Update the hidden field
        }
      });

      minusBtn.addEventListener('click', function () {
        let value = parseInt(numDisplay.value);
        if (value > 5) {
          value--;
          numDisplay.value = value + "DT";
          numericPriceField.value = value; // Update the hidden field
        }
      });

      numDisplay.addEventListener('blur', function () {
        let value = parseInt(numDisplay.value);
        if (isNaN(value) || value < 5) {
          numDisplay.value = "5DT";
          numericPriceField.value = 5; // Update the hidden field
        } else if (value > 50) {
          numDisplay.value = "50DT";
          numericPriceField.value = 50; // Update the hidden field
        } else {
          numDisplay.value = value + "DT";
          numericPriceField.value = value; // Update the hidden field
        }
      });
    });
  </script>
</body>
</html>
