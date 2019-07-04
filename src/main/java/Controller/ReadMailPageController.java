package Controller;

import Model.ALL_MESSAGES;
import Model.ALL_USERS;
import Model.IO.Connection.Connection;
import Model.IO.Connection.Connection2;
import Model.IO.Connection.Connection3;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import Model.Main;
import com.sun.javafx.tk.Toolkit;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

import static Model.ALL_USERS.ClientTemp;

public class ReadMailPageController {
    public Label From;
    public Label Subject;
    public TextArea Text;
    public ImageView Image;
    public ImageView Attachment;
    public static boolean isReply;
    public static boolean isForward;
    private static String DB_ROOT = "./src/main/resources/Files/";
    public AnchorPane ProgressPane;
    public static Exchanger<String> e = new Exchanger<>();
    public ImageView BlockIcon;

    public void initialize() {
        ProgressPane.setVisible(false);
        String Address = "./src/main/resources/Images/Faces/" + ALL_MESSAGES.ClientMessage.getSender().getImageNumber() + ".png";
        Subject.setText(ALL_MESSAGES.ClientMessage.getSubject());
        From.setText(ALL_MESSAGES.ClientMessage.getSender().getUsername() + "@gmail.com");
        Text.setText(ALL_MESSAGES.ClientMessage.getText());
        Image.setImage(new Image(Paths.get(Address).toUri().toString()));
        if (ALL_MESSAGES.ClientMessage.getAttachment() == null)
            Attachment.setDisable(true);
        else
            Attachment.setDisable(false);
    }

    public void Attach(MouseEvent mouseEvent) throws IOException {
        Task<String> task = null;
        if (!ALL_MESSAGES.ClientMessage.getAttachment().isEmpty()) {
            ProgressPane.setVisible(true);
            File f = new File(DB_ROOT + ALL_MESSAGES.ClientMessage.getFileName());
            if (!f.exists()) {
                task = new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        Connection2.main(new String[5]);
                        return "";
                    }
                };
                new Thread(task).start();
                SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.OpenFile, ClientTemp, null, ALL_MESSAGES.ClientMessage));
            }
            if (task != null)
                task.setOnSucceeded(e -> {
                    try {
                        Openfile();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
            else
                Openfile();
        }
    }

    public void quit(MouseEvent mouseEvent) throws IOException {
        new FxmlLoader().load("./src/main/java/View/EmailPage.fxml");
    }

    public void reply(MouseEvent mouseEvent) throws IOException {
        isReply = true;
        new FxmlLoader().load("./src/main/java/View/SendMailPage.fxml");
    }

    public void Forward(MouseEvent mouseEvent) throws IOException {
        isForward = true;
        new FxmlLoader().load("./src/main/java/View/SendMailPage.fxml");
    }

    public void Block(MouseEvent mouseEvent) {
        BlockIcon.setVisible(false);
        ALL_USERS.ClientTemp.getBlackList().add(ALL_MESSAGES.ClientMessage.getSender());
        Main.ConnectionTemp.sendRequest(new ServerMessage(MessageType.Block, ClientTemp, ALL_MESSAGES.ClientMessage.getSender(), null));
    }

    public void Openfile() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(Paths.get(DB_ROOT + ALL_MESSAGES.ClientMessage.getFileName()).toFile());
        ProgressPane.setVisible(false);
    }
}
