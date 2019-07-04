package Model.IO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FxmlLoader {
    private static final int SCENE_WIDTH = 700;
    private static final int SCENE_HEIGHT = 500;
    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
        mainStage.setTitle("Gmail");
        //disable resizable
        mainStage.setResizable(false);
        //initStyle as Decorated
        mainStage.initStyle(StageStyle.DECORATED);
        mainStage.getIcons().add(new Image(Paths.get("./src/main/resources/Icons/icons8-gmail-48.png").toUri().toString()));
    }

    public void load(String url) throws IOException {
        //load root using FXMLLoader.load -> pass get class and get resource(url)
        Parent root = FXMLLoader.load(Paths.get(url).toUri().toURL());
        //set new Scene(Passes root and width and height in constructor) to the main stage
        mainStage.setScene(new Scene(root));
        //show the Stage
        mainStage.show();
    }

    public void load(String url, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Paths.get(url).toUri().toURL());
        fxmlLoader.setController(controller);
        fxmlLoader.load();
    }
}
