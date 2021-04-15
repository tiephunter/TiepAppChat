package server.payload;

public class SendMessageRequest extends BasePayload {
    private int sessionID;
    private String tfInputMessage;
    private int idUser;
    private int idFriend;

    public SendMessageRequest( ) {

    }

    public SendMessageRequest(int action, int sessionID, String tfInputMessage, int idUser, int idFriend) {
        super(action);
        this.sessionID = sessionID;
        this.tfInputMessage = tfInputMessage;
        this.idUser = idUser;
        this.idFriend = idFriend;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public String getTfInputMessage() {
        return tfInputMessage;
    }

    public void setTfInputMessage(String tfInputMessage) {
        this.tfInputMessage = tfInputMessage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(int idFriend) {
        this.idFriend = idFriend;
    }
}
