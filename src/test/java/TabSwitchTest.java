
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.*;


import static org.junit.Assert.assertEquals;
import static tools.URLs.URL;

public class TabSwitchTest extends TestSetUp {
    private AuthorizationPage authorizationPage;
    private MainPage mainPage;


    @Before
    public void setUp() {
        super.setUp();
        driver.get(URL);
        mainPage = new MainPage(driver, authorizationPage);
    }

    @Test
    @DisplayName("Switching to 'Buns Tab'")
    public void checkSwitchToBunsTabScreenAndReturnsCorrectCurrentTabTitle() {

        mainPage.clickSauceTab();
        mainPage.clickBunsTab();

        //Используем локатор текущей выбранной вкладки без привязки к наименованию, для проверки имени вкладки.]
        //Если кликнуть по другой вкладке, то getText вернет другое название вкладки
        String actualTabText = driver.findElement(By.xpath(".//div[@class= 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']")).getText();
        String expectedTabText = "Булки";

        assertEquals(expectedTabText, actualTabText);

    }

    @Test
    @DisplayName("Switching to 'Sauce Tab'")
    public void checkSwitchToSauceTabScreenAndReturnsCorrectCurrentTabTitle() {

        mainPage.clickSauceTab();

        //Используем локатор текущей выбранной вкладки без привязки к наименованию, для проверки имени вкладки.]
        //Если кликнуть по другой вкладке, то getText вернет другое название вкладки
        String actualTabText = driver.findElement(By.xpath(".//div[@class= 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']")).getText();
        String expectedTabText = "Соусы";

        assertEquals(expectedTabText, actualTabText);
    }

    @Test
    @DisplayName("Switching to 'Filling Tab'")
    public void checkSwitchToFillingTabScreenAndReturnsCorrectCurrentTabTitle() {

        mainPage.clickFillingsTab();
        //Используем локатор текущей выбранной вкладки без привязки к наименованию, для проверки имени вкладки.]
        //Если кликнуть по другой вкладке, то getText вернет другое название вкладки
        String actualTabText = driver.findElement(By.xpath(".//div[@class= 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']")).getText();
        String expectedTabText = "Начинки";

        assertEquals(expectedTabText, actualTabText);
    }
}
