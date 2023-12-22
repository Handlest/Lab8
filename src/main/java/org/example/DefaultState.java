package org.example;

public class DefaultState implements PageState{
    @Override public void displayContent(){
        System.out.println("Дополнительное содежимое страницы ОБЫЧНОГО пользователя");
    }
}
