package server.payload;

public class SendMessgeGroupRespond extends BasePayload {
    private int idMsg;
    private int idSession;
    private int idUser;
    private String tfInputMsg;
    private String userName;

    public SendMessgeGroupRespond() {
    }

    public SendMessgeGroupRespond(int action, int idMsg, int idSession, int idUser, String tfInputMsg, String userName) {
        super(action);
        this.idMsg = idMsg;
        this.idSession = idSession;
        this.idUser = idUser;
        this.tfInputMsg = tfInputMsg;
        this.userName = userName;
    }

    public int getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(int idMsg) {
        this.idMsg = idMsg;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTfInputMsg() {
        return tfInputMsg;
    }

    public void setTfInputMsg(String tfInputMsg) {
        this.tfInputMsg = tfInputMsg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
