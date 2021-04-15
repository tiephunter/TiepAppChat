package clientmess;

import clientmess.payload.ChatGroupRespond;
import clientmess.payload.ChatMessage;
import clientmess.payload.SendGroupMessageRequest;
import clientmess.payload.SendMessgeGroupRespond;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GroupConversationFrame {
    JFrame frameConversation;
    JPanel panelWhole;
    JPanel panelBackBtn;
    JScrollPane spChat;
    JPanel panelConversation;
    JPanel panelTextChat;
    JTextField tfInputMessage;
    JLabel lbReceivedMessage;
    JButton backToHome;
    JButton btnSend;
    JFileChooser fileDialog;
    Mess message;
     int height = 0;
     int width = 380;

    public GroupConversationFrame(ChatGroupRespond chatGroupRespond) {
        try {
            //read data from server
            int idUser = chatGroupRespond.getIdUser();
            List<ChatGroupRespond.IdMember> idMemberList = chatGroupRespond.getIdMembersList();
            List<SendGroupMessageRequest.IdMember> idMemberList1 = new ArrayList<>();
            for (ChatGroupRespond.IdMember idMember : idMemberList) {
                int id = idMember.getIdMember();

                SendGroupMessageRequest.IdMember idMbSend = new SendGroupMessageRequest.IdMember(id);
                idMemberList1.add(idMbSend);
            }
            String sessionName = chatGroupRespond.getSessionName();
            frameConversation = new JFrame(sessionName);
            frameConversation.setSize(420, 620);
            frameConversation.setLocationRelativeTo(frameConversation);
            frameConversation.setResizable(true);
            frameConversation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //
            panelWhole = new JPanel();
            panelWhole.setLayout(new FlowLayout(FlowLayout.LEFT));
            panelWhole.setPreferredSize(new Dimension(450, 650));
            panelWhole.setBorder(new EmptyBorder(0,30,0,0));
            panelWhole.setBackground(new java.awt.Color(205 ,201 ,165));
            //
            //panel Back button
            panelBackBtn = new JPanel();
            panelBackBtn.setLayout(new FlowLayout(FlowLayout.LEFT));
            panelBackBtn.setPreferredSize(new Dimension(400, 50));
            panelBackBtn.setBackground(new java.awt.Color(205 ,201 ,165));
            //create panelConversation
            panelConversation = new JPanel();
            panelConversation.setLayout(new FlowLayout());
            panelConversation.setBackground(new java.awt.Color(255 ,255 ,240));
            //create JscrollpaneChat
            spChat = new JScrollPane(panelConversation, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            spChat.setPreferredSize(new Dimension(380, 450));
            //create panelTextChat
            panelTextChat = new JPanel();
            panelTextChat.setLayout(new FlowLayout());
            panelTextChat.setPreferredSize(new Dimension(380,50));
            panelTextChat.setBackground(new java.awt.Color(205 ,201 ,165));
            //create button back to home
            Icon backIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\backBtnSmall.png");
            backToHome = new JButton(backIcon);
            backToHome.setBorderPainted(false);
            backToHome.setFocusPainted(false);
            backToHome.setBackground(new java.awt.Color(205 ,201 ,165));
            backToHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frameConversation.setVisible(false);
                    AppMessenger.displayLMainFrame();
                }
            });
            panelBackBtn.add(backToHome);
            //read data from server
            int sessionId = chatGroupRespond.getSessionId();
            int amountMessage = chatGroupRespond.getMessageList().size();
            List<ChatMessage> listMessage = chatGroupRespond.getMessageList();
            System.out.println("List Message" + listMessage);
            //count Messsager
//            int width = 400;
//            int height = 0;
            for (int i = 0; i < amountMessage; i++) {
                height += 55;
                ChatMessage chatMessage = listMessage.get(i);
                int idMsg = chatMessage.getIdMsg();
                String textMsg = chatMessage.getTextMsg();
                int idSender = chatMessage.getIdSender();
                String userName = chatMessage.getUserName();

                if (idUser == idSender) {
                    //create panel
                    JPanel panelText = new JPanel();
                    panelText.setLayout(new FlowLayout(FlowLayout.RIGHT));
                    panelText.setPreferredSize(new Dimension(380, 50));
                    panelText.setBorder(new EmptyBorder(0, 0, 0, 15));
                    panelText.setBackground(new java.awt.Color(255, 255 ,240));
                    //
                    JButton lbMsg = new JButton(textMsg);
                    lbMsg.setPreferredSize(new Dimension(200, 30));
                    lbMsg.setFocusPainted(false);
//                    lbMsg.setEnabled(false);
                    lbMsg.setBackground(new java.awt.Color(139 ,137 ,112));
//                    lbMsg.setForeground(Color.black);
                    lbMsg.setBorder(new RoundedBorder(20));

//                  add to panel;
                    panelText.add(lbMsg);
                    panelConversation.add(panelText);
                } else {
                    //
                    JPanel panelText = new JPanel();
                    panelText.setLayout(new FlowLayout(FlowLayout.LEFT));
                    panelText.setPreferredSize(new Dimension(380, 50));
                    panelText.setBackground(new java.awt.Color(255, 255 ,240));
                    panelText.setBorder(new EmptyBorder(0, 0, 0, 0));
//                    panelText.setForeground(Color.blue);
                    //
                    JLabel userNameLabel = new JLabel(userName+":");
                    userNameLabel.setPreferredSize(new Dimension(380,10));
                    JButton msgBtn = new JButton(textMsg);
                    msgBtn.setPreferredSize(new Dimension(200, 30));
                    msgBtn.setFocusPainted(false);
//                    msgBtn.setEnabled(false);
                    msgBtn.setBackground(new java.awt.Color(139 ,137, 112));
                    msgBtn.setForeground(Color.black);
                    msgBtn.setBorder(new RoundedBorder(10));

//                    msgBtn.setHorizontalAlignment(JLabel.LEFT);
                    panelText.add(userNameLabel);
                    panelText.add(msgBtn);
                    panelConversation.add(panelText);
                }
            }
            panelConversation.setPreferredSize(new Dimension(width, height));
            panelConversation.revalidate();
            panelConversation.repaint();
            Rectangle viewBounds = spChat.getViewportBorderBounds();
            panelConversation.scrollRectToVisible(new Rectangle(0, height, viewBounds.width, viewBounds.height));

            //create tfMessage and BtnSend
            JTextArea tfInputMessage = new JTextArea();
            JScrollPane areaScrollPane = new JScrollPane(tfInputMessage);
            areaScrollPane.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            areaScrollPane.setPreferredSize(new Dimension(250, 50));
            //
            Icon sendIcon = new ImageIcon("D:\\OOP\\code\\ProjectMessage_CLIENT\\image\\sendBtn.png");
            btnSend  = new JButton(sendIcon);
            btnSend.setFocusPainted(false);
            btnSend.setBorderPainted(false);
            btnSend.setBackground(new java.awt.Color(205 ,201 ,165));
            btnSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //create panel
                        if (checkEmpty(tfInputMessage.getText()) == true) {
                            JPanel panelText = new JPanel();
                            panelText.setLayout(new FlowLayout(FlowLayout.RIGHT));
                            panelText.setPreferredSize(new Dimension(380, 50));
                            panelText.setBorder(new EmptyBorder(0, 0, 0, 15));
                            panelText.setBackground(Color.white);
                            //
                            System.out.println("sesion id " + sessionId);
                            SendGroupMessageRequest sendGroupMessageRequest = new SendGroupMessageRequest(AppMessenger.SEND_MESSAGE_GROUP_ACTION, sessionId,
                                    tfInputMessage.getText(), idUser, idMemberList1);
                            String json = AppMessenger.mapper.writeValueAsString(sendGroupMessageRequest);
                            AppMessenger.out.writeUTF(json);
                            AppMessenger.out.flush();
                            //creatte button to hold text
                            JButton msgBtn = new JButton(tfInputMessage.getText());
                            msgBtn.setPreferredSize(new Dimension(200, 30));
                            msgBtn.setFocusPainted(false);
//                    msgBtn.setEnabled(false);
                            msgBtn.setBackground(new java.awt.Color(139 ,137 ,112));
                            msgBtn.setForeground(Color.black);
                            msgBtn.setBorder(new RoundedBorder(20));
                            panelText.add(msgBtn);
                            panelConversation.add(panelText);
                            //
                            tfInputMessage.setText("");
//                            panelConversation.updateUI();
                            Rectangle viewBounds = spChat.getViewportBorderBounds();
                            panelConversation.setPreferredSize(new Dimension(width, height += 55));
                            panelConversation.revalidate();
                            panelConversation.repaint();
                            panelConversation.scrollRectToVisible(new Rectangle(0, height, viewBounds.width, viewBounds.height));
                        }
                        else{
                            System.out.println("Khong cho nhap rong");
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });


            //panel add to component
            panelTextChat.add(areaScrollPane);
            panelTextChat.add(btnSend);

            //panleCon add component

            panelWhole.add(panelBackBtn);
            panelWhole.add(spChat);
            panelWhole.add(panelTextChat);
            frameConversation.add(panelWhole);
            frameConversation.setLayout(new FlowLayout());
            frameConversation.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean checkEmpty(String tf) {
        if (tf.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }
    public void displayOnlGroupMessage(SendMessgeGroupRespond sendMessgeGroupRespond){
        System.out.println("nhan dc message luc onl");
            //create panel
            JPanel panelText = new JPanel();
            panelText.setLayout(new FlowLayout(FlowLayout.LEFT));
            panelText.setPreferredSize(new Dimension(380, 50));
            panelText.setBorder(new EmptyBorder(0, 0, 0, 15));
            panelText.setBackground(new java.awt.Color(255, 255 ,240));
            int idMsg = sendMessgeGroupRespond.getIdMsg();
            int idSession = sendMessgeGroupRespond.getIdSession();
            int idUser = sendMessgeGroupRespond.getIdUser();
            String messageTxt = sendMessgeGroupRespond.getTfInputMsg();
            String userName = sendMessgeGroupRespond.getUserName();
            //creatte button to hold text
            JLabel userNameLb = new JLabel(userName+":");
            userNameLb.setPreferredSize(new Dimension(380,10));
            JButton msgBtn = new JButton(messageTxt);
            msgBtn.setPreferredSize(new Dimension(200, 30));
            msgBtn.setFocusPainted(false);
    //                    msgBtn.setEnabled(false);
            msgBtn.setBackground(new java.awt.Color(139, 137 ,112));
            msgBtn.setForeground(Color.black);
            msgBtn.setBorder(new RoundedBorder(20));
            //
            panelText.add(userNameLb);
            panelText.add(msgBtn);
            panelConversation.add(panelText);
            panelConversation.setPreferredSize(new Dimension(width, height += 55));
            panelConversation.revalidate();
            panelConversation.repaint();

            Rectangle viewBounds = spChat.getViewportBorderBounds();
            panelConversation.scrollRectToVisible(new Rectangle(0, height, viewBounds.width, viewBounds.height));
//        int idMsg = sendMessgeGroupRespond.getIdMsg();
//        int idSession = sendMessgeGroupRespond.getIdSession();x
//        int idUser = sendMessgeGroupRespond.getIdUser();
//        String tfInputMsg = sendMessgeGroupRespond.getTfInputMsg();
//        groupConversationFrame.lbReceivedMessage = new JLabel(tfInputMsg, JLabel.LEFT);
//        groupConversationFrame.panelConversation.add(groupConversationFrame.lbReceivedMessage);
//        groupConversationFrame.lbReceivedMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
//        groupConversationFrame.panelConversation.updateUI();
//        System.out.println("Nhận được lúc onl");
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
