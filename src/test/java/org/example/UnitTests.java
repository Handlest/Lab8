package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UnitTests {
    @Test
    @DisplayName("Проверка, что пользователь корректно добавляется и извлекается по паролю и логину из базы данных")
    public void test1() {
        DataBase db = new DataBase();
//        MainPage mainPage = new MainPage();

        String username = "Vasiliy";
        String password = "Password";

        User user = new DefaultUser(username, password);

        db.addUser(user);

        Assertions.assertThat(db.getUserByUsernameAndPassword(username, password)).isEqualTo(user);
    }

    @Test
    @DisplayName("Проверка, что зарегистрированный пользователь имеет доступ к содержимому страницы")
    public void test2() {
        DataBase db = new DataBase();
        MainPage mainPage = new MainPage();

        String username = "Vasiliy";
        String password = "Password";

        User user = new DefaultUser(username, password);

        db.addUser(user);

        Assertions.assertThat(mainPage.isAllowed(mainPage.authenticateUser(username, password, db))).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка, что пользователь возвращается при регистрации")
    public void test3() {
        DataBase db = new DataBase();

        String username = "Vasiliy";
        String password = "Password";

        User user = new DefaultUser();

        user = user.register(username, password, db);

        Assertions.assertThat(user).isEqualTo(db.getUserByUsernameAndPassword(username, password));
    }

    @Test
    @DisplayName("Проверка ошибки валидации пароля")
    public void test4() {
        DataBase db = new DataBase();

        String username = "Vasiliy";
        String password = "Password";

        User user = new DefaultUser();

        user = user.register(username, password, db);

        String passwordToCheck = "Wrong password";
        boolean isValid = user.validatePassword(passwordToCheck, db);

        Assertions.assertThat(isValid).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка успешной валидации пароля")
    public void test5() {
        DataBase db = new DataBase();

        String username = "Vasiliy";
        String password = "Password";

        User user = new DefaultUser();

        user = user.register(username, password, db);

        String passwordToCheck = "Password";
        boolean isValid = user.validatePassword(passwordToCheck, db);

        Assertions.assertThat(isValid).isEqualTo(true);
    }
}
