import com.sun.net.httpserver.*;

import java.io.*;

public class File_Handler implements HttpHandler {
    private final String basePath;

    public File_Handler(String basePath) {
        this.basePath = basePath;
    }

    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            try {
                String requestURI = exchange.getRequestURI().getPath();

                if (requestURI.equals("/")) {
                    requestURI = "/Krawall.txt";
                }

                File file = new File(basePath + requestURI);

                if (file.exists() && file.isFile()) {
                    exchange.sendResponseHeaders(200, file.length());
                    OutputStream os = exchange.getResponseBody();
                    FileInputStream fs = new FileInputStream(file);
                    byte[] buffer = new byte[8192];
                    int len;

                    while ((len = fs.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }

                    fs.close();
                    os.close();

                } else {
                    exchange.sendResponseHeaders(404, 0);
                    exchange.getResponseBody().close();
                }
            } catch (IOException e) {
                exchange.sendResponseHeaders(500, 0);
                exchange.getResponseBody().close();
                e.printStackTrace();
            }
        } else {
            exchange.sendResponseHeaders(405, 0);
            exchange.getResponseBody().close();
        }
    }
}