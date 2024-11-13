<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/css/certificate.css">
    <title>Voting Certificate</title>
</head>
<body>


    <div class="certificate-container">
        <div class="certificate">
            <img src="https://www.pngitem.com/pimgs/m/274-2743839_national-emblem-of-india-hd-png-download.png" alt="National Emblem" class="emblem">
            <h1>Certificate of Voting</h1>
            <p>This is to certify that</p>
            <h2>${user.name}</h2> 
            <p>with VoterCard Number: ${user.voterCardNo}</p> 
            <p>has successfully cast their vote in the</p>
            <h3>${electionName}</h3> 
            <p>held on ${electionDate}.</p>
            <p class="thank-you">Thank you for participating in the democratic process!</p>
        </div>
        <button id="downloadBtn" onclick="downloadCertificate()">Download Certificate</button>
    </div>
     <c:if test="${not empty error}">
           <script>

                   alert("${error}");

           </script>
       </c:if>
    <script>
        function downloadCertificate() {
            window.print();
        }
    </script>
</body>
</html>
