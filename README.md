# Echo protocol TCP+UDP
Imaged hotlinked with Discord LOL
<img src="https://cdn.discordapp.com/attachments/899764220189605888/1162073940605292614/hffhaafhafhfhahafhfa.PNG?ex=653a9cb1&is=652827b1&hm=e8d65e63ac0714fd81a6cc10e1c1735338ce669ed500d3b5059c0c304cae556a&" width=100% height=100%>

# UDP

**Server**

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

**Client**

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

# TCP

**Server**

```java
import java.net.*;
import java.util.ArrayList;

public class UDP_EchoServer {
    public static void main(String[] args) {
        final int serverPort = 420;
        boolean runonce = true;

        try (DatagramSocket serverSocket = new DatagramSocket(serverPort)) {
            while (true) {
                byte[] receivedData = new byte[1024];

                DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
                serverSocket.receive(receivePacket);

                String clientData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String response = String.valueOf(forkData(clientData));

                System.out.println("Received data: " + clientData);

                byte[] sendData = response.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);

                if (runonce) {
                    break;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static ArrayList<String> forkData(String data) {
        String[] values = data.split(", ");
        ArrayList<String> forkedNumbers = new ArrayList<>();

        for (String value : values) {
            int number = Integer.parseInt(value);
            int forkedNumber = number / 2;

            forkedNumbers.add(String.valueOf(forkedNumber));
        }

        return forkedNumbers;
    }
}

```

**Client**

```java
import java.net.*;

public class UDP_EchoClient {
    public static void main(String[] args) {
        final String IP = "localhost";
        final int port = 420;

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(IP);

            String dataToSend = "138, 840, 2674";
            byte[] sendData = dataToSend.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Antwort vom Server: " + serverResponse);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

```
