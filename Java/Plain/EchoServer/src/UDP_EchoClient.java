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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
