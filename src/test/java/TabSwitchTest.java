
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;

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

        mainPage.clickSauceTab();
        mainPage.clickBunsTab();
        //Тут идет проверка по элементу, в случае, если выбрана не та вкладка, то тест упадет на поиске элемента
        WebElement  bunTabIsCurrent = driver.findElement(By.xpath(".//div[@class= 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[@class = 'text text_type_main-default' and text() = 'Булки']"));

    }

    @Test
    @DisplayName("Switching to 'Sauce Tab'")
    public void checkSwitchToSauceTabScreen() {

        mainPage.clickFillingsTab();
        mainPage.clickSauceTab();
        //Тут идет проверка по элементу, в случае, если выбрана не та вкладка, то тест упадет на поиске элемента
        WebElement  sauceTabIsCurrent = driver.findElement(By.xpath(".//div[@class= 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[@class = 'text text_type_main-default' and text() = 'Соусы']"));

    }

    @Test
    @DisplayName("Switching to 'Filling Tab'")
    public void checkSwitchToFillingTabScreen() {

        mainPage.clickSauceTab();
        mainPage.clickFillingsTab();
        //Тут идет проверка по элементу, в случае, если выбрана не та вкладка, то тест упадет на поиске элемента
        WebElement  fillingsTabIsCurrent = driver.findElement(By.xpath(".//div[@class= 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[@class = 'text text_type_main-default' and text() = 'Начинки']"));

    }
}
