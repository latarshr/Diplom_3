import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import pageObjects.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    MainPage mainPage;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        mainPage = open(MainPage.URL, MainPage.class);
    }
}
