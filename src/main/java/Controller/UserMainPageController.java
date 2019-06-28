package Controller;

import static Model.Main.ME;

import Model.ALL_MESSAGES;
import Model.ALL_USERS;
import Model.Message;
import Model.MessageListItem;
import Model.User;
import Model.UserListItem;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class UserMainPageController {

  @FXML
  public ListView<User> usersListView;
  @FXML
  public ListView<Message> messagesListView;
  @FXML
  public Button inboxButton;
  @FXML
  public Button newMessageButton;
  @FXML
  public Button sentButton;
  @FXML
  public Button importantButton;

  public void initialize() {
    ArrayList<User> usersToShow = (ArrayList<User>) ALL_USERS.getAllUsers().stream()
        .filter(a -> !a.getUsername().equals(ME.getUsername())).collect(
            Collectors.toList());
    usersListView.setItems(FXCollections.observableArrayList(usersToShow));
    ArrayList<Message> messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages().stream()
        .filter(a -> a.getReciever().getUsername().equals(ME.getUsername())).collect(
            Collectors.toList());
    messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));

    usersListView.setCellFactory(usersListView -> new UserListItem());
    messagesListView.setCellFactory(messagesListView -> new MessageListItem());
  }

  public void showInbox(ActionEvent event) {
    ArrayList<Message> messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages().stream()
        .filter(a -> a.getReciever().getUsername().equals(ME.getUsername())).collect(
            Collectors.toList());
    messagesListView.setItems(FXCollections.observableArrayList(messagesToShow));
    messagesListView.setCellFactory(messagesListView -> new MessageListItem());
  }

  public void showImportant(ActionEvent event) {
    ArrayList<Message> importantMessages = (ArrayList<Message>) ALL_MESSAGES.getAllMessages()
        .stream()
        .filter(a -> a.isImportant() && a.getReciever().getUsername().equals(ME.getUsername()))
        .collect(
            Collectors.toList());
    messagesListView.setItems(FXCollections.observableArrayList(importantMessages));
    messagesListView.setCellFactory(messagesListView -> new MessageListItem());
  }

  public void showSent(ActionEvent event) {
    ArrayList<Message> sentMessages = (ArrayList<Message>) ALL_MESSAGES.getAllMessages().stream()
        .filter(a -> a.getSender().getUsername().equals(ME.getUsername())).collect(
            Collectors.toList());
    messagesListView.setItems(FXCollections.observableArrayList(sentMessages));
    messagesListView.setCellFactory(messagesListView -> new MessageListItem());
  }
}
