package org.example;

public class SuperUserState implements PageState{
    @Override public void displayContent(){
        System.out.println("Дополнительное содержимое страницы СУПЕРПОЛЬЗОВАТЕЛЯ");
    }
}
