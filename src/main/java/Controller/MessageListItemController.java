package Controller;

import static Model.ALL_USERS.ClientTemp;
import static Model.Main.ME;

import Model.ALL_MESSAGES;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import Model.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MessageListItemController {

    private Message message;
    public static Message toRead;

    @FXML
    private AnchorPane root;
    @FXML
    private Label textLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label From;
    @FXML
    private Button starButton;
    @FXML
    private Label removeLabel;
    @FXML
    private Button removeButton;
    @FXML
    private ImageView statusImage;
    @FXML
    private ImageView readedStatusImage;
    @FXML
    private ImageView Image1;
    @FXML
    private Button subjectButton;
    @FXML
    private Button readButton;

    public MessageListItemController(Message message) throws IOException {
        this.message = message;
        new FxmlLoader().load("./src/main/java/View/MessageListItem.fxml", this);
    }

    public AnchorPane init() {
        if (message.getSender().equals(ClientTemp)) {
            readedStatusImage.setVisible(false);
            readButton.setVisible(false);
            From.setText("To : " + message.getReciever().getUsername() + "@gmail.com");
            Image1.setImage(new Image(Paths.get(message.getReciever().getImagePath()).toUri().toString()));
            if (message.isImportantForMe()) {
                starButton.setStyle("-icon-paint: blue;\n"
                        + "-fx-background-color: -icon-paint;\n"
                        + "-fx-border-style: solid;\n"
                        + "-fx-min-height: 20;\n"
                        + "-fx-max-height: 20;\n"
                        + "-fx-min-width: 20;\n"
                        + "-fx-max-width: 20;\n"
                        + "-fx-shape:  \"M 0.000 5.000L 5.878 8.090L 4.755 1.545L 9.511 -3.090L 2.939 -4.045L 0.000 -10.000L -2.939 -4.045L -9.511 -3.090L -4.755 1.545L -5.878 8.090L 0.000 5.000\";\n");
            }
        } else {
            if (message.isImportant()) {
                starButton.setStyle("-icon-paint: blue;\n"
                        + "-fx-background-color: -icon-paint;\n"
                        + "-fx-border-style: solid;\n"
                        + "-fx-min-height: 20;\n"
                        + "-fx-max-height: 20;\n"
                        + "-fx-min-width: 20;\n"
                        + "-fx-max-width: 20;\n"
                        + "-fx-shape:  \"M 0.000 5.000L 5.878 8.090L 4.755 1.545L 9.511 -3.090L 2.939 -4.045L 0.000 -10.000L -2.939 -4.045L -9.511 -3.090L -4.755 1.545L -5.878 8.090L 0.000 5.000\";\n");
            }
            From.setText("From : " + message.getSender().getUsername() + "@gmail.com");
            if (message.isReaded()) {
                readedStatusImage.setImage(new Image(
                        Paths.get("./src/main/resources/Icons/readedMessage.png").toUri().toString()));
            } else {
                readedStatusImage.setImage(new Image(
                        Paths.get("./src/main/resources/Icons/unreadMessage.png").toUri().toString()));
            }
            Image1.setImage(new Image(Paths.get(message.getSender().getImagePath()).toUri().toString()));
        }
        subjectButton.setText(message.getSubject());
        textLabel.setText(message.getText());
        timeLabel.setText(message.getTime());
        return root;
    }

    public void importantChanger(ActionEvent event) throws IOException {
        if (message.getSender().equals(ClientTemp)) {
            if (message.isImportantForMe()) {
                starButton.setStyle("-icon-paint: white;\n"
                        + "-fx-background-color: -icon-paint;\n"
                        + "-fx-border-style: solid;\n"
                        + "-fx-min-height: 20;\n"
                        + "-fx-max-height: 20;\n"
                        + "-fx-min-width: 20;\n"
                        + "-fx-max-width: 20;\n"
                        + "-fx-shape:  \"M 0.000 5.000L 5.878 8.090L 4.755 1.545L 9.511 -3.090L 2.939 -4.045L 0.000 -10.000L -2.939 -4.045L -9.511 -3.090L -4.755 1.545L -5.878 8.090L 0.000 5.000\";\n");
            } else {
                starButton.setStyle("-icon-paint: blue;\n"
                        + "-fx-background-color: -icon-paint;\n"
                        + "-fx-border-style: solid;\n"
                        + "-fx-min-height: 20;\n"
                        + "-fx-max-height: 20;\n"
                        + "-fx-min-width: 20;\n"
                        + "-fx-max-width: 20;\n"
                        + "-fx-shape:  \"M 0.000 5.000L 5.878 8.090L 4.755 1.545L 9.511 -3.090L 2.939 -4.045L 0.000 -10.000L -2.939 -4.045L -9.511 -3.090L -4.755 1.545L -5.878 8.090L 0.000 5.000\";\n");
            }
            SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Star, ClientTemp, null, message));
            message.setImportantForMe(!message.isImportantForMe());

        } else {
            if (message.isImportant()) {
                starButton.setStyle("-icon-paint: white;\n"
                        + "-fx-background-color: -icon-paint;\n"
                        + "-fx-border-style: solid;\n"
                        + "-fx-min-height: 20;\n"
                        + "-fx-max-height: 20;\n"
                        + "-fx-min-width: 20;\n"
                        + "-fx-max-width: 20;\n"
                        + "-fx-shape:  \"M 0.000 5.000L 5.878 8.090L 4.755 1.545L 9.511 -3.090L 2.939 -4.045L 0.000 -10.000L -2.939 -4.045L -9.511 -3.090L -4.755 1.545L -5.878 8.090L 0.000 5.000\";\n");
            } else {
                starButton.setStyle("-icon-paint: blue;\n"
                        + "-fx-background-color: -icon-paint;\n"
                        + "-fx-border-style: solid;\n"
                        + "-fx-min-height: 20;\n"
                        + "-fx-max-height: 20;\n"
                        + "-fx-min-width: 20;\n"
                        + "-fx-max-width: 20;\n"
                        + "-fx-shape:  \"M 0.000 5.000L 5.878 8.090L 4.755 1.545L 9.511 -3.090L 2.939 -4.045L 0.000 -10.000L -2.939 -4.045L -9.511 -3.090L -4.755 1.545L -5.878 8.090L 0.000 5.000\";\n");
            }
            SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Star, ClientTemp, null, message));
            message.setImportant(!message.isImportant());
        }
    }

    public void deleteMessage(ActionEvent event) throws IOException {
        if (message.getSender().equals(ClientTemp)) {
            if (!message.isRemovedForMe()) {
                SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Delete, ClientTemp, null, message));
                message.setRemovedForMe(true);
                removeLabel.setVisible(true);
            }
        } else {
            if (!message.isRemoved()) {
                SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Delete, ClientTemp, null, message));
                message.setRemoved(true);
                removeLabel.setVisible(true);
            }
        }
    }

    public void openMessage(ActionEvent event) throws IOException {
        ALL_MESSAGES.ClientMessage = message;
        new FxmlLoader().load("./src/main/java/View/ReadMailPage.fxml");
        message.setReaded(true);
        SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Read, ClientTemp, null, message));
        readedStatusImage.setImage(new Image(
                Paths.get("./src/main/resources/Icons/readedMessage.png").toUri().toString()));
        FileOutputStream fileOut =
                new FileOutputStream("./src/main/resources/messages.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(ALL_MESSAGES.getAllMessages());
        out.close();
        fileOut.close();
    }

    public void ChangeRead(ActionEvent event) throws IOException {
        message.setReaded(!message.isReaded());
        if (message.isReaded()) {
            readedStatusImage.setImage(new Image(
                    Paths.get("./src/main/resources/Icons/readedMessage.png").toUri().toString()));
            SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Unread, ClientTemp, null, message));
        } else {
            readedStatusImage.setImage(new Image(
                    Paths.get("./src/main/resources/Icons/unreadMessage.png").toUri().toString()));
            SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Read, ClientTemp, null, message));

        }
        message.setImportant(!message.isReaded());
    }
}
