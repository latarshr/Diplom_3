import dto.userDto.UserDto;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObjects.*;
import service.UserService;
import utils.UserDataGeneration;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class AuthorizationTest extends BaseTest {
    UserDto user;
    String accessToken;

    MainPage mainPage = open(MainPage.URL, MainPage.class);
    AuthPage authPage = open(AuthPage.URL, AuthPage.class);
    HeaderPage headerPage = open(HeaderPage.URL, HeaderPage.class);

    @Before
    public void setUp() {
        UserDto newUser = UserDataGeneration.generateNewUser();
        user = new UserDto();
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        accessToken = UserService.registerUser(newUser)
                .then().assertThat()
                .statusCode(SC_OK)
                .extract().path("accessToken");
    }

    @After
    public void teardown() {
        if (accessToken == null)
            return;
        UserService.deleteUser(accessToken)
                .then().assertThat()
                .statusCode(SC_ACCEPTED)
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));
    }

    @Test
    @DisplayName("Проверка входа с главной страницы")
    public void checkEnterFromMainPage() throws Exception {
        mainPage.clickEnterBtn();
        authPage.fillAuthInputEmail(user.getEmail());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.clickAuthBtn();
        assertTrue(mainPage.isUserAuthorized());
    }

    @Test
    @DisplayName("Проверка входа с страницы личного кабинета")
    public void checkEnterFromProfile() throws Exception {
        headerPage.clickProfileBtn();
        authPage.fillAuthInputEmail(user.getEmail());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.clickAuthBtn();
        assertTrue(mainPage.isUserAuthorized());
    }

    @Test
    @DisplayName("Проверка входа со страницы регистрации")
    public void checkEnterFromRegPage() throws Exception {
        RegistrationPage regPage = open(RegistrationPage.URL, RegistrationPage.class);
        regPage.clickEnterLinkBtn();
        authPage.fillAuthInputEmail(user.getEmail());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.clickAuthBtn();
        assertTrue(mainPage.isUserAuthorized());
    }

    @Test
    @DisplayName("Проверка входа со страницы Восстановление пароля")
    public void checkEnterFromPasswordRefreshPage() throws Exception {
        PasswordRefreshPage passwordRefreshPage = open(PasswordRefreshPage.URL, PasswordRefreshPage.class);
        passwordRefreshPage.clickAuthFromRefreshLinkBtn();
        authPage.fillAuthInputEmail(user.getEmail());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.clickAuthBtn();
        assertTrue(mainPage.isUserAuthorized());
    }
}
