package http;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Path("/teams")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class TeamsServlet {

    @SneakyThrows
    @GET
    public Response doGet(){
        return sendRequestToWebserver();
    }

    private Response sendRequestToWebserver() {
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL("http://webserver/teams/zythos");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            log.error("Impossible to contact the server", e);
            ServletUtils.createErrorResponse(503, "Service Unavailable");
        }
        return ServletUtils.createOkResponse(response.toString());
    }

}
