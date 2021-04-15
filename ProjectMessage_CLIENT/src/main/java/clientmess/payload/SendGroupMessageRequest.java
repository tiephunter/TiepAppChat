package clientmess.payload;

import java.util.List;

public class SendGroupMessageRequest extends BasePayload {
    private int sessionID;
    private String tfInputMessage;
    private int idUser;
    List<IdMember> idMembersList;

    public SendGroupMessageRequest() {
    }

    public SendGroupMessageRequest(int action, int sessionID, String tfInputMessage, int idUser, List<IdMember> idMembersList) {
        super(action);
        this.sessionID = sessionID;
        this.tfInputMessage = tfInputMessage;
        this.idUser = idUser;
        this.idMembersList = idMembersList;
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

    public List<IdMember> getIdMembersList() {
        return idMembersList;
    }

    public void setIdMembersList(List<IdMember> idMembersList) {
        this.idMembersList = idMembersList;
    }

    public static class IdMember {
        private int idMember;

        public IdMember() {
        }

        public IdMember(int idMember) {
            this.idMember = idMember;
        }

        public int getIdMember() {
            return idMember;
        }

        public void setIdMember(int idMember) {
            this.idMember = idMember;
        }
    }
}

