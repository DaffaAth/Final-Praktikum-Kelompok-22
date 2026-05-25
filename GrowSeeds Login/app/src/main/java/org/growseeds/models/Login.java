

package org.growseeds.models;

public class Login {
    private String username;
    private String password;

    // Constructor
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Constructor untuk data baru (tanpa ID karena auto-increment)

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}