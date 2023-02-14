package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {

    private WebDriver driver;

    //Локаторы

    public By sectionProfile = By.xpath("//a[text()='Профиль']");
    public By logoutButton = By.xpath("//button[text()='Выход']");


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    //Шаги
    @Step("Logout button click")
    public void logoutButtonClick() {
        driver.findElement(logoutButton).click();
    }
}
