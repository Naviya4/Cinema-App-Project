<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Form</title>
</head>
<body>
    <h2>Provide Your Feedback</h2>

    <form:form method="POST" action="/submitFeedback" modelAttribute="feedbackDTO">
        <div>
            <label for="rating">Rating (1-5):</label>
            <input type="number" id="rating" name="rating" min="1" max="5" required />
        </div>
        <div>
            <label for="comment">Comments (optional):</label>
            <textarea id="comment" name="comment"></textarea>
        </div>
        <div>
            <button type="submit">Submit Feedback</button>
        </div>
    </form:form>

</body>
</html>