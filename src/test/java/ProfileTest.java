import dto.userDto.UserDto;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObjects.AuthPage;
import pageObjects.HeaderPage;
import pageObjects.MainPage;
import pageObjects.ProfilePage;
import service.UserService;
import utils.UserDataGeneration;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class ProfileTest extends BaseTest{
    UserDto user;
    String accessToken;

    MainPage mainPage = open(MainPage.URL, MainPage.class);
    AuthPage authPage = open(AuthPage.URL, AuthPage.class);
    HeaderPage headerPage = open(HeaderPage.URL, HeaderPage.class);
    ProfilePage profilePage = open(ProfilePage.URL, ProfilePage.class);

    @Before
    public void setUp() throws Exception {
        UserDto newUser = UserDataGeneration.generateNewUser();
        user = new UserDto();
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        accessToken = UserService.registerUser(newUser)
                .then().assertThat()
                .statusCode(SC_OK)
                .extract().path("accessToken");

        mainPage.clickEnterBtn();
        authPage.fillAuthInputEmail(user.getEmail());
        authPage.fillAuthInputPassword(user.getPassword());
        authPage.clickAuthBtn();
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
    @DisplayName("Проверка входа в аккаунт")
    public void checkEnterProfile() {
        headerPage.clickProfileBtn();
        assertTrue(profilePage.isUserProfileVisible());
    }

    @Test
    @DisplayName("Проверка перехода из ЛК на страницу Конструктор")
    public void checkEnterConstructorFromProfile() {
        headerPage.clickProfileBtn();
        assertTrue(profilePage.isUserProfileVisible());
        headerPage.clickConstructorBtn();
        assertTrue(mainPage.isConstHeaderVisible());
    }

    @Test
    @DisplayName("Проверка перехода из ЛК по клику на лого")
    public void checkEnterConstructorFromProfileClickLogo() {
        headerPage.clickProfileBtn();
        assertTrue(profilePage.isUserProfileVisible());
        headerPage.clickBurgerLogo();
        assertTrue(mainPage.isConstHeaderVisible());
    }

    @Test
    @DisplayName("Проверка выхода из ЛК")
    public void checkLogOut() {
        headerPage.clickProfileBtn();
        profilePage.logOut();
        assertTrue(authPage.isAuthPageVisible());
    }
}
