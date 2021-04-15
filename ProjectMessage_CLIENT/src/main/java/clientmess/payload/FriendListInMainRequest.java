package clientmess.payload;

public class FriendListInMainRequest extends BasePayload {
    private int idUser;

    public FriendListInMainRequest() {
    }

    public FriendListInMainRequest(int action, int idUser) {
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
