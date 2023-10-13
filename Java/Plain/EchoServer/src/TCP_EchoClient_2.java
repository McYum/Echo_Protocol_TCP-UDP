import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCP_EchoClient_2 extends Thread{
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 420;

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while (true) {
                System.out.println("[Say something] ");
                System.out.print("[Input]: ");
                message = userInput.readLine();

                out.println(message);

                String response = in.readLine();
                System.out.println("Response: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
