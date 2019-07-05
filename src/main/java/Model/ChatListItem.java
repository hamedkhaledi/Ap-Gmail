package Model;

import Controller.ChatListItemController;
import Controller.MessageListItemController;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ChatListItem extends ListCell<Message> {

    @Override
    public void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);
        if (message != null) {
          //  setStyle("-fx-background-color: #D8BFD8");
            try {
                setGraphic(new ChatListItemController(message).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
