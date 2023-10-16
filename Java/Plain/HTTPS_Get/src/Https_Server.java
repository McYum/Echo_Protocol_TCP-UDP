import com.sun.net.httpserver.*;
import java.util.concurrent.*;

public class Https_Server {
    public static void main(String[] args) throws Exception {
        int port = 4200;
        String keystorePath = "C:\\Keystores\\local.keystore";
        char[] keystorePassword = "123456".toCharArray();

        HttpsServer server = Https_Server_Factory.createHttpsServer(port, keystorePath, keystorePassword);

        int maxConcurrentRequests = 10;
        int maxRequestsPerSecond = 50;
        Semaphore requestSemaphore = new Semaphore(maxConcurrentRequests);

        RateLimiter rateLimiter = new RateLimiter(maxRequestsPerSecond);

        server.createContext("/", new RateLimitedFileHandler("C:\\Users\\ducmy\\Documents\\IDK\\Java\\Plain\\HTTPS_Get\\readonly\\", requestSemaphore, rateLimiter));
        server.setExecutor(Executors.newFixedThreadPool(5));
        server.start();

        System.out.println("[Server is running on port]: " + port);
    }
}


