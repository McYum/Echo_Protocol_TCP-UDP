import com.sun.net.httpserver.*;
import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.*;

public class Https_Server_Factory {
    public static HttpsServer createHttpsServer(int port, String keystorePath, char[] keystorePassword) throws IOException, GeneralSecurityException {
        HttpsServer server = HttpsServer.create(new InetSocketAddress(port), 0);
        SSLContext sslContext = configureSSL(keystorePath, keystorePassword);

        server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
            public void configure(HttpsParameters params) {
                params.setCipherSuites(params.getCipherSuites());
                params.setProtocols(params.getProtocols());
            }
        });

        return server;
    }

    private static SSLContext configureSSL(String keystorePath, char[] keystorePassword) throws IOException, GeneralSecurityException {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        FileInputStream keystoreFile = new FileInputStream(keystorePath);
        keyStore.load(keystoreFile, keystorePassword);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, keystorePassword);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        trustManagerFactory.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        return sslContext;
    }
}
