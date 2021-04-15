/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author cmtie
 */
public class Friends {
    private int idUser;
    private String tenTaiKhoan;
    private String hoTen;

    public Friends() {
    }

    public Friends(int idUser, String tenTaiKhoan, String hoTen) {
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
