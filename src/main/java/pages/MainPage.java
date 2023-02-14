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
    public String constructorIngredientTab = "//section[contains(@class, 'BurgerIngredients')]//span[text()='%s']";
    public String ingredientsTitleConstructor = "//h2[contains(text(), '%s')]";


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

    @Step("Constructor tab click")
    public void clickTabConstructor(String ingredient) {
        driver.findElement(By.xpath(String.format(constructorIngredientTab, ingredient))).click();
    }

}
