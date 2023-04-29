import dto.userDto.UserDto;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObjects.AuthPage;
import pageObjects.MainPage;
import pageObjects.RegistrationPage;
import service.UserService;
import utils.UserDataGeneration;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class RegistrationTest extends BaseTest {
    UserDto user;
    String accessToken;
    MainPage mainPage = open(MainPage.URL, MainPage.class);
    AuthPage authPage = open(AuthPage.URL, AuthPage.class);
    RegistrationPage regPage = open(RegistrationPage.URL, RegistrationPage.class);

    @Before
    public void setUp() {
        user = UserDataGeneration.generateNewUser();

        mainPage.clickEnterBtn();
        authPage.clickRegLinkBtn();
    }

    @After
    public void teardown() {
        if (accessToken == null)
            return;
        accessToken = UserService.loginUser(user)
                .then().assertThat()
                .statusCode(SC_OK)
                .extract().path("accessToken");
        UserService.deleteUser(accessToken)
                .then().assertThat()
                .statusCode(SC_ACCEPTED)
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void checkSuccessRegistration() {
        regPage.fillInputName(user.getName());
        regPage.fillInputEmail(user.getEmail());
        regPage.fillInputPassword(user.getPassword());
        regPage.clickRegBtn();
        assertTrue(authPage.isAuthPageVisible());

        UserService.registerUser(user);
        int statusCode = UserService.loginUser(user).then().extract().statusCode();
        assertEquals("Код статуса отличается от ожидаемого результата", SC_OK, statusCode);
    }

    @Test
    @DisplayName("Проверка регистрации с невалидным паролем")
    public void checkRegistrationFailed() {
        user.setPassword(RandomStringUtils.randomAlphanumeric(5));
        regPage.fillInputName(user.getName());
        regPage.fillInputEmail(user.getEmail());
        regPage.fillInputPassword(user.getPassword());
        regPage.clickRegBtn();
        assertTrue(regPage.isErrorPasswordVisible());

        UserService.registerUser(user).then().assertThat()
                .statusCode(SC_OK);
        assertNull(accessToken);
    }

    @Test
    @DisplayName("Проверка невозможности регистрации с пустым паролем")
    public void checkPasswordCanNotBeNull() {
        user.setPassword("");
        regPage.fillInputName(user.getName());
        regPage.fillInputEmail(user.getEmail());
        regPage.fillInputPassword(user.getPassword());
        regPage.clickRegBtn();
        assertTrue(regPage.isRegPageVisible());

        UserService.registerUser(user).then().assertThat()
                .statusCode(SC_FORBIDDEN);
        assertNull(accessToken);
    }
}
