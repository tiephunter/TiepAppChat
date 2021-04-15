package clientmess.payload;

import java.util.List;

public class LoadFriendRespond extends BasePayload {
    List<LoadFriend> loadFriendsList;

    public LoadFriendRespond() {
    }

    public LoadFriendRespond(int action, List<LoadFriend> loadFriendsList) {
        super(action);
        this.loadFriendsList = loadFriendsList;
    }

    public List<LoadFriend> getLoadFriendsList() {
        return loadFriendsList;
    }

    public void setLoadFriendsList(List<LoadFriend> loadFriendsList) {
        this.loadFriendsList = loadFriendsList;
    }

    public static class LoadFriend {
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
