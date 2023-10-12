# Echo protocol TCP+UDP

## UDP

### **Server**

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
```

### **Client**

```java
import java.io.*;
import java.net.*;

public class TCP_EchoClient extends Thread{
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 420;

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while (true) {
                System.out.println("[Say \"Kill\" to shutdown client and server]");
                System.out.print("[Input]: ");
                message = userInput.readLine();

                out.println(message);

                if (message.equalsIgnoreCase("kill")) {
                    System.out.println("Stopping client");
                    break;
                }

                String response = in.readLine();
                System.out.println("Response: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

end)
```
