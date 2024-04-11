package protocol_udp.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        final String serverHost = "localhost";
        final int serverPort = 9009;

        try {
            Socket socket = new Socket(serverHost, serverPort);
            System.out.println("Connected to server.");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Thread serverListener = new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = serverInput.readLine()) != null) {
                        System.out.println("Server: " + serverResponse);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverListener.start();

            String userInputString;
            while ((userInputString = userInput.readLine()) != null) {
                output.println(userInputString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
