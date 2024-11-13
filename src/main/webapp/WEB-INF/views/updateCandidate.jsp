<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Candidate</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/Admin.css' />">
</head>
<body>
    <h2>Update Candidate</h2>
    <form action="/admin/updateCandidate" method="POST" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${candidate.id}"/>
        <label for="candidateName">Name:</label>
        <input type="text" id="candidateName" name="name" value="${candidate.name}" required>
        <label for="party">Party:</label>
        <input type="text" id="party" name="party" value="${candidate.party}" required>
        <label for="description">Description:</label>
        <textarea id="description" name="description" required>${candidate.description}</textarea>
        <label for="photoFile">Photo:</label>
        <input type="file" id="photoFile" name="photoFile" accept="image/*">
        <label for="logoFile">Logo:</label>
        <input type="file" id="logoFile" name="logoFile" accept="image/*">
        <label for="electionId">Election:</label>
        <select id="electionId" name="electionId" required>
            <c:forEach var="election" items="${elections}">
                <option value="${election.id}" <c:if test="${election.id == candidate.election.id}">selected</c:if>>${election.name}</option>
            </c:forEach>
        </select>
        <button type="submit">Update Candidate</button>
    </form>
    <button onclick="window.history.back()">Back</button>
</body>
</html>
