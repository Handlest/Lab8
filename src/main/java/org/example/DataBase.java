package org.example;

import java.util.HashMap;

public class DataBase {

    protected DataBase() {

    }
    private static final HashMap<String, User> DB = new HashMap<>();

    protected static void addUser(User user) {
        DB.put(user.getUsername(), user);
    }

    protected static User getUserByUsernameAndPassword(String username, String password) {
        User user = DB.get(username);
        if (user == null){
            return null;
        }
        return user.getPassword().equals(password) ? user : null;
    }
}
