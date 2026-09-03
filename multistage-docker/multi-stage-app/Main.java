import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public final class Main {
    private static final int PORT = 8080;
    private static final byte[] PAGE = "<!doctype html><html><body><h1>Hello World from Docker multi-stage build</h1></body></html>"
            .getBytes(StandardCharsets.UTF_8);

    private Main() {}

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", PORT), 0);
        server.createContext("/", Main::respond);
        server.start();
        System.out.println("Multi-stage application listening on port " + PORT);
    }

    private static void respond(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
        exchange.sendResponseHeaders(200, PAGE.length);
        exchange.getResponseBody().write(PAGE);
        exchange.close();
    }
}
