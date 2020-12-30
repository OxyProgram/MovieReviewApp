package lt.Karolis.MovieReviewTest.dto;

public class AuthenticationResponse {
    private String username;
    private String authenticationToken;
    private String email;
    private boolean success;

    public AuthenticationResponse() { }

    public AuthenticationResponse(String username, String authenticationToken, String email, boolean success) {
        this.username = username;
        this.authenticationToken = authenticationToken;
        this.email = email;
        this.success = success;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
