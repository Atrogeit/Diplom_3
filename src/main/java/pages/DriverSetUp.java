package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Objects;

public class DriverSetUp {

    public WebDriver driver;
    public WebDriver getDriver(String name){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        if (Objects.equals(name, "chrome")){
            driver = new ChromeDriver(options);
        }
        return driver;
    }
}
