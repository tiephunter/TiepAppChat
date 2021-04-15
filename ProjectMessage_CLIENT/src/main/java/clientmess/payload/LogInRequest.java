package clientmess.payload;

public class LogInRequest extends BasePayload {

    private String account;
    private String pass;

    public LogInRequest() {
    }

    public LogInRequest(int action, String account, String pass) {
        super(action);
        this.account = account;
        this.pass = pass;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
