package http;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/teams")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class TeamsServlet {

    @SneakyThrows
    @GET
    public Response doGet(){
        return ServletUtils.createOkResponse("Team page");
    }

}
