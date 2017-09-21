package es.lema.service.application;

import java.util.HashSet;
import java.util.Set;

import javax.json.spi.JsonProvider;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import es.lema.service.resources.AuthenticationResource;
import es.lema.service.resources.ClientResource;
import es.lema.service.resources.FileResource;

public class ServiceApplication extends Application {

	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(MultiPartFeature.class);
        classes.add(JsonProvider.class);
        classes.add(ClientResource.class);
        classes.add(FileResource.class);
        classes.add(AuthenticationResource.class);
        classes.add(LoggingFeature.class);
        return classes;
    }

	@Override
	public Set<Object> getSingletons() {
		
		return null;
	}
}

