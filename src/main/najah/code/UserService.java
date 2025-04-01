package main.najah.code;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, String> users = new HashMap<>();

    public void addUser(String username, String password) {
        users.put(username, password);
    }

    public boolean authenticate(String username, String password) {
        return password.equals(users.get(username));
    }

    
    public boolean removeUser(String username) {
        if (users.containsKey(username)) {
            users.remove(username);
            return true;
        }
        return false;
    }
}
