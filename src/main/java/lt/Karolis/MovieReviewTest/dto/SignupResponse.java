package lt.Karolis.MovieReviewTest.dto;

public class SignupResponse {

    private boolean success;

    public SignupResponse() {
    }

    public SignupResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
