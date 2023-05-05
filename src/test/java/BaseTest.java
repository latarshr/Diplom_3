import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit. jupiter.api. BeforeEach;
import pageObjects.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    MainPage mainPage;

    @BeforeEach
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        mainPage = open(MainPage.URL, MainPage.class);
    }
}
