package clientmess.payload;

public class AddFriendRequest extends BasePayload {
    private int idUser;
    private int idFriend;
    private String FriendName;

    public AddFriendRequest() {
    }

    public AddFriendRequest(int action, int idUser, int idFriend, String friendName) {
        super(action);
        this.idUser = idUser;
        this.idFriend = idFriend;
        FriendName = friendName;
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

    public String getFriendName() {
        return FriendName;
    }

    public void setFriendName(String friendName) {
        FriendName = friendName;
    }
}
