<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Make Payment</title>
    <style>
        body {
            margin: 0;
            font-family: 'Poppins', sans-serif;
            background-image: url('/images/Background.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .payment-container {
            text-align: center;
            padding: 40px;
            background: rgba(255, 255, 255, 0.1); /* Transparent box */
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5);
            width: 90%;
            max-width: 500px;
            backdrop-filter: blur(10px); /* Smooth blur effect */
        }
        .payment-container h1 {
            margin-bottom: 20px;
            font-size: 2em;
            color: #ff8c00;
            font-weight: bold;
        }
        .payment-details {
            margin-bottom: 20px;
            font-size: 1.2em;
            line-height: 1.8;
        }
        .payment-details span {
            color: #ffd700;
            font-weight: bold;
        }
        form {
            margin-top: 20px;
        }
        button {
            padding: 12px 30px;
            font-size: 16px;
            color: #fff;
            background: #ff8c00;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        button:hover {
            background: #ffc107;
            color: #000;
        }
        footer {
            margin-top: 20px;
            color: #aaa;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <div class="payment-container">
        <h1>Make the Payment Now!</h1>
        <div class="payment-details">
            <p>Movie: <span>${reservedMovie}</span></p>
            <p>Seats: <span>${reservedSeats}</span></p>
        </div>
        <!-- Add a form with a button -->
        <form action="/pay" method="get">
            <button type="submit">Proceed to Payment</button>
        </form>
        <footer>&copy; 2024 Online Movie Ticket Reservation. All rights reserved.</footer>
    </div>
</body>
</html>