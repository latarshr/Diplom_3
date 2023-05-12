import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageObjects.MainPage;
import io.qameta.allure.AllureId;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class MainPageTest {
    private static MainPage mainPage;

    @BeforeAll
    public static void openPage() {
        open(MainPage.URL);
        mainPage = page(MainPage.class);
    }

    @Test
    @AllureId("5")
    void shouldSelectSauce() {
        mainPage.sauceTab.click();
    }

    @Test
    @AllureId("6")
    void shouldCheckActiveSauceTab() throws Exception {
        mainPage.isActiveSauceTab();
    }

    @Test
    @AllureId("7")
    void shouldSelectFilling() {
        mainPage.fillingTab.click();
    }

    @Test
    @AllureId("8")
    void shouldCheckActiveFillingTab() throws Exception {
        mainPage.isActiveFillingTab();
    }

    @Test
    @AllureId("9")
    void shouldSelectBun() {
        mainPage.fillingTab.click();
        mainPage.bunTab.click();
    }

    @Test
    @AllureId("10")
    void shouldCheckActiveBunTab() throws Exception {
        mainPage.isActiveBunTab();
    }
}