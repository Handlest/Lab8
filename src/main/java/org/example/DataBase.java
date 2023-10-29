package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
    HashMap<String, User> db = new HashMap<>();
    public void addUser(User user){
        db.put(user.getUsername(), user);
    }

    public User getUserByUsernameAndPassword(String username, String password){
        User user = db.get(username);
        return user.getPassword().equals(password) ? user : null;
    }
}
