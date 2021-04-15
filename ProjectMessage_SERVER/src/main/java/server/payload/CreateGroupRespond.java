package server.payload;

public class CreateGroupRespond extends BasePayload {
    private String state;

    public CreateGroupRespond() {
    }

    public CreateGroupRespond(int action, String state) {
        super(action);
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
