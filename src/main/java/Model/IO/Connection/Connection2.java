package Model.IO.Connection;

import Controller.ReadMailPageController;
import Model.ALL_MESSAGES;

import java.awt.*;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


//for Read file


public class Connection2 {
    private static final int PORT = 2001;

    public static void main(String[] args) throws IOException {
        System.out.println("Yes");
        ServerSocket server = new ServerSocket(PORT);
        new DBThread(server.accept()).Start();
    }
}

class DBThread extends Thread {
    private static String DB_ROOT = "./src/main/resources/Files/";
    private Socket socket;
    private DataInputStream in;
    private OutputStream out;
    private String fileName;

    public DBThread(Socket socket) throws IOException {
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        fileName = in.readUTF();
        out = new FileOutputStream(DB_ROOT + fileName);
        System.out.println(DB_ROOT + fileName);
    }

    public void Start() {
        try {
            int readBytes;
            byte[] buffer = new byte[2048];
            while ((readBytes = in.read(buffer)) > 0) {
                out.write(buffer, 0, readBytes);
                out.flush();
            }
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            int readBytes;
            byte[] buffer = new byte[2048];
            while ((readBytes = in.read(buffer)) > 0) {
                out.write(buffer, 0, readBytes);
                out.flush();
            }
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() throws IOException {
        in.close();
        out.close();
        socket.close();

    }
}