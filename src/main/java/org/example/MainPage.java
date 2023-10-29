package org.example;

public class MainPage implements WebPage {
    User authenticateUser(String username, String password, DataBase db){
        User user = new DefaultUser();
        return user.authenticate(username, password, db);
    }

    boolean isAllowed(User user){
        return user != null;
    }

    @Override
    public String render(User user) {
        return null;
    }
}
