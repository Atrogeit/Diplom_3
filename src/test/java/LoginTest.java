import User.User;
import tools.UserClientSpec;
import User.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;
import static tools.URLs.*;
import static org.junit.Assert.assertEquals;
import static tools.URLs.URL;


public class LoginTest  extends TestSetUp {
    private AuthorizationPage authorizationPage;
    private LoginSteps loginSteps;
    private RegistrationPage registrationPage;
    private PasswordRecoveryPage passwordRecoveryPage;
    private MainPage mainPage;
    private HeaderMainPage headerMainPage;
    private User user;
    private UserClientSpec UserClientSpec;
    private String token;


    @Before
    public void setUp() {
        super.setUp();
        driver.get(URL);
        user = UserGenerator.getUser();
        UserClientSpec = new UserClientSpec();
        ValidatableResponse createResponse = UserClientSpec.create(user);
        token = createResponse.extract().path("accessToken");
    }

    @Test
    @DisplayName("Main page authorization")
    public void checkLoginFromMainPage() {
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, passwordRecoveryPage, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);

        mainPage.clickLoginButton();
        authorizationPage.userAuthorization(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();

        String expectedUrlLogin = URL;
        String actualUrlLogin = driver.getCurrentUrl();

        assertEquals(expectedUrlLogin, actualUrlLogin);
    }

    @Test
    @DisplayName("Authorization from account screen")
    public void checkLoginFromAccountScreen() {
        headerMainPage = new HeaderMainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, passwordRecoveryPage, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);

        headerMainPage.clickAccountButton();
        authorizationPage.waitForTitleLoginPage();
        authorizationPage.userAuthorization(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();

        String expectedUrlLogin = URL;
        String actualUrlLogin = driver.getCurrentUrl();

        assertEquals(expectedUrlLogin, actualUrlLogin);
    }

    @Test
    @DisplayName("Authorization from registration page")
    public void checkLoginFromRegistrationPage() {
        headerMainPage = new HeaderMainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, passwordRecoveryPage, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);

        driver.get(REGISTRATION_PATH);
        registrationPage.waitForTitleRegistration();
        registrationPage.clickLoginButtonLink();

        authorizationPage.userAuthorization(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();

        String expectedUrlLogin = URL;
        String actualUrlLogin = driver.getCurrentUrl();

        assertEquals(expectedUrlLogin, actualUrlLogin);
    }

    @Test
    @DisplayName("Authorization from password recovery page")
    public void checkLoginFromPasswordRecoveryScreen() {
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        headerMainPage = new HeaderMainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, passwordRecoveryPage, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);

        driver.get(LOGIN_PATH);
        authorizationPage.waitForTitleLoginPage();

        authorizationPage.clickRecoverPasswordButton();
        passwordRecoveryPage.LoginButtonClick();

        authorizationPage.userAuthorization(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();

        String expectedUrlLogin = URL;
        String actualUrlLogin = driver.getCurrentUrl();

        assertEquals(expectedUrlLogin, actualUrlLogin);
    }

    @After
    public void cleanUp() {
        if ( token != null) {
            UserClientSpec.delete(token);
        }
    }
}
