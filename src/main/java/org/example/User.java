package org.example;

public interface User {

    User authenticate(String username, String password, DataBase db);

    User register(String username, String password, DataBase db);

    String getUsername();

    String getPassword();

    boolean validatePassword(String password, DataBase db);

}
