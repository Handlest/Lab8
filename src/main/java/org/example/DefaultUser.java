package org.example;

public class DefaultUser implements User {
    private final boolean isStaff = false;
    private boolean isSuperUser = false;
    private boolean isActive = false;

    private String username;

    private String password;

    public DefaultUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.isActive = true;
    }

    public DefaultUser(String username, String password, boolean isSuperUser) {
        this.username = username;
        this.password = password;
        this.isActive = true;
        this.isSuperUser = isSuperUser;
    }

    public DefaultUser() {

    }

    @Override
    public User authenticate(String username, String password) {
        return DataBase.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public User register() {
        User user = new DefaultUser(username, password);
        DataBase.addUser(user);
        return DataBase.getUserByUsernameAndPassword(username, password);
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
    public boolean validatePassword(String password) {
        return DataBase.getUserByUsernameAndPassword(username, password) != null;
    }
}
