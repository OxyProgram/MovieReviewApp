package lt.Karolis.MovieReviewTest.dto;

public class SuccessResponse {

    private boolean success;

    public SuccessResponse() {
    }

    public SuccessResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
