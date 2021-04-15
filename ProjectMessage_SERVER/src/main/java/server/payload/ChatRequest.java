package server.payload;

public class ChatRequest extends BasePayload {
    private int idUser;
    private int idFriend;
    private String tenTaiKhoanFriend;

    public ChatRequest() {
    }

    public ChatRequest(int action, int idUser, int idFriend, String tenTaiKhoanFriend) {
        super(action);
        this.idUser = idUser;
        this.idFriend = idFriend;
        this.tenTaiKhoanFriend = tenTaiKhoanFriend;
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
}
