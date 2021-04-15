package clientmess;

import clientmess.payload.AddFriendRequest;
import clientmess.payload.LoadUserRequest;
import clientmess.payload.LoadUserRespond;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static clientmess.AppMessenger.ADD_FRIEND_ACTION;
import static clientmess.AppMessenger.idUser;

public class AddFriendFrame {
    final static int LOAD_USER_lIST_ACTION = 5;
    final static int LOAD_FRIEND_LIST_ACTION = 7;
    JFrame AddFriendFrame;
    JPanel panelButton;
    JPanel panelSearchUser;
    JButton btnSearchFriendList;
    JScrollPane spLoadUser;
    JButton backToHome;
    JButton btnAdd;
    JButton btnSearchUser;
    JTextField tfSearch;


    public AddFriendFrame() {
        AddFriendFrame = new JFrame("Add Friend");
        AddFriendFrame.setSize(400, 600);
        AddFriendFrame.setLocationRelativeTo(AddFriendFrame);
        AddFriendFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        AddFriendFrame.setResizable(true);
        AddFriendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create PanelHome and panel Search Friend
        panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(400, 120));
        panelButton.setLayout((new FlowLayout(FlowLayout.LEFT)));
        panelButton.setBorder(new EmptyBorder(0, 15, 30, 15));
        panelButton.setBackground(new java.awt.Color(205 ,201 ,165));
        // panel Search Friend
        panelSearchUser = new JPanel();
        panelSearchUser.setLayout(new FlowLayout());
//        panelSearchUser.setBorder(new EmptyBorder(30, 0, 0, 0));
        panelSearchUser.setBackground(new java.awt.Color(255,255,240));

        JLabel lbMsg;
        Icon backBtnIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\backBtnSmall.png");
        backToHome = new JButton(backBtnIcon);
        backToHome.setBorderPainted(false);
        backToHome.setFocusPainted(false);
        backToHome.setForeground(Color.white);
        backToHome.setBackground(new java.awt.Color(205 ,201 ,165));
        backToHome.setBorder(new RoundedBorder(10));
        backToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFriendFrame.setVisible(false);
                AppMessenger.displayLMainFrame();

            }
        });
        Icon searchFriendListBtnIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\searchFriendListBtn.png");
        btnSearchFriendList = new JButton(searchFriendListBtnIcon);
        btnSearchFriendList.setBorderPainted(false);
        btnSearchFriendList.setFocusPainted(false);
//        btnSearchFriendList.setForeground(Color.black);
        btnSearchFriendList.setBackground(new java.awt.Color(205 ,201 ,165));
        btnSearchFriendList.setBorder(new RoundedBorder(5));
        btnSearchFriendList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppMessenger.displaySearchFriendListFrame();
                AddFriendFrame.setVisible(false);
            }
        });
        //create tf and btn
        tfSearch = new JTextField("", 20);
        Icon searchBtnIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\searchBtn.png");
        btnSearchUser = new JButton(searchBtnIcon);
        btnSearchUser.setFocusPainted(false);
        btnSearchUser.setBorderPainted(false);
        btnSearchUser.setForeground(Color.black);
        btnSearchUser.setBackground(new java.awt.Color(205 ,201 ,165));
        btnSearchUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LoadUserRequest loadUserRequest = new LoadUserRequest(LOAD_USER_lIST_ACTION, AppMessenger.idUser, tfSearch.getText());
                    ObjectMapper mapper = new ObjectMapper();
                    String json = mapper.writeValueAsString(loadUserRequest);
                    AppMessenger.out.writeUTF(json);
                    AppMessenger.out.flush();
                } catch (Exception e3) {
                    System.out.println(e3 + " Lỗi Search User");
                }
            }
        });
        //add component to panel home
        panelButton.add(backToHome);
        panelButton.add(btnSearchFriendList);
//        panelSearchUser.add(panelButton);
        panelButton.add(tfSearch);
        panelButton.add(btnSearchUser);
        //add panel Load User to scroll panel

        spLoadUser = new JScrollPane(panelSearchUser, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        spLoadUser.setPreferredSize(new Dimension(380, 400));
//        spLoadUser.setForeground(Color.black);
//        spLoadUser.setBackground(new java.awt.Color(0 ,191 ,255));


        //add component to frame home
        AddFriendFrame.add(panelButton);
        AddFriendFrame.add(spLoadUser);
        AddFriendFrame.setVisible(true);
    }

    public void displayUserList(List<LoadUserRespond.LoadUser> loadUserList) {
        panelSearchUser.removeAll();
        int width = 400;
        int height = 0;
        for (LoadUserRespond.LoadUser loadUser : loadUserList) {
            //create user panel
            height += 55;
            JPanel userPanel = new JPanel();
            userPanel.setPreferredSize(new Dimension(400, 50));
            userPanel.setBackground(new java.awt.Color(255 ,255 ,240));
            //create label
            JLabel userNameLabel = new JLabel(loadUser.getNameFriend());
            userNameLabel.setPreferredSize(new Dimension(200, 50));
            //Create add Friend Button
            Icon addFriendBtnIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\addFriendBtn.png");
            JButton btnAdd = new JButton(addFriendBtnIcon);
            btnAdd.setBorderPainted(false);
            btnAdd.setBackground(new java.awt.Color(255 ,255 ,240));
            btnAdd.setBorder(new RoundedBorder(5));
            //set button
            btnAdd.setFocusPainted(false);
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        btnAdd.setEnabled(false);
                        AddFriendRequest addFriendRequest = new AddFriendRequest(ADD_FRIEND_ACTION, idUser, loadUser.getIdFriend(), loadUser.getNameFriend());
                        String json = AppMessenger.mapper.writeValueAsString(addFriendRequest);
                        AppMessenger.out.writeUTF(json);
                        AppMessenger.out.flush();

                    } catch (Exception e4) {
                        System.out.println(e4 + "Lỗi kết bạn");
                    }
                }
            });
            userPanel.add(userNameLabel);
            userPanel.add(btnAdd);
            panelSearchUser.add(userPanel);
        }

        panelSearchUser.setPreferredSize(new Dimension(width, height));
        panelSearchUser.revalidate();
        panelSearchUser.repaint();
//        Rectangle viewBounds = spLoadUser.getViewportBorderBounds();
//        panelSearchUser.scrollRectToVisible(new Rectangle(0,height,viewBounds.width,viewBounds.height));

    }

    public void hide() {
        AddFriendFrame.setVisible(false);
    }

    public static void main(String[] args) {
        AddFriendFrame addFriendFrame = new AddFriendFrame();
        List<LoadUserRespond.LoadUser> loadUsers = new ArrayList<>();
        for (int i = 0; i < 210; i++) {
            loadUsers.add(new LoadUserRespond.LoadUser(i, "" + i));
        }

        addFriendFrame.displayUserList(loadUsers);


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
}

