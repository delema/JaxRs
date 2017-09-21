package es.lema.service.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

/**
 * Client resource
 *
 * @author Enrique de Lema (enrique.de.lema@gmail.com)
 */
@Path("/client")
public class ClientResource {
	
	// http://localhost:8080/jaxrs/rest/client/1
	@GET
	@Path("/{identifier: [0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Client getClient(@PathParam("identifier") String identifier)
	throws RuntimeException {

		if (StringUtils.isEmpty(identifier)) {
			throw new RuntimeException("Identifier is null");
		}
		Client client = new Client();
		client.setAddress("address");
		client.setIdentifier(new Integer(identifier));
		client.setName("name");
		client.setTelephone("telephone");
		return client;
	}

	// http://localhost:8080/jaxrs/rest/client
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Client> getClients() throws RuntimeException {

		List<Client> clients = new ArrayList<Client>();
		
		Client client = new Client();
		client.setAddress("address");
		client.setIdentifier(1024);
		client.setName("name");
		client.setTelephone("telephone");
		clients.add(client);
		client = new Client();
		client.setAddress("address");
		client.setIdentifier(1025);
		client.setName("name");
		client.setTelephone("telephone");
		clients.add(client);
		
		return clients;
	}

	// http://localhost:8080/jaxrs/rest/client/count
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() throws RuntimeException {

		return "1024";
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putClient(Client client) 
	throws RuntimeException {
		String output = "OK";
		return Response.ok().entity(output).build();
	}
	  
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postClient(Client client) 
	throws RuntimeException {
		String output = "OK";
		return Response.ok().entity(output).build();
	}
	
	@DELETE
	@Path("/{identifier: [0-9]*}")
	public Response deleteClient(@PathParam("identifier") String identifier) 
	throws RuntimeException {

		if (StringUtils.isEmpty(identifier)) {
			throw new RuntimeException("Identifier is null");
		}
		String output = "OK";
		return Response.ok().entity(output).build();
	}
}
