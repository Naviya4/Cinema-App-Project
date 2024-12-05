package com.abc.cinema.cinema_website.model;

public class FeedbackDTO {
    private int rating; // Rating (1-5)
    private String comment; // Optional comment

    // Getters and setters
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
