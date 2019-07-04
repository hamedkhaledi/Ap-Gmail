package Controller;

import Model.ALL_USERS;
import Model.Gender;
import Model.IO.Connection.Connection;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import Model.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static Model.Main.CurrentUser;

public class SignUpPage2Controller {
    public TextField PhoneNumber;
    public RadioButton Male;
    public RadioButton Female;
    private int ImageNumber = 1;

    public void Submit(ActionEvent actionEvent) throws IOException {
        if (!PhoneNumber.getText().isEmpty()) {
            SignUpPageController.Temp.setPhoneNumber(PhoneNumber.getText());
        }
        if (Male.isSelected())
            SignUpPageController.Temp.setGender(Gender.Male);
        else
            SignUpPageController.Temp.setGender(Gender.Female);
        SignUpPageController.Temp.setImageNumber(ImageNumber);
        String Address = "./src/main/resources/Images/Faces/" + ImageNumber + ".png";
        SignUpPageController.Temp.setImagePath(Address);
        //ALL_USERS.getAllUsers().add(SignUpPageController.Temp);
        Main.ConnectionTemp = new Connection(SignUpPageController.Temp);
        Main.ConnectionTemp.initializeServices();
        Main.ConnectionTemp.sendRequest(new ServerMessage(MessageType.SignUp, SignUpPageController.Temp, null, null));
        //Main.ConnectionTemp.sendRequest(new ServerMessage(MessageType.Connect, CurrentUser, null, null));
        new FxmlLoader().load("./src/main/java/View/SignInPage.fxml");
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
