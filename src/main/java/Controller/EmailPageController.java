package Controller;

import Model.*;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import com.sun.javafx.charts.Legend;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import static Model.ALL_USERS.ClientTemp;
import static Model.Main.ME;

public class EmailPageController {
    @FXML
    public ListView<Message> messagesListView;
    @FXML
    public ListView<User> UserListView;

    public TextField SearchText;
    public RadioButton SubjectRadio;
    public RadioButton UsernameRadio;
    public ImageView Arrow;
    public Label ProfileLabel;
    public AnchorPane ProfilePane;
    public Label ProfileLabel2;
    public ImageView ProfileImage;
    public Line line;
    public ImageView ChatImage;
    private ArrayList<Message> messagesToShow;
    private ArrayList<User> users;
    private ArrayList<User> usersTemp;

    public void initialize() throws IOException, ClassNotFoundException {
        ChatImage.setVisible(false);
        Arrow.setOpacity(0.3);
        UserListView.setVisible(false);
        messagesListView.setVisible(true);
        SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Reload, ClientTemp, null, null));
        ALL_MESSAGES.initUser(SignInPageController.ConnectionSign);
        ReadMailPageController.isReply = false;
        ReadMailPageController.isForward = false;
        messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages().stream()
                .filter(a -> a.getReciever().equals(ClientTemp) && !a.isRemoved())
                .filter(a -> !ClientTemp.getBlackList().contains(a.getSender())).collect(Collectors.toList());
        Collections.reverse(messagesToShow);
        messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
        messagesListView.setCellFactory(messagesListView -> new MessageListItem());
    }


    public void Compose(MouseEvent mouseEvent) throws IOException {
        ReadMailPageController.isReply = false;
        new FxmlLoader().load("./src/main/java/View/SendMailPage.fxml");
    }

    public void showImportant(MouseEvent mouseEvent) {
        ChatImage.setVisible(false);
        UserListView.setVisible(false);
        messagesListView.setVisible(true);
        messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages()
                .stream()
                .filter(a -> (a.getReciever().equals(ClientTemp) && a.isImportant() && !a.isRemoved())
                        || (a.getSender().equals(ClientTemp) && a.isImportantForMe() && !a.isRemovedForMe()))
                .filter(a -> !ClientTemp.getBlackList().contains(a.getSender())).collect(Collectors.toList());
        Collections.reverse(messagesToShow);
        messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
        messagesListView.setCellFactory(messagesListView -> new MessageListItem());

    }

    public void Chat(MouseEvent mouseEvent) {
        ChatImage.setVisible(true);
        UserListView.setVisible(true);
        messagesListView.setVisible(false);
        //List Payamaii tosh payam gerefte
        messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages()
                .stream()
                .filter(a -> a.getReciever().equals(ClientTemp) && !a.isRemoved()).collect(Collectors.toList());
        usersTemp = new ArrayList<>();
        for (Message message : messagesToShow) {
            usersTemp.add(message.getSender());
        }
        users = (ArrayList<User>) ALL_USERS.getAllUsers()
                .stream()
                .filter(a -> !ClientTemp.getBlackList().contains(a)
                        && usersTemp.contains(a)).collect(Collectors.toList());
        Collections.reverse(users);
        UserListView.setItems(FXCollections.observableArrayList(users));
        UserListView.setCellFactory(messagesListView -> new UserListItem2());

    }

    public void Chat2(MouseEvent mouseEvent) {
        UserListView.setVisible(true);
        messagesListView.setVisible(false);
        //List Payamaii tosh payam dade
        messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages()
                .stream()
                .filter(a -> a.getSender().equals(ClientTemp) && !a.isRemovedForMe()).collect(Collectors.toList());
        usersTemp = new ArrayList<>();
        for (Message message : messagesToShow) {
            usersTemp.add(message.getReciever());
        }
        users = (ArrayList<User>) ALL_USERS.getAllUsers()
                .stream()
                .filter(a -> !ClientTemp.getBlackList().contains(a)
                        && usersTemp.contains(a)).collect(Collectors.toList());
        Collections.reverse(users);
        UserListView.setItems(FXCollections.observableArrayList(users));
        UserListView.setCellFactory(messagesListView -> new UserListItem2());

    }

    public void showSent(MouseEvent event) {
        ChatImage.setVisible(false);
        UserListView.setVisible(false);
        messagesListView.setVisible(true);
        messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages().stream()
                .filter(a -> a.getSender().equals(ClientTemp) && !a.isRemovedForMe()).collect(
                        Collectors.toList());
        Collections.reverse(messagesToShow);
        messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
        messagesListView.setCellFactory(messagesListView -> new MessageListItem());
    }

    public void Inbox(MouseEvent mouseEvent) {
        ChatImage.setVisible(false);
        UserListView.setVisible(false);
        messagesListView.setVisible(true);
        messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages().stream()
                .filter(a -> a.getReciever().equals(ClientTemp) && !a.isRemoved())
                .filter(a -> !ClientTemp.getBlackList().contains(a.getSender())).collect(Collectors.toList());
        Collections.reverse(messagesToShow);
        messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
        messagesListView.setCellFactory(messagesListView -> new MessageListItem());
    }

    public void BackUp(MouseEvent mouseEvent) {
        messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages().stream()
                .filter(a -> a.getReciever().equals(ClientTemp) || a.getSender().equals(ClientTemp))
                .filter(a -> !ClientTemp.getBlackList().contains(a.getSender())).collect(Collectors.toList());
        for (Message message : messagesToShow) {
            if (message.getReciever().equals(ClientTemp))
                message.setRemoved(false);
            if (message.getSender().equals(ClientTemp))
                message.setRemovedForMe(false);
        }
        Collections.reverse(messagesToShow);
        messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
        messagesListView.setCellFactory(messagesListView -> new MessageListItem());
    }

    public void Exit(MouseEvent mouseEvent) throws IOException {
        SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Disconnect, ClientTemp, null, null));
        new FxmlLoader().load("./src/main/java/View/SignInPage.fxml");
    }

    public void Setting(MouseEvent mouseEvent) throws IOException {
        new FxmlLoader().load("./src/main/java/View/Setting.fxml");
    }

    public void Search(MouseEvent mouseEvent) {
        if (UsernameRadio.isSelected()) {
            messagesToShow = (ArrayList<Message>) messagesToShow.stream().filter(a -> a.getSender().getUsername().startsWith(SearchText.getText()))
                    .collect(Collectors.toList());
            users = (ArrayList<User>) users
                    .stream()
                    .filter(a -> a.getUsername().startsWith(SearchText.getText())).collect(Collectors.toList());

        } else {
            messagesToShow = (ArrayList<Message>) messagesToShow.stream().filter(a -> a.getSubject().startsWith(SearchText.getText()))
                    .collect(Collectors.toList());
        }
        UserListView.setItems(FXCollections.observableArrayList(users));
        UserListView.setCellFactory(messagesListView -> new UserListItem2());
        messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
        messagesListView.setCellFactory(messagesListView -> new MessageListItem());
    }

    public void Reload(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        this.initialize();
    }

    public void ClickArrow(MouseEvent mouseEvent) {

        String Address = "./src/main/resources/Images/Faces/" + ClientTemp.getImageNumber() + ".png";
        ProfileImage.setImage(new Image(
                Paths.get(Address).toUri().toString()));
        ProfileLabel.setText(ClientTemp.getMailAddress() + '\n');
        ProfileLabel2.setText(ClientTemp.getFirstName() + " " + ClientTemp.getLastName());
        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(ProfilePane);
        line = new Line(375, 103, 175, -103);
        pathTransition.setDuration(Duration.seconds(3));
        pathTransition.setPath(line);
        pathTransition.play();
    }

    public void ShowArrow(MouseEvent mouseEvent) {
        Arrow.setOpacity(1);
    }

    public void OffArrow(MouseEvent mouseEvent) {
        Arrow.setOpacity(0.3);
    }
}
