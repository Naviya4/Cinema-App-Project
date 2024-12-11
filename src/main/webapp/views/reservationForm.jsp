<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reserve Your Seat</title>
    <style>
        body {
            background-image: url('/images/Background.jpg'); /* Replace with your background image URL */
            background-size: cover; /* This ensures the image covers the entire background */
            background-position: center center; /* This centers the image */
            background-attachment: fixed; /* Keeps the background image fixed while scrolling */
            font-family: Arial, sans-serif;
            color: #fff;
            margin: 0;
            padding: 0;
        }
        header {
            text-align: center;
            padding: 20px;
            background: rgba(0, 0, 0, 0.8);
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.5);
        }
        header h1 {
            margin: 0;
            font-size: 2.5em;
            color: #ff8c00;
        }
        .seat-container {
            display: flex;
            justify-content: center;
            padding: 30px;
        }
        .seat-grid {
            display: grid;
            grid-template-columns: repeat(10, 50px);
            gap: 8px;
        }
        .seat {
            width: 50px;
            height: 50px;
            text-align: center;
            line-height: 50px;
            border-radius: 8px;
            cursor: pointer;
            background: #f1f1f1;
            color: #333;
            font-weight: bold;
            transition: all 0.3s ease-in-out;
            box-shadow: 0 3px 5px rgba(0, 0, 0, 0.3);
        }
        .seat:hover {
            transform: scale(1.1);
        }
        .seat.reserved {
            background: #ff4d4d;
            color: #fff;
            cursor: not-allowed;
        }
        .seat.selected {
            background: #4CAF50;
            color: #fff;
        }
        .form-container {
            margin: 20px auto;
            padding: 20px;
            background: rgba(0, 0, 0, 0.9);
            border-radius: 10px;
            width: 90%;
            max-width: 500px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.4);
            text-align: center;
        }
        .form-container label {
            display: block;
            text-align: left;
            margin: 10px 0 5px;
            color: #ff8c00;
            font-size: 1.1em;
        }
        .form-container input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: none;
            border-radius: 5px;
            outline: none;
        }
        .form-container input:focus {
            box-shadow: 0 0 5px #4caf50;
        }
        .form-container button {
            padding: 12px 25px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            background: #ff8c00;
            color: #333;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        .form-container button:hover {
            background: #ffc107;
            color: #000;
        }
        footer {
            text-align: center;
            padding: 15px;
            background: rgba(0, 0, 0, 0.8);
            margin-top: 30px;
            color: #aaa;
            font-size: 0.9em;
        }
    </style>
    <script>
        let selectedSeats = [];

        function selectSeat(seatId) {
            const seatElement = document.getElementById(seatId);

            if (seatElement.classList.contains("reserved")) {
                alert("This seat is already reserved!");
                return;
            }

            if (seatElement.classList.contains("selected")) {
                seatElement.classList.remove("selected");
                selectedSeats = selectedSeats.filter(seat => seat !== seatId);
            } else {
                seatElement.classList.add("selected");
                selectedSeats.push(seatId);
            }

            document.getElementById("seats").value = selectedSeats.join(", ");
        }
    </script>
</head>
<body>
    <header>
        <h1>Reserve Your Seat for "${selectedMovie}"</h1>
    </header>

    <div class="seat-container">
        <div class="seat-grid">
            <%
                String[][] seatLayout = (String[][]) request.getAttribute("seatLayout");
                for (int row = 0; row < seatLayout.length; row++) {
                    for (int col = 0; col < seatLayout[row].length; col++) {
                        String seatId = (char) ('A' + row) + String.valueOf(col + 1);
                        String status = seatLayout[row][col];
                        String cssClass = "seat";
                        if ("RESERVED".equals(status)) {
                            cssClass += " reserved";
                        }
            %>
            <div id="<%= seatId %>" class="<%= cssClass %>" onclick="selectSeat('<%= seatId %>')">
                <%= seatId %>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>

    <div class="form-container">
        <form action="/reserve" method="post">
            <input type="hidden" id="seats" name="seats" required>
            <input type="hidden" id="movie" name="movie" value="${selectedMovie}">
            <label for="customerName">Your Name:</label>
            <input type="text" id="customerName" name="customerName" placeholder="Enter your name" required>
            <label for="email">Email Address:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>
            <button type="submit">Reserve</button>
        </form>
    </div>

    <footer>
        &copy; 2024 Online Movie Ticket Reservation. All rights reserved.
    </footer>
</body>
</html>