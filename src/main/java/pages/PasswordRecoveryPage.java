package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordRecoveryPage {

    private WebDriver driver;

    //Локаторы
    public By loginButton = By.xpath("//a[text()='Войти']");
    public By passwordRecoveryTitle = By.xpath("//*[contains(text(),'Восстановление пароля')]");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    //Шаги
    @Step("Login button click")
    public void LoginButtonClick() {
        driver.findElement(loginButton).click();
    }

    @Step("Ожидаем заголвок 'Восстановление пароля' на экране Восстановление пароля")
    public void waitPasswordRecoveryTitle() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(passwordRecoveryTitle));
    }


}
