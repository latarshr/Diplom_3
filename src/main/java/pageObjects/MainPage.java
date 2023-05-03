package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site";

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement enterBtn;

    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement constructBurgerText;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect')]")
    private SelenideElement bunTab;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect')]")
    private SelenideElement sauceTab;

    @FindBy(how = How.XPATH, using = ".//*[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect')]")
    private SelenideElement fillingTab;

    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement bunSubHeader;

    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement sauceSubHeader;

    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
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
        Thread.sleep(3000);
        return tab.getAttribute("class").equals("tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
    }

    public boolean isUserAuthorized() {
        makeOrderBtn.shouldBe(Condition.visible);
        return makeOrderBtn.getText().equals ("Оформить заказ");
    }
}