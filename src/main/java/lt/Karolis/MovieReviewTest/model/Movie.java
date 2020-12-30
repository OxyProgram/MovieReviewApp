package lt.Karolis.MovieReviewTest.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "MOVIE", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")})
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MOVIE_ID")
    private String movieID;

    @Column(name = "REVIEW")
    private String review;

    @Column(name = "RATING")
    private int rating;

    @ManyToOne
    private User user;


    public Movie() {
    }

    public Movie(Long id, User user, String movieID, String review, int rating) {
        this.id = id;
        this.user = user;
        this.movieID = movieID;
        this.review = review;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
