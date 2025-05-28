<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>inCarpool Dashboard</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="loginA.css">
</head>
<body>
  
    <dic class="vvv">
        <div class="back-login">
            <div class="center">
                    <h1>Login</h1>
                    <form method="post" action="/projet_coVoiturage/LoginA">
                        <div class="txt-fieled">
                            <input type="texte" id="username" name="username" />
                            <label>UserName</label>
                        </div>
                        <div class="txt-fieled">
                            <input type="password" id="password" name="password" />
                            <span></span>
                            <label>Password</label>
                        </div>
	
                        <div class="pass">
                            Forgot Password?
                        </div>
                        
                        <!-- Affichage d'un message d'erreur si le mot de passe est incorrect -->
				        <% String checkMessage = (String) request.getAttribute("checkMessage"); %>
						<% if (checkMessage != null) { %>
						<p style="color: red;"><%= checkMessage %></p>
						<% } %>
											
						<!-- Affichage d'un message si le compte n'existe pas -->
						<% String accountError = (String) request.getAttribute("accountError"); %>
						<% if (accountError != null) { %>
						<p style="color: red;"><%= accountError %></p>
						<% } %>
                        <input type="submit" value="Login">
                       
                    </form>

            </div>
        </div>
    </div>
</dic>

    



    <!-- =========== Scripts =========  -->
    <script src="assets/js/main.js"></script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>
    