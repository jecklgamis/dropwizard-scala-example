package dropwizard.scala.example.api;

public class UserPojo {
    private String username = "me";
    private String email = "me@example.com";

    public UserPojo(String username, String email) {
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


