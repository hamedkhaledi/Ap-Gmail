package Controller;

import Model.*;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static Model.ALL_USERS.ClientTemp;
import static Model.Main.ME;

public class SendMailPageController {
    public AnchorPane Pane;
    public ImageView Attachment;
    @FXML
    private TextField To;
    @FXML
    private TextField Text;
    @FXML
    private TextField Subject;
    private String AttachURL = "";

    public void initialize() {
        To.setText("");
        To.setEditable(true);
        Text.setEditable(true);
        Text.setText("");
        Subject.setEditable(true);
        Subject.setText("");
        AttachURL = "";
        Attachment.setDisable(false);
        if (ReadMailPageController.isReply) {
            To.setText(ALL_MESSAGES.ClientMessage.getSender().getUsername());
            To.setEditable(false);
        }

        if (ReadMailPageController.isForward) {
            Text.setText(ALL_MESSAGES.ClientMessage.getText());
            Text.setEditable(false);
            Subject.setText(ALL_MESSAGES.ClientMessage.getSubject());
            Subject.setEditable(false);
            Attachment.setDisable(true);
            if (ALL_MESSAGES.ClientMessage.getAttachment() != null) {
                AttachURL = ALL_MESSAGES.ClientMessage.getAttachment();
            }
        }
    }

    public void Attach(MouseEvent mouseEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) Pane.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null)
            AttachURL = selectedFile.getCanonicalPath();
        else
            AttachURL = "";
        System.out.println(AttachURL);
    }

    public void Send(MouseEvent mouseEvent) throws IOException {
        FileOutputStream fileOut =
                new FileOutputStream("./src/main/resources/messages.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        Message message;
        if (ALL_USERS.getAllUsers().contains(new User(To.getText()))) {
            User Temp = ALL_USERS.getAllUsers().get(ALL_USERS.getAllUsers().indexOf(new User(To.getText())));
            if (AttachURL.isEmpty()) {
                message = new Message(ClientTemp, Temp, getCurrentTimeUsingCalendar(), Subject.getText(), Text.getText());
            } else {
                message = new Message(ClientTemp, Temp, getCurrentTimeUsingCalendar(), Subject.getText(), Text.getText(), AttachURL);
//                Connection.sendFile(message);
            }
            if (ReadMailPageController.isForward)
                SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Forward, ClientTemp, Temp, message));
            else if (ReadMailPageController.isReply)
                SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Reply, ClientTemp, Temp, message));
            else
            SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Send, ClientTemp, Temp, message));
            //          ALL_MESSAGES.getAllMessages().add(message);
            //out.writeObject(ALL_MESSAGES.getAllMessages());
            //out.close();
            //fileOut.close();
            System.out.println(2);
            new FxmlLoader().load("./src/main/java/View/EmailPage.fxml");
            System.out.println(3);
        } else {
//TODO
        }
    }

    public void quit(MouseEvent mouseEvent) throws IOException {
        new FxmlLoader().load("./src/main/java/View/EmailPage.fxml");
    }


    public static String getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
        return dateFormat.format(date);
    }
}

