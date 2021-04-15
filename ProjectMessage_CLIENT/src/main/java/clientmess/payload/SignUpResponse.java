package clientmess.payload;

public class SignUpResponse extends BasePayload {
    String message;

    public SignUpResponse() {
    }

    public SignUpResponse(int action, String message) {
        super(action);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
