package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {
    public final static String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //Локатор надписи в личном кабинете
    @FindBy(how = How.XPATH, using = ".//p[@class='Account_text__fZAIn text text_type_main-default']")
    private SelenideElement profileText;

    //Локатор кнопки Выход
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement logOutBtn;

    //Метод проверки отображения кабинета авторизованного юзера
    public boolean isUserProfileVisible() {
        profileText.shouldBe(Condition.visible);
        return profileText.getText().equals("В этом разделе вы можете изменить свои персональные данные") && URL.equals("https://stellarburgers.nomoreparties.site/account/profile");
    }

    //Метод выхода из аккаунта
    public AuthPage logOut() {
        logOutBtn.scrollIntoView(true).click();
        return page(AuthPage.class);
    }
}
