package ui.rozetkaPages.PO_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ProductPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public ProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
    }

    By productPrice = By.cssSelector("p.product-prices__big.product-prices__big_color_red");
    By productName = By.className("product__title");
    By comparisonButton = By.xpath("//button[@class='compare-button']");
    By comparisonNumber = By.cssSelector("span.header-actions__button-counter");

    public void waitForProductPriceAppear() {
        wait.until(presenceOfElementLocated(productPrice));
    }

    public void clickOnComparisonButton() {
        webDriver.findElement(comparisonButton).click();
    }

    public void waitForComparisonNumberAppear() {
        wait.until(presenceOfElementLocated(comparisonNumber));
    }

    public String getComparisonNumber() {
        return webDriver.findElement(comparisonNumber).getText();
    }

    public String getProductName() {
        return webDriver.findElement(productName).getText();
    }

    public String getProductPrice() {
        return webDriver.findElement(productPrice).getText();
    }

    public void waitForUpdatedComparisonNumberAppear() {
        wait.until(ExpectedConditions.not(textToBePresentInElement(comparisonNumber, "1")));
    }
}
