package Controller;

import Model.*;
import Model.IO.Connection.Connection;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import static Model.ALL_USERS.ClientTemp;
import static Model.Main.ConnectionTemp;
import static Model.Main.CurrentUser;

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
    private boolean Settingflag;

    public void Submit(ActionEvent actionEvent) throws IOException {
        Settingflag = true;
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
        if (!LastName.getText().isEmpty())
            ALL_USERS.ClientTemp.setLastName(LastName.getText());
        if (!Password.getText().isEmpty()) {
            if (Password.getText().length() < 8 || !Password.getText().matches("^[a-zA-Z0-9]+$")) {
                Label.setText("Password must be 8 character at least");
                Label.setVisible(true);
                Settingflag = false;
            } else {
                ALL_USERS.ClientTemp.setPassword(Password.getText());
            }
        }
        if (!FirstName.getText().isEmpty())
            ALL_USERS.ClientTemp.setFirstName(FirstName.getText());
        if (!age.getText().isEmpty()) {
            try {
                double d = Double.parseDouble(age.getText());
                if (d < 13) {
                    Label.setText("Error1");
                    Label.setVisible(true);
                    Settingflag = false;
                } else
                    ALL_USERS.ClientTemp.setAge(Integer.parseInt(age.getText()));
            } catch (NumberFormatException | NullPointerException nfe) {
                Label.setText("Error2");
                Label.setVisible(true);
                Settingflag = false;
            }
        }
        if (Settingflag) {
            Main.ConnectionTemp = new Connection(CurrentUser);
            Main.ConnectionTemp.initializeServices();
            new FxmlLoader().load("./src/main/java/View/EmailPage.fxml");
            ConnectionTemp.sendRequest(new ServerMessage(MessageType.Setting, ALL_USERS.ClientTemp, null, null));
        }
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

    /**
     * Blocks Page
     */
    @FXML
    public ListView<User> ListView;

    private ArrayList<User> UsersToShow = new ArrayList<>();

    public void initialize() throws IOException, ClassNotFoundException {

        UsersToShow = (ArrayList<User>) ALL_USERS.getAllUsers().stream()
                .filter(a -> ClientTemp.getBlackList().contains(a))
                .collect(Collectors.toList());
        Collections.reverse(UsersToShow);
        ListView.setItems(FXCollections.observableArrayList(UsersToShow));
        ListView.setCellFactory(ListView -> new UserListItem());
    }

    public void Back(MouseEvent mouseEvent) throws IOException {
        new FxmlLoader().load("./src/main/java/View/EmailPage.fxml");
    }
}
