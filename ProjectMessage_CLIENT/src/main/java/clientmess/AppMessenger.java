/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmess;

import clientmess.payload.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author cmtie
 */
public class AppMessenger {

    final static int SIGNUP_ACTION = 1;
    final static int SIGNUP_RESPONSE_ACTION = 100;

    final static int LOGIN_ACTION = 2;
    final static int LOGIN_SUCCESS = 3;
    final static int LOGIN_FALSE = 4;
    final static int LOAD_USER_lIST_ACTION = 5;
    final static int ADD_FRIEND_ACTION = 6;
    final static int ADD_FRIEND_SUCCESS = 8;
    final static int ADD_FRIEND_FAIL = 9;
    final static int LOAD_FRIEND_LIST_ACTION = 7;
    final static int CHAT_ACTION = 10;
    final static int CHAT_ACTION_SUCESS = 11;
    final static int CHAT_ACTION_FAIL = 12;
    final static int SEND_MESSAGE_ACTION = 13;
    final static int RECEIVED_MESSAGE_ACTION = 14;
    final static int RECEIVED_MESSENGER_NOW = 15;
    final static int RECEIVED_MESSENGER_LATER = 16;
    final static int CREATE_GROUP_ACTION = 17;
    final static int LOAD_GROUP_ACTION = 18;
    final static int CHAT_GROUP_ACTION = 19;
    final static int SEND_MESSAGE_GROUP_ACTION = 20;
    final static int RECEIVED_MESSAGE_GROUP_ACTION = 21;
    final static int CHAT_ACTION_MAIN = 22;
    final static int LOAD_FRIEND_IN_MAIN_FRAME = 23;
    final static int SEARCH_FRIEND_LIST_ACTION = 24;
    final static int SEARCH_FRIEND_LIST_ACTION_MAIN_FRAME = 25;
    final static int SEARCH_FRIEND_LIST_ACTION_CREATE_GROUP = 26;
    final static int LOAD_FRIEND_LIST_ACTION_TO_CREATE_GROUP = 70;


    static int idUser = 0;


    public static Socket mySocket = null;
    public static DataInputStream in = null;
    public static DataOutputStream out = null;

    private static AddFriendFrame addFriendFrame;
    private static LogInFrame logInFrame;
    private static ConversationFrame conversationFrame;
    private static GroupConversationFrame groupConversationFrame;
    private static SigUpFrame sigUpFrame;
    private static FriendListFrame friendListFrame;
    private static CreateGroupFrame createGroupFrame;
    private static GroupFrame groupFrame;
    private static MainFrame mainFrame;
    public static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public static void connection() {
        try {
            System.out.println("1");
            mySocket = new Socket("localhost", 1234);
            out = new DataOutputStream(mySocket.getOutputStream());
            in = new DataInputStream(mySocket.getInputStream());
            ThreadHandleInput threadHandleInput = new ThreadHandleInput(in);
            threadHandleInput.start();
            System.out.println("Connected !!!");

        } catch (UnknownHostException e) {
            System.err.println(e + "Lỗi ko tìm được server");
        } catch (IOException e) {
            System.err.println(e + "Ngoại lệ socket");
        }
    }


    public static void handleSigUpAction(SignUpResponse signUpResponse) throws Exception {
        String input = signUpResponse.getMessage();
        System.out.println(input);
        sigUpFrame.tfTenTK.setText("");
        sigUpFrame.tfMK.setText("");
        sigUpFrame.tfHoTen.setText("");
        sigUpFrame.tfNgaySinh.setText("");
        sigUpFrame.tfGioiTinh.setText("");
        sigUpFrame.tfDiaChi.setText("");
        sigUpFrame.tfQueQuan.setText("");
        sigUpFrame.tfEmail.setText("");
        JOptionPane.showMessageDialog(null, input);
    }

    public static void handleLogInAction(LogInRespond logInRespond) throws Exception {
        int LogInResult = logInRespond.getState();
        if (LogInResult == LOGIN_SUCCESS) {
            System.out.println("input Log in");
            idUser = logInRespond.getIdUser();
            JOptionPane.showMessageDialog(null, "Đăng Nhập Thành Công");
            //display home frame and hide login Frame
            mainFrame = new MainFrame(idUser);
//            mainFrame.displayFriendList(logInRespond.getLoadFriendsList());
            logInFrame.hide();
            //
        } else if (LogInResult == LOGIN_FALSE) {
            JOptionPane.showMessageDialog(null, "Sai tài khoản rồi babea !,\n " +
                    " MK của bạn có thể là 123");
            logInFrame.tfAccount.setText("");
            logInFrame.tfPass.setText("");
        }
    }
    public static void handleLoadFriendInMainFrame(LoadFriendRespond loadFriendRespond) throws Exception{
        mainFrame.displayFriendList(loadFriendRespond.getLoadFriendsList());
    }

    public static void handleLoadUserListAction(LoadUserRespond loadUserRespond) throws Exception {
        addFriendFrame.displayUserList(loadUserRespond.getLoadUserList());
    }
    public static void handleSearchFriendListAction(SearchFriendListRespond searchFriendListRespond){
        int position = searchFriendListRespond.getPosition();
        if (position == SEARCH_FRIEND_LIST_ACTION_MAIN_FRAME){
            mainFrame.displaySearchFriendList(searchFriendListRespond.getLoadFriendsList());
        }
        else if(position == SEARCH_FRIEND_LIST_ACTION_CREATE_GROUP){
            createGroupFrame.displaySearchFriendList(searchFriendListRespond.getLoadFriendsList());
        }
        else {
            JOptionPane.showMessageDialog(null,"Lỗi load friend");
        }

    }

    public static void handleAddFriendAction(AddFriendRespond addFriendRespond) throws Exception {
        int result = addFriendRespond.getState();
        if (result == ADD_FRIEND_SUCCESS) {
            JOptionPane.showMessageDialog(null, "Kết Bạn Thành Công!!!");
        } else if (result == ADD_FRIEND_FAIL) {
            JOptionPane.showMessageDialog(null, "Kết Bạn Thất Bại !!!");

        }
    }

    public static void handleLoadFriendListAction(LoadFriendRespond loadFriendRespond) throws Exception {
        friendListFrame.displayFriendList(loadFriendRespond.getLoadFriendsList());

    }

    public static void handleCreateGroup(LoadFriendGroupRespond loadFriendGroupRespond) throws Exception {
        createGroupFrame.displayCreate(loadFriendGroupRespond);

    }

    public static void handleLoadGroupList(LoadGroupListRespond loadGroupListRespond) {
        List<LoadGroupListRespond.Group> groups = loadGroupListRespond.getGroupList();

        groupFrame = new GroupFrame(groups);
        mainFrame.hide();
    }
//    public static  void hideFriendListFrame(){
//        AppMessenger.friendListFrame.hide();
//    }

    public static void handleChatAction(ChatRespond chatRespond) throws Exception {
        int success = chatRespond.getStateChat();
        if (success == CHAT_ACTION_SUCESS) {

            conversationFrame = new ConversationFrame(chatRespond);
            friendListFrame.hide();
//            hideFriendListFrame();
            System.out.println("done handle chat action");
        } else {
            JOptionPane.showMessageDialog(null, "Can not Chat");
        }

    }
    public static void handleChatActionInMain(ChatRespond chatRespondInMain) throws Exception{
        int success = chatRespondInMain.getStateChat();
        if (success == CHAT_ACTION_SUCESS) {

            conversationFrame = new ConversationFrame(chatRespondInMain);
            mainFrame.hide();
//            hideFriendListFrame();
            System.out.println("done handle chat action");
        } else {
            JOptionPane.showMessageDialog(null, "Can not Chat");
        }
    }

    public static void handleReceiverMessage(SendMessageRespond sendMessageRespond) throws Exception {
        conversationFrame.displayOnlMessage(sendMessageRespond);
    }

    public static void handleChatGroupAction(ChatGroupRespond chatGroupRespond) {
        groupConversationFrame = new GroupConversationFrame(chatGroupRespond);
        groupFrame.hide();
        System.out.println("done handle chat action");

    }

    public static void hadleReceiverGroupMessage(SendMessgeGroupRespond sendMessgeGroupRespond) {
        try {
            groupConversationFrame.displayOnlGroupMessage(sendMessgeGroupRespond);
//            System.out.println("nhan dc message luc onl");
//            int idMsg = sendMessgeGroupRespond.getIdMsg();
//            int idSession = sendMessgeGroupRespond.getIdSession();
//            int idUser = sendMessgeGroupRespond.getIdUser();
//            String tfInputMsg = sendMessgeGroupRespond.getTfInputMsg();
//            groupConversationFrame.lbReceivedMessage = new JLabel(tfInputMsg, JLabel.LEFT);
//            groupConversationFrame.panelConversation.add(groupConversationFrame.lbReceivedMessage);
//            groupConversationFrame.lbReceivedMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
//            groupConversationFrame.panelConversation.updateUI();
//            System.out.println("Nhận được lúc onl");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displaySigUpFrame() {
        sigUpFrame = new SigUpFrame();
    }

    public static void displayLogInFrame() {
        logInFrame = new LogInFrame();
    }

    public static void displayAddFriendFrame() {
        addFriendFrame = new AddFriendFrame();
    }

    public static void displayLMainFrame() {
        mainFrame = new MainFrame(idUser);
    }

    public static void displaySearchFriendListFrame() {
        friendListFrame = new FriendListFrame();
        friendListFrame.sendData();

    }

    public static void displaySearchFriendListFrameToCreateGroup() {
        createGroupFrame = new CreateGroupFrame();
        createGroupFrame.sendDataToCreateGroup();

    }

    public static void displayConversationFrame(ChatRespond chatRespond) {
        conversationFrame = new ConversationFrame(chatRespond);
    }
//    public static void displayCreateGroupFrame(){
//        friendListFrame.hide();
//        createGroupFrame = new CreateGroupFrame(loadFriendGroupRespond);
//    }

    public static void main(String[] args) {
        // TODO code application logic here
        AppMessenger.connection();
        logInFrame = new LogInFrame();

    }

}


class ThreadHandleInput extends Thread {

    DataInputStream in;

    public ThreadHandleInput(DataInputStream in) {
        this.in = in;
    }

    public void run() {
        try {
            //take input from server
            while (true) {
                if (in.available() > 0) {
                    String json = in.readUTF();
                    BasePayload basePayload = AppMessenger.mapper.readValue(json, BasePayload.class);
                    int action = basePayload.getAction();
                    switch (action) {
                        case AppMessenger.SIGNUP_ACTION:
                            System.out.println("Handle Sig up Action");
                            SignUpResponse signUpResponse = AppMessenger.mapper.readValue(json, SignUpResponse.class);
                            AppMessenger.handleSigUpAction(signUpResponse);
                            break;
                        case AppMessenger.LOGIN_ACTION:
                            System.out.println("Handle Log In Action");
                            LogInRespond logInRespond = AppMessenger.mapper.readValue(json, LogInRespond.class);
                            AppMessenger.handleLogInAction(logInRespond);
                            break;
                        case AppMessenger.LOAD_USER_lIST_ACTION:
                            System.out.println("Handle Load User List Action");
                            LoadUserRespond loadUserRespond = AppMessenger.mapper.readValue(json, LoadUserRespond.class);
                            AppMessenger.handleLoadUserListAction(loadUserRespond);
                            break;
                        case AppMessenger.SEARCH_FRIEND_LIST_ACTION:
                            System.out.println("handle search Friend List");
                            SearchFriendListRespond searchFriendListRespond = AppMessenger.mapper.readValue(json,SearchFriendListRespond.class);
                            AppMessenger.handleSearchFriendListAction(searchFriendListRespond);
                            break;
                        case AppMessenger.ADD_FRIEND_ACTION:
                            System.out.println("Handle Add friend Action");
                            AddFriendRespond addFriendRespond = AppMessenger.mapper.readValue(json, AddFriendRespond.class);
                            AppMessenger.handleAddFriendAction(addFriendRespond);
                            break;
                        case AppMessenger.LOAD_FRIEND_LIST_ACTION:
                            System.out.println("Handle Load Friend List Action");
                            LoadFriendRespond loadFriendRespond = AppMessenger.mapper.readValue(json, LoadFriendRespond.class);
                            AppMessenger.handleLoadFriendListAction(loadFriendRespond);
                            break;
                        case AppMessenger.LOAD_FRIEND_LIST_ACTION_TO_CREATE_GROUP:
                            System.out.println("Handle Load friend list to Create Group");
                            LoadFriendGroupRespond loadFriendGroupRespond = AppMessenger.mapper.readValue(json, LoadFriendGroupRespond.class);
                            AppMessenger.handleCreateGroup(loadFriendGroupRespond);
                            break;
                        case AppMessenger.LOAD_FRIEND_IN_MAIN_FRAME:
                            System.out.println("Handle Load Friend List in Main frame");
                            LoadFriendRespond loadFriendRespond1 = AppMessenger.mapper.readValue(json,LoadFriendRespond.class);
                            AppMessenger.handleLoadFriendInMainFrame(loadFriendRespond1);
                            break;
                        case AppMessenger.CREATE_GROUP_ACTION:
                            System.out.println("Handle Create Group ACtion");
                            CreateGroupRespond createGroupRespond = AppMessenger.mapper.readValue(json, CreateGroupRespond.class);
                            String state = createGroupRespond.getState();
                            JOptionPane.showMessageDialog(null,state);
                            break;
                        case AppMessenger.LOAD_GROUP_ACTION:
                            System.out.println("Load Group List ");
                            LoadGroupListRespond loadGroupListRespond = AppMessenger.mapper.readValue(json, LoadGroupListRespond.class);
                            AppMessenger.handleLoadGroupList(loadGroupListRespond);
                            break;
                        case AppMessenger.CHAT_ACTION:
                            System.out.println("Handle Chat Action");
                            ChatRespond chatRespond = AppMessenger.mapper.readValue(json, ChatRespond.class);
                            AppMessenger.handleChatAction(chatRespond);
                            break;
                        case AppMessenger.CHAT_ACTION_MAIN:
                            System.out.println("Handle Chat Action in Main");
                            ChatRespond chatRespondInMain = AppMessenger.mapper.readValue(json, ChatRespond.class);
                            AppMessenger.handleChatActionInMain(chatRespondInMain);
                            break;
                        case AppMessenger.RECEIVED_MESSAGE_ACTION:
                            System.out.println(" Received Message");
                            SendMessageRespond sendMessageRespond = AppMessenger.mapper.readValue(json, SendMessageRespond.class);
                            AppMessenger.handleReceiverMessage(sendMessageRespond);
                            break;
                        case AppMessenger.CHAT_GROUP_ACTION:
                            System.out.println("Handle Chat Group Action");
                            ChatGroupRespond chatGroupRespond = AppMessenger.mapper.readValue(json, ChatGroupRespond.class);
                            AppMessenger.handleChatGroupAction(chatGroupRespond);
                            break;
                        case AppMessenger.RECEIVED_MESSAGE_GROUP_ACTION:
                            System.out.println("Received Message Group");
                            SendMessgeGroupRespond sendMessgeGroupRespond = AppMessenger.mapper.readValue(json, SendMessgeGroupRespond.class);
                            AppMessenger.hadleReceiverGroupMessage(sendMessgeGroupRespond);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}