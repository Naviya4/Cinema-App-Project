<%@page language="java" %>
<html>
<body>
    <h1>Reserve Your Seat</h1>
    <form action="/reserve" method="post">
        <label for="customerName">Your Name:</label>
        <input type="text" id="customerName" name="customerName" placeholder="Enter your name" required>
        <br><br>

        <label for="movie">Selected Movie:</label>
        <input type="text" id="movie" name="movie" value="${selectedMovie}" readonly>
        <br><br>

        <label for="seat">Select Seat:</label>
        <input type="text" id="seat" name="seat" placeholder="Enter Seat Number" required>
        <br><br>

        <label for="email">Email Address:</label>
        <input type="email" id="email" name="email" placeholder="Enter your email" required>
        <br><br>

        <button type="submit">Reserve</button>
    </form>
</body>
</html>
