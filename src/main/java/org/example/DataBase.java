package org.example;

import java.util.HashMap;

public class DataBase {

    private DataBase() {

    }

    private static final HashMap<String, User> DB = new HashMap<>();

    public static void addUser(User user) {
        DB.put(user.getUsername(), user);
    }

    public static User getUserByUsernameAndPassword(String username, String password) {
        User user = DB.get(username);
        return user.getPassword().equals(password) ? user : null;
    }
}
