# Networking Server Examples

This repository contains examples of networking server code in Java.

## TCP Server

### Name: TCP_EchoServer.java

#### Description
The `TCP_EchoServer` is a simple TCP server that listens on a specified port (in this example, port 420). It accepts incoming client connections and echoes back the messages received from clients. It also includes a special command "kill" to stop the server gracefully.

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
