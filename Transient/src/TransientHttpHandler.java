import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class TransientHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        TransientServer.logger.info("Request is made");
        String requestParams = null;
        requestParams = handleRequest(exchange);
        handleResponse(exchange, requestParams);
    }

    private String handleRequest(HttpExchange exchange){
        String[] pathVariables = exchange.getRequestURI().toString().split("\\?");
        if (pathVariables.length > 1){
            return pathVariables[1].split("=")[1];
        }
        return "";
    }

    // When a get request is made, displays the Person object as a json. Jackson is used to map the object to json.
    private void handleResponse(HttpExchange exchange, String requestParams) throws IOException{
        Person p = new Person("Test", 1, "This is a person", 2000);
        ObjectMapper mapper = new ObjectMapper();
        // The transient field, uid, is dropped during serialization.
        mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        String response = mapper.writeValueAsString(p);
        OutputStream os = exchange.getResponseBody();

        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());
        os.write(response.getBytes());
        os.close();

    }
}
