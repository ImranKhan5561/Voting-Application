<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Election Results</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/results.css' />">
</head>
<body>
    <header>
        <h1>Results for ${election.name}</h1>
    </header>

    <table>
        <thead>
            <tr>
                <th>Candidate Name</th>
                <th>Party</th>
                <th>Votes</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="result" items="${results}">
                <tr>
                    <td>${result.candidate.name}</td>
                    <td>${result.candidate.party}</td>
                    <td>${result.voteCount}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
