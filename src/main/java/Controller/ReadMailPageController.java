package Controller;

import Model.ALL_MESSAGES;
import Model.ALL_USERS;
import Model.IO.FxmlLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;

public class ReadMailPageController {
    public Label From;
    public Label Subject;
    public TextArea Text;
    public ImageView Image;
    public ImageView Attachment;
    public static boolean isReply;
    public static boolean isForward;

    public void initialize() {
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
        if (!ALL_MESSAGES.ClientMessage.getAttachment().isEmpty()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(Paths.get(ALL_MESSAGES.ClientMessage.getAttachment()).toFile());
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
        ALL_USERS.ClientTemp.getBlackList().add(ALL_MESSAGES.ClientMessage.getSender());
    }
}
