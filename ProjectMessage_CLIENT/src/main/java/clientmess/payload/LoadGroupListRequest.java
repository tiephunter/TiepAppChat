package clientmess.payload;

public class LoadGroupListRequest extends BasePayload {
    private int idUser;

    public LoadGroupListRequest() {
    }

    public LoadGroupListRequest(int action, int idUser) {
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
