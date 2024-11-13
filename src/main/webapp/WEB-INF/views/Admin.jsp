<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>

    <title>Admin Panel</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/Admin.css' />">
</head>
<body>
    <header>
        <h1>Admin Panel</h1>
        <nav>
            <button onclick="toggleForm('addElectionForm')">Add Election</button>
            <button onclick="toggleForm('addCandidateForm')">Add Candidate</button>
            <button onclick="toggleForm('seeCandidatesForm')">See Candidates</button>
            <button onclick="toggleForm('declareResultsForm')">Declare Results</button> <!-- New Button -->
        </nav>
    </header>
<h2>${message}</h2>
<h4 style="text-align:center">User Registration Requests </h4>
<table id="my-table">
    <thead>
        <tr>
            <th>Username</th>
            <th>Voter Id</th>
            <th>Mobile</th>
            <th>Gender</th>
            <th>Dob</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="request" items="${pending}">
            <tr>
                <td>${request.username}</td>
                <td>${request.voterCardNo}</td>
                <td>${request.mobile}</td>
               <td>${request.gender}</td>
                <td>${request.dob}</td>
                <td>
                    <form action="/admin/approve" method="post">
                        <input type="hidden" name="requestId" value="${request.id}" />
                        <button type="submit">Approve</button>
                    </form>
                </td>
                 <td>
                                    <form action="/admin/reject" method="post">
                                        <input type="hidden" name="requestId" value="${request.id}" />
                                        <button type="submit" id="reject">Reject</button>
                                    </form>
                                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<h4 style="text-align:center">Updation Requests</h4>
<table id="my-table">
    <thead>
        <tr>
            <th>Current Username</th>
            <th>New Username</th>
            <th>Current Mobile</th>
            <th>New Mobile</th>
            <th>Current Gender</th>
            <th>New Gender</th>
            <th>Current DOB</th>
            <th>New DOB</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="requestData" items="${UpdatePending}">
            <tr>
                <td>${requestData.currentUser.name}</td>
                <td>${requestData.updateRequest.username}</td>
                <td>${requestData.currentUser.mobile}</td>
                <td>${requestData.updateRequest.newMobile}</td>
                <td>${requestData.currentUser.gender}</td>
                <td>${requestData.updateRequest.newGender}</td>
                <td>${requestData.currentUser.dob}</td>
                <td>${requestData.updateRequest.newDob}</td>
                <td>
                    <form action="/admin/approveUpdate" method="post">
                        <input type="hidden" name="requestId" value="${requestData.updateRequest.id}" />
                        <button type="submit">Approve</button>
                    </form>
                </td>
                <td>
                    <form action="/admin/rejectUpdate" method="post">
                        <input type="hidden" name="requestId" value="${requestData.updateRequest.id}" />
                        <button type="submit" id="reject">Reject</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

    <div id="addElectionForm" style="display:none;">
        <h2>Add Election</h2>
        <form action="addElection" method="POST">
            <label for="electionName">Election Name:</label>
            <input type="text" id="electionName" name="name" required>
            <label for="electionDate">Election Date:</label>
            <input type="date" id="electionDate" name="date" required>
            <button type="submit">Add Election</button>
        </form>
    </div>

    <div id="addCandidateForm" style="display:none;">
        <h2>Add Candidate</h2>
        <form action="addCandidate" method="POST" enctype="multipart/form-data">
            <label for="candidateName">Name:</label>
            <input type="text" id="candidateName" name="name" required>
            <label for="party">Party:</label>
            <input type="text" id="party" name="party" required>
            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>
            <label for="photoFile">Photo:</label>
            <input type="file" id="photoFile" name="photoFile" accept="image/*" required>
            <label for="logoFile">Logo:</label>
            <input type="file" id="logoFile" name="logoFile" accept="image/*" required>
            <label for="electionId">Election:</label>
            <select id="electionId" name="electionId" required>
                <option>Select Election Type</option>
                <c:forEach var="election" items="${elections}">
                    <option value="${election.id}">${election.name}</option>
                </c:forEach>
            </select>
            <button type="submit">Add Candidate</button>
        </form>
    </div>

    <div id="seeCandidatesForm" style="display:none;">
        <h2>See Candidates by Election</h2>
        <form action="/admin/seeCandidates" method="GET">
            <label for="electionId">Election:</label>
            <select id="electionId" name="electionId" required>
            <option value="" disabled selected>Select Election</option>
                <c:forEach var="election" items="${elections}">
                    <option value="${election.id}">${election.name}</option>
                </c:forEach>
            </select>
            <button type="submit">See Candidates</button>
        </form>
    </div>

   
    <div id="declareResultsForm" style="display:none;">
        <h2>Declare Results</h2>
        <form action="/admin/declareResult" method="POST">
            <label for="electionId">Election:</label>
            <select id="electionId" name="electionId" required>
            <option value="" disabled selected>Select Election</option>
                <c:forEach var="election" items="${elections}">
                    <option value="${election.id}">${election.name}</option>
                </c:forEach>
            </select>
            <button type="submit">Declare Result</button>
        </form>
    </div>

    <script>
        function toggleForm(formId) {
            var form = document.getElementById(formId);
            if (form.style.display === "none") {
                form.style.display = "block";
            } else {
                form.style.display = "none";
            }
        }
    </script>
</body>
</html>
