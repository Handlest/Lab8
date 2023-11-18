package org.example;

public interface User {

    User authenticate(String username, String password);

    User register();

    String getUsername();

    String getPassword();

    boolean validatePassword(String password);

}
