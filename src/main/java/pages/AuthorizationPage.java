package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationPage {
    LoginSteps loginSteps;
    private PasswordRecoveryPage passwordRecoveryPage;
    private WebDriver driver;

    // Локаторы
    public By loginButton = By.xpath("//button[text()='Войти']");
    public By AuthorizationPageTitle = By.xpath("//*[contains(text(),'Вход')]");
    public By passwordRecoveryButton = By.xpath("//a[text()='Восстановить пароль']");

    public AuthorizationPage(WebDriver driver, PasswordRecoveryPage recoveryPasswordPage, LoginSteps loginSteps) {
        this.driver = driver;
        this.passwordRecoveryPage = recoveryPasswordPage;
        this.loginSteps = loginSteps;
    }

    public AuthorizationPage(WebDriver driver, LoginSteps loginSteps) {
        this.driver = driver;
        this.loginSteps = loginSteps;
    }

    //Шаги
    @Step("Click on login button")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Authorization")
    public void userAuthorization(String email, String password) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(loginButton)));
        loginSteps.setEmailField(email);
        this.loginSteps.setPasswordField(password);
        clickOnLoginButton();
    }

    @Step("Wait for 'Login' title")
    public void waitForTitleLoginPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(AuthorizationPageTitle));
    }

    @Step("'Recover password' button click")
    public void clickRecoverPasswordButton() {
        driver.findElement(passwordRecoveryButton).click();
        passwordRecoveryPage.waitPasswordRecoveryTitle();
    }

}
