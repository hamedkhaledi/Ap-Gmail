package Controller;

import Model.ALL_USERS;
import Model.IO.FxmlLoader;
import Model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class SignInPageController {
    public PasswordField Password;
    public TextField Username;
    public Label Label;

    public void SignUp(MouseEvent mouseEvent) throws IOException {
        new FxmlLoader().load("./src/main/java/View/SignUpPage.fxml");
    }

    public void Submit(ActionEvent actionEvent) {
        if (Username.getText().isEmpty() || Password.getText().isEmpty()) {
            Label.setText("Fill all items");
            Label.setVisible(true);
        } else if (!ALL_USERS.getAllUsers().contains(new User(Username.getText()))) {
            Label.setText("Error1");
            Label.setVisible(true);
        } else {
            User Temp = ALL_USERS.getAllUsers().get(ALL_USERS.getAllUsers().indexOf(new User(Username.getText())));
            if (!Temp.getPassword().equals(Password.getText())) {
                Label.setText("Error2");
                Label.setVisible(true);
            } else {
                //ToDO
            }
        }
    }
}
