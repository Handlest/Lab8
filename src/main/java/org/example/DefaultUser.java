package org.example;

public class DefaultUser implements User {
    private final boolean isStaff = false;
    private final boolean isSuperUser = false;
    private boolean isActive = false;

    private String username;

    private String password;

    public DefaultUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.isActive = true;
    }

    public DefaultUser() {

    }

    @Override
    public User authenticate(String username, String password, DataBase db) {
        return db.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public User register(String username, String password, DataBase db) {
        User user = new DefaultUser(username, password);
        db.addUser(user);
        return db.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean validatePassword(String password, DataBase db) {
        return db.getUserByUsernameAndPassword(username, password) != null;
    }
}
