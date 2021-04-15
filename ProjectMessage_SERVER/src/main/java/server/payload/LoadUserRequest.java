package server.payload;

public class LoadUserRequest extends BasePayload {
    private int idUser;
    private String message;

    public LoadUserRequest() {
    }

    public LoadUserRequest(int action, int idUser, String message) {
        super(action);
        this.idUser = idUser;
        this.message = message;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
