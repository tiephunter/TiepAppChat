package clientmess.payload;

import java.util.List;

//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//        "stateChat",
//        "idUser",
//        "idFriend",
//        "tenTaiKhoanFriend",
//        "sessionId",
//        "messageList"
//})
//public class ChatRespond {
//
//    @JsonProperty("stateChat")
//    private Integer stateChat;
//    @JsonProperty("idUser")
//    private Integer idUser;
//    @JsonProperty("idFriend")
//    private Integer idFriend;
//    @JsonProperty("tenTaiKhoanFriend")
//    private String tenTaiKhoanFriend;
//    @JsonProperty("sessionId")
//    private Integer sessionId;
//    @JsonProperty("messageList")
//    private Object messageList;
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//    @JsonProperty("stateChat")
//    public Integer getStateChat() {
//        return stateChat;
//    }
//
//    @JsonProperty("stateChat")
//    public void setStateChat(Integer stateChat) {
//        this.stateChat = stateChat;
//    }
//
//    @JsonProperty("idUser")
//    public Integer getIdUser() {
//        return idUser;
//    }
//
//    @JsonProperty("idUser")
//    public void setIdUser(Integer idUser) {
//        this.idUser = idUser;
//    }
//
//    @JsonProperty("idFriend")
//    public Integer getIdFriend() {
//        return idFriend;
//    }
//
//    @JsonProperty("idFriend")
//    public void setIdFriend(Integer idFriend) {
//        this.idFriend = idFriend;
//    }
//
//    @JsonProperty("tenTaiKhoanFriend")
//    public String getTenTaiKhoanFriend() {
//        return tenTaiKhoanFriend;
//    }
//
//    @JsonProperty("tenTaiKhoanFriend")
//    public void setTenTaiKhoanFriend(String tenTaiKhoanFriend) {
//        this.tenTaiKhoanFriend = tenTaiKhoanFriend;
//    }
//
//    @JsonProperty("sessionId")
//    public Integer getSessionId() {
//        return sessionId;
//    }
//
//    @JsonProperty("sessionId")
//    public void setSessionId(Integer sessionId) {
//        this.sessionId = sessionId;
//    }
//
//    @JsonProperty("messageList")
//    public Object getMessageList() {
//        return messageList;
//    }
//
//    @JsonProperty("messageList")
//    public void setMessageList(Object messageList) {
//        this.messageList = messageList;
//    }
//
//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//
//}

public class ChatRespond extends BasePayload {
    private int stateChat;
    private int idUser;
    private int idFriend;
    private String tenTaiKhoanFriend;
    private int sessionId;
    private List<ChatMessage> messageList;

    public ChatRespond() {
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
