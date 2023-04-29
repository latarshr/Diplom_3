package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site/register";

    //Локатор заголовка Регистрация
    @FindBy(how = How.XPATH, using = ".//h2[text()='Регистрация']")
    private SelenideElement regHeader;

    //Локатор поля Имя
    @FindBy(how = How.XPATH, using = "(.//*[contains(@class, 'input pr-6 pl-6')]/input)[1]")
    private SelenideElement inputName;

    //Локатор поля email
    @FindBy(how = How.XPATH, using = "(.//*[contains(@class, 'input pr-6 pl-6')]/input)[2]")
    private SelenideElement inputEmail;

    //Локатор поля Пароль
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement inputPassword;

    //Локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement regBtn;

    //Локатор ошибки поля Пароль
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement errorPassword;

    //Локатор линка Войти
    @FindBy(how = How.XPATH, using = ".//a[@href='/login'][text()='Войти']")
    private SelenideElement enterLinkBtn;

    //Метод заполнения поля Имя
    public RegistrationPage fillInputName(String value) {
        inputName.click();
        inputName.sendKeys(value);
        return page(RegistrationPage.class);
    }

    //Метод заполнения поля Email
    public RegistrationPage fillInputEmail(String value) {
        inputEmail.click();
        inputEmail.sendKeys(value);
        return page(RegistrationPage.class);
    }

    //Метод заполнения поля Пароль
    public RegistrationPage fillInputPassword(String value) {
        inputPassword.click();
        inputPassword.sendKeys(value);
        return page(RegistrationPage.class);
    }

    //Метод перехода по линку Войти
    public AuthPage clickEnterLinkBtn() {
        enterLinkBtn.click();
        return page(AuthPage.class);
    }

    //Метод нажатия на кнопку Зарегистрироваться
    public AuthPage clickRegBtn() {
        regBtn.scrollIntoView(true).click();
        return page(AuthPage.class);
    }

    //Проверка отображения ошибки регистрации
    public boolean isErrorPasswordVisible() {
        errorPassword.shouldBe(Condition.visible);
        return errorPassword.getText().equals("Некорректный пароль");
    }

    //Проверка отображения экрана регистрации
    public boolean isRegPageVisible() {
        regHeader.shouldBe(Condition.visible);
        return regHeader.getText().equals("Регистрация") && URL.equals("https://stellarburgers.nomoreparties.site/register");
    }
}
