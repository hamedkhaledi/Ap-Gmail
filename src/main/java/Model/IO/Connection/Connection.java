package Model.IO.Connection;

import Model.ALL_USERS;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import Model.Message;
import Model.User;


import java.io.*;
import java.net.Socket;
import java.util.List;


public class Connection {
    private User currentuser;
    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public static final int requestPort = 1379;
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
            throw new ServerConnectionException();
        }
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
            throw new ServerConnectionException();
        }
    }

    public void sendText(Message message, User receiver) {
        sendRequest(new ServerMessage(MessageType.Send, currentuser, receiver, message));
    }

    public String getRespond() {
        try {
            ALL_USERS.setAllUsers((List<User>) in.readObject());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/users.ser"));
            objectOutputStream.writeObject(ALL_USERS.getAllUsers());
            objectOutputStream.close();
            return "";
            //  return ClientMessageHandler.handle((ServerMessage) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new ServerConnectionException();
        }
    }
}

