import User.User;
import tools.UserClientSpec;
import User.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.*;
import static tools.URLs.*;
import static org.junit.Assert.assertTrue;

public class LogoutTest extends TestSetUp {
    private ProfilePage profilePage;
    private AuthorizationPage authorizationPage;
    private LoginSteps loginSteps;
    private RegistrationPage registrationPage;
    private  PasswordRecoveryPage passwordRecoveryPage;
    private MainPage mainPage;
    private HeaderMainPage headerMainPage;
    private User user;
    private UserClientSpec UserClientSpec;
    private String token;

    @Before
    public void setUp() {
        super.setUp();
        driver.get(LOGIN_PATH);
        user = UserGenerator.getUser();
        UserClientSpec = new UserClientSpec();
        ValidatableResponse createResponse = UserClientSpec.create(user);
        token = createResponse.extract().path("accessToken");
    }

    @Test
    @DisplayName("Logout")
    public void checkProfileLogout() {
        profilePage = new ProfilePage(driver);
        headerMainPage = new HeaderMainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, passwordRecoveryPage, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);

        authorizationPage.waitForTitleLoginPage();
        authorizationPage.userAuthorization(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();
        headerMainPage.clickAccountButton();
        profilePage.logoutButtonClick();

        WebElement element = driver.findElement(/**/authorizationPage.loginButton);
        boolean elementIsDisplayedAfterLogout = element.isDisplayed();

        assertTrue(elementIsDisplayedAfterLogout);
    }

    @After
    public void cleanUp() {
        if ( token != null) {
            UserClientSpec.delete(token);
        }
    }
}
