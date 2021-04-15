package clientmess.payload;

import java.util.List;

public class SearchFriendListRespond extends BasePayload{
    private int position;
    List<LoadFriend> loadFriendsList;

    public SearchFriendListRespond() {
    }

    public SearchFriendListRespond(int action, int position, List<LoadFriend> loadFriendsList) {
        super(action);
        this.position = position;
        this.loadFriendsList = loadFriendsList;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<LoadFriend> getLoadFriendsList() {
        return loadFriendsList;
    }

    public void setLoadFriendsList(List<LoadFriend> loadFriendsList) {
        this.loadFriendsList = loadFriendsList;
    }

    public static class LoadFriend{
        private int idUser;
        private String tenTaiKhoan;
        private String hoTen;

        public LoadFriend() {
        }

        public LoadFriend(int idUser, String tenTaiKhoan, String hoTen) {
            this.idUser = idUser;
            this.tenTaiKhoan = tenTaiKhoan;
            this.hoTen = hoTen;
        }

        public int getIdUser() {
            return idUser;
        }

        public void setIdUser(int idUser) {
            this.idUser = idUser;
        }

        public String getTenTaiKhoan() {
            return tenTaiKhoan;
        }

        public void setTenTaiKhoan(String tenTaiKhoan) {
            this.tenTaiKhoan = tenTaiKhoan;
        }

        public String getHoTen() {
            return hoTen;
        }

        public void setHoTen(String hoTen) {
            this.hoTen = hoTen;
        }
    }
}
