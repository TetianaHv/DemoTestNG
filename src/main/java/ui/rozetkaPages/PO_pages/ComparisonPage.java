package ui.rozetkaPages.PO_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ComparisonPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public ComparisonPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
    }

    By productList = By.cssSelector("li.products-grid__cell");
    By productName = By.className("product__heading");
    By productPrice = By.cssSelector("div.product__price.product__price--red");

    public void waitForProductsAppear() {
        wait.until(presenceOfElementLocated(productList));
    }

    public List<WebElement> getAllProducts() {
        return webDriver.findElements(productList);
    }

    public String getProductName(int i) {
        return getAllProducts().get(i).findElement(productName).getText();
    }

    public String getProductPrice(int i) {
        String price = getAllProducts().get(i).findElement(productPrice).getText().replaceAll("\\s+", "");
        String[] priceSplit = price.split("₴");
        return priceSplit[1] + "₴";
    }
}
