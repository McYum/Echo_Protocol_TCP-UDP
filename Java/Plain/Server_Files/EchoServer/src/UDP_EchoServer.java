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
        } catch (Exception e) {
            e.printStackTrace();
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
