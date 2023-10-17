import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("[Enter the server host]: ");
        String serverHost = scanner.nextLine();
        System.out.print("[Enter the server port]: ");
        int serverPort = Integer.parseInt(scanner.nextLine());

        while (true) {
            System.out.print("[Enter the path]: ");
            String pathWithQuery = scanner.nextLine();

            try {
                URL url = new URL("http", serverHost, serverPort, pathWithQuery);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        response.append("\n");
                    }

                    in.close();
                    System.out.println("[Response]:");
                    System.out.println(GREEN + response);
                } else {
                    System.err.println(RED + "[Request failed. Response Code]: " + responseCode);
                }

                System.out.print("[Create a new request? (YES/NO)]: ");
                String response = scanner.nextLine();

                if (!response.equalsIgnoreCase("YES")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
