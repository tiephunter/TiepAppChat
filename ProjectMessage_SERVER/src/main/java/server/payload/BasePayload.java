package server.payload;

import java.io.Serializable;


public class BasePayload implements Serializable {
    int action;

    public BasePayload() {
    }

    public BasePayload(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
