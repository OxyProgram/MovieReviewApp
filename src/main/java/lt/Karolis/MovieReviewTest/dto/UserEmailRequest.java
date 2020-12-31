package lt.Karolis.MovieReviewTest.dto;

public class UserEmailRequest {

    private String email;

    public UserEmailRequest() {
    }

    public UserEmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
