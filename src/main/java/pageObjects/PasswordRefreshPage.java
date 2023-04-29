package pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class PasswordRefreshPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //Локатор линка Войти
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement authFromRefreshLinkBtn;

    //Метод перехода по линку со страницы восстановления пароля
    public AuthPage clickAuthFromRefreshLinkBtn() {
        authFromRefreshLinkBtn.click();
        return page(AuthPage.class);
    }
}
