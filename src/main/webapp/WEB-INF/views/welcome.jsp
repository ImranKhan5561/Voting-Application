<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <link rel="stylesheet" href="/css/welcome.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

  <header>
        <div class="hamburger-menu">
            <i class="fa fa-bars" onclick="toggleMenu()"></i>
            <div id="menu" class="menu-content">
             <nav id="adminm">
              <a href="/admin/panel"><button class="admin-button">Admin</button></a>
             </nav>

                <nav id="user-infom">
               <sec:authorize access="isAuthenticated()">
              <div class="user-dropdown">
               <button class="user-button">
                <i class="fa fa-user"></i>
                Hi, ${username} &nbsp;
               <i class="fa fa-caret-down" onclick="toggleMenu2()"></i>
              <div class="user-menu" id="menu2">

               <a href="logout">Logout</a>
               <a href="SeeProfile">See Profile</a>
                <a href="forgot">Change Password</a>
                 </div>
                 </button>

                    </div>
                  </sec:authorize>
                  </nav>

             <nav id="login-signupm">
             <sec:authorize access="!isAuthenticated()">
            <a href="loginpage"><button class="login-button">Login</button></a>

             </sec:authorize>
              <a href="signuppage"><button class="signup-button">Signup</button></a>
               </nav>

                <a href="seeResults">See Results</a>
                <a href="#Help">Help</a>


            </div>
            </div>

            </div>
           <div id="logo"><img src="logoVA.jpg" alt="Logo">
            <p id="site-name">ElectEase</p>
            </div>

            <nav id="admin">
            <a href="/admin/panel"><button class="admin-button">Admin</button></a>
            </nav>

       <nav id="user-info">
                     <sec:authorize access="isAuthenticated()">
                    <div class="user-dropdown">
                     <button class="user-button">
                      <i class="fa fa-user"></i>
                      Hi, ${username} &nbsp;
                     <i class="fa fa-caret-down" onclick="toggleMenu1()"></i>
                    <div class="user-menu" id="menu1">

                     <a href="logout">Logout</a>
                     <a href="SeeProfile">See Profile</a>
                     <a href="forgot">Change Password</a>
                       </div>
                       </button>

                          </div>
                        </sec:authorize>
                        </nav>

        <nav id="login-signup">
            <sec:authorize access="!isAuthenticated()">
                <a href="loginpage"><button class="login-button">Login</button></a>

            </sec:authorize>
            <a href="signuppage"><button class="signup-button">Signup</button></a>
        </nav>
    </header>


    <main>
        <section class="hero-section">
            <div class="hero-content">
                <h1>Welcome to the Voting Platform</h1>
                <p>Your vote shapes the future. Make it count.</p>
                <form action="voting" method="GET">
                    <div id="voting-button">
                        <select id="election" name="electionId" required>
                            <option value="" disabled selected>Select Election</option>
                            <c:forEach var="election" items="${elections}">
                                <option value="${election.id}">${election.name} - ${election.date}</option>
                            </c:forEach>
                        </select>
                        <button type="submit">Vote</button>
                    </div>
                </form>
            </div>
        </section>


        <section class="seeResults">
            <h2>View Election Results</h2>
            <form action="seeResults" method="GET">
                <div id="results-button">
                    <select id="declaredElection" name="electionId" required>
                        <option value="" disabled selected>Select Election to View Results</option>
                        <c:forEach var="election" items="${declaredElections}">
                            <option value="${election.id}">${election.name} - ${election.date}</option>
                        </c:forEach>
                    </select>
                    <button type="submit">See Results</button>
                </div>
            </form>
        </section>

        <section class="upcoming-events">
            <h2>Extra Activity</h2>
            <div class="event-cards">
                <div class="event-card">
                    <h3>View Live</h3>
                    <p class="event-date">Next Election</p>
                    <p class="event-time">Time: 7:00 PM</p>
                    <p class="event-location">Election: Presidential Election</p>
                    <form action="live-results" method="GET">
                                    <div id="results-button">
                                        <select id="undeclaredElection" name="electionId" required>
                                            <option value="" disabled selected>Select Election to View Results</option>
                                            <c:forEach var="election" items="${elections}">
                                                <option value="${election.id}">${election.name} - ${election.date}</option>
                                            </c:forEach>
                                        </select>
                                        <button class="event-button">See Live</button>
                                    </div>
                                </form>
                </div>
                <div class="event-card">
                    <h3>Voter Registration Drive</h3>
                    <p class="event-date">Date: 25 December, 2024</p>
                    <p class="event-time">Time: 10:00 AM</p>
                    <p class="event-location">Location: Kmclu University</p><br>
                   <p style="font-weight:bold; color:black"> You Are Welcome!</p>
                </div>
            </div>
        </section>

        <section class="testimonials-section">
            <h2>What Our Voters Say</h2>
            <div class="testimonial">
                <p>"Voting is the foundation of democracy. This platform made it easy and secure!"</p>
                <span>-John Wick</span>
            </div>
            <div class="testimonial">
                <p>"I never realized how important my vote was until I used this site. Highly recommend it!"</p>
                <span>-Mr. Bean</span>
            </div>
        </section>

        <section class="faq-section" id="Help">
            <h2>Frequently Asked Questions</h2>
            <div class="faq">
                <h3>How do I vote?</h3>
                <p>(1). Register on the website,Wait for Approval Once approved you can vote.<br>
                 (2). If you already have an account, then log in and select the election
                    you want to vote in, then click the vote button.
                </p>
            </div>
            <div class="faq">
                <h3>Is my vote secure?</h3>
                <p>Yes, we use advanced encryption to ensure your vote is secure and confidential.</p>
            </div>
        </section>

    </main>

    <footer>
        <p>&copy; 2024 Vote For Future. All rights reserved. | <a href="privacy-policy.html">Privacy Policy</a></p>
    </footer>

   <c:if test="${not empty message}">
       <script>
           window.onload = function() {
               alert("${message}");
           };
       </script>
   </c:if>

    <script>

        function toggleMenu() {
            var menu = document.getElementById('menu');
            if (menu.style.display === 'block') {
                menu.style.display = 'none';
            } else {
                menu.style.display = 'block';
            }
        }
        function toggleMenu1(){
        	var menu1 = document.getElementById('menu1');

            if (menu1.style.display === 'block') {
                menu1.style.display = 'none';
            } else {
                menu1.style.display = 'block';
            }
        }
        function toggleMenu2(){
        	var menu2= document.getElementById('menu2');
        	if (menu2.style.display === 'block') {
                            menu2.style.display = 'none';
                        } else {
                            menu2.style.display = 'block';
                        }
                        }
    </script>
</body>
</html>

