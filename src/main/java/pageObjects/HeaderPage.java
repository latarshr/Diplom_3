package pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;

public class HeaderPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site";

    //Локатор перехода в личный кабинет
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement profileBtn;

    //Локатор кнопки Конструктор
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement constructorBtn;

    //Локатор логотипа компании
    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement burgerLogo;

    //Метод перехода по кнопке Конструктор
    public MainPage clickConstructorBtn() {
        constructorBtn.click();
        return page(MainPage.class);
    }

    //Метод перехода на главную по клику на логотип
    public MainPage clickBurgerLogo() {
        burgerLogo.click();
        return page(MainPage.class);
    }

    //Метод клика по личному кабинету
    public ProfilePage clickProfileBtn() {
        profileBtn.scrollIntoView(true).click();
        return page(ProfilePage.class);
    }
}
