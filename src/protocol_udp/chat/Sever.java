package protocol_udp.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Sever {
    public static void main(String[] args) {
        final int serverPort = 9009;
        List<ClientHandler> clients = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println("Server is running.");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected.");
                ClientHandler clientHandler = new ClientHandler(clientSocket, clients);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<ClientHandler> clients;
    private PrintWriter output;

    public ClientHandler(Socket clientSocket, List<ClientHandler> clients) {
        this.clientSocket = clientSocket;
        this.clients = clients;
        try {
            output = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String clientMessage;
            while ((clientMessage = input.readLine()) != null) {
                System.out.println("Client message: " + clientMessage);
                broadcast(clientMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clients.remove(this);
        }
    }

    private void broadcast(String message) {
        for (ClientHandler client : clients) {
            if (client != this) {
                client.output.println(message);
            }
        }
    }
}
