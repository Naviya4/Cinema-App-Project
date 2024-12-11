<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Form</title>
    <style>
        body {
            margin: 0;
            font-family: 'Poppins', sans-serif;
            background-image: url('/images/Background.jpg');
            background-size: cover;
            background-position: center;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .feedback-container {
            background: rgba(0, 0, 0, 0.7);
            padding: 30px;
            border-radius: 10px;
            width: 100%;
            max-width: 600px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5);
        }

        .feedback-container h2 {
            font-size: 2.5em;
            margin-bottom: 20px;
            text-align: center;
            color: #ff8c00;
        }

        .feedback-container label {
            font-size: 1.2em;
            margin-bottom: 10px;
            display: block;
        }

        .feedback-container input, .feedback-container textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            font-size: 1.1em;
            border-radius: 5px;
            border: 1px solid #ccc;
            background: #f1f1f1;
            color: #333;
        }

        .feedback-container input:focus, .feedback-container textarea:focus {
            outline: none;
            border-color: #ff8c00;
        }

        .feedback-container button {
            padding: 12px 30px;
            background: #ff8c00;
            color: #fff;
            font-size: 1.2em;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
            width: 100%;
            max-width: 200px;
            margin: 0 auto;
            display: block;
        }

        .feedback-container button:hover {
            background: #ffc107;
            color: #333;
        }

        footer {
            margin-top: 20px;
            font-size: 0.9em;
            color: #aaa;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="feedback-container">
        <h2>Provide Your Feedback</h2>
        <form:form method="POST" action="/submitFeedback" modelAttribute="feedbackDTO">
            <div>
                <label for="customer">Your Name:</label>
                <input type="text" id="customer" name="customer" required/>
            </div>
            <div>
                <label for="rating">Rating (1-10):</label>
                <input type="number" id="rating" name="rating" min="1" max="10" required />
            </div>
            <div>
                <label for="comment">Comments:</label>
                <input type="text" id="comment" name="comment" required/>
            </div>
            <div>
                <button type="submit">Submit Feedback</button>
            </div>
        </form:form>
        <footer>&copy; 2024 Online Movie Ticket Reservation. All rights reserved.</footer>
    </div>
</body>
</html>