package http;

import javax.ws.rs.core.Response;

public final class ServletUtils {

    public static Response createErrorResponse(int status, String message) {
        return Response
                .status(status)
                .header("Cache-Control", "no-store, must-revalidate")
                .header("Content-Type", "text/plain")
                .entity(message)
                .build();
    }

    public static Response createOkResponse(String message) {
        return Response
                .ok()
                .header("Cache-Control", "no-store, must-revalidate")
                .header("Content-Type", "text/plain")
                .entity(message)
                .build();
    }

}
