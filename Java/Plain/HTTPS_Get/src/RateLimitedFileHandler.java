import com.sun.net.httpserver.*;
import java.io.*;
import java.util.concurrent.*;

public class RateLimitedFileHandler implements HttpHandler {
    private final File_Handler fileHandler;
    private final Semaphore requestSemaphore;
    private final RateLimiter rateLimiter;

    public RateLimitedFileHandler(String path, Semaphore requestSemaphore, RateLimiter rateLimiter) {
        this.fileHandler = new File_Handler(path);
        this.requestSemaphore = requestSemaphore;
        this.rateLimiter = rateLimiter;
    }

    public void handle(HttpExchange exchange) throws IOException {
        try (exchange) {
            if (requestSemaphore.tryAcquire()) {
                rateLimiter.acquire();
                fileHandler.handle(exchange);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            requestSemaphore.release();
        }
    }
}

class RateLimiter {
    private final long milliSecs;
    private final Semaphore semaphore;

    public RateLimiter(int maxRequestsPerSecond) {
        this.milliSecs = 1000 / maxRequestsPerSecond;
        this.semaphore = new Semaphore(maxRequestsPerSecond);
    }

    public void acquire() throws InterruptedException {
        semaphore.acquire();
        Thread.sleep(milliSecs);
        semaphore.release();
    }
}
