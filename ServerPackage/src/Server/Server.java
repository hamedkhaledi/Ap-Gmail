package Server;

import ViewModel.ServerMessage;
import ViewModel.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    public static final int requestPort = 8888;
    public static final String serverIP = "localhost";
    private static ServerSocket requestServerSocket;

    public static void main(String[] args) {
        Server.start();
    }

    public static void start() {
        try {
            System.out.println(1);
            requestServerSocket = new ServerSocket(requestPort);
            Thread serverThread = new Thread(new Server(), "Server Thread");
            serverThread.start();
        } catch (IOException e) {
            // ignore it
        }
    }

    @Override
    public void run() {
        while (!requestServerSocket.isClosed()) {
            try {
                new Thread(new ServerRunner(requestServerSocket.accept()), "Server Runner").start();
            } catch (IOException e) {
                // ignore it
            }
        }
    }
}

class ServerRunner implements Runnable {
    private Socket serverSocket;
    private ServerHandler serverHandler;

    public ServerRunner(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        ServerMessage clientRequest = null;
        try {
            serverHandler = new ServerHandler(serverSocket,
                    new ObjectInputStream(serverSocket.getInputStream()),
                    new ObjectOutputStream(serverSocket.getOutputStream()));

            while (clientRequest == null || clientRequest.getMessageType() != MessageType.Disconnect) {
                clientRequest = (ServerMessage) serverHandler.getInputStream().readObject();
                serverHandler.handle(clientRequest);
            }
        } catch (IOException | ClassNotFoundException e) {
            /* Ignore it */
        } finally {
            userDisconnect();
        }
    }

    private void userDisconnect() {
        try {
            serverHandler.getOutputStream().close();
            serverHandler.getInputStream().close();
        } catch (IOException e) {
            // ignore it
        }
    }
}

