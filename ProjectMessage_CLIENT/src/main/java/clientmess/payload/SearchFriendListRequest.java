package clientmess.payload;

public class SearchFriendListRequest extends BasePayload {
    private int position;
    private int idUser;
    private String message;

    public SearchFriendListRequest() {
    }

    public SearchFriendListRequest(int action, int position, int idUser, String message) {
        super(action);
        this.position = position;
        this.idUser = idUser;
        this.message = message;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
