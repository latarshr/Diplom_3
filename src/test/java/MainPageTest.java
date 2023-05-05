import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageObjects.MainPage;
import io.qameta.allure.AllureId;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class MainPageTest {

    @BeforeAll
    public static void openPage() {
        open(MainPage.URL);
    }
    @Test
    @AllureId("5")
    void shouldSelectSauce() {
        MainPage mainPage = page(MainPage.class);
        mainPage.sauceTab.click();
        //TODO: select sauce
    }
    @Test
    @AllureId("6")
    void shouldCheckActiveSauceTab() throws Exception {
        MainPage mainPage = page(MainPage.class);
        mainPage.isActiveSauceTab();
    }
    @Test
    @AllureId("7")
    void shouldSelectFilling() {
        MainPage mainPage = page(MainPage.class);
        mainPage.fillingTab.click();
        //TODO: select filling
    }
    @Test
    @AllureId("8")
    void shouldCheckActiveFillingTab() throws Exception {
        MainPage mainPage = page(MainPage.class);
        mainPage.isActiveFillingTab();
    }
    @Test
    @AllureId("9")
    void shouldSelectBun() {
        MainPage mainPage = page(MainPage.class);
        open(MainPage.URL);
        mainPage.fillingTab.click();
        mainPage.bunTab.click();
        //TODO: select bun
    }
    @Test
    @AllureId("10")
    void shouldCheckActiveBunTab() throws Exception {
        MainPage mainPage = page(MainPage.class);
        mainPage.isActiveBunTab();
    }
}