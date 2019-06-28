package ViewModel;

import java.io.Serializable;

public class ServerMessage implements Serializable
{
    public static final long serialVersionUID = 14L;
    private String sender;
    private String receiver;
    private String messageText;
    /**
     * server or client use type of message to do correct work correspond to that
     */
    private final MessageType messageType;

    public ServerMessage(MessageType messageType, String sender, String receiver, String messageText) {
        this.messageType = messageType;
        this.receiver = receiver;
        this.messageText = messageText;
        this.sender = sender;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "ServerMessage{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", messageText='" + messageText + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}
