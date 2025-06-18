package lt.ca.javau12.employeeshiftplanner.dto;

public class JwtAuthenticationResponse {
    private String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
