import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 4200;
        boolean IsRunning = true;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("[Server is running on port]: " + port);

            while (IsRunning) {
                Socket clientSocket = serverSocket.accept();
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}