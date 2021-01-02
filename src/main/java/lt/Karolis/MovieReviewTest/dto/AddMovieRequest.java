package lt.Karolis.MovieReviewTest.dto;

public class AddMovieRequest {

    private String email;
    private String movieId;
    private String review;
    private int rating;

    public AddMovieRequest(String email, String movieId, String review, int rating) {
        this.email = email;
        this.movieId = movieId;
        this.review = review;
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
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
}
