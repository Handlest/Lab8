package org.example;

public class AccessManager {
    public static boolean hasAccess(User user){
        return user.isSuperUser();
    }
}
