package Model;

import Model.IO.Connection.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ALL_MESSAGES {
    private static final String MESSAGES_FILE_URL = "src/main/resources/messages.ser";
    private static List<Message> ALL_MESSAGES = new ArrayList<>();
    public static Message ClientMessage;

    public static void init()
            throws IndexOutOfBoundsException, IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(MESSAGES_FILE_URL));
        ALL_MESSAGES = (List<Message>) objectInputStream.readObject();
        objectInputStream.close();
    }

    public static void initUser(Connection connection)
            throws IndexOutOfBoundsException, IOException, ClassNotFoundException {
        ALL_MESSAGES = (List<Message>) connection.getIn().readObject();
        System.out.println(ALL_MESSAGES);
    }

    public static List<Message> getAllMessages() {
        return ALL_MESSAGES;
    }
}
