<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Canceled</title>
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
        .canceled-container {
            text-align: center;
            padding: 40px;
            background: rgba(0, 0, 0, 0.7); /* Transparent box */
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5);
            width: 90%;
            max-width: 500px;
            backdrop-filter: blur(10px); /* Smooth blur effect */
        }
        .canceled-container h1 {
            margin-bottom: 20px;
            font-size: 2.5em;
            color: #ff4d4d;
            font-weight: bold;
        }
        .message {
            margin-bottom: 30px;
            font-size: 1.2em;
            color: #ffd700;
        }
        .message span {
            color: #fff;
            font-weight: bold;
        }
        a {
            text-decoration: none;
            padding: 12px 30px;
            font-size: 16px;
            color: #fff;
            background: #ff4d4d;
            border-radius: 5px;
            transition: all 0.3s ease;
        }
        a:hover {
            background: #e53935;
            color: #fff;
        }
        footer {
            margin-top: 20px;
            color: #aaa;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <div class="canceled-container">
        <h1>Payment Canceled</h1>
        <div class="message">
            <p>${message}</p>
        </div>
        <a href="/">Go to Home</a>
        <footer>&copy; 2024 Online Movie Ticket Reservation. All rights reserved.</footer>
    </div>
</body>
</html>
