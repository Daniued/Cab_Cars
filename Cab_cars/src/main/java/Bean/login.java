package Bean;

public class login {
    private String username;
    private String hashedPassword;
    private String salt;
    private String role;

    // Constructors
    public login() {}

    public login(String username, String hashedPassword, String salt, String role) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.role = role;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}