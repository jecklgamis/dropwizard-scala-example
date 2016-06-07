package dropwizard.scala.example.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserPojoWithJaxbAnnotation {
    private String username;
    private String email;

    public UserPojoWithJaxbAnnotation() {
        this("me", "me@example.com");
    }

    public UserPojoWithJaxbAnnotation(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}


