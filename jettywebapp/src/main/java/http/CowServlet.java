package http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Singleton
@Slf4j
@Path("/cow")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class CowServlet {

	@GET
	public Response checkHealth(@QueryParam("say") String say) {

		if (say == null) {
			say = cowSay("Hellow world!");
		} else {
			say = cowSay(say);
		}

		return Response
				.ok()
				.header("Cache-Control", "no-store, must-revalidate")
				.header("Content-Type", "text/plain")
				.entity(say)
				.build();
	}

	public String cowSay(String displayedMessage) {
		String cowMessage =
				"-----------------------------\n" +
						"<" + displayedMessage + ">\n" +
						"<" + System.currentTimeMillis() + ">\n" +
						"-----------------------------\n" +
						"      \\   ^__^              \n" +
						"       \\  (oo)\\_______      \n" +
						"          (__)\\       )\\/\\  \n" +
						"              ||----w |      \n" +
						"              ||     ||      ";
		return cowMessage;
	}

}
