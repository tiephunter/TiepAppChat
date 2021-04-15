package clientmess;

import clientmess.payload.SignUpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SigUpFrame {
    JFrame sigUpFrame;
    JPanel panelBody;
    JTextField tfTenTK;
    JTextField tfMK;
    JTextField tfHoTen;
    JTextField tfNgaySinh;
    JTextField tfGioiTinh;
    JTextField tfDiaChi;
    JTextField tfQueQuan;
    JTextField tfEmail;
    JPanel panelPrf;
    public SigUpFrame() {
        sigUpFrame = new JFrame("Form Đăng kí Tai Khoan");
        sigUpFrame.setSize(400, 600);
        sigUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sigUpFrame.setLocationRelativeTo(null);
        sigUpFrame.setResizable(true);
        sigUpFrame.setForeground(new java.awt.Color(205 ,201 ,165));

        //set layout for panel profile
        //set Layout for body
//        panelBody = new JPanel();
//        panelBody.setLayout(new FlowLayout());
//        panelBody.setBorder(new EmptyBorder(20, 0, 20, 0));
//        panelBody.setBackground(new java.awt.Color(205 ,201 ,165));

        panelPrf = new JPanel();
        BoxLayout boxPanelPrf = new BoxLayout(panelPrf, BoxLayout.Y_AXIS);
        panelPrf.setLayout(new FlowLayout());
        panelPrf.setBorder(new EmptyBorder(50, 0, 0, 0));
        panelPrf.setBackground(new java.awt.Color(205 ,201 ,165));


        JLabel labelPrf = new JLabel("                           Thông tin đăng kí          ");
        JLabel labelhr = new JLabel("                                               ----------              ");
        JLabel labelTenTK = new JLabel("Tên Tài Khoản", JLabel.LEFT);
        JLabel labelMK = new JLabel("Mật Khẩu", JLabel.LEFT);
        JLabel labelHoTen = new JLabel("Họ Tên", JLabel.LEFT);
        JLabel labelNgaySinh = new JLabel("Ngày Sinh", JLabel.LEFT);
        JLabel labelGioiTinh = new JLabel("Giới Tính", JLabel.LEFT);
        JLabel labelDiaChi = new JLabel("Địa Chỉ", JLabel.LEFT);
        JLabel labelQueQuan = new JLabel("Quê Quán", JLabel.LEFT);
        JLabel labelEmail = new JLabel("Email", JLabel.LEFT);
        labelPrf.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelTenTK.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelMK.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelHoTen.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelQueQuan.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        labelDiaChi.setFont(new Font("Tahoma", Font.BOLD, 12));

        //set text field
        tfTenTK = new JTextField("", 15);
        tfMK = new JTextField("", 15);
        tfHoTen = new JTextField("", 15);
        tfNgaySinh = new JTextField("", 15);
        tfGioiTinh = new JTextField("", 15);
        JLabel sexLb = new JLabel("Notice: Male = 0; Female = 1");
        tfDiaChi = new JTextField("", 15);
        tfQueQuan = new JTextField("", 15);
        tfEmail = new JTextField("", 15);

        //create button Sing up
        Icon signUpBtn = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\signUpBtn.png");
        JButton btnDky = new JButton(signUpBtn);
        btnDky.setBackground(new java.awt.Color(205 ,201 ,165));
        btnDky.setForeground(Color.white);
        btnDky.setBorderPainted(false);
        btnDky.setFocusPainted(false);
        btnDky.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SignUpRequest signUpRequest = new SignUpRequest();
                    signUpRequest.setAction(AppMessenger.SIGNUP_ACTION);
                    signUpRequest.setTenTk(tfTenTK.getText());
                    signUpRequest.setTenMk(tfMK.getText());
                    signUpRequest.setHoten(tfHoTen.getText());
                    signUpRequest.setNgaysinh(tfNgaySinh.getText());
                    signUpRequest.setGioitinh(Integer.parseInt(tfGioiTinh.getText()));
                    signUpRequest.setDiachi(tfDiaChi.getText());
                    signUpRequest.setQuequan(tfQueQuan.getText());
                    signUpRequest.setEmail(tfEmail.getText());
                    signUpRequest.hienthi();
                    ObjectMapper mapper = new ObjectMapper();
                    String json = mapper.writeValueAsString(signUpRequest);
                    AppMessenger.out.writeUTF(json);
                    AppMessenger.out.flush();
                    System.out.println("send sign up action");

                } catch (IOException e1) {
                    System.err.println(e1);
                    System.out.println("ko đọc hết đc");
                }

            }
        });

        //create button back to menu
        Icon backBtn = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\backBtn.png");
        JButton btnBack = new JButton(backBtn);
        btnBack.setBackground(new java.awt.Color(205 ,201 ,165));
        btnBack.setForeground(Color.white);
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sigUpFrame.setVisible(false);
                AppMessenger.displayLogInFrame();
            }
        });
        //panelPrf add label and textfield
        panelPrf.add(labelPrf);
        panelPrf.add(labelhr);
        panelPrf.add(labelTenTK);
        panelPrf.add(tfTenTK);
        panelPrf.add(labelMK);
        panelPrf.add(tfMK);
        panelPrf.add(labelHoTen);
        panelPrf.add(tfHoTen);
        panelPrf.add(labelNgaySinh);
        panelPrf.add(tfNgaySinh);
        panelPrf.add(labelGioiTinh);
        panelPrf.add(tfGioiTinh);
        panelPrf.add(sexLb);
        panelPrf.add(labelDiaChi);
        panelPrf.add(tfDiaChi);
        panelPrf.add(labelQueQuan);
        panelPrf.add(tfQueQuan);
        panelPrf.add(labelEmail);
        panelPrf.add(tfEmail);
        panelPrf.add(btnDky);
        panelPrf.add(btnBack);

        //set Layout for panel
        panelPrf.setLayout(new BoxLayout(panelPrf, BoxLayout.Y_AXIS));
        //frame add content
//        panelBody.add(panelPrf);
        sigUpFrame.setLayout(new FlowLayout());
        sigUpFrame.add(panelPrf);
        sigUpFrame.setVisible(true);

    }

    public void hide() {
        sigUpFrame.setVisible(false);
    }
}
