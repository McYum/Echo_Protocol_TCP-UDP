import java.io.*;
import java.net.*;

public class Echo_Thread extends Thread {
    protected Socket socket;

    public Echo_Thread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            String message;

            while ((message = in.readLine()) != null) {
                System.out.println("Received: " + message);
                out.println(message);
            }

            this.socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
