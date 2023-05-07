import org.junit.jupiter.api.*;
import pageObjects.MainPage;
import io.qameta.allure.AllureId;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainPageTest {

    private static MainPage mainPage;

    @BeforeAll
    public static void openPage() {
        open(MainPage.URL);
        mainPage = page(MainPage.class);
    }

    @Test
    @Order(1)
    @AllureId("5")
    void shouldSelectSauce() {
        mainPage.sauceTab.click();

    }

    @Test
    @Order(2)
    @AllureId("6")
    void shouldCheckActiveSauceTab() throws Exception {
        mainPage.isActiveSauceTab();
    }

    @Test
    @Order(3)
    @AllureId("7")
    void shouldSelectFilling() {
        mainPage.fillingTab.click();

    }

    @Test
    @Order(4)
    @AllureId("8")
    void shouldCheckActiveFillingTab() throws Exception {
        mainPage.isActiveFillingTab();
    }

    @Test
    @Order(5)
    @AllureId("9")
    void shouldSelectBun() {
        mainPage.bunTab.click();

    }

    @Test
    @Order(6)
    @AllureId("10")
    void shouldCheckActiveBunTab() throws Exception {
        mainPage.isActiveBunTab();
    }
}