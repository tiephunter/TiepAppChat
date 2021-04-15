package clientmess;

import clientmess.payload.ChatRequest;
import clientmess.payload.LoadFriendRequest;
import clientmess.payload.LoadFriendRespond;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FriendListFrame {
    JFrame searchFriendListFrame;
    JPanel searchFriendListPanel;
    JPanel panelButton;
    JScrollPane spLoadUser;
    JButton backToHome;
    JButton btnChat;
    final int LOAD_FRIEND_LIST_ACTION = 7;
    final int LOAD_FRIEND_LIST_ACTION_TO_CREATE_GROUP = 70;
    final int CHAT_ACTION = 10;

    public FriendListFrame() {

        System.out.println("search Friends");
        searchFriendListFrame = new JFrame("Friend List");
        searchFriendListFrame.setSize(400, 600);
        searchFriendListFrame.setLocationRelativeTo(searchFriendListFrame);
        searchFriendListFrame.setResizable(true);
        searchFriendListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        searchFriendListFrame.setBackground(new java.awt.Color(0 ,191 ,255));
//        searchFriendListFrame.setForeground(new java.awt.Color(0 ,191 ,255));
        //
        //create PanelHome and panel Search Friend
        panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(400, 50));
        panelButton.setLayout((new FlowLayout(FlowLayout.LEFT)));
        panelButton.setBackground(new java.awt.Color(205 ,201 ,165));
//        panelButton.setForeground(new java.awt.Color(0 ,191 ,255));
        //create button back to home
        Icon backIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\backBtnSmall.png");
        backToHome = new JButton(backIcon);
        backToHome.setBorderPainted(false);
        backToHome.setFocusPainted(false);
        backToHome.setBackground(new java.awt.Color(205 ,201 ,165));
//        backToHome.setForeground(new java.awt.Color(0 ,191 ,255));
        backToHome.setBorder(new RoundedBorder(10));
        backToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFriendListFrame.setVisible(false);
                AppMessenger.displayAddFriendFrame();
            }
        });
        //create panel
        searchFriendListPanel = new JPanel();
        searchFriendListPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchFriendListPanel.setBackground(new java.awt.Color(205 ,201 ,165));

        //
        spLoadUser = new JScrollPane(searchFriendListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        spLoadUser.setPreferredSize(new Dimension(380, 450));


        //add component to panel and frame
        panelButton.add(backToHome);
        //set Layout cho FrameChat
        searchFriendListFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchFriendListFrame.add(panelButton);
        searchFriendListFrame.add(spLoadUser);
        searchFriendListFrame.setVisible(true);

    }

    public void displayFriendList(List<LoadFriendRespond.LoadFriend> loadFriendList) {
        int width = 400;
        int height = 0;
        for (LoadFriendRespond.LoadFriend loadFriend : loadFriendList) {
            height += 50;
            System.out.println("Amount Friends " + loadFriendList.size());
            int idFriend = loadFriend.getIdUser();
            String TenTaiKhoanFriend = loadFriend.getTenTaiKhoan();
            String HoTenFriend = loadFriend.getHoTen();
            //set label and button
            System.out.println("searchFriendListFrame");
            System.out.println("TenTaiKhoanFriend" + TenTaiKhoanFriend);
            JLabel labelTenTkFriend = new JLabel(TenTaiKhoanFriend);
            labelTenTkFriend.setPreferredSize(new Dimension(200, 50));
            labelTenTkFriend.setBackground(new java.awt.Color(139 ,137 ,112));
//            labelTenTkFriend.setForeground(Color.black);
            labelTenTkFriend.setBorder(new RoundedBorder(10));
            //create button
            Icon chatIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\chatBtn.png");
            btnChat = new JButton(chatIcon);
            btnChat.setFocusPainted(false);
            btnChat.setBorderPainted(false);
            btnChat.setForeground(Color.black);
            btnChat.setBackground(new java.awt.Color(205 ,201 ,165));
//            btnChat.setBorder(new RoundedBorder(10));
            btnChat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ChatRequest chatRequest = new ChatRequest(CHAT_ACTION, AppMessenger.idUser, idFriend, TenTaiKhoanFriend);
                        String json = AppMessenger.mapper.writeValueAsString(chatRequest);
                        AppMessenger.out.writeUTF(json);
                        AppMessenger.out.flush();

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
            });
            //add to panelChat
            //create panel

            searchFriendListPanel.add(labelTenTkFriend);
            searchFriendListPanel.add(btnChat);

        }
        searchFriendListPanel.setPreferredSize(new Dimension(width, height));
        searchFriendListPanel.revalidate();
        searchFriendListPanel.repaint();


    }

    public void hide() {
        searchFriendListFrame.setVisible(false);
    }

    public void sendData() {
        //output data
        try {
            LoadFriendRequest load_friend_request = new LoadFriendRequest(LOAD_FRIEND_LIST_ACTION, AppMessenger.idUser);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(load_friend_request);
            AppMessenger.out.writeUTF(json);
            AppMessenger.out.flush();
        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("Lỗi search Friend List");
        }
    }


    public void sendDataToCreateGroup() {
        //output data
        try {
            LoadFriendRequest load_friend_request = new LoadFriendRequest(LOAD_FRIEND_LIST_ACTION_TO_CREATE_GROUP, AppMessenger.idUser);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(load_friend_request);
            AppMessenger.out.writeUTF(json);
            AppMessenger.out.flush();
        } catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("Lỗi search Friend List");
        }
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
    public static void main(String[] args) {
        FriendListFrame friendListFrame = new FriendListFrame();
        List<LoadFriendRespond.LoadFriend> loadFriendList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            LoadFriendRespond.LoadFriend loadFriend = new LoadFriendRespond.LoadFriend(1, "ds", "sdf");
            loadFriendList.add(loadFriend);
        }
        friendListFrame.displayFriendList(loadFriendList);
    }
}
