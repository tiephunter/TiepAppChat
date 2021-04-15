package server.payload;

public class ChatGroupRequest extends BasePayload {

    private int idUser;
    private int idSession;
    private String sessionName;

    public ChatGroupRequest() {
    }

    public ChatGroupRequest(int action, int idUser, int idSession, String sessionName) {
        super(action);
        this.idUser = idUser;
        this.idSession = idSession;
        this.sessionName = sessionName;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
}
