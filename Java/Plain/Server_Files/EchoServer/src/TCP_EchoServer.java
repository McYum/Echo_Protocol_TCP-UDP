import java.io.*;
import java.net.*;

public class TCP_EchoServer {
    static final int port = 420;

    public static void main(String[] args) {
        boolean isRunning = true;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (isRunning) {
                try {
                    Socket socket = serverSocket.accept();
                    new Echo_Thread(socket).start();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

