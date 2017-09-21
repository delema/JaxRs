package es.lema.service.resources;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response connect(Authentication authentication) 
	throws RuntimeException {
		String user = authentication.getUser();
		String password = authentication.getPassword();
		String token = authentication.getToken();
		
		token = UUID.randomUUID().toString();
		return Response.ok().entity(token).build();
	}

	@DELETE
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response disconnect(Authentication authentication) 
	throws RuntimeException {
		String user = authentication.getUser();
		String password = authentication.getPassword();
		String token = authentication.getToken();
		
		String output = "OK";
		return Response.ok().entity(output).build();
	}
}
