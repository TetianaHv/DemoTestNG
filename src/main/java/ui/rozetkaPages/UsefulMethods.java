package ui.rozetkaPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UsefulMethods {

    public void scrollToElement(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void navigateToUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public void navigateBack(WebDriver driver) {
        driver.navigate().back();
    }
}
