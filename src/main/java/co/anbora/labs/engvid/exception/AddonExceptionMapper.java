package co.anbora.labs.engvid.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class AddonExceptionMapper implements ExceptionMapper<RuntimeException> {

    private static final Map<String, String> response = new HashMap<>();

    @Override
    public Response toResponse(RuntimeException exception) {

        response.put(Response.Status.Family.SERVER_ERROR.name(), exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(response).build();
    }
}
