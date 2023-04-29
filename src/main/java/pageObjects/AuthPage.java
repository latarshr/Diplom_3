package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;

public class AuthPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site/login";

    //Локатор заголовка Вход
    @FindBy(how = How.XPATH, using = ".//h2[text()='Вход']")
    private SelenideElement authHeader;

    //Локатор поля Email
    @FindBy(how = How.XPATH, using = ".//input[@name = 'name']")
    private SelenideElement authInputEmail;

    //Локатор поля Пароль
    @FindBy(how = How.XPATH, using = ".//input[@name = 'Пароль']")
    private SelenideElement authInputPassword;

    //Локатор кнопки Войти
    @FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa'][text()='Войти']")
    private SelenideElement authBtn;

    //Локатор линка Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//a[@href='/register'][text()='Зарегистрироваться']")
    private SelenideElement regLinkBtn;

    //Локатор линка Восстановить пароль
    @FindBy(how = How.XPATH, using = ".//a[@href='/forgot-password'][text()='Восстановить пароль']")
    private SelenideElement refreshLinkBtn;

    //Метод заполнения поля Email
    public AuthPage fillAuthInputEmail(String value) {
        authInputEmail.click();
        authInputEmail.sendKeys(value);
        return page(AuthPage.class);
    }

    //Метод заполнения поля Пароль
    public AuthPage fillAuthInputPassword(String value) {
        authInputPassword.click();
        authInputPassword.sendKeys(value);
        return page(AuthPage.class);
    }

    //Метод авторизации юзера, кнопка Войти
    public MainPage clickAuthBtn() throws Exception {
        authBtn.scrollIntoView(true).click();
        Thread.sleep(1000);
        return page(MainPage.class);
    }

    //Метод проверки отображения страницы авторизации
    public boolean isAuthPageVisible() {
        authHeader.shouldBe(Condition.visible);
        return  authHeader.getText().equals("Вход") && URL.equals("https://stellarburgers.nomoreparties.site/login");
    }

    //Метод перехода по линку Зарегистрироваться
    public RegistrationPage clickRegLinkBtn() {
        regLinkBtn.click();
        return page(RegistrationPage.class);
    }

    //Метод перехода по линку Восстановить пароль
    public PasswordRefreshPage clickRefreshLinkBtn() {
        refreshLinkBtn.click();
        return page(PasswordRefreshPage.class);
    }
}
