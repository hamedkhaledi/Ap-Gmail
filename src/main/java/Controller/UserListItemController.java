package Controller;

import Model.IO.FxmlLoader;
import Model.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class UserListItemController {
  private static final String UNDEFINED_PROFILE_PHOTO_URL = "./src/main/resources/Images/no_photo.jpg";
  private User user;

  @FXML
  private AnchorPane root;
  @FXML
  private ImageView userProfileImageView;
  @FXML
  private Label textLabel;

  public UserListItemController(User user) throws IOException{
    this.user = user;
    new FxmlLoader().load("./src/main/java/View/UserListItem.fxml",this);
  }

  public AnchorPane init(){
    textLabel.setText(user.getUsername());
    String url = user.getImagePath();
    if(url == null)
      url = UNDEFINED_PROFILE_PHOTO_URL;

    userProfileImageView.setImage(new Image(Paths.get(url).toUri().toString() ,60, 60, false, false));
    userProfileImageView.setPreserveRatio(true);
    userProfileImageView.setClip(new Circle(30,30,30));
    return root;
  }
}
