package clientmess;

import clientmess.payload.ChatGroupRequest;
import clientmess.payload.LoadGroupListRespond;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class GroupFrame {
    JFrame groupListFrame;
    JPanel groupListPanel;
    JPanel btnPanel;
    JButton sessionNameBtn;
    JButton chatBtn;
    JButton backToHome;
    JScrollPane groupSp;
    List<LoadGroupListRespond.Group> groupList;
    final static int CHAT_GROUP_ACTION = 19;

    public GroupFrame(List<LoadGroupListRespond.Group> groupList1) {
        this.groupList = groupList1;

        System.out.println("Group Frame");
        groupListFrame = new JFrame("Group List");
        groupListFrame.setSize(400, 600);
        groupListFrame.setLocationRelativeTo(groupListFrame);
        groupListFrame.setResizable(true);
        groupListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create BtnPanel
        btnPanel = new JPanel();
        btnPanel.setPreferredSize(new Dimension(400, 70));
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        btnPanel.setBackground(new java.awt.Color(205 ,201 ,165));
        //create groupList panel
        groupListPanel = new JPanel();
        groupListPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        groupListPanel.setBackground(new java.awt.Color(205 ,201 ,165));
        groupListPanel.setPreferredSize(new Dimension(400,600));

        //create Scroll pane
        groupSp = new JScrollPane(groupListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        groupSp.setPreferredSize(new Dimension(380, 450));
        //create button back to home
        Icon backBtnIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\backBtnSmall.png");
        backToHome = new JButton(backBtnIcon);
        backToHome.setFocusPainted(false);
        backToHome.setBorderPainted(false);
        backToHome.setBackground(new java.awt.Color(205 ,201 ,165));
        backToHome.setBorder(new RoundedBorder(10));
        backToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupListFrame.setVisible(false);
                AppMessenger.displayLMainFrame();

            }
        });
        //create session name button
        int width = 400;
        int height = 0;
        for (LoadGroupListRespond.Group group : groupList) {
            //
            height += 55;
            JPanel groupPanel = new JPanel();
            groupPanel.setPreferredSize(new Dimension(400, 50));
            groupPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            groupPanel.setBackground(new java.awt.Color(205 ,201 ,165));
            String sessionName = group.getSessionName();
            sessionNameBtn = new JButton(sessionName);
            System.out.println("session name" + sessionName);
            sessionNameBtn.setFocusPainted(false);
            sessionNameBtn.setBorderPainted(false);
//            sessionNameBtn.setBackground(new java.awt.Color(30 ,144 ,255));
            sessionNameBtn.setBackground(new java.awt.Color(139,137,112));
            sessionNameBtn.setBorder(new RoundedBorder(10));
            sessionNameBtn.setPreferredSize(new Dimension(200, 50));
            //create ChatBtn
            Icon ChatBtnIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\chatBtn.png");
            chatBtn = new JButton(ChatBtnIcon);
            chatBtn.setFocusPainted(false);
            chatBtn.setForeground(Color.black);
            chatBtn.setBorderPainted(false);
            chatBtn.setBackground(new java.awt.Color(205 ,201 ,165));
            chatBtn.setBorder(new RoundedBorder(10));
            chatBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        ChatGroupRequest chatGroupRequest = new ChatGroupRequest(CHAT_GROUP_ACTION, group.getIdUser(), group.getIdSession(), group.getSessionName());
                        String json = AppMessenger.mapper.writeValueAsString(chatGroupRequest);
                        AppMessenger.out.writeUTF(json);
                        AppMessenger.out.flush();

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
            groupPanel.add(sessionNameBtn);
            groupPanel.add(chatBtn);
            groupListPanel.add(groupPanel);

        }
        groupListPanel.setPreferredSize(new Dimension(width, height));
        groupListPanel.revalidate();
        groupListPanel.repaint();


        //set Layout cho FrameChat
        groupListFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

        //add component to panel and frame
        btnPanel.add(backToHome);
        groupListFrame.add(btnPanel);
        groupListFrame.add(groupSp);
        groupListFrame.setVisible(true);
        System.out.println("End Group Frame");
    }
//    class RoundedBorder implements Border {
//        int radius;
//        RoundedBorder(int radius) {
//            this.radius = radius;
//        }
//        public Insets getBorderInsets(Component c) {
//            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
//        }
//        public boolean isBorderOpaque() {
//            return true;
//        }
//        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
//        }
//    }
    public void hide() {
        groupListFrame.setVisible(false);
    }
//
//    public static void main(String[] args) {
//        GroupFrame f = new GroupFrame(sa);
//    }
}
