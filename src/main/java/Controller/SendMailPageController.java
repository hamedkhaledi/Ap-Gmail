package Controller;

import Model.*;
import Model.IO.Connection.Connection;
import Model.IO.Connection.Connection2;
import Model.IO.Connection.Connection3;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import javafx.concurrent.Task;
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
    private String FileName;
    public AnchorPane ProgressPane;

    public void initialize() {
        To.setText("");
        To.setEditable(true);
        Text.setEditable(true);
        Text.setText("");
        Subject.setEditable(true);
        Subject.setText("");
        AttachURL = "";
        Attachment.setDisable(false);
        ProgressPane.setVisible(false);
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
        if (selectedFile != null) {
            AttachURL = selectedFile.getCanonicalPath();
            FileName = selectedFile.getName();
        } else
            AttachURL = "";
        System.out.println(AttachURL);
    }

    public void Send(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        Main.ConnectionTemp = new Connection(SignUpPageController.Temp);
        Main.ConnectionTemp.initializeServices();
        SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Reload, ClientTemp, null, null));
        ALL_MESSAGES.initUser(SignInPageController.ConnectionSign);
        Message message = null;
        if (ALL_USERS.getAllUsers().contains(new User(To.getText()))) {
            User Temp = ALL_USERS.getAllUsers().get(ALL_USERS.getAllUsers().indexOf(new User(To.getText())));
            final Message[] finalMessage = {message};
            Task<String> task2 = null;
            task2 = new Task<String>() {
                @Override
                protected String call() throws Exception {
                    if (AttachURL.isEmpty()) {
                        finalMessage[0] = new Message(ClientTemp, Temp, getCurrentTimeUsingCalendar(), Subject.getText(), Text.getText());
                    } else {
                        finalMessage[0] = new Message(ClientTemp, Temp, getCurrentTimeUsingCalendar(), Subject.getText(), Text.getText(), AttachURL);
                        finalMessage[0].setFileName(FileName);
                        Connection3.sendFile(finalMessage[0]);
                    }
                    if (ReadMailPageController.isForward)
                        SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Forward, ClientTemp, Temp, finalMessage[0]));
                    else if (ReadMailPageController.isReply)
                        SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Reply, ClientTemp, Temp, finalMessage[0]));
                    else
                        SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Send, ClientTemp, Temp, finalMessage[0]));
                    return "";
                }
            };
            ProgressPane.setVisible(true);
            new Thread(task2).start();

            task2.setOnSucceeded(e -> {
                try {
                    new FxmlLoader().load("./src/main/java/View/EmailPage.fxml");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        } else {
            message = new Message();
            message.setTime(getCurrentTimeUsingCalendar());
            message.setSubject(Subject.getText());
            message.setReciever(new User());
            message.setSender(ClientTemp);
            new FxmlLoader().load("./src/main/java/View/EmailPage.fxml");
            SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Error, ClientTemp, new User(), message));
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

