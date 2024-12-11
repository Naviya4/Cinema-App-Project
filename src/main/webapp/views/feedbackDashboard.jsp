<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Feedback Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('/images/Background.jpg'); /* Set your background image here */
            background-size: cover;
            background-position: center;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            min-height: 100vh;
            color: #fff;
        }

        h2 {
            color: #ff8c00;
            text-align: center;
            margin-top: 20px;
        }

        .feedback-table {
            width: 80%;
            margin-top: 30px;
            border-collapse: collapse;
            background-color: rgba(0, 0, 0, 0.6);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
        }

        .feedback-table th, .feedback-table td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        .feedback-table th {
            background-color: #ff8c00;
            color: white;
        }

        .feedback-table tr:nth-child(even) {
            background-color: rgba(255, 140, 0, 0.2);
        }

        .feedback-table tr:hover {
            background-color: rgba(255, 140, 0, 0.3);
        }

        .chart-container {
            width: 80%;
            margin-top: 50px;
            max-width: 800px;
        }

        .no-feedback-message {
            color: #ff6347;
            text-align: center;
            font-size: 1.2em;
        }

        .back-home {
            margin-top: 20px;
            display: block;
            text-align: center;
            font-size: 1.1em;
            color: #fff; /* Text color */
            background-color: #ff8c00; /* Initial background color */
            text-decoration: none;
            padding: 10px 20px;
            border: 2px solid #ff8c00; /* Border color matches the background */
            border-radius: 5px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .back-home:hover {
            background-color: #ffc107; /* Lighter color on hover */
            color: #333; /* Change text color on hover */
        }
        footer {
            background: rgba(0, 0, 0, 0.8);
            color: #aaa;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
            font-size: 0.9em;
        }

    </style>
</head>
<body>

    <h2>Customer Feedback Dashboard</h2>

    <div class="chart-container">
        <canvas id="feedbackChart"></canvas>
    </div>

    <table class="feedback-table">
        <thead>
            <tr>
                <th>Customer Name</th>
                <th>Rating</th>
                <th>Comment</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="feedback" items="${feedbackList}">
                <tr>
                    <td>${feedback.customer}</td>
                    <td>${feedback.rating}</td>
                    <td>${feedback.comments}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty feedbackList}">
        <p class="no-feedback-message">No feedback available.</p>
    </c:if>

    <a class="back-home" href="/">Go back to homepage</a>

    <script>
        // Get the feedback ratings from the server-side (JSTL)
        var ratings = [];
        var ratingCount = [0, 0, 0, 0, 0]; // For 1, 2, 3, 4, and 5-star ratings

        <c:forEach var="feedback" items="${feedbackList}">
            ratings.push(${feedback.rating});
        </c:forEach>

        // Calculate the rating count
        ratings.forEach(function(rating) {
            ratingCount[rating - 1]++;
        });

        // Create a chart to display the rating distribution
        var ctx = document.getElementById('feedbackChart').getContext('2d');
        var feedbackChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['1 Star', '2 Stars', '3 Stars', '4 Stars', '5 Stars'],
                datasets: [{
                    label: 'Rating Distribution',
                    data: ratingCount,
                    backgroundColor: '#ff8c00',
                    borderColor: '#e07b00',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 1,
                            color: '#fff'
                        },
                        grid: {
                            color: 'rgba(255, 255, 255, 0.5)',  // Lighter grid lines
                            lineWidth: 4                       // Thicker grid lines
                        },
                        title: {
                            display: true,
                            text: 'Number of Feedbacks',
                            color: '#fff',
                            font: {
                                size: 16
                            }
                        }
                    },
                    x: {
                        ticks: {
                            stepSize: 1,
                            color: '#fff'
                        },
                        grid: {
                            color: 'rgba(255, 255, 255, 0.5)',  // Lighter grid lines
                            lineWidth: 4                       // Thicker grid lines
                        },
                        title: {
                            display: true,
                            text: 'Rating',
                            color: '#fff',
                            font: {
                                size: 16
                            }
                        }
                    }
                },
                plugins: {
                    legend: {
                        position: 'top',
                        labels: {
                            color: '#fff'  // Ensure the legend text is white
                        }
                    }
                }
            }
        });
    </script>

    <footer>
        &copy; 2024 Online Movie Ticket Reservation. All rights reserved.
    </footer>

</body>
</html>