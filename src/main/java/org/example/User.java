package org.example;

public interface User {

    User authenticate(String username, String password);

    User register(String username, String password);

    String getUsername();

    String getPassword();

    boolean validatePassword(String password);

}
