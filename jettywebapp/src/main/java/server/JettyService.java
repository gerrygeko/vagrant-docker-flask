package server;

import http.CowServlet;
import http.TeamsServlet;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.http.HttpGenerator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class JettyService {

    private static final int PORT = 80;

    public static void main(String[] args) throws Exception {
        startWebServer();
    }

    private static void startWebServer() throws Exception {
        ResourceConfig resourceConfig = createResourceConfig();
        Handler servletContextHandler = createServletContextHandler(resourceConfig);
        Server jettyServer = createJettyServer(servletContextHandler);
        try {
            jettyServer.start();
        }
        catch (Exception e) { // NOSONAR
            log.error("Error starting the Jetty server", e);
            System.exit(1);
        }
        HttpGenerator.setJettyVersion("Jetty");
    }

    private static Server createJettyServer(Handler servletContextHandler) {
        Server server = new Server(PORT);
        server.setHandler(servletContextHandler);
        server.setStopAtShutdown(true);
        return server;
    }

    private static Handler createServletContextHandler(ResourceConfig resourceConfig) {
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        contextHandler.addServlet(new ServletHolder(new ServletContainer(resourceConfig)), "/*");

        HandlerWrapper statisticHandler = new StatisticsHandler();
        statisticHandler.setHandler(contextHandler);
        return statisticHandler;
    }

    private static ResourceConfig createResourceConfig() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(TeamsServlet.class);
        resourceConfig.register(CowServlet.class);
        return resourceConfig;
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
