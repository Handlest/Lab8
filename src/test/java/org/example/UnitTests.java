package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Проверка, что пользователь возвращается при регистрации")
    public void test3() {
        String username = "Vasiliy";
        String password = "Password";
        User user = new DefaultUser();

        user = user.register(username, password);
        User userFromDB = DataBase.getUserByUsernameAndPassword(username, password);

        Assertions.assertThat(userFromDB).isEqualTo(user);
    }

    @Test
    @DisplayName("Проверка ошибки валидации пароля")
    public void test4() {
        String username = "Vasiliy";
        String password = "Password";
        String passwordToCheck = "Wrong password";
        User user = new DefaultUser();

        user = user.register(username, password);
        boolean isValid = user.validatePassword(passwordToCheck);

        Assertions.assertThat(isValid).isEqualTo(false);
    }

    @Test
    @DisplayName("Проверка успешной валидации пароля")
    public void test5() {
        String username = "Vasiliy";
        String password = "Password";
        String passwordToCheck = "Password";
        User user = new DefaultUser();

        user = user.register(username, password);
        boolean isValid = user.validatePassword(passwordToCheck);

        Assertions.assertThat(isValid).isEqualTo(true);
    }
}
