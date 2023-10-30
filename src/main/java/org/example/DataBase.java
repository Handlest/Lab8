package org.example;

import java.util.HashMap;

public class DataBase {
    private static final HashMap<String, User> db = new HashMap<>();

    public static void addUser(User user) {
        db.put(user.getUsername(), user);
    }

    public static User getUserByUsernameAndPassword(String username, String password) {
        User user = db.get(username);
        return user.getPassword().equals(password) ? user : null;
    }
}
