import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.*;
import User.User;
import tools.UserClientSpec;
import User.UserGenerator;

import static org.junit.Assert.assertTrue;
import static tools.URLs.LOGIN_PATH;

public class SwitchOutAccountTest extends TestSetUp {
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
        headerMainPage = new HeaderMainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);
        authorizationPage.waitForTitleLoginPage();
        authorizationPage.userAuthorization(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();
        headerMainPage.clickAccountButton();
    }

    @Test
    @DisplayName("Switch to constructor page by clicking on logo")
    public void checkSwitchToConstructorByClickLogoSiteFromAccountScreen() {

        headerMainPage.clickByLogoStellarBurgers();

        WebElement titleConstructorTab = driver.findElement(mainPage.constructorTabTitle);
        boolean titleConstructorTabIsVisible = titleConstructorTab.isDisplayed();

        assertTrue(titleConstructorTabIsVisible);
    }

    @Test
    @DisplayName("Switch to constructor tab from account screen")
    public void checkSwitchToConstructorFromAccountScreen() {

        headerMainPage.clickSectionConstructor();

        WebElement titleConstructorTab = driver.findElement(mainPage.constructorTabTitle);
        boolean titleConstructorTabIsVisible = titleConstructorTab.isDisplayed();

        assertTrue(titleConstructorTabIsVisible);
    }

    @After
    public void cleanUp() {
        if ( token != null) {
            userClientSpec.delete(token);
        }
    }
}
