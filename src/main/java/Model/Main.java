package Model;

import Model.IO.FxmlLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Server.Server;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static User ME;
    private static final String USERS_FILE_URL = "src/main/resources/users.ser";

    public static void main(String[] args) {
/*    try {
      FileOutputStream fileOut =
          new FileOutputStream("./src/main/resources/users.ser");
      List<User> users = new ArrayList<User>();
      users.add(new User("Ham3d", "hamed@yahoo.com", "1985/08/07", "159753215",
          "./src/main/resources/Images/Ham3d.jpg"));
      users.add(new User("zivdar001matin", "zivdar1matin@gmail.com", "1995/08/07", "1651380",
          "./src/main/resources/Images/zivdar001matin.jpg"));
      users.add(new User("Mrseyyed2", "Mrseyyed2@gmail.com", "1995/08/07", "15428",
          "./src/main/resources/Images/Mrseyyed2.jpg"));
      users.add(new User("OmidIravani", "omid_ir@yahoo.com", "1987/08/07", "mafew45"));
      users.add(new User("Mahanmmi", "mahan@gmail.com", "1988/08/07", "hdh754",
          "./src/main/resources/Images/Mahanmmi.jpg"));
      users.add(new User("RoubekhodaAR", "fooladcity@yahoo.com", "1990/08/07", "jfskf556",
          "./src/main/resources/Images/RoubekhodaAR.jpg"));
      users.add(new User("Mamadia", "mammmadiauo@gmail.com", "1992/08/07", "hdsjds",
          "./src/main/resources/Images/Mamadia.jpg"));
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(users);
      out.close();
      fileOut.close();
      System.out.printf("Serialized data is saved in /tmp/employee.ser");
    } catch (IOException i) {
      i.printStackTrace();
    }*/
//        try {
////            ALL_USERS.init();
//            FileOutputStream fileOut =
//                    new FileOutputStream("./src/main/resources/messages.ser");
//            List<Message> messages = new ArrayList<>();
////            messages.add(new Message(ALL_USERS.getAllUsers().get(0), ALL_USERS.getAllUsers().get(1), "2019/07/21/13:33", "This Should not be Shown", "Go and F*** yourself"));
////            messages.add(new Message(ALL_USERS.getAllUsers().get(0), ALL_USERS.getAllUsers().get(2), "2019/06/09/8:33", "Hala FIFA", "Lanat b PES!!!"));
////            messages.add(new Message(ALL_USERS.getAllUsers().get(1), ALL_USERS.getAllUsers().get(3), "2018/11/27/9:46", "Save ServerMessage", "I want to save this message"));
////            messages.add(new Message(ALL_USERS.getAllUsers().get(2), ALL_USERS.getAllUsers().get(4), "2017/08/10/10:23", "Alan", "Darim mirim k ronaldo ro pack kninm."));
////            messages.add(new Message(ALL_USERS.getAllUsers().get(3), ALL_USERS.getAllUsers().get(5), "2011/07/10/4:56", "Test", "This is just for testing."));
////            messages.add(new Message(ALL_USERS.getAllUsers().get(4), ALL_USERS.getAllUsers().get(1), "2012/04/27/23:59", "Echte Liebe.", "Hummels raftesh bayern :/"));
////            messages.add(new Message(ALL_USERS.getAllUsers().get(5), ALL_USERS.getAllUsers().get(6), "2019/02/13/00:00", "Lool", "00:00"));
////            messages.add(new Message(ALL_USERS.getAllUsers().get(6), ALL_USERS.getAllUsers().get(1), "2011/03/20/15:03", "Echte Liebe.", "Realo 3:1 bordim :)))"));
////            messages.add(new Message(ALL_USERS.getAllUsers().get(1), ALL_USERS.getAllUsers().get(4), "2012/02/30/17:21", "Null", "chizi b zehnaam nmirese"));
////            messages.add(new Message(ALL_USERS.getAllUsers().get(1), ALL_USERS.getAllUsers().get(2), "2013/10/07/13:09", "Null2", "baz ham chizi b zehnam nmirese"));
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(messages);
//            out.close();
//            fileOut.close();
////            System.out.printf("Serialized data is saved in /tmp/employee.ser");
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        launch(args);
    }

    @Override
    public void init() throws IOException, ClassNotFoundException {
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(USERS_FILE_URL));
//        objectOutputStream.writeObject(ALL_USERS.getAllUsers());
//        objectOutputStream.close();
        Server.start();
        ALL_USERS.init();
        ALL_MESSAGES.init();
//        ME = ALL_USERS.getAllUsers().get(1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //init stage using FXMLLoader
        FxmlLoader.setMainStage(primaryStage);
        //load "./src/main/java/View/UserMainPage.fxml" using FxmlLoader
        new FxmlLoader().load("./src/main/java/View/SignInPage.fxml");
    }

    @Override
    public void stop() throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(USERS_FILE_URL));
        objectOutputStream.writeObject(ALL_USERS.getAllUsers());
        objectOutputStream.close();
    }
}
