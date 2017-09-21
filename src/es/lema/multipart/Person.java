package es.lema.multipart;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Enrique de Lema (enrique.de.lema@gmail.com)
 */
@XmlRootElement
public class Person {
    private String name;
    private String uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
