package org.example;

public class DataBaseManager extends DataBase{
    public static void addUser(User user){
        if(DataBase.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()) == null
            && validatePassword(user.getPassword())){
            DataBase.addUser(user);
        }
    }
    public static void addUser(String username, String password){
        if(DataBase.getUserByUsernameAndPassword(username, password) == null && validatePassword(password)){
            DataBase.addUser(new DefaultUser(username, password));
        }
    }
    private static boolean validatePassword(String password){
        return password.length() >= 8;
    }
}
