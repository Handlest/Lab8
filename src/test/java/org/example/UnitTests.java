package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnitTests {
    @Test
    @DisplayName("Проверка, что пользователь корректно добавляется и извлекается по паролю и логину из базы данных")
    public void test1() {
        String username = "Vasiliy";
        String password = "Password";
        User user = new DefaultUser(username, password);

        DataBase.addUser(user);
        User userFromDB = DataBase.getUserByUsernameAndPassword(username, password);

        Assertions.assertThat(userFromDB).isEqualTo(user);
    }

    @Test
    @DisplayName("Проверка, что зарегистрированный пользователь имеет доступ к содержимому страницы")
    public void test2() {
        MainPage mainPage = new MainPage();
        String username = "Vasiliy";
        String password = "Password";
        User user = new DefaultUser(username, password);

        DataBase.addUser(user);
        boolean isUserAllowed = mainPage.isAllowed(mainPage.authenticateUser(username, password));

        Assertions.assertThat(isUserAllowed).isEqualTo(true);
    }

//    @Test
//    @DisplayName("Проверка, что пользователь возвращается при регистрации")
//    public void test3() {
//        String username = "Vasiliy";
//        String password = "Password";
//        User user = new DefaultUser();
//
//        user = user.register();
//        User userFromDB = DataBase.getUserByUsernameAndPassword(username, password);
//
//        Assertions.assertThat(userFromDB).isEqualTo(user);
//    }
//
//    @Test
//    @DisplayName("Проверка ошибки валидации пароля")
//    public void test4() {
//        String username = "Vasiliy";
//        String password = "Password";
//        String passwordToCheck = "Wrong password";
//        User user = new DefaultUser();
//
//        user = user.register();
//        boolean isValid = user.validatePassword(passwordToCheck);
//
//        Assertions.assertThat(isValid).isEqualTo(false);
//    }
//
//    @Test
//    @DisplayName("Проверка успешной валидации пароля")
//    public void test5() {
//        String username = "Vasiliy";
//        String password = "Password";
//        String passwordToCheck = "Password";
//        User user = new DefaultUser();
//
//        user = user.register();
//        boolean isValid = user.validatePassword(passwordToCheck);
//
//        Assertions.assertThat(isValid).isEqualTo(true);
//    }

    @Test
    @DisplayName("Проверка медиатора User")
    public void test6() {
        String username1 = "Vasiliy1";
        String username2 = "Vasiliy2";
        String password = "Password";
        User user = new DefaultUser(username1, password);
        User superUser = new SuperUser(username2, password);

        List<User> users = List.of(user, superUser);
        for(User usr: users){
            DataBase.addUser(usr);
        }

        Assertions.assertThat(DataBase.getUserByUsernameAndPassword(username1, password)).isEqualTo(user);
        Assertions.assertThat(DataBase.getUserByUsernameAndPassword(username2, password)).isEqualTo(superUser);
    }
}
