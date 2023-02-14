package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderMainPage {

    private WebDriver driver;

    //Локаторы
    public By sectionPersonalAccount = By.xpath("//*[contains(text(),'Личный Кабинет')]");
    public By sectionConstructor = By.xpath("//p[text()='Конструктор']");
    public By logoStellarBurgers = By.xpath("//div[contains(@class, 'header__logo')]");

    public HeaderMainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Шаги
    @Step("Account button click")
    public void clickAccountButton() {
        driver.findElement(sectionPersonalAccount).click();
    }

    @Step("Constructor tab click")
    public void clickSectionConstructor() {
        driver.findElement(sectionConstructor).click();
    }

    @Step("Logo 'Stellar Burgers' click")
    public void clickByLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
    }
}
