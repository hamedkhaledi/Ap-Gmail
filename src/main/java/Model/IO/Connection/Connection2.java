package Model.IO.Connection;

import Controller.ReadMailPageController;
import Controller.SignInPageController;
import Model.ALL_MESSAGES;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import Model.Message;
import javafx.concurrent.Task;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Model.ALL_USERS.ClientTemp;


//for Read file


public class Connection2 {
    private static final int PORT = 2001;
    private static String DB_ROOT = "./src/main/resources/Files/";
    private static Socket socket;
    private static DataInputStream in;
    private static ObjectOutputStream out;
    private static OutputStream out2;
    private static String fileName;

    public static void Main() throws IOException, InterruptedException {
        System.out.println("Yes");
        //   ServerSocket server = new ServerSocket(PORT);
        try {
            connect();
            ReadData(ALL_MESSAGES.ClientMessage);
            disconnect();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void ReadData(Message message) throws IOException, InterruptedException {
        in = new DataInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(message);
        fileName = in.readUTF();
        out2 = new FileOutputStream(DB_ROOT + fileName);
        System.out.println(DB_ROOT + fileName);
        System.out.println("Yes2");
        try {
            int readBytes;
            byte[] buffer = new byte[2048];
            while ((readBytes = in.read(buffer)) > 0) {
                out2.write(buffer, 0, readBytes);
                out2.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connect() throws IOException {
        socket = new Socket(Connection.serverIP, PORT);

    }

    private static void disconnect() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

}
