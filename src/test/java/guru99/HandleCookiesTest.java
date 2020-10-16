package guru99;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;
import seleniumDemoLesson.BaseUITest;

import java.util.Set;

import static org.testng.Assert.*;

public class HandleCookiesTest extends BaseUITest {
    String loginUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String login = "1303";
    String password = "Guru99";

    @Test
    public void handleCookiesTest() {
        //- Login to http://demo.guru99.com/Agile_Project/Agi_V1/index.php
        driver.get(loginUrl);
        driver.findElement(By.name("uid")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        //- Print out all cookies (all data)
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("Cookie Name: " + cookie.getName() + "; " + "Cookie Value: " + cookie.getValue() + "; " + "Cookie Domain: " + cookie.getDomain() + "; " + "Cookie Path: " + cookie.getPath() + "; " + "Cookie Expiry: " + cookie.getExpiry() + "; " + "Cookie Secure: " + cookie.isSecure());
        }
        //- Clear all cookies
        driver.manage().deleteAllCookies();
        //- Refresh page, verify session of authorization still exists
        driver.navigate().refresh();
        assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());
    }
}
