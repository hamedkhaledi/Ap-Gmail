package Controller;

import Model.ALL_USERS;
import Model.IO.FxmlLoader;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpPageController {
    public static User Temp;
    public TextField LastName;
    public TextField age;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;
    @FXML
    private Label Label;
    private boolean SignUpflag;


    /**
     * Profile Submit
     */
    public void Submit(ActionEvent actionEvent) throws IOException {
        SignUpflag = true;
        Temp = new User();
        if (UserName.getText().isEmpty() || LastName.getText().isEmpty() || age.getText().isEmpty()) {
            Label.setText("Fill all items");
            Label.setVisible(true);
            SignUpflag = false;
        } else if (Password.getText().length() < 8 && !Password.getText().matches("^[a-zA-Z0-9]+$")) {
            Label.setText("Password must be 8 character at least");
            Label.setVisible(true);
            SignUpflag = false;
        } else {
            try {
                double d = Double.parseDouble(age.getText());
                if (d < 13) {
                    Label.setText("Error1");
                    Label.setVisible(true);
                    SignUpflag = false;
                }
            } catch (NumberFormatException | NullPointerException nfe) {
                Label.setText("Error2");
                Label.setVisible(true);
                SignUpflag = false;
            }
            Temp.setUsername(UserName.getText());
            if (ALL_USERS.getAllUsers().contains(Temp)) {
                Label.setText("Error3");
                Label.setVisible(true);
                SignUpflag = false;
            }
            if (SignUpflag) {
                Temp.setPassword(Password.getText());
                Temp.setAge(Integer.parseInt(age.getText()));
                Temp.setMailAddress(UserName.getText() + "@gmail.com");
                new FxmlLoader().load("./src/main/java/View/SignUpPage2.fxml");
            }
        }
    }

    public void Back(MouseEvent mouseEvent) throws IOException {
        new FxmlLoader().load("./src/main/java/View/SignInPage.fxml");

    }
}

