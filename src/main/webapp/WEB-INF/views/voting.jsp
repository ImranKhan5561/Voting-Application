<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vote</title>
    <link rel="stylesheet" href="/css/voting.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1 class="title">Vote for Your Favorite Candidate</h1>
            <div class="election-info">
                <h2>${election.name}</h2>
                <p>${election.date}</p>
            </div>
        </div>
        <br>
        <div class="candidate-list">
            <c:forEach var="candidate" items="${candidates}">
                <div class="candidate-item">
                    <div class="candidate-photo">
                        <img src="<c:url value='/candidatePhoto/${candidate.id}' />" alt="${candidate.name}" class="candidate-img">
                    </div>
                    <div class="candidate-logo">
                        <img src="<c:url value='/candidateLogo/${candidate.id}' />" alt="Logo" class="logo-img">
                    </div>
                    <div class="candidate-info">
                        <h2>${candidate.name}</h2>
                        <p>${candidate.party}</p>
                        <p>${candidate.description}</p>
                        
                         <form action="<c:url value='/vote' />" method="post">
            <input type="hidden" name="candidateId" value="${candidate.id}">
            <input type="hidden" name="election" value="${election.id}">
            <input type="hidden" name="VoterCardNo" value="${VoterCardNo}"> 
            <button type="submit" class="vote-button">Vote</button>
        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
