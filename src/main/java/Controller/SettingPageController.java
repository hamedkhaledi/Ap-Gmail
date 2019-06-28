package Controller;

import Model.ALL_USERS;
import Model.Gender;
import Model.IO.FxmlLoader;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static Model.ALL_USERS.ClientTemp;

public class SettingPageController {
    public TextField PhoneNumber;
    public RadioButton Male;
    public RadioButton Female;
    public TextField FirstName;
    private int ImageNumber = -1;
    public TextField LastName;
    public TextField age;
    @FXML
    private PasswordField Password;
    @FXML
    private Label Label;
    private boolean SignUpflag;

    public void Submit(ActionEvent actionEvent) throws IOException {
        if (!PhoneNumber.getText().isEmpty()) {
            ALL_USERS.ClientTemp.setPhoneNumber(PhoneNumber.getText());
        }
        if (Male.isSelected())
            ALL_USERS.ClientTemp.setGender(Gender.Male);
        else
            ALL_USERS.ClientTemp.setGender(Gender.Female);
        if (ImageNumber != -1) {
            ALL_USERS.ClientTemp.setImageNumber(ImageNumber);
            String Address = "./src/main/resources/Images/Faces/" + ImageNumber + ".png";
            ALL_USERS.ClientTemp.setImagePath(Address);
        }
        SignUpflag = true;
        if (!LastName.getText().isEmpty())
            ALL_USERS.ClientTemp.setLastName(LastName.getText());
        if (!Password.getText().isEmpty()) {
            if (Password.getText().length() < 8 || !Password.getText().matches("^[a-zA-Z0-9]+$")) {
                Label.setText("Password must be 8 character at least");
                Label.setVisible(true);
                SignUpflag = false;
            } else {
                ALL_USERS.ClientTemp.setLastName(Password.getText());
            }
        }
        if (!FirstName.getText().isEmpty())
            ALL_USERS.ClientTemp.setLastName(FirstName.getText());
        if (!age.getText().isEmpty()) {
            try {
                double d = Double.parseDouble(age.getText());
                if (d < 13) {
                    Label.setText("Error1");
                    Label.setVisible(true);
                    SignUpflag = false;
                } else
                    ALL_USERS.ClientTemp.setAge(Integer.parseInt(age.getText()));
            } catch (NumberFormatException | NullPointerException nfe) {
                Label.setText("Error2");
                Label.setVisible(true);
                SignUpflag = false;
            }
        }
        new FxmlLoader().load("./src/main/java/View/EmailPage.fxml");
    }


    public void Picturea(MouseEvent mouseEvent) {
        ImageNumber = 1;
    }

    public void Pictureb(MouseEvent mouseEvent) {
        ImageNumber = 2;
    }

    public void Picturec(MouseEvent mouseEvent) {
        ImageNumber = 3;
    }

    public void Pictured(MouseEvent mouseEvent) {
        ImageNumber = 4;
    }

    public void Picturee(MouseEvent mouseEvent) {
        ImageNumber = 5;
    }

    public void Picturef(MouseEvent mouseEvent) {
        ImageNumber = 6;
    }

    public void Pictureg(MouseEvent mouseEvent) {
        ImageNumber = 7;
    }

    public void Pictureh(MouseEvent mouseEvent) {
        ImageNumber = 8;
    }

}
