<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="classes.User" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>inCarpool Dashboard</title>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="styleA.css">
</head>

<body>
    <!-- =============== Navigation ================ -->
    <div class="container">
        <div class="navigation">
            <ul>
                <li>
                    <a href="#">
                       
                        <span class="title1">inCarpool</span>
                    </a>
                </li>

                <li>
                    <a href="#">
                        <span class="icon">
                            <ion-icon name="home-outline"></ion-icon>
                        </span>
                        <span class="title">Dashboard</span>
                    </a>
                </li>

                <li>
                    <a href="/projet_coVoiturage/AllUsers">
                        <span class="icon">
                            <ion-icon name="people-outline"></ion-icon>
                        </span>
                        <span class="title">Customers</span>
                    </a>
                </li>

                <li>
                    <a href="/projet_coVoiturage/CustomerRequest">
                        <span class="icon">
                            <ion-icon name="chatbubble-outline"></ion-icon>
                        </span>
                        <span class="title">Customer requests</span>
                    </a>
                </li>

                <li>
                    <a href="loginA.jsp">
                        <span class="icon">
                            <ion-icon name="log-out-outline"></ion-icon>
                        </span>
                        <span class="title">Log Out</span>
                    </a>
                </li>
            </ul>
        </div>

        <!-- ========================= Main ==================== -->   
        <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                <div class="search">
                    <label>
                        <input type="text" placeholder="Search here">
                        <ion-icon name="search-outline"></ion-icon>
                    </label>
                </div>

                <div class="user">
                    <img src="photo/unkown.jpg" alt="">
                </div>
            </div>

            <!-- ======================= Cards ================== -->
            <div class="cardBox">
                <div class="card">
                    <div>
                        <div class="numbers">4</div>
                        <div class="cardName">Customers</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="person-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers">6</div>
                        <div class="cardName">Offers</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="cart-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers">3</div>
                        <div class="cardName">Reservations</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="basket-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers">150</div>
                        <div class="cardName">Balance</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="cash-outline"></ion-icon>
                    </div>
                </div>
            </div>

            <!-- ================ Order Details List ================= -->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Recent customers</h2>
                        <a href="/projet_coVoiturage/AllUsers" class="btn">View All</a>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <td>Username</td>
                                <td>E-mail</td>
                                <td>ID Card</td>
                                <td>Phone Number</td>
                                <td>Status</td>
                            </tr>
                        </thead>

                        

						<tbody>
						    <%
						        // Récupération de la liste des utilisateurs depuis la servlet
						        List<User> ListeUsers = (List<User>) request.getAttribute("ListeUsers");
						        for (User user : ListeUsers) {
						    %>
						    <tr>
						        <td><%= user.getUsername() %></td>
						        <td><%= user.getEmail() %></td>
						        <td><%= user.getCin() %></td>
						        <td><%= user.getPhone() %></td>
						        <% if (user.getState().equals("pending")) { %>
						        <td><span class="status pending"><%= user.getState() %></span></td>
						        <% } else if (user.getState().equals("approved")) { %>
						        <td><span class="status confirmed"><%= user.getState() %></span></td>
						        <% } else { %>
						        <td><span class="status rejected"><%= user.getState() %></span></td>
						        <% } %>
						    </tr>
						    <% } %>
						</tbody>
                    </table>
                </div>

                <!-- ================= New Offers ================ -->
                <div class="recentOffers">
                    <div class="cardHeader">
                        <h2>Recent Offers</h2>
                        <a href="#" class="btn">View All</a>
                    </div>

                    <table>
                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="photo/vehicule.jpeg" alt=""></div>
                            </td>
                            <td>
                                <h4>Sfax->Tunis <br> <span>16/08/2022</span></h4>
                            </td>
                        </tr>

                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="photo/vehicule.jpeg" alt=""></div>
                            </td>
                            <td>
                                <h4>Kairouan->Tunis <br> <span>15/08/2022</span></h4>
                            </td>
                        </tr>

                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="photo/vehicule.jpeg" alt=""></div>
                            </td>
                            <td>
                                <h4>Hammamet->Tunis <br> <span>14/08/2022</span></h4>
                            </td>
                        </tr>
                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="photo/vehicule.jpeg" alt=""></div>
                            </td>
                            <td>
                                <h4>Hammamet->Sfax <br> <span>12/08/2022</span></h4>
                            </td>
                        </tr>
                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="photo/vehicule.jpeg" alt=""></div>
                            </td>
                            <td>
                                <h4>Tunis->Sfax <br> <span>11/08/2022</span></h4>
                            </td>
                        </tr>
                        <tr>
                            <td width="60px">
                                <div class="imgBx"><img src="photo/vehicule.jpeg" alt=""></div>
                            </td>
                            <td>
                                <h4>Tunis->Sfax <br> <span>10/08/2022</span></h4>
                            </td>
                        </tr>

                        

                      
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- =========== Scripts =========  -->
    <script src="assets/js/main.js"></script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>

</html>