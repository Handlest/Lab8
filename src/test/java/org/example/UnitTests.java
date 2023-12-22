package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

//    @Test
//    @DisplayName("Проверка, что зарегистрированный пользователь имеет доступ к содержимому страницы")
//    public void test2() {
//        MainPage mainPage = new MainPage();
//        String username = "Vasiliy";
//        String password = "Password";
//        User user = new DefaultUser(username, password);
//
//        DataBase.addUser(user);
//        boolean isUserAllowed = mainPage.isAllowed(mainPage.authenticateUser(username, password));
//
//        Assertions.assertThat(isUserAllowed).isEqualTo(true);
//    }

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
        //Создаём пользователей, относящихся к разным классам
        User user = new DefaultUser(username1, password);
        User superUser = new SuperUser(username2, password);

        //Создаём список пользователей и добавляем их в базу данных используя интерфейс User
        List<User> users = List.of(user, superUser);
        for(User usr: users){
            DataBase.addUser(usr);
        }

        //Проверка, что пользователи были успешно зарегистрированы
        Assertions.assertThat(DataBase.getUserByUsernameAndPassword(username1, password)).isEqualTo(user);
        Assertions.assertThat(DataBase.getUserByUsernameAndPassword(username2, password)).isEqualTo(superUser);
    }

//    @Test
//    @DisplayName("Проверка абстрактной фабрики для отрисовки страниц")
//    public void test7() {
//        User user = new DefaultUser();
//        PageFactory lightFactory = new LightPageFactory();
//        MainPage lightPage = new MainPage(lightFactory, user);
//        lightPage.display();
//
//        PageFactory darkFactory = new DarkPageFactory();
//        MainPage darkPage = new MainPage(darkFactory, user);
//        darkPage.display();
//    }

    @Test
    @DisplayName("Проверка прокси")
    public void test8() {
        String username1 = "User1";
        String username2 = "User2";
        String password1 = "Password";
        String password2 = "Short";
        //Создаём пользователей
        User user1 = new DefaultUser(username1, password1);
        User user2 = new DefaultUser(username2, password2);
        //Создаём список пользователей и добавляем их в базу данных с помощью защищающего прокси DataBaseManager
        DataBaseManager.addUser(user1);
        DataBaseManager.addUser(user2);
        //Проверка, что только пользователи с правильным паролем были добавлены в базу данных
        Assertions.assertThat(DataBaseManager.getUserByUsernameAndPassword(username1, password1)).isEqualTo(user1);
        Assertions.assertThat(DataBaseManager.getUserByUsernameAndPassword(username2, password2)).isEqualTo(null);
    }

    @Test
    @DisplayName("Паттерн легковес для кэширования страниц")
    public void test9() {
        PageFactory pageFactory = new PageFactory();
        System.out.println("Создание первой страницы\n");
        User user = new DefaultUser("Riko", "password");
        MainPage page = new MainPage(pageFactory, user);
        page.display();

        System.out.println("Страницы в кэше:  " + page.getCachedPages());


        System.out.println("Создание второй страницы\n");
        User user2 = new DefaultUser("Riko", "password");
        MainPage page2 = new MainPage(pageFactory, user2);
        page2.display();

        System.out.println("Страницы в кэше:  " + page.getCachedPages());
    }
}
