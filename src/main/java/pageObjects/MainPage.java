package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site";

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement enterBtn;

    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement constructBurgerText;

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    public SelenideElement bunTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    public SelenideElement sauceTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    public SelenideElement fillingTab;

    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Булки')]")
    private SelenideElement bunSubHeader;

    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Соусы')]")
    private SelenideElement sauceSubHeader;

    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Начинки')]")
    private SelenideElement fillingSubHeader;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement makeOrderBtn;

    public AuthPage clickEnterBtn() {
        enterBtn.click();
        return page(AuthPage.class);
    }


    public boolean isConstHeaderVisible() {
        constructBurgerText.shouldBe(Condition.visible);
        return constructBurgerText.getText().equals("Соберите бургер") && URL.equals("https://stellarburgers.nomoreparties.site");
    }

    public boolean isActiveBunTab() throws Exception {
        return checkTabIsActive(bunSubHeader, bunTab);
    }

    public boolean isActiveSauceTab() throws Exception {
        return checkTabIsActive(sauceSubHeader, sauceTab);
    }

    public boolean isActiveFillingTab() throws Exception {
        return checkTabIsActive(fillingSubHeader, fillingTab);
    }

    private boolean checkTabIsActive(SelenideElement subHeader, SelenideElement tab) throws InterruptedException {
        subHeader.scrollIntoView(true);
        System.out.println("Checking tab: " + subHeader.getText());

        // Проверяем, что таб находится в пределах видимости страницы
        if (!tab.isDisplayed()) {
            System.out.println("Tab is not visible");
            return false;
        }

        // Ожидаем, что класс таба содержит "tab_tab_type_current__2BEPc"
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 5000) {
            if (tab.getAttribute("class").contains("tab_tab_type_current__2BEPc")) {
                System.out.println("Tab is active");
                return true;
            }
            Thread.sleep(500);
        }

        // Если таб не стал активным в течение заданного времени, то возвращаем false
        System.out.println("Tab is not active");
        return false;
    }
    public boolean isUserAuthorized() {
        makeOrderBtn.shouldBe(Condition.visible);
        return makeOrderBtn.getText().equals ("Оформить заказ");
    }

}