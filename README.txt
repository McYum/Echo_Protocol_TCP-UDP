#### Usage

```java
import java.io.*;
import java.net.*;

public class TCP_EchoServer {
    public static void main(String[] args) {
        final int port = 420;
        boolean isRunning = true;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (isRunning){
                Socket clientSocket = serverSocket.accept();

                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    String message;

                    while ((message = in.readLine()) != null) {
                        System.out.println("Received: " + message);
                        out.println("Echo: " + message);

                        if (message.equalsIgnoreCase("kill")) {
                            System.out.println("Stopping server");
                            isRunning = false;
                            break;
                        }
                    }

                    clientSocket.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}