package Controller;

import Model.ALL_USERS;
import Model.Gender;
import ViewModel.MessageType;
import ViewModel.ServerMessage;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignUpPage2Controller {
    public TextField PhoneNumber;
    public RadioButton Male;
    public RadioButton Female;
    private int ImageNumber = 1;

    public void Submit(ActionEvent actionEvent) {
        if (!PhoneNumber.getText().isEmpty()) {
            SignUpPageController.Temp.setPhoneNumber(PhoneNumber.getText());
        }
        if (Male.isSelected())
            SignUpPageController.Temp.setGender(Gender.Male);
        else
            SignUpPageController.Temp.setGender(Gender.Female);
        SignUpPageController.Temp.setImageNumber(ImageNumber);
        ALL_USERS.getAllUsers().add(SignUpPageController.Temp);
        new ServerConnection.ServerConnection(SignUpPageController.Temp.getUsername());
    }

    public void a(MouseEvent mouseEvent) {
        ImageNumber = 1;
    }

    public void b(MouseEvent mouseEvent) {
        ImageNumber = 2;
    }

    public void c(MouseEvent mouseEvent) {
        ImageNumber = 3;
    }

    public void d(MouseEvent mouseEvent) {
        ImageNumber = 4;
    }

    public void e(MouseEvent mouseEvent) {
        ImageNumber = 5;
    }

    public void f(MouseEvent mouseEvent) {
        ImageNumber = 6;
    }

    public void g(MouseEvent mouseEvent) {
        ImageNumber = 7;
    }

    public void h(MouseEvent mouseEvent) {
        ImageNumber = 8;
    }

}
