package api.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Auth {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
