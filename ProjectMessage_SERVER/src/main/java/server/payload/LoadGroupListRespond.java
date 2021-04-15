package server.payload;

import java.util.List;

public class LoadGroupListRespond extends BasePayload {
    List<Group> groupList;

    public LoadGroupListRespond() {
    }

    public LoadGroupListRespond(int action, List<Group> groupList) {
        super(action);
        this.groupList = groupList;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public static class Group {
        private int idUser;
        private int idSession;
        private String sessionName;

        public Group(int idUser, int idSession, String sessionName) {
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

}
