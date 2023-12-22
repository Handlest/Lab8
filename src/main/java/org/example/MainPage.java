package org.example;

//Абстрактные классы
abstract class Button {
    public abstract void display();
}
abstract class TextField {
    public abstract void display();
}



//Реализации абстрактных классов
class LightButton extends Button {
    @Override
    public void display() {
        System.out.println("Light Button displayed");
    }
}
class DarkButton extends Button {
    @Override
    public void display() {
        System.out.println("Dark Button displayed");
    }
}
class LightTextField extends TextField {
    @Override
    public void display() {
        System.out.println("Light Text Field displayed");
    }
}
class DarkTextField extends TextField {
    @Override
    public void display() {
        System.out.println("Dark Text Field displayed");
    }
}

// Абстрактная фабрика для создания элементов интерфейса веб-страницы
abstract class PageFactory {
    public abstract Button createButton();
    public abstract TextField createTextField();
}

// Фабрика для создания элементов интерфейса веб-страницы в светлой теме
class LightPageFactory extends PageFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public TextField createTextField() {
        return new LightTextField();
    }
}

// Фабрика для создания элементов интерфейса веб-страницы в темной теме
class DarkPageFactory extends PageFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public TextField createTextField() {
        return new DarkTextField();
    }
}

// Клиент - веб-страница
public class MainPage {
    private Button button;
    private TextField textField;
    private User user;
    PageState currentState; // Состояние позволяет контролировать содержимое страницы в зависимости от прав пользователя

    public MainPage(PageFactory factory, User user) {
        this.user = user;
        button = factory.createButton();
        textField = factory.createTextField();
        currentState = new DefaultState(); // Базовое состояние по умолчанию
    }

    public void display() {
        button.display();
        textField.display();
        if (user.isSuperUser()){
            setState(new SuperUserState());
        }
        currentState.displayContent(); // Отображение содержимого страницы с помощью переопределённого для двух классов метода
    }

    private void setState(PageState state) {
        currentState = state; // При необходимости смены состояния, вызывается данная функция
    }
}
