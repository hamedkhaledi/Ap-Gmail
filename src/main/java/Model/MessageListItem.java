package Model;

import Controller.MessageListItemController;

import java.io.IOException;

import javafx.scene.control.ListCell;

public class MessageListItem extends ListCell<Message> {

    @Override
    public void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);
        if (message != null) {
            setStyle("-fx-background-color: #D8BFD8");
            try {
                setGraphic(new MessageListItemController(message).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
