import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.*;
import User.*;
import tools.UserClientSpec;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static tools.URLs.ACCOUNT_PATH;
import static tools.URLs.LOGIN_PATH;

public class SwitchToAccountTest extends TestSetUp {

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
    @DisplayName("Switch to Account via profile button click")
    public void checkSwitchToAccountViaProfileButton() {

        authorizationPage.waitForTitleLoginPage();
        authorizationPage.userAuthorization(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();
        headerMainPage.clickAccountButton();

        WebElement profileTab = driver.findElement(profilePage.sectionProfile);
        boolean profileTabIsActive = profileTab.getAttribute("class").contains("active");

        String expectedUrlLogin = ACCOUNT_PATH;
        String actualUrlLogin = driver.getCurrentUrl();

        assertTrue(profileTabIsActive);
        assertEquals(expectedUrlLogin, actualUrlLogin);
    }

    @After
    public void cleanUp() {
        if ( token != null) {
            userClientSpec.delete(token);
        }
    }
}
