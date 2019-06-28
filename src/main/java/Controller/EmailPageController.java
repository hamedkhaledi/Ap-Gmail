package Controller;

import Model.ALL_MESSAGES;
import Model.IO.FxmlLoader;
import Model.Message;
import Model.MessageListItem;
import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import static Model.ALL_USERS.ClientTemp;
import static Model.Main.ME;

public class EmailPageController {
    @FXML
    public ListView<Message> messagesListView;
    public TextField SearchText;
    public RadioButton SubjectRadio;
    public RadioButton UsernameRadio;
    private ArrayList<Message> messagesToShow;

    public void initialize() throws IOException, ClassNotFoundException {
        ALL_MESSAGES.init();
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
        messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages()
                .stream()
                .filter(a -> a.getReciever().equals(ClientTemp) && a.isImportant() && !a.isRemoved())
                .filter(a -> !ClientTemp.getBlackList().contains(a.getSender())).collect(Collectors.toList());
        Collections.reverse(messagesToShow);
        messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
        messagesListView.setCellFactory(messagesListView -> new MessageListItem());

    }

    public void showSent(MouseEvent event) {
        messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages().stream()
                .filter(a -> a.getSender().equals(ClientTemp)).collect(
                        Collectors.toList());
        Collections.reverse(messagesToShow);
        messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
        messagesListView.setCellFactory(messagesListView -> new MessageListItem());
    }

    public void Inbox(MouseEvent mouseEvent) {
        messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages().stream()
                .filter(a -> a.getReciever().equals(ClientTemp) && !a.isRemoved())
                .filter(a -> !ClientTemp.getBlackList().contains(a.getSender())).collect(Collectors.toList());
        Collections.reverse(messagesToShow);
        messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
        messagesListView.setCellFactory(messagesListView -> new MessageListItem());
    }

    public void Exit(MouseEvent mouseEvent) throws IOException {
        new FxmlLoader().load("./src/main/java/View/SignInPage.fxml");
    }

    public void Setting(MouseEvent mouseEvent) throws IOException {
        new FxmlLoader().load("./src/main/java/View/Setting.fxml");
    }

    public void Search(MouseEvent mouseEvent) {
        if (UsernameRadio.isSelected()) {
            messagesToShow = (ArrayList<Message>) messagesToShow.stream().filter(a -> a.getSender().getUsername().startsWith(SearchText.getText()))
                    .collect(Collectors.toList());
            messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
            messagesListView.setCellFactory(messagesListView -> new MessageListItem());
        } else {
            messagesToShow = (ArrayList<Message>) messagesToShow.stream().filter(a -> a.getSubject().startsWith(SearchText.getText()))
                    .collect(Collectors.toList());
            messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
            messagesListView.setCellFactory(messagesListView -> new MessageListItem());
        }
    }

    public void Reload(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        this.initialize();
    }
}
