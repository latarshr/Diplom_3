import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainPageTest extends BaseTest{
    @Test
    @DisplayName("Проверка возможности выбора булки")
    public void checkBunTabActive() throws Exception {
        boolean result = mainPage.isActiveBunTab();
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка возможности выбора соуса")
    public void checkSauceTabActive() throws Exception {
        boolean result = mainPage.isActiveSauceTab();
        assertTrue(result);
    }

    @Test
    @DisplayName("Проверка возможности выбора начинки")
    public void checkFillingTabActive() throws Exception {
        boolean result = mainPage.isActiveFillingTab();
        assertTrue(result);
    }
}
