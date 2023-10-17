import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String request;
            List<String> requestHeaders = new ArrayList<>();
            Map<String, String> queryParams = new HashMap<>();
            String url = "";

            while ((request = in.readLine()) != null) {
                if (request.isEmpty()) {
                    break;
                }

                requestHeaders.add(request);

                // Data splitter 🤯
                if (request.startsWith("GET")) {
                    String[] parts = request.split(" ");
                    url = parts[1];

                    int queryIndex = url.indexOf("?");

                    if (queryIndex != -1) {
                        String queryString = url.substring(queryIndex + 1);

                        for (String param : queryString.split("&")) {
                            String[] split = param.split("=");
                            if (split.length == 2) {
                                queryParams.put(split[0], URLDecoder.decode(split[1], StandardCharsets.UTF_8));
                            }
                        }
                    }
                }
            }

            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html; charset=UTF-8");
            out.println("");
            out.println("<html lang = \"de\">");
            out.println("<head><title>Server Response</title></head>");
            out.println("<body>");
            out.println("<h1>Request Information</h1>");
            out.println("<p>URL: " + url + "</p>");
            out.println("<p>Request Headers:</p>");
            out.println("<ul>");

            for (String header : requestHeaders) {
                out.println("<li>" + header + "</li>");
            }

            out.println("</ul>");
            out.println("<p>Query Parameters:</p>");
            out.println("<ul>");

            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                out.println("<li>" + entry.getKey() + " = " + entry.getValue() + "</li>");
            }

            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");

            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}