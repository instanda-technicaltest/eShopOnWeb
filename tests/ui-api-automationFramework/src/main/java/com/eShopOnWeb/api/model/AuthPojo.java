package com.eShopOnWeb.api.model;

/**
 * Created by Soniya Patel
 */


public class AuthPojo {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static AuthPojo getAuth(String username, String password) {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername(username);
        authPojo.setPassword(password);
        return authPojo;
    }
}
