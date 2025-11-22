package library;

public abstract class User {
    protected String userId;
    protected String name;
    protected String password;
    protected String role;

    public User(String userId, String name, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
