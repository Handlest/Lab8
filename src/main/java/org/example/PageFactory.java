package org.example;

import java.util.HashMap;
import java.util.Map;

public class PageFactory {
    private Map<String, PageFlyweight> flyweights = new HashMap<>();
    public PageFlyweight getPage(String pageType) {
        PageFlyweight flyweight = flyweights.get(pageType);

        if (flyweight == null) {
            // Создаем новый объект страницы и добавляем его в хранилище
            String content = generatePageContent(pageType);
            flyweight = new PageFlyweight(content);
            flyweights.put(pageType, flyweight);
        }

        return flyweight;
    }

    private String generatePageContent(String pageType) {
        return "This is a " + pageType + " page"; // Пример содержимого страницы
    }
}
