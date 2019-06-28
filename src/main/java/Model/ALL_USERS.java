package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import static Model.Main.ME;

public class ALL_USERS {
    private static final String USERS_FILE_URL = "src/main/resources/users.ser";
    private static List<User> ALL_USERS = new ArrayList<>();
    public static User ClientTemp;

    public static void init()
            throws IndexOutOfBoundsException, IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(USERS_FILE_URL));
        ALL_USERS = (List<User>) objectInputStream.readObject();
        objectInputStream.close();

    }

    public static List<User> getAllUsers() {
        return ALL_USERS;
    }
}