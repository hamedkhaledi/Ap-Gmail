package Server;

import Model.User;
import ViewModel.MessageType;
import ViewModel.ServerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ServerHandler {
    public static Semaphore semaphore = new Semaphore(1);
    static int size = 0;
    static List<User> users = new ArrayList<>();
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    ServerHandler(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }


    void handle(ServerMessage message) throws IOException {
        switch (message.getMessageType()) {
            case Connect:
                User user = new User(message.getSender(), inputStream, outputStream);
                users.add(user);
                System.out.println(1);
                break;
            case Disconnect:
                User usertemp = new User(message.getSender(), null, null);
                users.remove(usertemp);
                break;
            case Text:
                User tempUser = new User(message.getReceiver(), null, null);
                User user2 = new User(message.getSender(), null, null);
                if (users.contains(tempUser)) {
                    users.get(users.indexOf(tempUser)).getOutputStream().writeObject(message);
                } else {
                    ServerMessage TempMessege = new ServerMessage(MessageType.Error, message.getSender(), message.getReceiver(), "");
                    users.get(users.indexOf(user2)).getOutputStream().writeObject(TempMessege);
                }
                break;
        }
    }
}
