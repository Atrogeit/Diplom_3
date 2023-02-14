import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;

import static org.junit.Assert.assertTrue;
import static tools.URLs.URL;

public class TabSwitchTest extends TestSetUp {
    private ProfilePage profilePage;
    private AuthorizationPage authorizationPage;
    private LoginSteps loginSteps;
    private RegistrationPage registrationPage;
    private PasswordRecoveryPage passwordRecoveryPage;
    private MainPage mainPage;
    private HeaderMainPage headerMainPage;

    @Before
    public void setUp() {
        super.setUp();
        driver.get(URL);
    }

    @Test
    @DisplayName("Switching to 'Buns Tab'")
    public void checkSwitchToBunsTabScreen() {
        profilePage = new ProfilePage(driver);
        headerMainPage = new HeaderMainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, passwordRecoveryPage, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);

        mainPage.clickTabConstructor("Начинки");
        mainPage.clickTabConstructor("Булки");

        WebElement bunsTitle = driver.findElement(By.xpath(String.format(mainPage.ingredientsTitleConstructor, "Булки")));
        boolean bunsTitleElementIsVisible = bunsTitle.isDisplayed();
        assertTrue(bunsTitleElementIsVisible);
    }

    @Test
    @DisplayName("Switching to 'Sauce Tab'")
    public void checkSwitchToSauceTabScreen() {
        profilePage = new ProfilePage(driver);
        headerMainPage = new HeaderMainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, passwordRecoveryPage, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);

        mainPage.clickTabConstructor("Соусы");

        WebElement sauceTabTitle = driver.findElement(By.xpath(String.format(mainPage.ingredientsTitleConstructor, "Соусы")));
        boolean sauceTabElementIsVisible = sauceTabTitle.isDisplayed();
        assertTrue(sauceTabElementIsVisible);
    }

    @Test
    @DisplayName("Switching to 'Filling Tab'")
    public void checkSwitchToFillingTabScreen() {
        profilePage = new ProfilePage(driver);
        headerMainPage = new HeaderMainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, passwordRecoveryPage, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);

        mainPage.clickTabConstructor("Начинки");

        WebElement fillingTabTitle = driver.findElement(By.xpath(String.format(mainPage.ingredientsTitleConstructor, "Начинки")));
        boolean fillingTabElementIsVisible = fillingTabTitle.isDisplayed();
        assertTrue(fillingTabElementIsVisible);
    }
}
