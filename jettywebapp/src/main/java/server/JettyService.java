package server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JettyService {

    private static final int PORT = 80;

    public static void main(String[] args) throws Exception {
        Handler handler = new JettyHandler();

        startWebServer(handler);
    }

    private static void startWebServer(Handler handler) throws Exception {
        Server server = new Server(PORT);
        ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/context", true, false);
        server.setHandler(handler);
        server.start();
        server.join();
    }

    private static class JettyHandler extends AbstractHandler{

        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String message = "Welcome to the Jetty web server!";
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().println(message);
            baseRequest.setHandled(true);
        }
    }

}
