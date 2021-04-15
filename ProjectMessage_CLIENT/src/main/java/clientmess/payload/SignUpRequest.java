package clientmess.payload;

public class SignUpRequest extends BasePayload {
    String tenTk;
    String tenMk;
    String hoten;
    String ngaysinh;
    int gioitinh;
    String diachi;
    String quequan;
    String email;

    public SignUpRequest() {
    }


    public SignUpRequest(int action, String tenTk, String tenMk, String hoten, String ngaysinh, int gioitinh, String diachi, String quequan, String email) {
        super(action);
        this.tenTk = tenTk;
        this.tenMk = tenMk;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.quequan = quequan;
        this.email = email;
    }

    public String getTenTk() {
        return tenTk;
    }

    public void setTenTk(String tenTk) {
        this.tenTk = tenTk;
    }

    public String getTenMk() {
        return tenMk;
    }

    public void setTenMk(String tenMk) {
        this.tenMk = tenMk;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void hienthi() {
        System.out.println("ten tk " + getTenTk() + "MK la " + getTenMk());
    }
}
