package es.lema.service.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.media.multipart.MultiPart;

//@Provider
//@Consumes(MediaType.MULTIPART_FORM_DATA)
public class MultipartReaderProvider implements MessageBodyReader<MultiPart> {

    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return MultiPart.class.isAssignableFrom(type);
    }

    @Override
    public MultiPart readFrom(Class<MultiPart> type, 
                Type type1, 
                Annotation[] antns, 
                MediaType mt, MultivaluedMap<String, String> mm, 
                InputStream in) throws IOException, WebApplicationException {
        try {
            ObjectInputStream ois = new ObjectInputStream(in);
            return (MultiPart)ois.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MultipartReaderProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
