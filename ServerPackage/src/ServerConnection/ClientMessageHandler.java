package ServerConnection;

import ViewModel.ServerMessage;

public class ClientMessageHandler {
    public static String handle(ServerMessage message) {
        String respond = "";
        switch (message.getMessageType()) {
            case Connect:
                respond = message.getSender() + " connected";
                break;
            case Disconnect:
                respond = message.getSender() + " disconnected";
                break;
            case Text:
                respond = message.getSender() + ":" + message.getMessageText();
                break;
            case Error:
                respond = message.getReceiver() + " doesn't exist";
                break;

        }

        return respond;
    }
}
