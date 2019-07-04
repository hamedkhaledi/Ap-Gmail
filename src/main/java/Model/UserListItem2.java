package Model;

import Controller.UserListItemController;

import java.io.IOException;

import Controller.UserListItemController2;
import javafx.scene.control.ListCell;

public class UserListItem2 extends ListCell<User> {

    @Override
    public void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if (user != null) {
            //setStyle("-fx-background-color: #abd4bc");
            try {
                setGraphic(new UserListItemController2(user).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
