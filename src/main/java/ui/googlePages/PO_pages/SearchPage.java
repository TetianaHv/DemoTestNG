package ui.googlePages.PO_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    WebDriver webDriver;

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    By searchInput = By.xpath("//input[@name='q']");

    public void goToMainPage(WebDriver driver, String baseUrl) {
        driver.get(baseUrl);
    }

    public void performSearchRequest(String text) {
        webDriver.findElement(searchInput).sendKeys(text + Keys.ENTER);
    }
}
