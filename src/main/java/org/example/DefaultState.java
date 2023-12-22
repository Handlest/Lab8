package org.example;

public class DefaultState implements PageState{
    @Override public void displayContent(){
        System.out.println("Дополнительное содержимое страницы ОБЫЧНОГО пользователя");
    }
}
