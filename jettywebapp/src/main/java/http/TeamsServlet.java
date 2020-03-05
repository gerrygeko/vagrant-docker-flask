package http;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import model.Team;
import utils.JsonToObjectConverter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Slf4j
@Path("/teams")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class TeamsServlet {

    @SneakyThrows
    @GET
    public Response doGet(@QueryParam("name") String name){
        if (name == null) {
            return createResponseWithAllTeam();
        }
        return createResponseForOneTeam(name);
    }

    private Response createResponseForOneTeam(String name) {
        StringBuffer stringBuffer = new StringBuffer();
        Team team = new Team();
        try {
            URL url = new URL("http://webserver/teams/" + URLEncoder.encode(name));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            String jsonString = stringBuffer.toString();
            team = (Team)JsonToObjectConverter.readValue(jsonString, Team.class);
            log.info(team.toString());
            in.close();
        } catch (Exception e) {
            log.error("Impossible to contact the server", e);
            ServletUtils.createErrorResponse(503, "Service Unavailable");
        }
        return ServletUtils.createOkResponse(team.toString());
    }

    private Response createResponseWithAllTeam() {
        return ServletUtils.createOkResponse("ALLLLL TEAMSSS");
    }

}
