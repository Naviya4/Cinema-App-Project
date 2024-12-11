<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Welcome to ABC Cinema!</title>
    <style>
        /* Set background image */
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

        h1 {
            text-align: center;
            color: #ffffff;
            font-size: 2rem;
            font-weight: bold;
            text-transform: uppercase;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
            margin-top: 50px;
        }

        h1 .brand {
            color: #ff8c00; /* Highlight the NB brand name */
            font-size: 3.5rem;
            font-style: italic;
        }

        h2 {
            text-align: center;
            color: #ff8c00;
            font-size: 1.5rem;
            margin-bottom: 5px;
            text-transform: uppercase;
            letter-spacing: 2px;
            font-weight: 600;
            text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.5);
        }

        /* Movie container */
        .movie-container {
            display: grid;
            grid-template-columns: repeat(4, 1fr); /* 4 columns */
            gap: 20px;
            padding: 20px;
            margin: auto;
        }

        /* Movie box style */
        .movie-box {
            width: 250px;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 20px;
            margin: 10px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        /* Hover effect on movie box */
        .movie-box:hover {
            transform: scale(1.05);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.4);
        }

        /* Movie image */
        .movie-box img {
            width: 100%;
            height: auto;
            border-radius: 5px;
            margin-bottom: 15px;
            object-fit: cover;
        }

        /* Movie title */
        .movie-title {
            font-size: 18px;
            font-weight: bold;
            margin: 10px 0;
        }

        /* Movie description */
        .movie-description {
            font-size: 14px;
            margin-bottom: 15px;
        }

        /* Button style */
        .reserve-btn {
            background-color: #f39c12;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            font-size: 16px;
        }

        /* Button hover effect */
        .reserve-btn:hover {
            background-color: #e67e22;
        }

        /* Buttons for navigation */
        .nav-buttons {
            position: fixed;
            top: 50px;
            right: 50px;
            display: flex;
            gap: 20px;
        }

        .nav-button {
            background-color: #ff8c00;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 20px;
            text-align: center;
        }

        .nav-button:hover {
            background-color: #ffc107;
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
</head>
<body>
    <!-- Navigation Buttons -->
    <div class="nav-buttons">
        <a href="/feedback" class="nav-button">Add Feedback</a>
        <a href="/feedbackDashboard" class="nav-button">Feedback Dashboard</a>
    </div>

    <h1>Welcome to <span class="brand">NB</span> Cinema!</h1>
    <h2>Now Showing</h2>
    <div class="movie-container">
        <c:forEach var="movie" items="${movies}">
            <div class="movie-box">
                <!-- Movie Image -->
                <img src="${movie.imageUrl}" alt="${movie.name}">

                <!-- Movie Details -->
                <div class="movie-title">${movie.name}</div>
                <p class="movie-description">${movie.description}</p>

                <!-- Button to navigate to the reservation form -->
                <form action="/reservationForm" method="get">
                    <input type="hidden" name="movie" value="${movie.name}">
                    <button class="reserve-btn" type="submit">Reserve Now</button>
                </form>
            </div>
        </c:forEach>
    </div>

    <footer>
        &copy; 2024 Online Movie Ticket Reservation. All rights reserved.
    </footer>
</body>
</html>