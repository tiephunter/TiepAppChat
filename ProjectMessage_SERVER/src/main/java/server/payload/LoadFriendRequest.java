package server.payload;

public class LoadFriendRequest extends BasePayload {
    private int idUser;

    public LoadFriendRequest() {
    }

    public LoadFriendRequest(int action, int idUser) {
        super(action);
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
