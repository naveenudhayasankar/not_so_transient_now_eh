import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class TransientServer {
    public static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void runServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        server.createContext("/test", new TransientHttpHandler());
        server.setExecutor(null);
        server.start();
        logger.info("Server running on port 8001");
    }
}
