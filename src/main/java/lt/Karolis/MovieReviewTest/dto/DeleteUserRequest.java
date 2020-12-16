package lt.Karolis.MovieReviewTest.dto;

public class DeleteUserRequest {

    private String email;

    public DeleteUserRequest() {
    }

    public DeleteUserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
