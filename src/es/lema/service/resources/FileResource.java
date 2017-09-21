package es.lema.service.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * File resource
 *
 * @author Enrique de Lema (enrique.de.lema@gmail.com)
 */
@Path("/file")
public class FileResource {

	public static String path = "c:/temp/upload";
	
//	@POST
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	public Response post(MultiPart multiPart) throws IOException {
//
//		List<BodyPart> bodyParts = multiPart.getBodyParts();
//		
//		for (BodyPart bodyPart : bodyParts) {
//			ContentDisposition contentDisposition = bodyPart.getContentDisposition();
//			String name = ((FormDataBodyPart) bodyPart).getName();
//			if (name.equalsIgnoreCase("file")) {
//				String file = contentDisposition.getFileName();
//				BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyPart.getEntity();
//				InputStream inputStream = bodyPartEntity.getInputStream();
//				writeToFile(inputStream, file);
//			}
//			else if (name.equalsIgnoreCase("data")) {
//				JsonReader jsonReader = null;
//				try {
//					StringReader reader = new StringReader(bodyPart.getEntityAs(String.class));
//					jsonReader = new JsonReader(reader);
//					JsonObject Object = jsonReader.readObject();
//					System.out.println(Object.toString());
//				}
//				catch (JsonException e) {
//					throw new IOException(e);
//				}
//				finally {
//					if (jsonReader != null) jsonReader.close();
//				}
//			}
//		}
//
//		return Response.ok().build();
//	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response post(
	@FormDataParam("file") List<FormDataBodyPart> files,
	@FormDataParam("data") FormDataBodyPart data)
	throws IOException {
		
		for (FormDataBodyPart file : files) {
			ContentDisposition contentDisposition = file.getContentDisposition();
			String fileName = contentDisposition.getFileName();
			if (StringUtils.isNotEmpty(fileName)) {
				BodyPartEntity bodyPartEntity = (BodyPartEntity) file.getEntity();
				InputStream inputStream = bodyPartEntity.getInputStream();
				writeToFile(inputStream, fileName);
			}
		}

		if (data != null) {
			JsonReader jsonReader = null;
			try {
				StringReader reader = new StringReader(data.getEntityAs(String.class));
				jsonReader = Json.createReader(reader);
				JsonObject Object = jsonReader.readObject();
				String name = Object.getString("name");
				String client = Object.toString();
				System.out.println(client);
			}
			catch (JsonException e) {
				throw new IOException(e);
			}
			finally {
				if (jsonReader != null) jsonReader.close();
			}
		}
		
		String output = "OK";
		return Response.ok().entity(output).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response put(
	@Context HttpServletRequest servletRequest,
	@QueryParam("name") String name)	
	throws IOException {

		ServletInputStream in = servletRequest.getInputStream();

		writeToFile(in, name);

		String output = "OK";
		return Response.ok().entity(output).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response get(
	@Context HttpServletResponse servletResponse,
	@QueryParam("name") String name)	
	throws IOException {

		ServletOutputStream out = servletResponse.getOutputStream();
		
		int length = (int) readFromFile(out, name);
		servletResponse.setContentLength(length);
		
		String parameters = "attachment;filename=" + name;
		return Response.ok()
				   	   .header("Content-Disposition",parameters)
					   .build();
	}

	private void writeToFile(
	InputStream in,
	String name) throws IOException {

		OutputStream out = null;
		
		try {
			int read = 0;
			byte[] bytes = new byte[10240];

			File file = new File(path, name);
			out = new FileOutputStream(file);
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} 
		catch (IOException e) {
			throw e;
		}
		finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	private long readFromFile(
	OutputStream out,
	String name) throws IOException {

		InputStream in = null;
		long length = 0;
		try {
			int read = 0;
			byte[] bytes = new byte[10240];

			File file = new File(path, name);
			length = file.length();
			in = new FileInputStream(file);
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} 
		catch (IOException e) {
			throw e;
		}
		finally {
			out.flush();
			if (in != null) {
				in.close();
			}
		}
		
		return length;
	}
}
