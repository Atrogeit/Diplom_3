import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;

import static org.junit.Assert.assertTrue;
import static tools.URLs.URL;

public class TabSwitchTest extends TestSetUp {
    private AuthorizationPage authorizationPage;
    private LoginSteps loginSteps;
    private RegistrationPage registrationPage;
    private MainPage mainPage;

    @Before
    public void setUp() {
        super.setUp();
        driver.get(URL);
        registrationPage = new RegistrationPage(driver);
        loginSteps = new LoginSteps(driver, authorizationPage, registrationPage);
        authorizationPage = new AuthorizationPage(driver, loginSteps);
        mainPage = new MainPage(driver, authorizationPage);
    }

    @Test
    @DisplayName("Switching to 'Buns Tab'")
    public void checkSwitchToBunsTabScreen() {

        WebElement bunsTitle = driver.findElement(By.xpath(String.format(mainPage.ingredientsTitleConstructor, "Булки")));
        boolean bunsTitleElementIsVisible = bunsTitle.isDisplayed();
        assertTrue(bunsTitleElementIsVisible);
    }

    @Test
    @DisplayName("Switching to 'Sauce Tab'")
    public void checkSwitchToSauceTabScreen() {

        WebElement sauceTabTitle = driver.findElement(By.xpath(String.format(mainPage.ingredientsTitleConstructor, "Соусы")));
        boolean sauceTabElementIsVisible = sauceTabTitle.isDisplayed();
        assertTrue(sauceTabElementIsVisible);
    }

    @Test
    @DisplayName("Switching to 'Filling Tab'")
    public void checkSwitchToFillingTabScreen() {

        WebElement fillingTabTitle = driver.findElement(By.xpath(String.format(mainPage.ingredientsTitleConstructor, "Начинки")));
        boolean fillingTabElementIsVisible = fillingTabTitle.isDisplayed();
        assertTrue(fillingTabElementIsVisible);
    }
}
