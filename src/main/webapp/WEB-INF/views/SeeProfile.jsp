<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <style>
     body {
            font-family: Arial, sans-serif;
            background-color: #f3f4f6;
            padding: 20px;
        }
        
        .profile-section {
            max-width: 500px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .profile-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }

        .profile-label {
            font-weight: bold;
        }

        .edit-btn {
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        .edit-input {
            display: none;
            width: 60%;
            padding: 5px;
            margin-right: 5px;
        }

        .save-btn, .cancel-btn {
            display: none;
            padding: 5px 10px;
            border-radius: 4px;
            margin-left: 5px;
        }

        .save-btn {
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }

        .cancel-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            cursor: pointer;
        }
        
        #password-input {
            width: 60%;
            padding: 5px;
            margin-right: 5px;
        }

        #Reset{
        padding:6px 6px;
        background-color:green;
        color:white;
        border-radius:5px;
        margin-left:220px;
        border:none;
        }
        #v{
        background-color:#FFFFFF;
        }
       
        .voting-history {
            margin-top: 30px;
            overflow-x: auto; /* Allow horizontal scrolling on small screens */
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 10px 0;
        }

        table, th, td {
            border: 1px solid #ddd;
            text-align: left;
        }

        th, td {
            padding: 10px;
        }

        th {
            background-color: #007BFF;
            color: white;
        }

        /* Responsive styling for smaller screens */
        @media (max-width: 600px) {
            .profile-section, .voting-history {
                width: 100%;
                padding: 15px;
            }

            th, td {
                padding: 8px;
                font-size: 14px;
            }

            /* Optional: Stack table rows on small screens */
            .voting-history table {
                display: block;
                overflow-x: auto;
                white-space: nowrap;
            }
        }
      
    </style>
</head>
<body>

  <div class="profile-section">
   <sec:authorize access="isAuthenticated()">
    <h2><span id="v">VoterId:-</span> ${voterId}</h2>
</sec:authorize>

<c:if test="${not empty errorMessage}">
    <div class="error-message">${Message}</div>
</c:if>


<form action="reset" method="POST">
    <!-- Voter ID Field -->
    <div class="profile-item" id="voterId-field">
        <span class="profile-label">Voter ID:</span>
        <span class="profile-value" id="voterId-display">${voterId}</span>
     <input type="text" class="edit-input" id="voterId-input" value="${voterId}" name="voterCardNo" readonly>
        <button type="button" class="edit-btn" onclick="editField('voterId')">Edit</button>
        <button type="submit" class="save-btn" onclick="saveField('voterId')">Save</button>
        <button type="button" class="cancel-btn" onclick="cancelEdit('voterId')">Cancel</button>
    </div>

    <!-- Date of Birth Field -->
    <div class="profile-item" id="dob-field">
        <span class="profile-label">Date of Birth:</span>
        <span class="profile-value" id="dob-display">${dob}</span>
        <input type="date" class="edit-input" id="dob-input" min="1950-01-01" max="2024-12-12" value="${dob}" name="dob">
        <button type="button" class="edit-btn" onclick="editField('dob')">Edit</button>
        <button type="submit" class="save-btn" onclick="saveField('dob')">Save</button>
        <button type="button" class="cancel-btn" onclick="cancelEdit('dob')">Cancel</button>
    </div>

    <!-- Name Field -->
    <div class="profile-item" id="name-field">
        <span class="profile-label">Name:</span>
        <span class="profile-value" id="name-display">${username}</span>
        <input type="text" class="edit-input" id="name-input" value="${username}" name="name">
        <button type="button" class="edit-btn" onclick="editField('name')">Edit</button>
        <button type="submit" class="save-btn" onclick="saveField('name')">Save</button>
        <button type="button" class="cancel-btn" onclick="cancelEdit('name')">Cancel</button>
    </div>

    <!-- Gender Field -->
    <div class="profile-item" id="gender-field">
        <span class="profile-label">Gender:</span>
        <span class="profile-value" id="gender-display">${gender}</span>
        <input type="text" class="edit-input" id="gender-input" value="${gender}" name="gender">
        <button type="button" class="edit-btn" onclick="editField('gender')">Edit</button>
        <button type="submit" class="save-btn" onclick="saveField('gender')">Save</button>
        <button type="button" class="cancel-btn" onclick="cancelEdit('gender')">Cancel</button>
    </div>

    <!-- Mobile Field -->
    <div class="profile-item" id="mobile-field">
        <span class="profile-label">Mobile:</span>
        <span class="profile-value" id="mobile-display">${mobile}</span>
        <input type="text" class="edit-input" id="mobile-input" value="${mobile}" name="mobile">
        <button type="button" class="edit-btn" onclick="editField('mobile')">Edit</button>
        <button type="submit" class="save-btn" onclick="saveField('mobile')">Save</button>
        <button type="button" class="cancel-btn" onclick="cancelEdit('mobile')">Cancel</button>
    </div>

    <!-- Password Field -->
    <div class="profile-item" id="password-field">
        <span class="profile-label">Password:</span>
        <span class="profile-value" id="password-display"></span>
        <input type="password" id="password-input" placeholder="Enter Password"  name="password" required>

    </div>
                <input type="submit" id="Reset" value="Reset">
</form>
</div>
<div class="voting-history">
    <h3>Voting History</h3>
    <table>
        <thead>
            <tr>
                <th>Election</th>
                <th>Candidate</th>
                <th>Vote Date</th>
                <th>Result</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="history" items="${votingHistory}">
                <tr>
                    <td>${history[0]}</td> <!-- Election name -->
                    <td>${history[1]}</td> <!-- Candidate name -->
                    <td>${history[2]}</td> <!-- Vote date -->
                    <td>${history[3]}</td> <!-- Result status -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script src="/css/SeeProfile.js"></script>

        </body>
        </html>