package clientmess.payload;

import java.util.List;

public class LoadUserRespond extends BasePayload {
    List<LoadUser> loadUserList;

    public LoadUserRespond() {
    }

    public LoadUserRespond(int action, List<LoadUser> loadUserList) {
        super(action);
        this.loadUserList = loadUserList;
    }

    public List<LoadUser> getLoadUserList() {
        return loadUserList;
    }

    public void setLoadUserList(List<LoadUser> loadUserList) {
        this.loadUserList = loadUserList;
    }

    public static class LoadUser {
        private int idFriend;
        private String nameFriend;

        public LoadUser() {
        }

        public LoadUser(int idFriend, String nameFriend) {
            this.idFriend = idFriend;
            this.nameFriend = nameFriend;
        }

        public int getIdFriend() {
            return idFriend;
        }

        public void setIdFriend(int idFriend) {
            this.idFriend = idFriend;
        }

        public String getNameFriend() {
            return nameFriend;
        }

        public void setNameFriend(String nameFriend) {
            this.nameFriend = nameFriend;
        }
    }

}
