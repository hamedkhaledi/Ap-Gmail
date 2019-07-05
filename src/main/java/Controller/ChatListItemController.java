package Controller;

import Model.ALL_MESSAGES;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import Model.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import static Model.ALL_USERS.ClientTemp;

public class ChatListItemController {

    private Message message;
    public static Message toRead;

    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane Pane1;
    @FXML
    private AnchorPane Pane2;
    @FXML
    private Label Text;
    @FXML
    private Label Date;
    @FXML
    private Label From;
    @FXML
    private Label Subject;
    @FXML
    private Button starButton;
    @FXML
    private ImageView Image;
    @FXML
    private ImageView readedStatusImage;
    @FXML
    private Label Text1;
    @FXML
    private Label Date1;
    @FXML
    private Label From1;
    @FXML
    private Label Subject1;
    @FXML
    private Button starButton1;
    @FXML
    private ImageView Image1;
    @FXML
    private ImageView readedStatusImage1;

    // @FXML
    // private Button subjectButton;

    public ChatListItemController(Message message) throws IOException {
        this.message = message;
        new FxmlLoader().load("./src/main/java/View/ChatListItem.fxml", this);
    }

    public AnchorPane init() {
        Subject.setText(message.getSubject());
        Text.setText(message.getText());
        Date.setText(message.getTime());
        From.setText(message.getSender().getUsername() + "@gmail.com");
        if (message.isReaded()) {
            readedStatusImage1.setImage(new Image(
                    Paths.get("./src/main/resources/Icons/readedMessage.png").toUri().toString()));
        } else {
            readedStatusImage1.setImage(new Image(
                    Paths.get("./src/main/resources/Icons/unreadMessage.png").toUri().toString()));
        }
        if (message.isReaded()) {
            readedStatusImage.setImage(new Image(
                    Paths.get("./src/main/resources/Icons/readedMessage.png").toUri().toString()));
        } else {
            readedStatusImage.setImage(new Image(
                    Paths.get("./src/main/resources/Icons/unreadMessage.png").toUri().toString()));
        }
        Image.setImage(new Image(
                Paths.get(message.getSender().getImagePath()).toUri().toString()));
        Subject1.setText(message.getSubject());
        Text1.setText(message.getText());
        Date1.setText(message.getTime());
        From1.setText("From : " + message.getSender().getUsername() + "@gmail.com");
        Image1.setImage(new Image(
                Paths.get(message.getSender().getImagePath()).toUri().toString()));

        if (message.getSender().equals(ClientTemp)) {
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
            //
            if (message.isImportantForMe()) {
                starButton1.setStyle("-icon-paint: blue;\n"
                        + "-fx-background-color: -icon-paint;\n"
                        + "-fx-border-style: solid;\n"
                        + "-fx-min-height: 20;\n"
                        + "-fx-max-height: 20;\n"
                        + "-fx-min-width: 20;\n"
                        + "-fx-max-width: 20;\n"
                        + "-fx-shape:  \"M 0.000 5.000L 5.878 8.090L 4.755 1.545L 9.511 -3.090L 2.939 -4.045L 0.000 -10.000L -2.939 -4.045L -9.511 -3.090L -4.755 1.545L -5.878 8.090L 0.000 5.000\";\n");
            }
            Pane1.setVisible(false);
            Pane2.setVisible(true);
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
            //
            if (message.isImportant()) {
                starButton1.setStyle("-icon-paint: blue;\n"
                        + "-fx-background-color: -icon-paint;\n"
                        + "-fx-border-style: solid;\n"
                        + "-fx-min-height: 20;\n"
                        + "-fx-max-height: 20;\n"
                        + "-fx-min-width: 20;\n"
                        + "-fx-max-width: 20;\n"
                        + "-fx-shape:  \"M 0.000 5.000L 5.878 8.090L 4.755 1.545L 9.511 -3.090L 2.939 -4.045L 0.000 -10.000L -2.939 -4.045L -9.511 -3.090L -4.755 1.545L -5.878 8.090L 0.000 5.000\";\n");
            }

            Pane2.setVisible(false);
            Pane1.setVisible(true);
        }

        return root;
    }

    public void importantChanger(ActionEvent event) throws IOException {
        if (message.getSender().equals(ClientTemp)) {
            if (message.isImportantForMe()) {
                starButton1.setStyle("-icon-paint: white;\n"
                        + "-fx-background-color: -icon-paint;\n"
                        + "-fx-border-style: solid;\n"
                        + "-fx-min-height: 20;\n"
                        + "-fx-max-height: 20;\n"
                        + "-fx-min-width: 20;\n"
                        + "-fx-max-width: 20;\n"
                        + "-fx-shape:  \"M 0.000 5.000L 5.878 8.090L 4.755 1.545L 9.511 -3.090L 2.939 -4.045L 0.000 -10.000L -2.939 -4.045L -9.511 -3.090L -4.755 1.545L -5.878 8.090L 0.000 5.000\";\n");
            } else {
                starButton1.setStyle("-icon-paint: blue;\n"
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
