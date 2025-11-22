package library;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public boolean authenticate(String userId, String password) {
        User user = users.get(userId);
        return user != null && user.authenticate(password);
    }
}
