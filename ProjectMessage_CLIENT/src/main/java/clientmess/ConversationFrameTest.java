package clientmess;

import clientmess.payload.ChatMessage;
import clientmess.payload.ChatRespond;
import clientmess.payload.SendMessageRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ConversationFrameTest {
    JFrame frameConversation;
    JPanel panelConversation;
    JTextField tfInputMessage;
    JLabel lbReceivedMessage;
    JPanel panelTextChat;
    JButton backToHome;
    JFileChooser fileDialog;
    Mess message;

    public ConversationFrameTest(ChatRespond chatRespond) {
        try {
            //read data from server
            int idUser = chatRespond.getIdUser();
            int idFriend = chatRespond.getIdFriend();
            String tenTaiKhoanFriend = chatRespond.getTenTaiKhoanFriend();
            frameConversation = new JFrame(tenTaiKhoanFriend);
            frameConversation.setSize(400, 600);
            frameConversation.setLocationRelativeTo(frameConversation);
            frameConversation.setResizable(true);
            frameConversation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameConversation.setBackground(Color.gray);
            frameConversation.setForeground(Color.gray);
            JPanel panelCon = new JPanel();
            //create panelConversation
            panelConversation = new JPanel();
            panelConversation.setLayout(new FlowLayout());
            panelConversation.setPreferredSize(new Dimension(380, 400));
//            panelConversation.setBorder(new EmptyBorder(150, 100, 50, 100));
            panelConversation.setBackground(Color.white);
            //create panelTextChat
            panelTextChat = new JPanel();
            panelTextChat.setLayout(new FlowLayout());
            panelTextChat.setBackground(Color.gray);

            JLabel lbMsg;
            //create button back to home
            backToHome = new JButton("Back to home");
            backToHome.setFocusPainted(false);
            backToHome.setBackground(Color.gray);
            backToHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frameConversation.setVisible(false);
                    AppMessenger.displayAddFriendFrame();
                }
            });
            //read data from server
            int sessionId = chatRespond.getSessionId();
            int amountMessage = chatRespond.getMessageList().size();
            List<ChatMessage> listMessage = chatRespond.getMessageList();
            System.out.println("List Message" + listMessage);
            //count Messsager
            for (int i = 0; i < amountMessage; i++) {
                ChatMessage chatMessage = listMessage.get(i);
                int idMsg = chatMessage.getIdMsg();
                String textMsg = chatMessage.getTextMsg();
                int idSender = chatMessage.getIdSender();

                if (idUser == idSender) {
                    lbMsg = new JLabel(textMsg);
                    lbMsg.setPreferredSize(new Dimension(10, 10));
                    lbMsg.setHorizontalAlignment(SwingConstants.RIGHT);
                    panelConversation.add(lbMsg);
                    panelConversation.updateUI();
                } else {
                    lbMsg = new JLabel(textMsg);
                    lbMsg.setPreferredSize(new Dimension(10, 10));
                    lbMsg.setHorizontalAlignment(JLabel.LEFT);
                    panelConversation.add(lbMsg);
                    panelConversation.updateUI();
                }
            }

            //create tfMessage and BtnSend

            tfInputMessage = new JTextField("", 25);
            JButton btnSend = new JButton("Send");
            btnSend.setFocusPainted(false);
            btnSend.setBackground(Color.WHITE);

            panelTextChat.add(tfInputMessage);
            panelTextChat.add(btnSend);

            btnSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        System.out.println("sesion id " + sessionId);
                        SendMessageRequest sendMessageRequest = new SendMessageRequest(AppMessenger.SEND_MESSAGE_ACTION, sessionId, tfInputMessage.getText(), idUser, idFriend);
                        String json = AppMessenger.mapper.writeValueAsString(sendMessageRequest);
                        AppMessenger.out.writeUTF(json);
                        AppMessenger.out.flush();
                        JLabel lbSendMessage = new JLabel(tfInputMessage.getText(), JLabel.RIGHT);
                        tfInputMessage.setText("");
                        panelConversation.add(lbSendMessage);
                        lbSendMessage.setAlignmentX(Component.RIGHT_ALIGNMENT);
                        panelConversation.updateUI();

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });


            //panel add to component
            //create JscrollpaneChat
            JScrollPane spChat = new JScrollPane(panelConversation, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            spChat.setPreferredSize(new Dimension(400, 400));
            JScrollBar sb = spChat.getVerticalScrollBar();
            sb.setValue(sb.getMaximum());
            spChat.updateUI();

            //panleCon add component
            panelCon.add(backToHome);
            panelCon.add(spChat);
            panelCon.add(panelTextChat);

            //frame add to component
//            frameConversation.add(backToHome);
            panelCon.setLayout(new FlowLayout());
            panelCon.setPreferredSize(new Dimension(380, 400));
            panelCon.setBackground(Color.GRAY);
            frameConversation.add(panelCon);
            frameConversation.setLayout(new GridLayout(1, 1));
            frameConversation.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatRespond chatRespond = new ChatRespond();
        chatRespond.setMessageList(new ArrayList<>());
        for (int i = 0; i < 100; i++) {
            ChatMessage chatMessage = new ChatMessage(i, "balbalba " + i, i,"SD");
            chatRespond.getMessageList().add(chatMessage);
        }
        ConversationFrameTest conversationFrame = new ConversationFrameTest(chatRespond);
    }
}
