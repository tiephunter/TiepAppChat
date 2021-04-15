package server.payload;

import java.util.List;

public class ChatGroupRespond extends BasePayload {
        private int idUser;
        List<IdMember> idMembersList;
        private String sessionName;
        private int sessionId;
        private List<ChatMessage> messageList;

        public ChatGroupRespond() {
        }

        public ChatGroupRespond(int action, int idUser, List<IdMember> idMembersList, String sessionName, int sessionId, List<ChatMessage> messageList) {
                super(action);
                this.idUser = idUser;
                this.idMembersList = idMembersList;
                this.sessionName = sessionName;
                this.sessionId = sessionId;
                this.messageList = messageList;
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

        public String getSessionName() {
                return sessionName;
        }

        public void setSessionName(String sessionName) {
                this.sessionName = sessionName;
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
