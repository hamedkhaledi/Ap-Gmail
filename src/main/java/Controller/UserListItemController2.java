package Controller;

import Model.ALL_MESSAGES;
import Model.IO.Connection.Connection2;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import Model.Main;
import Model.Message;
import Model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import static Model.ALL_USERS.ClientTemp;

public class UserListItemController2 {
    private static final String UNDEFINED_PROFILE_PHOTO_URL = "./src/main/resources/Images/no_photo.jpg";
    private User user;

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView userProfileImageView;
    @FXML
    private Label Text;
    @FXML
    private Label Date;
    @FXML
    private Label UserName;

    @FXML
    private ImageView Star;
    @FXML
    private ImageView Unread;
    public static User ChatUser;

    private ArrayList<Message> messagesToShow;

    public UserListItemController2(User user) throws IOException {
        this.user = user;
        new FxmlLoader().load("./src/main/java/View/ConversationListItem.fxml", this);
    }

    public AnchorPane init() {
        UserName.setText(user.getUsername() + "@gmail.com");
        String url = user.getImagePath();
        userProfileImageView.setImage(new Image(Paths.get(url).toUri().toString()));
        userProfileImageView.setPreserveRatio(true);
        messagesToShow = (ArrayList<Message>) ALL_MESSAGES.getAllMessages().stream()
                .filter(a -> (a.getReciever().equals(ClientTemp) && a.getSender().equals(user) && !a.isRemoved()) ||
                        (a.getReciever().equals(user) && a.getSender().equals(ClientTemp) && !a.isRemovedForMe())).collect(Collectors.toList());
        Collections.reverse(messagesToShow);
        Date.setText(messagesToShow.get(0).getTime());
        Text.setText(messagesToShow.get(0).getText());
        Star.setVisible(false);
        Unread.setVisible(false);
        for (Message message : messagesToShow) {
            if (message.isImportant() && message.getReciever().equals(ClientTemp) && !message.isRemoved())
                Star.setVisible(true);
            if (message.isImportantForMe() && message.getSender().equals(ClientTemp) && !message.isRemoved())
                Star.setVisible(true);
            if (!message.isReaded() && message.getReciever().equals(ClientTemp) && !message.isRemoved())
                Unread.setVisible(true);
        }
        return root;
    }

    public void Show(ActionEvent mouseEvent) throws IOException {
        ChatUser = user;
        new FxmlLoader().load("./src/main/java/View/ChatPage.fxml");
//TODO
    }

    public void Delete(ActionEvent event) throws IOException {
        for (Message message : messagesToShow) {
            if (!message.isRemoved() && message.getReciever().equals(ClientTemp)) {
                SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Delete, ClientTemp, null, message));
                message.setRemoved(true);
            }
            if (!message.isRemovedForMe() && message.getSender().equals(ClientTemp)) {
                SignInPageController.ConnectionSign.sendRequest(new ServerMessage(MessageType.Delete, ClientTemp, null, message));
                message.setRemovedForMe(true);
            }

        }


    }
}
