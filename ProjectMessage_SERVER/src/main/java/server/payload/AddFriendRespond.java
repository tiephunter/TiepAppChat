package server.payload;

public class AddFriendRespond extends BasePayload {
    private int state;

    public AddFriendRespond() {
    }

    public AddFriendRespond(int action, int state) {
        super(action);
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
