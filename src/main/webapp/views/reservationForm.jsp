<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #4CAF50;
            text-align: center;
        }
        .seat-container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .seat-grid {
            display: grid;
            grid-template-columns: repeat(10, 50px);
            gap: 5px;
        }
        .seat {
            width: 50px;
            height: 50px;
            text-align: center;
            line-height: 50px;
            border: 1px solid #ccc;
            cursor: pointer;
            background-color: #f1f1f1;
        }
        .seat.reserved {
            background-color: #ff4d4d;
            cursor: not-allowed;
        }
        .seat.selected {
            background-color: #4CAF50;
            color: #fff;
        }
        .form-container {
            text-align: center;
            margin-top: 20px;
        }
    </style>
    <script>
        // Array to store selected seat IDs
        let selectedSeats = [];

        function selectSeat(seatId) {
            const seatElement = document.getElementById(seatId);

            // If the seat is already reserved, prevent selection
            if (seatElement.classList.contains("reserved")) {
                alert("This seat is already reserved!");
                return;
            }

            // Toggle selection of the seat
            if (seatElement.classList.contains("selected")) {
                seatElement.classList.remove("selected");
                // Remove seat from the selectedSeats array
                selectedSeats = selectedSeats.filter(seat => seat !== seatId);
            } else {
                seatElement.classList.add("selected");
                selectedSeats.push(seatId);
            }

            // Update the hidden input field with selected seats
            const selectedSeatsInput = document.getElementById("seats");
            selectedSeatsInput.value = selectedSeats.join(", ");  // Store as a comma-separated list
        }
    </script>
</head>
<body>
    <h1>Reserve Your Seat for "${selectedMovie}"</h1>
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
            <div id="<%= seatId %>" class="<%= cssClass %>"
                 onclick="selectSeat('<%= seatId %>')">
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
            <!-- Hidden input field to store selected seat IDs -->
            <input type="hidden" id="seats" name="seats" required>
            <input type="hidden" id="movie" name="movie" value="${selectedMovie}">
            <label for="customerName">Your Name:</label>
            <input type="text" id="customerName" name="customerName" placeholder="Enter your name" required>
            <br><br>
            <label for="email">Email Address:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>
            <br><br>
            <button type="submit">Reserve</button>
        </form>
    </div>
</body>
</html>