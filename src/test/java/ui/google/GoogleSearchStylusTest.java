package ui.google;
/*
Create 1 automation test for testing of search result listing
in ui.google by search text "iphone kyiv buy". Link to "stylus.ua" should
be present on any of first 5 pages of results. After text execution you
have to log in console which page contained searched link
("STYLUS.UA found on 2 page") or print "STYLUS.UA not found on first 5 pages"
 */

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.seleniumDemoLesson.BaseUITest;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class GoogleSearchStylusTest extends BaseUITest {
    String url = "https://google.com/ncr";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(url);
    }

    @Test
    public void positiveEnterSearchTest1() {
        driver.findElement(By.name("q")).sendKeys("iphone kyiv buy" + Keys.ENTER);

        for (int i = 1; i <= 5; i++) {
            try {
                //'cases.kiev.ua', 'ek.ua', 'epicentrk.ua'
                driver.findElement(By.xpath("//*[contains(text(), 'stylus.ua')]"));
                System.out.println("STYLUS.UA found on " + i + " page");
                break;
            } catch (NoSuchElementException ex) {
                wait.until(presenceOfElementLocated(By.cssSelector("#result-stats")));

                if (i == 5) {
                    System.out.println("STYLUS.UA not found on first 5 pages");
                } else {
                    driver.findElement(By.xpath("//*[@aria-label='Page " + (i + 1) + "']")).click();
                }
            }
        }
    }

    @Test
    public void positiveEnterSearchTest2() {
        driver.findElement(By.name("q")).sendKeys("iphone kyiv buy" + Keys.ENTER);

        for (int i = 1; i <= 6; i++) {
            if (i == 6) {
                System.out.println("STYLUS.UA not found on first 5 pages");
            } else {
                wait.until(presenceOfElementLocated(By.cssSelector("#result-stats")));

                List<WebElement> webList = driver.findElements(By.tagName("cite"));
                String str;
                String temp = null;
                for (WebElement webElement : webList) {
                    str = webElement.getText();
                    if (str.contains("stylus.ua")) {
                        temp = str;
                    }
                }

                if (temp != null) {
                    //'cases.kiev.ua', 'ek.ua', 'epicentrk.ua'
                    System.out.println("STYLUS.UA found on " + i + " page");
                    break;
                } else {
                    driver.findElement(By.xpath("//*[@aria-label='Page " + (i + 1) + "']")).click();
                }
            }
        }
    }
}



