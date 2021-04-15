package server.payload;

public class SignUpResponse extends BasePayload {
    String message;

    public SignUpResponse() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
