package lt.Karolis.MovieReviewTest.exceptions;

public class MovieReviewException extends RuntimeException {
    public MovieReviewException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public MovieReviewException(String exMessage) {
        super(exMessage);
    }
}