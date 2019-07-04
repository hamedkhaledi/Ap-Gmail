package Controller;

import Model.ALL_MESSAGES;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import Model.Main;
import Model.Message;
import Model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public ArrayList<Message> messagesToShow;

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
                .filter(a -> (a.getReciever().equals(ClientTemp) && a.getSender().equals(user)) ||
                        (a.getReciever().equals(user) && a.getSender().equals(ClientTemp)) && !a.isRemoved()).collect(Collectors.toList());
        Collections.reverse(messagesToShow);
        Date.setText(messagesToShow.get(0).getTime());
        Text.setText(messagesToShow.get(0).getText());
        Star.setVisible(false);
        Unread.setVisible(false);
        for (Message message : messagesToShow) {
            if (message.isImportant() && message.getReciever().equals(ClientTemp))
                Star.setVisible(true);
            if (!message.isReaded() && message.getReciever().equals(ClientTemp))
                Unread.setVisible(true);
        }
        return root;
    }

    public void Show(ActionEvent mouseEvent) throws IOException {
//TODO
    }
}
