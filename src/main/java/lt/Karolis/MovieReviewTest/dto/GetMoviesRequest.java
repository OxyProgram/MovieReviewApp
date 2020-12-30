package lt.Karolis.MovieReviewTest.dto;

public class GetMoviesRequest {
    
    private String email;

    public GetMoviesRequest() {}

    public GetMoviesRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
