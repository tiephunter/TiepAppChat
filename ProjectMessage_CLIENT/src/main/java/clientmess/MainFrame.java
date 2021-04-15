package clientmess;

import clientmess.payload.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame {
    private int idUser;

    JFrame chatListFrame;
    JPanel othersFunctionPanel;
    JPanel searchFriendPanel;
    JPanel searchPanel;
    JPanel chatListPanel;
    JButton addFriendBtn;
    JButton createGroupBtn;
    JButton signOutBtn;
    JTextField searchChatNameTF;
    JButton searchChatNameBtn;
    JScrollPane loadSessionSP;
    JButton groupBtn;
    JTextField searchFriendTf;
    JButton searchFriendBtn;
    JButton btnChat;
    JLabel labelTenTkFriend;

    final static int LOAD_GROUP_ACTION = 18;

    public MainFrame(int idUser) {

        this.idUser = idUser;
        chatListFrame = new JFrame("Home ");
        chatListFrame.setSize(420, 595);
        chatListFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        chatListFrame.setLocationRelativeTo(chatListFrame);
        chatListFrame.setResizable(true);
        chatListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatListFrame.setBackground(Color.gray);
        chatListFrame.setForeground(Color.gray);
        //panel text chat name
        othersFunctionPanel = new JPanel();
        othersFunctionPanel.setPreferredSize(new Dimension(450, 100));
        othersFunctionPanel.setLayout((new FlowLayout(FlowLayout.LEFT)));
        othersFunctionPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        othersFunctionPanel.setBackground(new java.awt.Color(139,137,112));
        //
        //
        searchFriendPanel = new JPanel();
        searchFriendPanel.setLayout((new FlowLayout()));
        searchFriendPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
        searchFriendPanel.setBackground(new java.awt.Color(255,255,240));
        //
        searchPanel = new JPanel();
        searchPanel.setLayout((new FlowLayout()));
        searchPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        searchPanel.setBackground(new java.awt.Color(255,255,240));
//        // panel Chat Name List
//        chatListPanel = new JPanel();
//        chatListPanel.setPreferredSize(new Dimension(500, 70));
//        chatListPanel.setLayout((new FlowLayout(FlowLayout.LEFT)));
//        chatListPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
//        chatListPanel.setBackground(new java.awt.Color(139,137,112));
        //create sign out button
        Icon logOutIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\logOutBtnSmall.png");
        signOutBtn = new JButton(logOutIcon);
        signOutBtn.setBorderPainted(false);
        signOutBtn.setBackground(new java.awt.Color(139,137,112));
        signOutBtn.setForeground(Color.white);
//        signOutBtn.setBorder(new RoundedBorder(10));
        signOutBtn.setFocusPainted(false);
        signOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bạn có muốn đăng xuât ko?");
                chatListFrame.setVisible(false);
                AppMessenger.displayLogInFrame();
            }
        });
        //Add Friend Button
        Icon addFriendIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\addFriendBtnSmall.png");
        addFriendBtn = new JButton(addFriendIcon);
        addFriendBtn.setBorderPainted(false);
        addFriendBtn.setBackground(new java.awt.Color(139,137,112));
        addFriendBtn.setForeground(Color.black);
//        addFriendBtn.setBorder(new RoundedBorder(10));
        addFriendBtn.setFocusPainted(false);
        addFriendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatListFrame.setVisible(false);
                AppMessenger.displayAddFriendFrame();
            }
        });
        //create group btn
        Icon createGroupIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\createGroupBtnSmall.png");
        createGroupBtn = new JButton(createGroupIcon );
        createGroupBtn.setBorderPainted(false);
        createGroupBtn.setBackground(new java.awt.Color(139,137,112));
        createGroupBtn.setForeground(Color.black);
//        createGroupBtn.setBorder(new RoundedBorder(10));
        createGroupBtn.setFocusPainted(false);
        createGroupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppMessenger.displaySearchFriendListFrameToCreateGroup();
                chatListFrame.setVisible(false);
            }
        });
        //create Group Button
        Icon groupIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\groupBtnSmall.png");
        groupBtn = new JButton(groupIcon);
        groupBtn.setBorderPainted(false);
        groupBtn.setBackground(new java.awt.Color(139,137,112));
        groupBtn.setForeground(Color.black);
//        groupBtn.setBorder(new RoundedBorder(10));
        groupBtn.setFocusPainted(false);
        groupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //List Group
                try {
                    LoadGroupListRequest loadGroupListRequest = new LoadGroupListRequest(LOAD_GROUP_ACTION, idUser);
                    String json = AppMessenger.mapper.writeValueAsString(loadGroupListRequest);
                    AppMessenger.out.writeUTF(json);
                    AppMessenger.out.flush();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        //create text field search chat name and Button
//        searchChatNameTF = new JTextField("", 30);
//        searchChatNameTF.setHorizontalAlignment(JTextField.LEFT);

        // searchChatNamePanel add component
        othersFunctionPanel.add(addFriendBtn);
        othersFunctionPanel.add(createGroupBtn);
        othersFunctionPanel.add(groupBtn);
        othersFunctionPanel.add(signOutBtn);

//        searchFiendListpanel
        searchFriendTf = new JTextField("",22);
        Icon searchIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\searchBtn.png");
        searchFriendBtn = new JButton(searchIcon);
        searchFriendBtn.setBackground(new java.awt.Color(255,255,240));
        searchFriendBtn.setBorderPainted(false);
        searchFriendBtn.setFocusPainted(false);
        searchFriendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SearchFriendListRequest searchFriendListRequest = new SearchFriendListRequest(AppMessenger.SEARCH_FRIEND_LIST_ACTION
                            ,AppMessenger.SEARCH_FRIEND_LIST_ACTION_MAIN_FRAME, AppMessenger.idUser, searchFriendTf.getText());
                    ObjectMapper mapper = new ObjectMapper();
                    String json = mapper.writeValueAsString(searchFriendListRequest);
                    AppMessenger.out.writeUTF(json);
                    AppMessenger.out.flush();
                } catch (Exception e3) {
                    System.out.println(e3 + " Lỗi Search User");
                }
            }
        });
        searchFriendPanel.add(searchFriendTf);
        searchFriendPanel.add(searchFriendBtn);

        //add panel Load User to scroll panel
        loadSessionSP = new JScrollPane(searchPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        loadSessionSP.setPreferredSize(new Dimension(400, 400));
        loadSessionSP.setBackground(Color.white);

        //frame add panel
        chatListFrame.add(othersFunctionPanel);
        chatListFrame.add(searchFriendPanel);
//        chatListFrame.add(searchPanel);
        chatListFrame.add(loadSessionSP);
        chatListFrame.setVisible(true);
        friendListInMainFrameRequest();
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
    //
    public void friendListInMainFrameRequest(){
        try {
            LoadFriendRequest loadFriendRequest = new LoadFriendRequest(AppMessenger.LOAD_FRIEND_IN_MAIN_FRAME,idUser);
            String json = AppMessenger.mapper.writeValueAsString(loadFriendRequest);
            AppMessenger.out.writeUTF(json);
            AppMessenger.out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //
    public void displayFriendList(List<LoadFriendRespond.LoadFriend> loadFriendList) {
        int width = 400;
        int height = 0;
        for (LoadFriendRespond.LoadFriend loadFriend : loadFriendList) {
            height += 55;
            JPanel userPanel = new JPanel();
            userPanel.setPreferredSize(new Dimension(400, 50));
            userPanel.setBackground(new java.awt.Color(255 ,255 ,240));
            System.out.println("Amount Friends " + loadFriendList.size());
            int idFriend = loadFriend.getIdUser();
            String TenTaiKhoanFriend = loadFriend.getTenTaiKhoan();
            String HoTenFriend = loadFriend.getHoTen();
            //set label and button
            System.out.println("searchFriendListFrame");
            System.out.println("TenTaiKhoanFriend" + TenTaiKhoanFriend);
            labelTenTkFriend = new JLabel(TenTaiKhoanFriend);
            labelTenTkFriend.setPreferredSize(new Dimension(200, 40));
            labelTenTkFriend.setBackground(new java.awt.Color(0 ,191 ,255));
//            labelTenTkFriend.setForeground(Color.black);
//            labelTenTkFriend.setBorder(new RoundedBorder(10));
            //create button
            Icon btnChatIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\chatBtn.png");
            btnChat = new JButton(btnChatIcon);
            btnChat.setFocusPainted(false);
            btnChat.setBorderPainted(false);
            btnChat.setBackground(new java.awt.Color(255 ,255 ,240));
            btnChat.setBorder(new RoundedBorder(10));
            btnChat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        chatListFrame.hide();
                        ChatRequest chatRequest = new ChatRequest(AppMessenger.CHAT_ACTION_MAIN, AppMessenger.idUser, idFriend, TenTaiKhoanFriend);
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

            userPanel.add(labelTenTkFriend);
            userPanel.add(btnChat);
            searchPanel.add(userPanel);

        }
        searchPanel.setPreferredSize(new Dimension(width, height));
        searchPanel.revalidate();
        searchPanel.repaint();


    }

    public void displaySearchFriendList(List<SearchFriendListRespond.LoadFriend> loadSearchFriendList){
        searchPanel.removeAll();
        int width = 400;
        int height = 0;
        for (SearchFriendListRespond.LoadFriend loadFriend : loadSearchFriendList) {
            height += 55;
            JPanel userPanel = new JPanel();
            userPanel.setPreferredSize(new Dimension(400, 50));
            userPanel.setBackground(new java.awt.Color(255 ,255 ,240));
            System.out.println("Amount Friends " + loadSearchFriendList.size());
            int idFriend = loadFriend.getIdUser();
            String TenTaiKhoanFriend = loadFriend.getTenTaiKhoan();
            String HoTenFriend = loadFriend.getHoTen();
            //set label and button
            System.out.println("searchFriendListFrame");
            System.out.println("TenTaiKhoanFriend" + TenTaiKhoanFriend);
            labelTenTkFriend = new JLabel(TenTaiKhoanFriend);
            labelTenTkFriend.setPreferredSize(new Dimension(200, 40));
            labelTenTkFriend.setBackground(new java.awt.Color(0 ,191 ,255));
//            labelTenTkFriend.setForeground(Color.black);
//            labelTenTkFriend.setBorder(new RoundedBorder(10));
            //create button
            Icon btnChatIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\chatBtn.png");
            btnChat = new JButton(btnChatIcon);
            btnChat.setFocusPainted(false);
            btnChat.setBorderPainted(false);
            btnChat.setBackground(new java.awt.Color(255 ,255 ,240));
            btnChat.setBorder(new RoundedBorder(10));
            btnChat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        chatListFrame.hide();
                        ChatRequest chatRequest = new ChatRequest(AppMessenger.CHAT_ACTION_MAIN, AppMessenger.idUser, idFriend, TenTaiKhoanFriend);
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

            userPanel.add(labelTenTkFriend);
            userPanel.add(btnChat);
            searchPanel.add(userPanel);

        }
        searchPanel.setPreferredSize(new Dimension(width, height));
        searchPanel.revalidate();
        searchPanel.repaint();

    }

    public static void main(String[] args) {
        MainFrame s = new MainFrame(4);
    }

    public void hide() {
        chatListFrame.setVisible(false);
    }

//    public static void main(String[] args) throws InterruptedException {
//        ChatListFrame chatListFrame = new ChatListFrame();
//        Thread.sleep(5000);
//    }

}

