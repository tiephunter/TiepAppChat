package server.payload;

import java.util.List;

public class ChatRespond extends BasePayload {
    private int stateChat;
    private int idUser;
    private int idFriend;
    private String tenTaiKhoanFriend;
    private int sessionId;
    private List<ChatMessage> messageList;

    public ChatRespond(int action) {
        super(action);
    }

    public ChatRespond(int action, int stateChat, int idUser, int idFriend, String tenTaiKhoanFriend, int sessionId, List<ChatMessage> messageList) {
        super(action);
        this.stateChat = stateChat;
        this.idUser = idUser;
        this.idFriend = idFriend;
        this.tenTaiKhoanFriend = tenTaiKhoanFriend;
        this.sessionId = sessionId;
        this.messageList = messageList;
    }

    public int getStateChat() {
        return stateChat;
    }

    public void setStateChat(int stateChat) {
        this.stateChat = stateChat;
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

    public String getTenTaiKhoanFriend() {
        return tenTaiKhoanFriend;
    }

    public void setTenTaiKhoanFriend(String tenTaiKhoanFriend) {
        this.tenTaiKhoanFriend = tenTaiKhoanFriend;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
    public List<ChatMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<ChatMessage> messageList) {
        this.messageList = messageList;
    }

}
