package Controller;

import Model.*;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import static Controller.UserListItemController2.ChatUser;
import static Model.ALL_USERS.ClientTemp;


public class ChatPageController {

    public ListView ListView;
    private ArrayList<User> users;

    public void initialize() throws IOException, ClassNotFoundException {
        ArrayList<Message> messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages().stream()
                .filter(a -> ((a.getReciever().equals(ClientTemp) && a.getSender().equals(ChatUser) && !a.isRemoved()) ||
                        (a.getReciever().equals(ChatUser) && a.getSender().equals(ClientTemp) && !a.isRemovedForMe()))).collect(Collectors.toList());
        ListView.setItems(FXCollections.observableArrayList(messagesToShow));
        ListView.setCellFactory(messagesListView -> new ChatListItem());
        //TODo
    }

    public void Back(MouseEvent mouseEvent) throws IOException {
        new FxmlLoader().load("./src/main/java/View/EmailPage.fxml");
    }
}
