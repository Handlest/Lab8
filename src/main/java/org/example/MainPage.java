package org.example;

public class MainPage implements WebPage {
    User authenticateUser(String username, String password) {
        User user = new DefaultUser();
        return user.authenticate(username, password);
    }

    boolean isAllowed(User user) {
        return user != null;
    }

    @Override
    public String render(User user) {
        return null;
    }
}
