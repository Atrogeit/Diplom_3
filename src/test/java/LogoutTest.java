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
    private MainPage mainPage;
    private HeaderMainPage headerMainPage;
    private User user;
    private UserClientSpec userClientSpec;
    private String token;

    @Before
    public void setUp() {
        super.setUp();
        driver.get(LOGIN_PATH);
        user = UserGenerator.getUser();
        userClientSpec = new UserClientSpec();
        ValidatableResponse createResponse = userClientSpec.create(user);
        token = createResponse.extract().path("accessToken");
        profilePage = new ProfilePage(driver);
        headerMainPage = new HeaderMainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);
    }

    @Test
    @DisplayName("Logout")
    public void checkProfileLogout() {

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
            userClientSpec.delete(token);
        }
    }
}
