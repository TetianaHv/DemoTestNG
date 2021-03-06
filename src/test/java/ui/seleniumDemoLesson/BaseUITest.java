package ui.seleniumDemoLesson;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseUITest {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeClass
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 25);
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}
