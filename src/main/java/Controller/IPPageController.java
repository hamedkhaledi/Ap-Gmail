package Controller;

import Model.IO.Connection.Connection;
import Model.IO.Connection.Connection3;
import Model.IO.FxmlLoader;
import Model.IO.ViewModel.MessageType;
import Model.IO.ViewModel.ServerMessage;
import Model.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;

import static Model.Main.CurrentUser;

public class IPPageController {
    public TextField IP;

    public void Set(ActionEvent actionEvent) throws IOException {
        Connection.serverIP = IP.getText();
        Connection3.IP = IP.getText();
        Main.ConnectionTemp = new Connection(CurrentUser);
        Main.ConnectionTemp.initializeServices();
        Main.ConnectionTemp.sendRequest(new ServerMessage(MessageType.Connect, CurrentUser, null, null));
        new FxmlLoader().load("./src/main/java/View/SignInPage.fxml");
    }
}
