<%@page language="java" %>

<html>
<body>
    <h1>Welcome to ABC Cinema!</h1>
    <form action="/reserve" method="post">
        <label for="movie">Select Movie:</label>
        <select id="movie" name="movie">
            <option value="Movie 1">Movie 1</option>
            <option value="Movie 2">Movie 2</option>
            <option value="Movie 3">Movie 3</option>
        </select>
        <br><br>

        <label for="seat">Select Seat:</label>
        <input type="text" id="seat" name="seat" placeholder="Enter Seat Number">
        <br><br>

        <button type="submit">Reserve</button>
    </form>
</body>
</html>
