package lt.Karolis.MovieReviewTest.dto;

public class AuthenticationResponse {
    private String authenticationToken;
    private String email;
    private boolean success;

    public AuthenticationResponse() { }

    public AuthenticationResponse(String authenticationToken, String email, boolean success) {
        this.authenticationToken = authenticationToken;
        this.email = email;
        this.success = success;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
