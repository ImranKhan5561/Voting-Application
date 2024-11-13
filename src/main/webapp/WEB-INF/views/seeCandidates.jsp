<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>See Candidates</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/Admin.css' />">
</head>
<body>
    <h2>Candidates for Election: ${election.name}</h2>
    <table>
        <tr>
            <th>Name</th>
            <th>Party</th>
            <th>Description</th>
            <th>Photo</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="candidate" items="${candidates}">
    <tr>
        <td>${candidate.name}</td>
        <td>${candidate.party}</td>
        <td>${candidate.description}</td>
        <td><img src="data:image/jpeg;base64,${candidate.photoBase64}" alt="Candidate Photo" width="100" /></td>
        <td>
            <form action="deleteCandidate" method="post">
    <input type="hidden" name="candidateId" value="${candidate.id}" />
    <input type="hidden" name="electionId" value="${election.id}" />
    <button type="submit">Delete</button>
</form>
            
           <form action="/admin/updateCandidate/${candidate.id}" method="get">
    <button type="submit">Update</button>
</form>
           
        </td>
    </tr>
</c:forEach>
        
    </table>
    <button onclick="window.history.back()">Back</button>
</body>
</html>
