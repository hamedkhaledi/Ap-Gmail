package Model.IO.Connection;

public class ListenerService implements Runnable {
    private Connection connection;

    ListenerService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            while (true) {
              connection.getRespond();
            }
        } catch (ServerConnectionException e) {
            e.printStackTrace();
        }
    }
}
