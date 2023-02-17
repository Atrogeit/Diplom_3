import User.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.AuthorizationPage;
import pages.LoginSteps;
import pages.PasswordRecoveryPage;
import pages.RegistrationPage;
import static org.junit.Assert.assertEquals;
import static tools.URLs.*;

public class RegistrationTest extends TestSetUp {

    private PasswordRecoveryPage passwordRecoveryPage;
    private RegistrationPage registrationPage;
    private AuthorizationPage authorizationPage;
    private LoginSteps loginSteps;
    private User user;
    private final String ERROR_TEXT = "Некорректный пароль";

    @Before
    public void setUp() {
        super.setUp();
        driver.get(REGISTRATION_PATH);
        user = UserGenerator.getUser();
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        authorizationPage = new AuthorizationPage(driver, passwordRecoveryPage, loginSteps);
    }

    @Test
    @DisplayName("Checking successful registration")
    public void checkRegistrationIsSuccessful() {

        loginSteps.registrationUser(user.getName(), user.getEmail(), user.getPassword());
        authorizationPage.waitForTitleLoginPage();

        String expectedRegistrationUrl = LOGIN_PATH;
        String actualRegistrationUrl = driver.getCurrentUrl();

        assertEquals(expectedRegistrationUrl, actualRegistrationUrl);
    }

    @Test
    @DisplayName("Check registration with password less than 6 symbols")
    public void checkRegistrationUnableWithPasswordLessSixSymbols() {

        loginSteps.registrationUser(user.getName(), user.getEmail(), "1234a");

        String actualErrorText = registrationPage.getErrorTextRegistration();

        assertEquals(ERROR_TEXT, actualErrorText);
    }
}



