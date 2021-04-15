package clientmess.payload;

public class ChatMessage {
    private int idMsg;
    private String TextMsg;
    private int idSender;
    private String userName;

    public ChatMessage() {
    }

    public ChatMessage(int idMsg, String textMsg, int idSender, String userName) {
        this.idMsg = idMsg;
        TextMsg = textMsg;
        this.idSender = idSender;
        this.userName = userName;
    }

    public int getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(int idMsg) {
        this.idMsg = idMsg;
    }

    public String getTextMsg() {
        return TextMsg;
    }

    public void setTextMsg(String textMsg) {
        TextMsg = textMsg;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
