package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSteps {

    RegistrationPage registrationPage;
    AuthorizationPage authorizationPage;
    private WebDriver driver;

    //Локаторы
    public By nameField = By.xpath("//label[text()='Имя']/following-sibling::input[@name='name']");
    public By emailField = By.xpath("//label[text()='Email']/following-sibling::input[@name='name']");
    public By passwordField = By.xpath("//input[@name='Пароль']");

    public LoginSteps(WebDriver driver, AuthorizationPage authorizationPage, RegistrationPage registrationPage) {
        this.driver = driver;
        this.authorizationPage = authorizationPage;
        this.registrationPage = registrationPage;
    }
    //Шаги
    @Step("Setting 'Name' field")
    public void setNameField(String name) {
        driver.findElement(nameField).click();
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Setting 'Email' field")
    public void setEmailField(String email) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Setting 'Password' field")
    public void setPasswordField(String password) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("User registration")
    public void registrationUser(String name, String email, String password) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(registrationPage.registrationButton)));
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
        registrationPage.clickRegistrationButton();
    }

    @Step("User authorization")
    public void loginUser(String email, String password) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(authorizationPage.loginButton)));
        setEmailField(email);
        setPasswordField(password);
        authorizationPage.clickOnLoginButton();
    }

}
