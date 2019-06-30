package Model.IO.ViewModel;

import Model.Message;
import Model.User;

import java.io.Serializable;

public class ServerMessage implements Serializable {
    public static final long serialVersionUID = 14L;
    private User sender;
    private User receiver;
    private Message message;
    /**
     * server or client use type of serverMessage to do correct work correspond to that
     */
    private final MessageType messageType;

    public ServerMessage(MessageType messageType, User sender, User receiver, Message message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.messageType = messageType;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}
