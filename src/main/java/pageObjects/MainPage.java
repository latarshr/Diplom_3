package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site";

    //Локатор кнопки Войти в аккаунт на главной странице
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement enterBtn;

    //Локатор текста Соберите бургер
    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement constructBurgerText;

    //Локатор вкладки Булки
    @FindBy(how = How.XPATH, using = ".//*[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect')]")
    private SelenideElement bunTab;

    //Локатор вкладки Соусы
    @FindBy(how = How.XPATH, using = ".//*[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect')]")
    private SelenideElement sauceTab;

    //Локатор вкладки Начинки
    @FindBy(how = How.XPATH, using = ".//*[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect')]")
    private SelenideElement fillingTab;

    //Локатор подзаголовка Булки
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement bunSubHeader;

    //Локатор подзаголовка Соусы
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement sauceSubHeader;

    //Локатор подзаголовка Начинки
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement fillingSubHeader;

    //Локатор кнопки Оформить заказ
    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement makeOrderBtn;

    //Метод клика по кнопке
    public AuthPage clickEnterBtn() {
        enterBtn.click();
        return page(AuthPage.class);
    }

    //Метод проверки отображения конструктора
    public boolean isConstHeaderVisible() {
        constructBurgerText.shouldBe(Condition.visible);
        return constructBurgerText.getText().equals("Соберите бургер") && URL.equals("https://stellarburgers.nomoreparties.site");
    }

    //Метод проверки видимости подраздел Булки
    public boolean isActiveBunTab() throws Exception {
        bunSubHeader.scrollIntoView(true);
        Thread.sleep(3000);
        return bunTab.getAttribute("class").equals("tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
    }

    //Метод проверки видимости подраздел Соусы
    public boolean isActiveSauceTab() throws Exception {
        sauceSubHeader.scrollIntoView(true);
        Thread.sleep(3000);
        return sauceTab.getAttribute("class").equals("tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
    }

    //Метод проверки видимости подраздел Начинки
    public boolean isActiveFillingTab() throws Exception {
        fillingSubHeader.scrollIntoView(true);
        Thread.sleep(3000);
        return fillingTab.getAttribute("class").equals("tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
    }

    //Метод проверки авторизации
    public boolean isUserAuthorized() {
        makeOrderBtn.shouldBe(Condition.visible);
        return makeOrderBtn.getText().equals("Оформить заказ");
    }
}
