package Controller;

import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import Model.Main;
import Model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import static Model.ALL_USERS.ClientTemp;

public class UserListItemController {
    private static final String UNDEFINED_PROFILE_PHOTO_URL = "./src/main/resources/Images/no_photo.jpg";
    private User user;

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView userProfileImageView;
    @FXML
    private Label textLabel;
    @FXML
    private ImageView userProfileImageView1;

    public UserListItemController(User user) throws IOException {
        this.user = user;
        new FxmlLoader().load("./src/main/java/View/UserListItem.fxml", this);
    }

    public AnchorPane init() {
        textLabel.setText(user.getUsername() + "@gmail.com");
        String url = user.getImagePath();
        if (url == null)
            url = UNDEFINED_PROFILE_PHOTO_URL;
        userProfileImageView.setImage(new Image(Paths.get(url).toUri().toString()));
        userProfileImageView.setPreserveRatio(true);
        return root;
    }

    public void Unblock(MouseEvent event) {
        userProfileImageView1.setVisible(false);
        ClientTemp.getBlackList().remove(user);
        Main.ConnectionTemp.sendRequest(new ServerMessage(MessageType.UnBlock, ClientTemp, user, null));

    }
}
