import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageObjects.MainPage;
import io.qameta.allure.AllureId;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static junit.framework.TestCase.assertTrue;

public class MainPageTest {
    private static MainPage mainPage;

    @BeforeAll
    public static void openPage() {
        open(MainPage.URL);
        mainPage = page(MainPage.class);
    }

    @Test
    @AllureId("5")
    void shouldCheckActiveSauceTab() throws Exception {
        mainPage.sauceTab.click();
        assertTrue(mainPage.isActiveSauceTab());
    }

    @Test
    @AllureId("6")
    void shouldCheckActiveFillingTab() throws Exception {
        mainPage.fillingTab.click();
        assertTrue(mainPage.isActiveFillingTab());
    }

    @Test
    @AllureId("7")
    void shouldCheckActiveBunTab() throws Exception {
        mainPage.bunTab.click();
        assertTrue(mainPage.isActiveBunTab());
    }
}