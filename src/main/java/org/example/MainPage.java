package org.example;

import java.util.HashMap;
import java.util.Map;

// Клиент - веб-страница
public class MainPage {
    private User user;
    private PageFactory factory;
    PageState currentState;

    private Map<String, PageFlyweight> cachedPages = new HashMap<>();

    public MainPage(PageFactory factory, User user) {
        this.user = user;
        this.factory = factory;
        currentState = new DefaultState(); // Базовое состояние по умолчанию
    }

    public void display() {
        if (user.isSuperUser()){
            setState(new SuperUserState());
        }
        // Получаем тип страницы, который нужно отобразить в зависимости от прав пользователя
        String pageType = getCurrentPageType();

        // Получаем легковесный объект страницы из хранилища или создаем новый, если его еще нет
        PageFlyweight page = getCachedPage(pageType);

        // Отображаем содержимое страницы
        System.out.println(page.getContent());

        currentState.displayContent(); // Отображение содержимого страницы с помощью переопределённого для двух классов метода
    }

    private String getCurrentPageType() {
        if (user.isSuperUser()) {
            return "SuperUser";
        } else {
            return "Default";
        }
    }

    private PageFlyweight getCachedPage(String pageType) {
        PageFlyweight page = cachedPages.get(pageType);

        if (page == null) {
            // Получаем легковесный объект страницы из фабрики и добавляем его в кэш
            page = factory.getPage(pageType);
            cachedPages.put(pageType, page);
        }

        return page;
    }

    public Map<String, PageFlyweight> getCachedPages() {
        return cachedPages;
    }

    private void setState(PageState state) {
        currentState = state; // При необходимости смены состояния, вызывается данная функция
    }
}
