import User.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tools.UserClientSpec;
import User.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;

import static org.junit.Assert.assertTrue;
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
    private UserClientSpec userClientSpec;
    private String token;

    private By createOrderButton = By.xpath("//button[text()='Оформить заказ']");


    @Before
    public void setUp() {
        super.setUp();
        driver.get(URL);
        user = UserGenerator.getUser();
        userClientSpec = new UserClientSpec();
        ValidatableResponse createResponse = userClientSpec.create(user);
        token = createResponse.extract().path("accessToken");
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, passwordRecoveryPage, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);
        headerMainPage = new HeaderMainPage(driver);
    }

    @Test
    @DisplayName("Main page authorization")
    public void checkLoginFromMainPage() {

        mainPage.clickLoginButton();
        authorizationPage.userAuthorization(user.getEmail(), user.getPassword());
        mainPage.waitForCreateOrderButton();

        String expectedUrlLogin = URL;
        String actualUrlLogin = driver.getCurrentUrl();

        assertEquals(expectedUrlLogin, actualUrlLogin);

        WebElement orderButton = driver.findElement(By.xpath("//button[text()='Оформить заказ']"));
        boolean orderButtonIsVisible = orderButton.isDisplayed();

        assertTrue(orderButtonIsVisible);
    }

    @Test
    @DisplayName("Authorization from account screen")
    public void checkLoginFromAccountScreen() {

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
        authorizationPage = new AuthorizationPage(driver, passwordRecoveryPage, loginSteps);

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
            userClientSpec.delete(token);
        }
    }
}
