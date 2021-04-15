package server.payload;

import java.util.List;

public class LogInRespond extends BasePayload{
    private int state;
    private int idUser;
//    List<LoadFriend> loadFriendsList;

    public LogInRespond() {
    }

//    public LogInRespond(int action, int state, int idUser, List<LoadFriend> loadFriendsList) {
//        super(action);
//        this.state = state;
//        this.idUser = idUser;
//        this.loadFriendsList = loadFriendsList;
//    }
    public LogInRespond(int action, int state, int idUser) {
        super(action);
        this.state = state;
        this.idUser = idUser;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

//    public List<LoadFriend> getLoadFriendsList() {
//        return loadFriendsList;
//    }
//
//    public void setLoadFriendsList(List<LoadFriend> loadFriendsList) {
//        this.loadFriendsList = loadFriendsList;
//    }
//    public static class LoadFriend{
//        private int idUser;
//        private String tenTaiKhoan;
//        private String hoTen;
//
//        public LoadFriend() {
//        }
//
//        public LoadFriend(int idUser, String tenTaiKhoan, String hoTen) {
//            this.idUser = idUser;
//            this.tenTaiKhoan = tenTaiKhoan;
//            this.hoTen = hoTen;
//        }
//
//        public int getIdUser() {
//            return idUser;
//        }
//
//        public void setIdUser(int idUser) {
//            this.idUser = idUser;
//        }
//
//        public String getTenTaiKhoan() {
//            return tenTaiKhoan;
//        }
//
//        public void setTenTaiKhoan(String tenTaiKhoan) {
//            this.tenTaiKhoan = tenTaiKhoan;
//        }
//
//        public String getHoTen() {
//            return hoTen;
//        }
//
//        public void setHoTen(String hoTen) {
//            this.hoTen = hoTen;
//        }
//    }
}
