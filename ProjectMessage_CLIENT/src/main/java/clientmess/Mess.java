package clientmess;

public class Mess {
    int signal;
    int sessionId;
    String message;
    int idUser;
    int idFriend;

    public Mess() {

    }

    public Mess(int signal, int sessionId, String message, int idUser, int idFriend) {
        this.signal = signal;
        this.sessionId = sessionId;
        this.message = message;
        this.idUser = idUser;
        this.idFriend = idFriend;
    }

}
