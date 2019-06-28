package Model.IO;

import Model.User;
import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.*;
import java.net.Socket;

public class Connection {
    private static final int PORT = 1379;
    private static final String IP = "localhost";
    private static Socket socket;
    private static InputStream in;
    private static DataOutputStream out;

    public static void sendFile(final User user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connect();
                    writeData(user);
                    disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void writeData(User user) throws IOException {
        System.out.println("writing data...");
        out = new DataOutputStream(socket.getOutputStream());
        in = new FileInputStream(user.getImagePath());
        //out.writeUTF(user.getImageFileName());
        int readBytes;
        byte[] buffer = new byte[2048];
        while ((readBytes = in.read(buffer)) > 0) {
            out.write(buffer, 0, readBytes);
            out.flush();
        }

    }

    private static void connect() throws IOException {
        socket = new Socket(IP, PORT);
    }

    private static void disconnect() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

}
