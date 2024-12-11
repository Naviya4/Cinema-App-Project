<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Submission</title>
    <style>
        body {
            margin: 0;
            font-family: 'Poppins', sans-serif;
            background-image: url('/images/Background.jpg');
            background-size: cover;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .feedback-success-container {
            text-align: center;
            padding: 30px;
            background: rgba(0, 0, 0, 0.8);
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5);
            width: 90%;
            max-width: 500px;
        }
        .feedback-success-container h2 {
            margin-bottom: 20px;
            font-size: 2em;
            color: #ffffff;
        }
        .feedback-success-container a {
            display: inline-block;
            margin-top: 20px;
            font-size: 1.2em;
            color: #ff8c00;
            text-decoration: none;
            padding: 10px 20px;
            border: 2px solid #ff8c00;
            border-radius: 5px;
            transition: all 0.3s ease;
        }
        .feedback-success-container a:hover {
            background-color: #ff8c00;
            color: #fff;
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
    <div class="feedback-success-container">
        <h2>${message}</h2>
        <a href="/">Go back to homepage</a>
        <footer>&copy; 2024 Online Movie Ticket Reservation. All rights reserved.</footer>
    </div>
</body>
</html>