package Model.IO.Connection;

import Model.ALL_USERS;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import Model.Message;
import Model.User;


import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.*;
import java.net.Socket;
import java.util.List;


public class Connection {
    private User currentuser;
    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public static final int requestPort = 1378;
    public static final String serverIP = "localhost";

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public Connection(User currentUser) {
        this.currentuser = currentUser;

        try {
            client = new Socket(serverIP, requestPort);
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
            //sendRequest(new ServerMessage(MessageType.Connect, currentUser, null, null));
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }

    public Connection() {

    }

    public void disconnect() throws IOException {
        sendRequest(new ServerMessage(MessageType.Disconnect, currentuser, null, null));
        in.close();
        out.close();
        client.close();
    }

    public void initializeServices() {
        Thread listenerThread = new Thread(new ListenerService(this), "Listener Thread");
        listenerThread.setDaemon(true);
        listenerThread.start();
    }

    public void sendRequest(ServerMessage request) {
        try {
            out.writeObject(request);
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }

    public void sendText(Message message, User receiver) {
        sendRequest(new ServerMessage(MessageType.Send, currentuser, receiver, message));
    }

    public String getRespond() {
        try {
            //       System.out.println(in.readObject());
            ALL_USERS.setAllUsers((List<User>) in.readObject());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/users.ser"));
            objectOutputStream.writeObject(ALL_USERS.getAllUsers());
            objectOutputStream.close();
            return "";
            //  return ClientMessageHandler.handle((ServerMessage) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            //    System.out.println("Exception");
            return "";
        }
    }
    //private static final int PORT = 8888;
    //private static final String IP = "localhost";
    //private static Socket socket;
    //private static InputStream in;
    //private static DataOutputStream out;


//
//    public static void sendFile(Message message) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    this.writeData(message);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    public void writeData(Message message) throws IOException {
//        System.out.println("writing data...");
//        //out = new DataOutputStream(socket.getOutputStream());
//        FileInputStream InFile = new FileInputStream(message.getAttachment());
//        System.out.println(message.getAttachment());
//        File file = new File(message.getAttachment());
//        this.getOut().writeUTF(file.getName());
//        System.out.println(file.getName());
//        int readBytes;
//        byte[] buffer = new byte[2048];
//        while ((readBytes = in.read(buffer)) > 0) {
//            out.write(buffer, 0, readBytes);
//            out.flush();
//        }
//    }
//


//
//    public static void connect() throws IOException {
//        socket = new Socket(IP, PORT);
//    }
//        private static void disconnect() throws IOException {
//        in.close();
//        out.close();
//        socket.close();
//}

}
