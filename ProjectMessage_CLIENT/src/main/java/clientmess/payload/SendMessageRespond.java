package clientmess.payload;

public class SendMessageRespond extends BasePayload {
    private int userState;
    private int idMsg;
    private int idSession;
    private int idUser;
    private String tfInputMsg;
    private String userName;

    public SendMessageRespond() {

    }

    public SendMessageRespond(int action, int userState, int idMsg, int idSession, int idUser, String tfInputMsg, String userName) {
        super(action);
        this.userState = userState;
        this.idMsg = idMsg;
        this.idSession = idSession;
        this.idUser = idUser;
        this.tfInputMsg = tfInputMsg;
        this.userName = userName;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
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
