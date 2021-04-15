package clientmess;

import clientmess.payload.LogInRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInFrame {
    //    final static int LOGIN_ACTION = 2;
    JFrame logInFrame;
    protected JTextField tfAccount;
    protected JPasswordField tfPass;
    JButton btnLogIn;
    JButton btnSignUp;
    JPanel panleLogIn;

    public LogInFrame() {
        logInFrame = new JFrame("Orange Messenger");
        logInFrame.setSize(350, 350);
        logInFrame.setLocationRelativeTo(logInFrame);
        logInFrame.setResizable(true);
        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set panelDangNhap
        panleLogIn = new JPanel();
        //set Layout for panel;
        panleLogIn.setLayout(new BoxLayout(panleLogIn, BoxLayout.Y_AXIS));
        panleLogIn.setBorder(new EmptyBorder(50, 50, 100, 70));
        panleLogIn.setBackground(new java.awt.Color(205 ,201 ,165));
//        panleLogIn.setForeground(Color.black);
        panleLogIn.setBorder(new RoundedBorder(20));
//        panleLogIn.setBackground(Color.GRAY);

        //set label, textfield and button
        JLabel labelOrangeMess = new JLabel("                            ORANGE--MESS             ", JLabel.CENTER);
        labelOrangeMess.setBackground(Color.orange);
        labelOrangeMess.setSize(100, 20);
        JLabel labelTenTaiKhoan = new JLabel("Accout", JLabel.LEFT);
        JLabel labelMatKhau = new JLabel("Password", JLabel.LEFT);
        tfAccount = new JTextField("", JTextField.LEFT);
        tfPass = new JPasswordField("", JPasswordField.LEFT);
        //create Login button
        Icon logInIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\logInBtn.png");
        btnLogIn = new JButton(logInIcon);
//        btnLogIn.setBorderPainted(false);
        btnLogIn.setBackground(new java.awt.Color(205 ,201 ,165));
//        btnLogIn.setForeground(Color.black);
        btnLogIn.setBorder(new RoundedBorder(10));
        btnLogIn.setFocusPainted(false);
        btnLogIn.setBorderPainted(false);
        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Log in action");
                    LogInRequest logInRequest = new LogInRequest();
                    logInRequest.setAction(AppMessenger.LOGIN_ACTION);
                    logInRequest.setAccount(tfAccount.getText());
                    logInRequest.setPass(tfPass.getText());
                    ObjectMapper objectMapper = new ObjectMapper();
                    String json = objectMapper.writeValueAsString(logInRequest);
                    AppMessenger.out.writeUTF(json);
                    AppMessenger.out.flush();
                    System.out.println("Send LogIn Request");
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "cath Nhập sai tài khoản hoặc mật khẩu");
                    System.out.println("Nhập lại đeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                    System.out.println("loi day");
                    System.out.println(e2);
                }

            }
        });
        //create Sign up btn
        Icon signUpIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\createNewAccountBtn.png");
        btnSignUp = new JButton(signUpIcon);
        btnSignUp.setBackground(new java.awt.Color(205 ,201 ,165));
        btnSignUp.setBorderPainted(false);
//        btnSignUp.setForeground(Color.black);
        btnSignUp.setBorder(new RoundedBorder(10));
//        btnSignUp.
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppMessenger.displaySigUpFrame();
                logInFrame.setVisible(false);
            }
        });

        //add component to panel
        panleLogIn.add(labelOrangeMess);
        panleLogIn.add(labelTenTaiKhoan);
        panleLogIn.add(tfAccount);
        panleLogIn.add(labelMatKhau);
        panleLogIn.add(tfPass);
        panleLogIn.add(btnLogIn);
        panleLogIn.add(btnSignUp);



        //add to Frame
        logInFrame.setLayout(new GridLayout(1, 1));
        logInFrame.add(panleLogIn);
        logInFrame.setVisible(true);
    }
    class RoundedBorder implements Border {
        int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }
    public void hide() {
        logInFrame.setVisible(false);
    }
}
