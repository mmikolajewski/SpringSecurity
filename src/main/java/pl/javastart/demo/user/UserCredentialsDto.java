package pl.javastart.demo.user;

import java.util.Set;

public class UserCredentialsDto {
    private final String username;
    private final String password;
    private final Set<String> roles;

    public UserCredentialsDto(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Set<String> getRoles() {
        return roles;
    }
}
