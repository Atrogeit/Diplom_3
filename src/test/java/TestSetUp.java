import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.DriverSetUp;

import java.util.concurrent.TimeUnit;

public class TestSetUp extends DriverSetUp {
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
// Закрыть браузер
        driver.quit();
    }
}
