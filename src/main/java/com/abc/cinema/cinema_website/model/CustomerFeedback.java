package com.abc.cinema.cinema_website.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class CustomerFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movie;
    private int rating;  // Rating from 1 to 5
    private String comments;  // Customer's feedback

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;  // Link to reservation

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "CustomerFeedback{" +
                "id=" + id +
                ", movie='" + movie + '\'' +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                ", reservation=" + reservation +
                '}';
    }
}