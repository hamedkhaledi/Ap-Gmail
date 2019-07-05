package Model.IO.Connection;

import Model.Message;
import Model.User;
import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.*;
import java.net.Socket;

//for send file
public class Connection3 {
    private static final int PORT = 2000;
    public static String IP = "localhost";
    private static Socket socket;
    private static InputStream in;
    private static DataOutputStream out;

    public static void sendFile(Message message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connect();
                    writeData(message);
                    disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void writeData(Message message) throws IOException {
        System.out.println("writing data...");
        out = new DataOutputStream(socket.getOutputStream());
        in = new FileInputStream(message.getAttachment());
        System.out.println(message.getAttachment());
        File file = new File(message.getAttachment());
        out.writeUTF(file.getName());
        System.out.println(file.getName());
        int readBytes;
        byte[] buffer = new byte[2048];
        while ((readBytes = in.read(buffer)) > 0) {
            out.write(buffer, 0, readBytes);
            out.flush();
        }

    }

    public static void connect() throws IOException {
        socket = new Socket(IP, PORT);
    }

    private static void disconnect() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

}
