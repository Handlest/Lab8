package org.example;

public class SuperUser extends DefaultUser implements User{
    SuperUser(String username, String password) {
        super(username, password, true);
    }

}
