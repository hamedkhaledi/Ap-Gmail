package ServerConnection;

public class ListenerService implements Runnable {
    private ServerConnection serverConnection;

    ListenerService(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(serverConnection.getRespond());
            }
        } catch (ServerConnectionException e) {
            // ignore it
        }
    }
}
