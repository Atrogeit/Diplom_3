package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private WebDriver driver;

    // Локаторы
    public By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    public By loginButton = By.xpath("//*[contains(text(),'Войти')]");
    public By errorText = By.xpath("//*[starts-with(@class, 'input__error')]");
    public By registrationTitle = By.xpath("//*[contains(text(),'Регистрация')]");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //Шаги
    @Step("Registration button click")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Get error message while registration")
    public String getErrorTextRegistration() {
        String text = driver.findElement(errorText).getText();
        return text;
    }
    @Step("Registration title waiting")
    public void waitForTitleRegistration() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(registrationTitle));
    }
    @Step("Нажать на кнопку-ссылку 'Войти'")
    public void clickLoginButtonLink() {
        driver.findElement(loginButton).click();
    }
}
