import java.util.HashMap;

public class Bank {
    private static HashMap<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User validateUser(String userId, String pin) {
        User user = users.get(userId);
        if (user != null && user.getPin().equals(pin)) {
            return user;
        }
        return null;
    }

    public static User getUser(String userId) {
        return users.get(userId);
    }
}


