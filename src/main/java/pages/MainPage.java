package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage{
    private AuthorizationPage authorizationPage;
    private WebDriver driver;

    //Локаторы
    public By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    public By createOrderButton = By.xpath("//button[text()='Оформить заказ']");
    public By constructorTabTitle = By.xpath("//h1[text()='Соберите бургер']");
    public By bunsTab = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[@class = 'text text_type_main-default' and text() = 'Булки']");

    public By sauceTab = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[@class = 'text text_type_main-default' and text() = 'Соусы']");
    public By fillingsTab = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[@class = 'text text_type_main-default' and text() = 'Начинки']");


    public MainPage(WebDriver driver, AuthorizationPage authorizationPage) {
        this.driver = driver;
        this.authorizationPage = authorizationPage;
    }

    //Шаги
    @Step("Waiting for 'Order creation' button on Main page")
    public void waitForCreateOrderButton() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    }

    @Step("Login button click")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        authorizationPage.waitForTitleLoginPage();
    }

    @Step("bunsTab is clicked")
    public void clickBunsTab(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
        driver.findElement(bunsTab).click();
    }

    @Step("sauceTab is clicked")
    public void clickSauceTab(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(sauceTab));
        driver.findElement(sauceTab).click();
    }

    @Step("fillingsTab is clicked")
    public void clickFillingsTab(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
        driver.findElement(fillingsTab).click();
    }

}
