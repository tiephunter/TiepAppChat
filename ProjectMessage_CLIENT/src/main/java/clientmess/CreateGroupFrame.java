package clientmess;

import clientmess.payload.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreateGroupFrame {

    JFrame CreateGroupFrame;
    JPanel btnPanel;
    JTextField searchFriendTf;
    JButton searchFriendBtn;
    JButton backToHome;
    JPanel friendListPanel;
    JPanel createBtnPanel;
    JLabel labelTenTkFriend;
    JButton btnAddToGroup;
    JScrollPane friendGroupSp;
    JButton btnCreateGroup;

    final int LOAD_FRIEND_LIST_ACTION = 7;
    final int LOAD_FRIEND_LIST_ACTION_TO_CREATE_GROUP = 70;
    final static int CREATE_GROUP_ACTION = 17;
    static int idUser =0;
    static String UserName ="";
    ArrayList<Member> memberList ;

    public CreateGroupFrame() {

        System.out.println("");
        //create Frame
        CreateGroupFrame = new JFrame("Create Group ");
        CreateGroupFrame.setSize(400, 630);
        CreateGroupFrame.setLocationRelativeTo(CreateGroupFrame);
        CreateGroupFrame.setResizable(true);
        CreateGroupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set Layout cho FrameChat
        CreateGroupFrame.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        //create panel
        btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        btnPanel.setPreferredSize(new Dimension(400, 150));
        btnPanel.setBorder(new EmptyBorder(0,0,0,20));
        btnPanel.setBackground(new java.awt.Color(139,137,112));
        //
        friendListPanel = new JPanel();
//        friendListPanel.setPreferredSize(new Dimension(380,500));
        friendListPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        friendListPanel.setBackground(new java.awt.Color(205 ,201 ,165));
        //
        friendGroupSp = new JScrollPane(friendListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        friendGroupSp.setPreferredSize(new Dimension(380, 380));
        //create panel
        createBtnPanel = new JPanel();
        createBtnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        createBtnPanel.setPreferredSize(new Dimension(400, 70));
        createBtnPanel.setBorder(new EmptyBorder(0,0,0,20));
        createBtnPanel.setBackground(new java.awt.Color(205 ,201 ,165));
        //create button back to home
        Icon backBtnIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\backBtnSmall.png");
        backToHome = new JButton(backBtnIcon);
        backToHome.setBorderPainted(false);
        backToHome.setFocusPainted(false);
        backToHome.setBackground(new java.awt.Color(139,137,112));
        backToHome.setBorder(new RoundedBorder(10));
        backToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateGroupFrame.setVisible(false);
                AppMessenger.displayLMainFrame();
            }
        });
        //        searchFiendListpanel
        searchFriendTf = new JTextField("",22);
        Icon searchIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\searchBtn.png");
        searchFriendBtn = new JButton(searchIcon);
        searchFriendBtn.setBackground(new java.awt.Color(139,137,112));
        searchFriendBtn.setBorderPainted(false);
        searchFriendBtn.setFocusPainted(false);
        searchFriendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SearchFriendListRequest searchFriendListRequest = new SearchFriendListRequest(AppMessenger.SEARCH_FRIEND_LIST_ACTION
                            ,AppMessenger.SEARCH_FRIEND_LIST_ACTION_CREATE_GROUP, AppMessenger.idUser, searchFriendTf.getText());
                    ObjectMapper mapper = new ObjectMapper();
                    String json = mapper.writeValueAsString(searchFriendListRequest);
                    AppMessenger.out.writeUTF(json);
                    AppMessenger.out.flush();
                } catch (Exception e3) {
                    System.out.println(e3 + " Lỗi Search User");
                }
            }
        });
        btnPanel.add(backToHome);
        btnPanel.add(searchFriendBtn);
        btnPanel.add(searchFriendTf);
        btnPanel.add(backToHome);

        //add component to panel and frame
//        friendListPanel.add(btnCreateGroup);
        CreateGroupFrame.add(btnPanel);
        CreateGroupFrame.add(friendGroupSp);
        CreateGroupFrame.setVisible(true);

    }

    public void hide() {
        CreateGroupFrame.setVisible(false);
    }

    public void displaySearchFriendList(List<SearchFriendListRespond.LoadFriend> loadFriendList){
                    friendListPanel.removeAll();
//        createBtnPanel.removeAll();
//        idUser = loadFriendGroupRespond.getIdUser();
//        UserName = loadFriendGroupRespond.getAccountName();
//        ArrayList<Member> memberList = new ArrayList<>();
//        Member member = new Member(idUser, UserName);
//        memberList.add(member);
        List<SearchFriendListRespond.LoadFriend> loadFriendList1= loadFriendList;
        int width = 380;
        int height = 0;

        for (SearchFriendListRespond.LoadFriend loadFriend : loadFriendList1) {
            //
            height += 55;
            System.out.println("Amount Friends " + loadFriendList.size());
            int idFriend = loadFriend.getIdUser();
            String TenTaiKhoanFriend = loadFriend.getTenTaiKhoan();
            String HoTenFriend = loadFriend.getHoTen();
            //
            JPanel friendPanel = new JPanel();
            friendPanel.setPreferredSize(new Dimension(380, 50));
            friendPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            friendPanel.setBackground(new java.awt.Color(205 ,201 ,165));
            //set label and button
            System.out.println("Create Group");
            System.out.println("TenTaiKhoanFriend" + TenTaiKhoanFriend);
            labelTenTkFriend = new JLabel(TenTaiKhoanFriend);
            labelTenTkFriend.setBackground(Color.white);
            labelTenTkFriend.setBorder(new RoundedBorder(10));
            labelTenTkFriend.setPreferredSize(new Dimension(200, 50));
            //action create group button
            Icon addGroupBtnIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\addToGroup.png");
            JButton btnAddToGroup = new JButton(addGroupBtnIcon);
            btnAddToGroup.setSize(20, 20);
            btnAddToGroup.setBackground(new java.awt.Color(205 ,201 ,165));
            btnAddToGroup.setBorderPainted(false);
//            btnAddToGroup.setFocusPainted(false);
            btnAddToGroup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
//                        btnAddToGroup.setEnabled(false);
                        Member member = new Member(idFriend, TenTaiKhoanFriend);
                        memberList.add(member);
                        btnAddToGroup.setEnabled(false);
//                        friendPanel.remove(btnAddToGroup);

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
            });
            friendPanel.add(labelTenTkFriend);
            friendPanel.add(btnAddToGroup);
            friendListPanel.add(friendPanel);
        }

        friendListPanel.setPreferredSize(new Dimension(width, height));
        friendListPanel.revalidate();
        friendListPanel.repaint();
        //create group button
        Icon createGroupBtn = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\createGroupBtnSmall.png");
        btnCreateGroup = new JButton(createGroupBtn);
        btnCreateGroup.setBackground( new java.awt.Color(205 ,201 ,165));
        btnCreateGroup.setBorderPainted(false);
        btnCreateGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CreateGroupRequest createGroupRequest = new CreateGroupRequest(CREATE_GROUP_ACTION, memberList);
                    String json = AppMessenger.mapper.writeValueAsString(createGroupRequest);
                    AppMessenger.out.writeUTF(json);
                    AppMessenger.out.flush();
                    System.out.println("gửi yêu cầu add gr");
                } catch (Exception e1) {
                    System.out.println("Exception chat group");
                    e1.printStackTrace();
                }
            }
        });

        createBtnPanel.add(btnCreateGroup);
//        CreateGroupFrame.add(createBtnPanel);
//        friendListPanel.add(btnCreateGroup);

//        friendListPanel.add(btnCreateGroup);
//            friendListPanel.updateUI();
//            CreateGroupFrame.add(friendListPanel);
//        CreateGroupFrame.setVisible(true);
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

    public void displayCreate(LoadFriendGroupRespond loadFriendGroupRespond) {
        //fetch data
//            JButton btnCreateGroup;
//            friendListPanel.removeAll();

        idUser = loadFriendGroupRespond.getIdUser();
        UserName = loadFriendGroupRespond.getAccountName();
        memberList = new ArrayList<>();
        Member member = new Member(idUser, UserName);
        memberList.add(member);
        List<LoadFriendGroupRespond.LoadFriend> loadFriendList = loadFriendGroupRespond.getLoadFriendList();
        int width = 380;
        int height = 0;

        for (LoadFriendGroupRespond.LoadFriend loadFriend : loadFriendList) {
            //
            height += 55;
            System.out.println("Amount Friends " + loadFriendList.size());
            int idFriend = loadFriend.getIdFriend();
            String TenTaiKhoanFriend = loadFriend.getAccountName();
            String HoTenFriend = loadFriend.getFriendName();
            //
            JPanel friendPanel = new JPanel();
            friendPanel.setPreferredSize(new Dimension(380, 50));
            friendPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            friendPanel.setBackground(new java.awt.Color(205 ,201 ,165));
            //set label and button
            System.out.println("Create Group");
            System.out.println("TenTaiKhoanFriend" + TenTaiKhoanFriend);
            labelTenTkFriend = new JLabel(TenTaiKhoanFriend);
            labelTenTkFriend.setBackground(Color.white);
            labelTenTkFriend.setBorder(new RoundedBorder(10));
            labelTenTkFriend.setPreferredSize(new Dimension(200, 50));
            //action create group button
            Icon addGroupBtnIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\addToGroup.png");
            JButton btnAddToGroup = new JButton(addGroupBtnIcon);
            btnAddToGroup.setSize(20, 20);
            btnAddToGroup.setBackground(new java.awt.Color(205 ,201 ,165));
            btnAddToGroup.setBorderPainted(false);
//            btnAddToGroup.setFocusPainted(false);
            btnAddToGroup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
//                        btnAddToGroup.setEnabled(false);
                        Member member = new Member(idFriend, TenTaiKhoanFriend);
                        memberList.add(member);
                        btnAddToGroup.setEnabled(false);
//                        friendPanel.remove(btnAddToGroup);

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
            });
            friendPanel.add(labelTenTkFriend);
            friendPanel.add(btnAddToGroup);
            friendListPanel.add(friendPanel);
        }

        friendListPanel.setPreferredSize(new Dimension(width, height));
        friendListPanel.revalidate();
        friendListPanel.repaint();
        //create group button
        Icon createGroupBtn = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\createGroupBtnSmall.png");
        btnCreateGroup = new JButton(createGroupBtn);
        btnCreateGroup.setBackground( new java.awt.Color(205 ,201 ,165));
        btnCreateGroup.setBorderPainted(false);
        btnCreateGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CreateGroupRequest createGroupRequest = new CreateGroupRequest(CREATE_GROUP_ACTION, memberList);
                    String json = AppMessenger.mapper.writeValueAsString(createGroupRequest);
                    AppMessenger.out.writeUTF(json);
                    AppMessenger.out.flush();
                    System.out.println("gửi yêu cầu add gr");
                } catch (Exception e1) {
                    System.out.println("Exception chat group");
                    e1.printStackTrace();
                }
            }
        });

        createBtnPanel.add(btnCreateGroup);
        CreateGroupFrame.add(createBtnPanel);
//        friendListPanel.add(btnCreateGroup);

//        friendListPanel.add(btnCreateGroup);
//            friendListPanel.updateUI();
//            CreateGroupFrame.add(friendListPanel);
        CreateGroupFrame.setVisible(true);

    }
    public static void main(String[] args) {

        LoadFriendGroupRespond loadFriendGroupRespond = new LoadFriendGroupRespond();
        loadFriendGroupRespond.setIdUser(1);
        loadFriendGroupRespond.setAccountName("sf");
        loadFriendGroupRespond.setLoadFriendList(new ArrayList<>());
        for (int i=0; i<142; i++){
            LoadFriendGroupRespond.LoadFriend loadFriend = new LoadFriendGroupRespond.LoadFriend(1,""+i,"dsf");
            loadFriendGroupRespond.getLoadFriendList().add(loadFriend);
        }

        CreateGroupFrame createGroupFrame = new CreateGroupFrame();
        createGroupFrame.displayCreate(loadFriendGroupRespond);
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