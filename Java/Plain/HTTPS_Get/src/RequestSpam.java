import java.io.*;
import java.net.*;
import java.security.cert.*;
import javax.net.ssl.*;

public class RequestSpam {
    public static final String RED = "\u001B[31m";

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 2000; i++) {
            Thread.sleep(10);
            Thread requestThread = new Thread(new RequestTask());
            requestThread.start();
        }
    }

    private static class RequestTask implements Runnable {

        public void run() {
            try {
                String serverURL = "https://localhost:4200";

                // This stinks 🦨💨
                TrustManager[] trustAllCertificates = new TrustManager[]{
                        new X509TrustManager() {
                            public X509Certificate[] getAcceptedIssuers() {
                                return null;
                            }

                            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            }

                            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                            }
                        }
                };

                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());

                URL url = new URL(serverURL + "/" + "momo.txt");
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setSSLSocketFactory(sslContext.getSocketFactory());

                try {
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line;
                        StringBuilder response = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                            response.append("\n");
                        }

                        reader.close();
                        System.out.println("[Response]: ");
                        System.out.println(response);
                    } else {
                        System.out.println("[Couldn't fetch file with name]: " + RED);
                    }
                } finally {
                    connection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
