<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Feedback Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h2>Customer Feedback</h2>

    <!-- Feedback Table -->
    <table border="1">
        <thead>
            <tr>
                <th>Movie</th>
                <th>Rating</th>
                <th>Comment</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="feedback" items="${feedbackList}">
                <tr>
                    <td>${feedback.movie}</td>
                    <td>${feedback.rating}</td>
                    <td>${feedback.comments}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty feedbackList}">
        <p>No feedback available.</p>
    </c:if>
</body>
</html>
