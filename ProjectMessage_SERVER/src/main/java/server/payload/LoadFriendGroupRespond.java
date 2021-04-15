package server.payload;

import java.util.List;

public class LoadFriendGroupRespond extends BasePayload {
    private int idUser;
    private String accountName;
    List<LoadFriend> loadFriendList ;

    public LoadFriendGroupRespond() {
    }

    public LoadFriendGroupRespond(int action, int idUser, String accountName, List<LoadFriend> loadFriendList) {
        super(action);
        this.idUser = idUser;
        this.accountName = accountName;
        this.loadFriendList = loadFriendList;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<LoadFriend> getLoadFriendList() {
        return loadFriendList;
    }

    public void setLoadFriendList(List<LoadFriend> loadFriendList) {
        this.loadFriendList = loadFriendList;
    }

    public static class LoadFriend{
        private int idFriend;
        private String accountName;
        private String friendName;

        public LoadFriend() {
        }

        public LoadFriend(int idFriend, String accountName, String friendName) {
            this.idFriend = idFriend;
            this.accountName = accountName;
            this.friendName = friendName;
        }

        public int getIdFriend() {
            return idFriend;
        }

        public void setIdFriend(int idFriend) {
            this.idFriend = idFriend;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getFriendName() {
            return friendName;
        }

        public void setFriendName(String friendName) {
            this.friendName = friendName;
        }
    }
}
