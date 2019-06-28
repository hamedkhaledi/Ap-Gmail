package Model;

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

    public static List<Message> getAllMessages() {
        return ALL_MESSAGES;
    }
}
