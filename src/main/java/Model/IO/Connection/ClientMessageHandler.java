package Model.IO.Connection;

import Model.IO.ViewModel.ServerMessage;
import Model.Message;

public class ClientMessageHandler {
    public static String handle(ServerMessage message) {
        String respond = "";
        switch (message.getMessageType()) {
            case Connect:
                respond = message.getSender() + " conected";
                break;
            case Disconnect:
                // TODO
                break;
            case Send:
                // TODO
                break;
            case Error:
                // TODO
                break;
        }
        return respond;
    }
}
