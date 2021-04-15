/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.payload.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author cmtie
 */
public class TcpServer {


    private static ServerSocket myServer = null;

    private static Connection conn = null;
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=Messenger;"
            + "integratedSecurity=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "sa";
    public static HashMap<Integer, Socket> clientHashMap = null;

    public static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static void connectToDB(String dbURL, String userName, String password) {
        System.out.println("Start Connect to SQL");
        try {
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //mo server Socket
    public static void openServer() {
        try {
            myServer = new ServerSocket(1234);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    //wait connect va chuyen connect
    public static void startServer() {
        try {
            while (true) {
                HandleConnection();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    //xu ly connection
    public static void HandleConnection() {
        try {
            Socket clientSocket = myServer.accept();
            System.out.println("client be accepted !");
            HandleClientThread clientthread = new HandleClientThread(conn, clientSocket);
            clientthread.start();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        connectToDB(DB_URL, USER_NAME, PASSWORD);
        clientHashMap = new HashMap();
        openServer();
        startServer();
    }
}

//handle thread de lay cac bien dung chung
class HandleClientThread extends Thread {

    Connection conn;
    Socket clientSocket;
//    HashMap<Integer, Socket> clientHashMap;

    DataInputStream in;
    DataOutputStream out;

    final static int SIGN_UP_ACTION = 1;
    final static int LOGIN_ACTION = 2;
    final static int LOGIN_SUCCESS = 3;
    final static int LOGIN_FALSE = 4;
    final static int LOAD_USER_lIST_ACTION = 5;
    final static int ADD_FRIEND_ACTION = 6;
    final static int ADD_FRIEND_SUCCESS = 8;
    final static int ADD_FRIEND_FAIL = 9;
    final static int LOAD_FRIEND_LIST_ACTION = 7;
    final static int CHAT_ACTION = 10;
    final static int CHAT_ACTION_SUCCESS = 11;
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
    public HandleClientThread(Connection conn, Socket clientSocket) {
        this.conn = conn;
        this.clientSocket = clientSocket;
//        this.clientHashMap = clientHashMap;
    }

    public static boolean testEmty(SignUpRequest signUpRequest) {
        if (signUpRequest.getTenTk().equals("") || signUpRequest.getTenMk().equals("") || signUpRequest.getHoten().equals("") || signUpRequest.getNgaysinh().equals("") || signUpRequest.getDiachi().equals("") || signUpRequest.getQuequan().equals("") || signUpRequest.getEmail().equals("")) {
            return true;
        }
        return false;
    }

    public static String encryptThisString(String input) {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleSignUp(SignUpRequest signUpRequest) throws Exception {

        String MatKhau = signUpRequest.getTenMk();
        System.out.println(MatKhau);
        String encryptPassword = encryptThisString(MatKhau);
        System.out.println("Mã hóa SHA 512" + encryptPassword);

        SignUpResponse signUpResponse = new SignUpResponse();
        // crate statement
        if (testEmty(signUpRequest) == true) {
            signUpResponse.setAction(SIGN_UP_ACTION);
            signUpResponse.setMessage("Ban chua dang nhap thong tin");
            out.writeUTF(TcpServer.mapper.writeValueAsString(signUpResponse));
            out.flush();
            return;

        } else {
            Statement stmt = conn.createStatement();
            // insert data to table
            stmt.execute("INSERT INTO Users(HoTen,NgaySinh,GioiTinh,DiaChi,QueQuan,Email,TenTaiKhoan,MatKhau)"
                    + " values(N'" + signUpRequest.getHoten() + "',N'" + signUpRequest.getNgaysinh() + "'," + signUpRequest.getGioitinh() + "" +
                    ",N'" + signUpRequest.getDiachi() + "',N'" + signUpRequest.getQuequan() + "',N'" + signUpRequest.getEmail() + "',N'" + signUpRequest.getTenTk() + "',N'" + encryptPassword + "')");
            signUpResponse.setAction(SIGN_UP_ACTION);
            signUpResponse.setMessage("Dang ki thanh cong");
            out.writeUTF(TcpServer.mapper.writeValueAsString(signUpResponse));
            out.flush();
        }
    }

    public void handleLogin(LogInRequest logInRequest) throws Exception {

        String pass = logInRequest.getPass();
        String encryptPass = encryptThisString(pass);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select *\n"
                + "from Users\n"
                + "where TenTaiKhoan = N'" + logInRequest.getAccount() + "' and MatKhau = N'" + encryptPass + "'");
        System.out.println("encryptPass" + encryptPass);
        System.out.println("Login by" + logInRequest.getAccount());


        if (rs.next()) {
            int idUser = rs.getInt(1);
            LogInRespond logInRespond = new LogInRespond();
            logInRespond.setAction(LOGIN_ACTION);
            logInRespond.setState(LOGIN_SUCCESS);
            logInRespond.setIdUser(idUser);
//            Statement stmtSearchNameFriend = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//
//            ResultSet rsSearchNameFriend = stmtSearchNameFriend.executeQuery("select Users.IdUser, Users.TenTaiKhoan, Users.HoTen ,FriendShip.IdSession,Session.TimeFinish\n"
//                    + "from FriendShip, Users,Session\n"
//                    + " where\n"
//                    + "FriendShip.UserId = " + idUser + "\n"
//                    + "and FriendShip.FriendId = Users.IdUser and FriendShip.IdSession = Session.Id order by Session.TimeFinish desc");
//            List<LogInRespond.LoadFriend> loadFriendList = new ArrayList<>();
//            while (rsSearchNameFriend.next()) {
//                int idUseri = rsSearchNameFriend.getInt(1);
//                String accountName = rsSearchNameFriend.getString(2);
//                String hoTen = rsSearchNameFriend.getString(3);
//                LogInRespond.LoadFriend loadFriend = new LogInRespond.LoadFriend(idUseri,accountName,hoTen);
//                loadFriendList.add(loadFriend);
//            }
//            logInRespond.setLoadFriendsList(loadFriendList);


            String json = TcpServer.mapper.writeValueAsString(logInRespond);
            out.writeUTF(json);
            out.flush();
            TcpServer.clientHashMap.put(rs.getInt(1), clientSocket);
            System.out.println("HashMap" + TcpServer.clientHashMap);
        } else {
            LogInRespond logInRespond = new LogInRespond(LOGIN_ACTION, LOGIN_FALSE, 0);
            String json = TcpServer.mapper.writeValueAsString(logInRespond);
            out.writeUTF(json);
            out.flush();
        }
    }

    public void handleSearchUserList(LoadUserRequest loadUserRequest) throws Exception {
        int idUser = loadUserRequest.getIdUser();
        String tfSearch = loadUserRequest.getMessage();
        System.out.println("id User " + idUser + "Mess" + tfSearch);
        Statement stmtSearch = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rsSearch = stmtSearch.executeQuery("SELECT Users.IdUser, Users.TenTaiKhoan \n"
                + " from Users \n"
                + " where TenTaiKhoan like N'" + tfSearch + "%'  "
                + " except "
                + " select Users.IdUser, Users.TenTaiKhoan \n"
                + " from FriendShip, Users \n"
                + " where \n"
                + " FriendShip.UserId = " + idUser + " \n"
                + " and FriendShip.FriendId = Users.IdUser \n"
                + " except "
                + " select Users.IdUser, Users.TenTaiKhoan \n"
                + " from Users \n"
                + " where \n"
                + " Users.IdUser = " + idUser);
        LoadUserRespond loadUserRespond = new LoadUserRespond();
        loadUserRespond.setAction(LOAD_USER_lIST_ACTION);
        List<LoadUserRespond.LoadUser> loadUserList = new ArrayList<>();
        while (rsSearch.next()) {
            System.out.println(rsSearch.getInt(1) + " " + rsSearch.getString(2));
            int friendId = rsSearch.getInt(1);
            String friendName = rsSearch.getString(2);
            LoadUserRespond.LoadUser loadUser = new LoadUserRespond.LoadUser(friendId, friendName);
            loadUserList.add(loadUser);
        }
        loadUserRespond.setLoadUserList(loadUserList);
        out.writeUTF(TcpServer.mapper.writeValueAsString(loadUserRespond));
        out.flush();
    }

    public void handleSearchFriendList(SearchFriendListRequest searchFriendListRequest){
        try {
            int position = searchFriendListRequest.getPosition();
            System.out.println("position"+position);
            int idUser = searchFriendListRequest.getIdUser();
            String tfSearch = searchFriendListRequest.getMessage();
            Statement stmtSearchFriend = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rsSearchFriend = stmtSearchFriend.executeQuery("select Users.IdUser, Users.TenTaiKhoan, Users.HoTen \n" +
                    "                from FriendShip, Users\n" +
                    "                 where\n" +
                    "                FriendShip.UserId = " + idUser + " and FriendShip.FriendId = Users.IdUser and Users.TenTaiKhoan like N'"+tfSearch+"%'");

            SearchFriendListRespond searchFriendListRespond = new SearchFriendListRespond();
            searchFriendListRespond.setAction(SEARCH_FRIEND_LIST_ACTION);

            if (position == SEARCH_FRIEND_LIST_ACTION_MAIN_FRAME){
                searchFriendListRespond.setPosition(SEARCH_FRIEND_LIST_ACTION_MAIN_FRAME);
                System.out.println("main");
            }
            else {
                searchFriendListRespond.setPosition(SEARCH_FRIEND_LIST_ACTION_CREATE_GROUP);
                System.out.println("create");
            }

            List<SearchFriendListRespond.LoadFriend> loadFriendList = new ArrayList<>();
            while (rsSearchFriend.next()) {
                int idUseri = rsSearchFriend.getInt(1);
                String accountName = rsSearchFriend.getString(2);
                String hoTen = rsSearchFriend.getString(3);
                SearchFriendListRespond.LoadFriend loadFriend = new SearchFriendListRespond.LoadFriend(idUseri,accountName,hoTen);
                loadFriendList.add(loadFriend);
            }
            searchFriendListRespond.setLoadFriendsList(loadFriendList);
            System.out.println("Number of Friend list" + loadFriendList.size()  );
            out.writeUTF(TcpServer.mapper.writeValueAsString(searchFriendListRespond));
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void handleAddFriend(AddFriendRequest addFriendRequest) throws Exception {
        int idUser = addFriendRequest.getIdUser();
        int idFriend = addFriendRequest.getIdFriend();
        String FriendName = addFriendRequest.getFriendName();

        LocalTime TimeStart = LocalTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        LocalTime TimeFinish = LocalTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        //insert to session
        PreparedStatement insertStmt = conn.prepareStatement("insert into "
                        + "Session(SessionName,AmountUsers, TimeStart, TimeFinish) "
                        + "values( N'"+ FriendName + "'," + 2 + ", '" + TimeStart + "', '" + TimeFinish + "')",
                Statement.RETURN_GENERATED_KEYS);
        insertStmt.execute();
        ResultSet insertRs = insertStmt.getGeneratedKeys();
        int sessionId=0;
        if(insertRs.next()){
            sessionId = insertRs.getInt(1);
        }

        //insert to friend Ship
        LocalTime TimeCreated = LocalTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        Statement stmtAddFriend = conn.createStatement();
        stmtAddFriend.execute("INSERT INTO FriendShip(UserId,FriendId,TimeCreated,IdSession)"
                + "Values(" + idUser + "," + idFriend + ",'" + TimeCreated + "', " + sessionId + ")");
        System.out.println("add 1");
        Statement stmtAddFriend1 = conn.createStatement();
        stmtAddFriend1.execute("INSERT INTO FriendShip(UserId,FriendId,TimeCreated,IdSession)"
                + "Values(" + idFriend + "," + idUser + ",'" + TimeCreated + "'," +sessionId + ")");
        //insert to Participate
        Statement stmParticipate = conn.createStatement();
        stmParticipate.execute("INSERT INTO Participate(IdUser,IdSession,TimeJoin)"
                + "Values(" + idUser + "," + sessionId + ",'" + TimeCreated + "')");
        System.out.println("add 1");
        //select from friendship
        Statement stmtCheckFriend = conn.createStatement();
        ResultSet rsCheckFriend = stmtCheckFriend.executeQuery("select * \n" +
                "from FriendShip " +
                "where UserId = " + idUser + " and FriendId = " + idFriend);
        if (rsCheckFriend.next()) {
            System.out.println("success add fr 1");
            AddFriendRespond addFriendRespond = new AddFriendRespond(ADD_FRIEND_ACTION,ADD_FRIEND_SUCCESS);
            String json = TcpServer.mapper.writeValueAsString(addFriendRespond);
            out.writeUTF(json);
            out.flush();
        } else {
            System.out.println("success add fr 2");
            AddFriendRespond addFriendRespond = new AddFriendRespond(ADD_FRIEND_ACTION,ADD_FRIEND_SUCCESS);
            String json = TcpServer.mapper.writeValueAsString(addFriendRespond);
            out.writeUTF(json);
            out.flush();
        }
    }

    public void handleLoadFriendList(LoadFriendRequest loadFriendRequest) throws Exception {
        int idUser = loadFriendRequest.getIdUser();
        Statement stmtSearchNameFriend = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rsSearchNameFriend = stmtSearchNameFriend.executeQuery("select Users.IdUser, Users.TenTaiKhoan, Users.HoTen \n"
                + "from FriendShip, Users\n"
                + " where\n"
                + "FriendShip.UserId = " + idUser + "\n"
                + "and FriendShip.FriendId = Users.IdUser");

        LoadFriendRespond loadFriendRespond = new LoadFriendRespond();
        loadFriendRespond.setAction(LOAD_FRIEND_LIST_ACTION);
        List<LoadFriendRespond.LoadFriend> loadFriendList = new ArrayList<>();
        while (rsSearchNameFriend.next()) {
            int idUseri = rsSearchNameFriend.getInt(1);
            String accountName = rsSearchNameFriend.getString(2);
            String hoTen = rsSearchNameFriend.getString(3);
            LoadFriendRespond.LoadFriend loadFriend = new LoadFriendRespond.LoadFriend(idUseri,accountName,hoTen);
            loadFriendList.add(loadFriend);
        }
        loadFriendRespond.setLoadFriendsList(loadFriendList);
        System.out.println("Number of Friend list" + loadFriendList.size()  );
        out.writeUTF(TcpServer.mapper.writeValueAsString(loadFriendRespond));
        out.flush();
    }

    public void handleLoadFriendListToCreateGroup(LoadFriendRequest loadFriendRequest1) throws Exception {
        int idUser = loadFriendRequest1.getIdUser();
        Statement stmtSearchNameFriend = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement stmtSearchNameFriendGroup = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rsSearchNameFriendGroup = stmtSearchNameFriendGroup.executeQuery("select Users.IdUser, Users.TenTaiKhoan, Users.HoTen \n"
                + "from FriendShip, Users\n"
                + " where\n"
                + "FriendShip.UserId = " + idUser + "\n"
                + "and FriendShip.FriendId = Users.IdUser");

        ResultSet rsAccountName = stmtSearchNameFriend.executeQuery("select  Users.TenTaiKhoan \n"
                + "from  Users\n"
                + " where\n"
                + "Users.IdUser = " + idUser );
        String accountName = "";
        if (rsAccountName.next()){
            accountName = rsAccountName.getString(1);
        }
        LoadFriendGroupRespond loadFriendGroupRespond = new LoadFriendGroupRespond();
        loadFriendGroupRespond.setAction(LOAD_FRIEND_LIST_ACTION_TO_CREATE_GROUP);
        loadFriendGroupRespond.setIdUser(idUser);
        loadFriendGroupRespond.setAccountName(accountName);

        List<LoadFriendGroupRespond.LoadFriend> loadFriendList = new ArrayList<>();
        while (rsSearchNameFriendGroup.next()) {
            int idFriend = rsSearchNameFriendGroup.getInt(1);
            String friendAccountName = rsSearchNameFriendGroup.getString(2);
            String Name = rsSearchNameFriendGroup.getString(3);
            LoadFriendGroupRespond.LoadFriend loadFriend = new LoadFriendGroupRespond.LoadFriend(idFriend,friendAccountName,Name);
            loadFriendList.add(loadFriend);
        }
        loadFriendGroupRespond.setLoadFriendList(loadFriendList);
        out.writeUTF(TcpServer.mapper.writeValueAsString(loadFriendGroupRespond));
        out.flush();
    }
    public void handleLoadListFriendInMainFrame(LoadFriendRequest loadFriendRequest2)throws  Exception{
        int idUser = loadFriendRequest2.getIdUser();
        LoadFriendRespond loadFriendRespond = new LoadFriendRespond();
        loadFriendRespond.setAction(LOAD_FRIEND_IN_MAIN_FRAME);
        Statement stmtSearchNameFriend = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rsSearchNameFriend = stmtSearchNameFriend.executeQuery("select Users.IdUser, Users.TenTaiKhoan, Users.HoTen ,FriendShip.IdSession,Session.TimeFinish\n"
                + "from FriendShip, Users,Session\n"
                + " where\n"
                + "FriendShip.UserId = " + idUser + "\n"
                + "and FriendShip.FriendId = Users.IdUser and FriendShip.IdSession = Session.Id order by Session.TimeFinish desc");
        List<LoadFriendRespond.LoadFriend> loadFriendList = new ArrayList<>();
        while (rsSearchNameFriend.next()) {
            int idUseri = rsSearchNameFriend.getInt(1);
            String accountName = rsSearchNameFriend.getString(2);
            String hoTen = rsSearchNameFriend.getString(3);
            LoadFriendRespond.LoadFriend loadFriend = new LoadFriendRespond.LoadFriend(idUseri,accountName,hoTen);
            loadFriendList.add(loadFriend);
        }
        loadFriendRespond.setLoadFriendsList(loadFriendList);
        String json = TcpServer.mapper.writeValueAsString(loadFriendRespond);
        out.writeUTF(json);
        out.flush();
    }

    public void handleLoadGroupList(LoadGroupListRequest loadGroupListRequest){
        try {
            int idUser = loadGroupListRequest.getIdUser();
            Statement stmtGroupList= conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rsGroupList = stmtGroupList.executeQuery("select idUser,Session.Id,SessionName \n"
                    + "from Participate, Session, [dbo].[Group] \n"
                    + " where\n"
                    + "Participate.idUser = " + idUser + "\n"
                    + "and Participate.idSession = Session.Id and Session.Id = [dbo].[Group].IdSession");
            List<LoadGroupListRespond.Group> groupList = new ArrayList<>();
            while(rsGroupList.next()){
                LoadGroupListRespond.Group group = new LoadGroupListRespond.Group(idUser, rsGroupList.getInt(2),rsGroupList.getString(3));
                groupList.add(group);
                System.out.println("group list "+group);
            }
//            System.out.println("group List "+groupList);
            LoadGroupListRespond loadGroupListRespond = new LoadGroupListRespond(LOAD_GROUP_ACTION,groupList);
            String json = TcpServer.mapper.writeValueAsString(loadGroupListRespond);
            out.writeUTF(json);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void handleCreateGroup(CreateGroupRequest createGroupRequest){
        try{
            int member = createGroupRequest.getMembersList().size();
            ArrayList<Member> memberList = createGroupRequest.getMembersList();
            String sessionName ="";
            for (Member ms: memberList) {
                sessionName = sessionName + " "+ms.getMemberName();
            }
            LocalTime TimeStart = LocalTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            LocalTime TimeFinish = LocalTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            //insert to session
            PreparedStatement insertStmt = conn.prepareStatement("insert into "
                            + "Session(SessionName,AmountUsers, TimeStart, TimeFinish) "
                            + "values( N'"+ sessionName + "'," + member + ", '" + TimeStart + "', '" + TimeFinish + "')",
                    Statement.RETURN_GENERATED_KEYS);
            insertStmt.execute();
            ResultSet insertRs = insertStmt.getGeneratedKeys();
            int sessionId=0;
            if(insertRs.next()){
                sessionId = insertRs.getInt(1);
            }
            // insert in to Group
            PreparedStatement insertStmt1 = conn.prepareStatement("insert into "
                            + "[dbo].[Group](IdSession) "
                            + "values( '"+  sessionId + "')",
                    Statement.RETURN_GENERATED_KEYS);
            insertStmt1.execute();
            //insert into participate

            for (Member ms: memberList) {
                 int idUser = ms.getIdMember();
                Statement stmParticipate = conn.createStatement();
                stmParticipate.execute("INSERT INTO Participate(IdUser,IdSession,TimeJoin)"
                        + "Values(" + idUser + "," + sessionId + ",'" + TimeStart + "')");
                System.out.println("add 1");
            }
            CreateGroupRespond createGroupRespond = new CreateGroupRespond(CREATE_GROUP_ACTION,"Tạo nhóm thành công");
            String json = TcpServer.mapper.writeValueAsString(createGroupRespond);
            out.writeUTF(json);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Không tạo được nhóm");
        }
    }
    public void handleChat(ChatRequest chatRequest) throws Exception {
        int idUser = chatRequest.getIdUser();
        int idFriend = chatRequest.getIdFriend();
        String tenTaiKhoanFriend = chatRequest.getTenTaiKhoanFriend();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select *\n"
                + "from FriendShip\n"
                + "where UserId = " + idUser + " and FriendId = " + idFriend);
//        int idSession=0;
        if (rs.next()) {
            int sessionId = rs.getInt(4);
            if (sessionId == 0) {
                List<ChatMessage> messageList = new ArrayList<>();
                ChatRespond chatRespond = new ChatRespond(CHAT_ACTION, CHAT_ACTION_SUCCESS, idUser, idFriend, tenTaiKhoanFriend, sessionId, messageList);
                String json = TcpServer.mapper.writeValueAsString(chatRespond);
                out.writeUTF(json);
                out.flush();
            } else {
                Statement loadMessageStmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet loadMessageRs = loadMessageStmt.executeQuery("select Message.Id,Message.TextMess,Message.IdSender,Users.TenTaiKhoan\" +\n" +
                        "                    \" from Message, Users \n" +
                        "where IdSession = " + sessionId + "and Users.IdUser = Message.IdSender");
                System.out.println("LoadMessage "+ loadMessageRs);
                loadMessageRs.last();
                loadMessageRs.beforeFirst();
                List<ChatMessage> messageList = new ArrayList<>();
                while (loadMessageRs.next()) {
                    int idMsg = loadMessageRs.getInt(1);
                    String textMsg = loadMessageRs.getString(2);
                    int idSender = loadMessageRs.getInt(3);
                    String userName = loadMessageRs.getString(4);

                    ChatMessage chatMessage = new ChatMessage(idMsg,textMsg,idSender,userName);
                    messageList.add(chatMessage);

                }
                ChatRespond chatRespond = new ChatRespond(CHAT_ACTION, CHAT_ACTION_SUCCESS, idUser, idFriend, tenTaiKhoanFriend, sessionId, messageList);
                String json = TcpServer.mapper.writeValueAsString(chatRespond);
                out.writeUTF(json);
                out.flush();
            }
        }

    }

    public void handleChatInMain(ChatRequest chatRequestMainFrame) throws Exception{
        int idUser = chatRequestMainFrame.getIdUser();
        int idFriend = chatRequestMainFrame.getIdFriend();
        String tenTaiKhoanFriend = chatRequestMainFrame.getTenTaiKhoanFriend();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select *\n"
                + "from FriendShip\n"
                + "where UserId = " + idUser + " and FriendId = " + idFriend);
        if (rs.next()) {
            int sessionId = rs.getInt(4);
            if (sessionId == 0) {
                List<ChatMessage> messageList = new ArrayList<>();
                ChatRespond chatRespond = new ChatRespond(CHAT_ACTION_MAIN, CHAT_ACTION_SUCCESS, idUser, idFriend, tenTaiKhoanFriend, sessionId, messageList);
                String json = TcpServer.mapper.writeValueAsString(chatRespond);
                out.writeUTF(json);
                out.flush();
            } else {
                Statement loadMessageStmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet loadMessageRs = loadMessageStmt.executeQuery("select Message.Id,Message.TextMess,Message.IdSender,Users.TenTaiKhoan\" +\n" +
                        "                    \" from Message, Users where IdSession = " + sessionId + "and Users.IdUser = Message.IdSender");
                System.out.println("LoadMessage "+ loadMessageRs);
                loadMessageRs.last();
                loadMessageRs.beforeFirst();
                List<ChatMessage> messageList = new ArrayList<>();
                while (loadMessageRs.next()) {
                    int idMsg = loadMessageRs.getInt(1);
                    String textMsg = loadMessageRs.getString(2);
                    int idSender = loadMessageRs.getInt(3);
                    String userName = loadMessageRs.getString(4);

                    ChatMessage chatMessage = new ChatMessage(idMsg,textMsg,idSender,userName);
                    messageList.add(chatMessage);
                }
                ChatRespond chatRespond = new ChatRespond(CHAT_ACTION_MAIN, CHAT_ACTION_SUCCESS, idUser, idFriend, tenTaiKhoanFriend, sessionId, messageList);
                String json = TcpServer.mapper.writeValueAsString(chatRespond);
                out.writeUTF(json);
                out.flush();
            }
        }

    }

    public void handleReceivedMsg(SendMessageRequest sendMessageRequest){
        try {
            int idSession = sendMessageRequest.getSessionID();
            String tfInputMsg = sendMessageRequest.getTfInputMessage();
            int idUser = sendMessageRequest.getIdUser();
            int idFriend = sendMessageRequest.getIdFriend();
            Statement stmtGroupList= conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rsLoadUserName = stmtGroupList.executeQuery("\tselect Users.TenTaiKhoan from Users where Users.IdUser = "+idUser);
            String userName ="";
            if (rsLoadUserName.next()){
                userName = rsLoadUserName.getString(1);
            }
            if(tfInputMsg.equalsIgnoreCase("")){
                System.out.println( "Ko có tn");
            }
            else {
                //check idFriend in map or not
                boolean testIdFriend = TcpServer.clientHashMap.containsKey(idFriend);
                //
                LocalTime TimeStart = LocalTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
                PreparedStatement stmt = conn.prepareStatement("insert into Message(IdSession,Time,IdSender,TextMess) "
                        + "values(" + idSession + ",'" + TimeStart + "'," + idUser + ",N'" + tfInputMsg + "')", Statement.RETURN_GENERATED_KEYS);
                stmt.execute();
                ResultSet rs = stmt.getGeneratedKeys();
                System.out.println("detect success 1");
                while (rs.next()) {
                    if (testIdFriend == true) {
                        System.out.println("detect success 2");
                        Socket friendSocket = TcpServer.clientHashMap.get(idFriend);
                        DataOutputStream friendOut = new DataOutputStream(friendSocket.getOutputStream());
                        SendMessageRespond sendMessageRespond = new SendMessageRespond(RECEIVED_MESSAGE_ACTION,RECEIVED_MESSENGER_NOW,
                                                                rs.getInt(1),idSession,idUser,tfInputMsg,userName);
                        String json = TcpServer.mapper.writeValueAsString(sendMessageRespond);
                        friendOut.writeUTF(json);
                        friendOut.flush();
                        System.out.println("gửi lúc onl");
                    } else {
                        System.out.println("offline");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void handleChatGroup(ChatGroupRequest chatGroupRequest){
        int idUser = chatGroupRequest.getIdUser();
        int idSession = chatGroupRequest.getIdSession();
        String sessionName = chatGroupRequest.getSessionName();
        try {
            Statement loadMessageGroupStmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet loadMessageRs = loadMessageGroupStmt.executeQuery("select Message.Id,Message.TextMess,Message.IdSender,Users.TenTaiKhoan" +
                    " from Message, Users where IdSession = " + idSession + "and Users.IdUser = Message.IdSender");
            System.out.println("LoadMessage "+ loadMessageRs);
            loadMessageRs.last();
            loadMessageRs.beforeFirst();
            List<ChatMessage> messageList = new ArrayList<>();
            while (loadMessageRs.next()) {
                int idMsg = loadMessageRs.getInt(1);
                String textMsg = loadMessageRs.getString(2);
                int idSender = loadMessageRs.getInt(3);
                String userName = loadMessageRs.getString(4);

                ChatMessage chatMessage = new ChatMessage(idMsg,textMsg,idSender,userName);
                messageList.add(chatMessage);

            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select idUser \n"
                    + "from Participate \n"
                    + "where IdSession = " + idSession  );
            List<ChatGroupRespond.IdMember> idMembersList = new ArrayList<>();
            while (rs.next()){
                ChatGroupRespond.IdMember id1 = new ChatGroupRespond.IdMember(rs.getInt(1));
                idMembersList.add(id1);
            }

            ChatGroupRespond chatGroupRespond = new ChatGroupRespond(CHAT_GROUP_ACTION,idUser,idMembersList,sessionName,idSession,messageList);
            String json = TcpServer.mapper.writeValueAsString(chatGroupRespond);
            out.writeUTF(json);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void handleReceivedGroupMsg(SendGroupMessageRequest sendGroupMessageRequest){
        try {
            int idSession = sendGroupMessageRequest.getSessionID();
            String tfInputMsg = sendGroupMessageRequest.getTfInputMessage();
            int idUser = sendGroupMessageRequest.getIdUser();
            Statement stmtGroupList= conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rsLoadUserName = stmtGroupList.executeQuery("\tselect Users.TenTaiKhoan from Users where Users.IdUser = "+idUser);
            String userName ="";
            if (rsLoadUserName.next()){
                userName = rsLoadUserName.getString(1);
            }
            if(tfInputMsg.equalsIgnoreCase("")){
                System.out.println( "Ko có tn");
            }else {
                // Insert message to DB.
                LocalTime TimeStart = LocalTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
                PreparedStatement stmt = conn.prepareStatement("insert into Message(IdSession,Time,IdSender,TextMess) "
                        + "values(" + idSession + ",'" + TimeStart + "'," + idUser + ",N'" + tfInputMsg + "')", Statement.RETURN_GENERATED_KEYS);
                stmt.execute();
                ResultSet rs = stmt.getGeneratedKeys();
                int idMessage = 0;
                if (rs.next()){
                    idMessage = rs.getInt(1);
                }
                // Get list participants
                Statement stmt1 = conn.createStatement();
                ResultSet rs1 = stmt1.executeQuery("select idUser \n"
                        + "from Participate \n"
                        + "where IdSession = " + idSession +"and IdUser !="+ idUser );

                while (rs1.next()) {
                    int idMember = rs1.getInt(1);
                    System.out.println("friend Id " + idMember);
                    Socket memberSocket = TcpServer.clientHashMap.get(idMember);
                    if (memberSocket != null) {
                        System.out.println("Friend " + idMember + " is online");
                        DataOutputStream friendOut = new DataOutputStream(memberSocket.getOutputStream());
                        SendMessgeGroupRespond sendMessgeGroupRespond = new SendMessgeGroupRespond(RECEIVED_MESSAGE_GROUP_ACTION,
                                idMessage, idSession, idUser, tfInputMsg,userName);
                        String json = TcpServer.mapper.writeValueAsString(sendMessgeGroupRespond);
                        friendOut.writeUTF(json);
                        friendOut.flush();
                        System.out.println("gửi lúc onl");
                    } else {
                        System.out.println("Friend" + idMember + " is offline");
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            //takes input from client socket
            System.out.println("qqdsf");
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());

            System.out.println("1");
            while (true) {
                //read messenge from client
//                System.out.println("2");
                Thread.sleep(1000);
                if (in.available() > 0) {
                    String json = in.readUTF();
                    BasePayload basePayload = TcpServer.mapper.readValue(json, BasePayload.class);
                    int action = basePayload.getAction();
                    switch (action) {
                        case SIGN_UP_ACTION:
                            System.out.println("go to singup action" + action);
                            SignUpRequest signUpRequest = TcpServer.mapper.readValue(json, SignUpRequest.class);
                            handleSignUp(signUpRequest);
                            break;
                        case LOGIN_ACTION:
//                            Thread.sleep(5000);
                            System.out.println("go to login action" + action);
                            LogInRequest logInRequest = TcpServer.mapper.readValue(json, LogInRequest.class);
                            handleLogin(logInRequest);
                            break;

                        case LOAD_USER_lIST_ACTION:
                            System.out.println("go to search user list ");
                            LoadUserRequest loadUserRequest = TcpServer.mapper.readValue(json, LoadUserRequest.class);
                            handleSearchUserList(loadUserRequest);
                            break;
                        case SEARCH_FRIEND_LIST_ACTION:
                            System.out.println("go to search friend list");
                            SearchFriendListRequest searchFriendListRequest = TcpServer.mapper.readValue(json,SearchFriendListRequest.class);
                            handleSearchFriendList(searchFriendListRequest);
                            break;

                        case ADD_FRIEND_ACTION:
                            System.out.println("go to add friend ");
                            AddFriendRequest addFriendRequest = TcpServer.mapper.readValue(json,AddFriendRequest.class);
                            handleAddFriend(addFriendRequest);
                            break;

                        case LOAD_FRIEND_LIST_ACTION:
                            System.out.println("go to load Friends List ");
                            LoadFriendRequest loadFriendRequest = TcpServer.mapper.readValue(json, LoadFriendRequest.class);
                            handleLoadFriendList(loadFriendRequest);
                            break;
                        case LOAD_FRIEND_LIST_ACTION_TO_CREATE_GROUP:
                            System.out.println("go to load Friends List to create group ");
                            LoadFriendRequest loadFriendRequest1 = TcpServer.mapper.readValue(json, LoadFriendRequest.class);
                            handleLoadFriendListToCreateGroup(loadFriendRequest1);
                            break;
                        case LOAD_FRIEND_IN_MAIN_FRAME:
                            System.out.println("go to load friend in main frame");
                            LoadFriendRequest loadFriendRequest2 = TcpServer.mapper.readValue(json, LoadFriendRequest.class);
                            handleLoadListFriendInMainFrame(loadFriendRequest2);
                            break;
                        case CREATE_GROUP_ACTION:
                            System.out.println("create group");
                            CreateGroupRequest createGroupRequest = TcpServer.mapper.readValue(json,CreateGroupRequest.class);
                            handleCreateGroup(createGroupRequest);
                            break;
                        case LOAD_GROUP_ACTION:
                            System.out.println("Group List");
                            LoadGroupListRequest loadGroupListRequest = TcpServer.mapper.readValue(json,LoadGroupListRequest.class);
                            handleLoadGroupList(loadGroupListRequest);
                            break;
                        case CHAT_ACTION:
                            System.out.println("go to chat ");
                            ChatRequest chatRequest = TcpServer.mapper.readValue(json, ChatRequest.class);
                            handleChat(chatRequest);
                            break;
                        case CHAT_ACTION_MAIN:
                            System.out.println("go to chat in main frame");
                            ChatRequest chatRequestMainFrame = TcpServer.mapper.readValue(json, ChatRequest.class);
                            handleChatInMain(chatRequestMainFrame);
                            break;
                        case SEND_MESSAGE_ACTION:
                            System.out.println("go to recived Message Action");
                            SendMessageRequest sendMessageRequest = TcpServer.mapper.readValue(json,SendMessageRequest.class);
                            handleReceivedMsg(sendMessageRequest);
                            break;
                        case CHAT_GROUP_ACTION:
                            System.out.println("go to chat group");
                            ChatGroupRequest chatGroupRequest = TcpServer.mapper.readValue(json,ChatGroupRequest.class);
                            handleChatGroup(chatGroupRequest);
                            break;
                        case SEND_MESSAGE_GROUP_ACTION:
                            System.out.println("go to received Message Action");
                            SendGroupMessageRequest sendGroupMessageRequest = TcpServer.mapper.readValue(json,SendGroupMessageRequest.class);
                            handleReceivedGroupMsg(sendGroupMessageRequest);
                            break;

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
