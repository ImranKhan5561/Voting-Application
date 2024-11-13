<<%@ page import="java.util.List" %>
 <%
     List<Object[]> results = (List<Object[]>) request.getAttribute("results");
     String electionName = (String) request.getAttribute("electionName");
     String[] colors = { "#4CAF50", "#2196F3", "#FFC107", "#FF5722", "#9C27B0", "#00BCD4", "#FF9800", "#E91E63" };

     // Calculate the total number of votes
     long totalVotes = 0;
     for (Object[] result : results) {
         totalVotes += (Long) result[2];
     }
 %>

 <!DOCTYPE html>
 <html lang="en">
 <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Live Election Results</title>
     <style>
         .bar-container {
             width: 50%;
             margin: 10px auto;
             background-color: #f1f1f1;
             border-radius: 5px;
             padding: 5px;
         }
         .bar {
             display: flex;
             gap: 8px;
             height: 30px;
             text-align: right;
             line-height: 30px;
             color: blue;
             border-radius: 5px;
             margin: 5px 0;
             transition: width 0.5s;
             align-items: center;
         }
     </style>
     <!-- Include Chart.js from CDN -->
     <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
 </head>
 <body>
     <h2 style="text-align: center;">Live Election Results</h2>
     <h3 style="text-align: center;">Election: <%= electionName %></h3>

     <div id="results-container">
         <%
             int colorIndex = 0;
             for (Object[] result : results) {
                 String party = (String) result[0];
                 String candidateName = (String) result[1];
                 Long voteCount = (Long) result[2];

                 // Calculate percentage of votes
                 double votePercentage = (totalVotes > 0) ? (voteCount * 100.0 / totalVotes) : 0;
                 String barColor = colors[colorIndex % colors.length];
                 colorIndex++;
         %>
             <p style="color:red; text-align:center;"><%= party %></p>
             <div class="bar-container"><%= candidateName %>
                 <div class="bar" style="width: <%= votePercentage %>%; background-color: <%= barColor %>;">
                     <p><%= voteCount %> votes</p>
                 </div>
             </div>
         <% } %>
     </div>

     <!-- Pie Chart Container -->
     <div style="width: 40%; margin: 20px auto;">
         <canvas id="pieChart"></canvas>
     </div>

     <script>
         // Data for the pie chart
         const candidateNames = [
             <% for (Object[] result : results) { %>
                 "<%= result[1] %>",
             <% } %>
         ];

         const voteCounts = [
             <% for (Object[] result : results) { %>
                 <%= result[2] %>,
             <% } %>
         ];

         const colors = [
             <% for (int i = 0; i < results.size(); i++) { %>
                 "<%= colors[i % colors.length] %>",
             <% } %>
         ];

         // Initialize the pie chart
         const ctx = document.getElementById('pieChart').getContext('2d');
         new Chart(ctx, {
             type: 'pie',
             data: {
                 labels: candidateNames,
                 datasets: [{
                     data: voteCounts,
                     backgroundColor: colors
                 }]
             },
             options: {
                 responsive: true,
                 plugins: {
                     legend: {
                         position: 'top',
                     },
                     tooltip: {
                         callbacks: {
                             label: function(tooltipItem) {
                                 const totalVotes = voteCounts.reduce((a, b) => a + b, 0);
                                 const votePercentage = ((tooltipItem.raw / totalVotes) * 100).toFixed(2);
                                 return `${tooltipItem.label}: ${tooltipItem.raw} votes (${votePercentage}%)`;
                             }
                         }
                     }
                 }
             }
         });
     </script>
 </body>
 </html>
