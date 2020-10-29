package ui.rozetkaPages.PO_PF_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ProductFactoryPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public ProductFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "p.product-prices__big.product-prices__big_color_red")
    private WebElement productPrice;
    @FindBy(className = "product__title")
    private WebElement productName;
    @FindBy(xpath = "//button[@class='compare-button']")
    private WebElement comparisonButton;
    @FindBy(css = "span.header-actions__button-counter")
    private WebElement comparisonNumber;

    public void waitForProductPriceAppear() {
        wait.until(visibilityOf(productPrice));
    }

    public void clickOnComparisonButton() {
        comparisonButton.click();
    }

    public void waitForComparisonNumberAppear() {
        wait.until(visibilityOf(comparisonNumber));
    }

    public String getComparisonNumber() {
        return comparisonNumber.getText();
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void waitForUpdatedComparisonNumberAppear() {
        wait.until(ExpectedConditions.not(textToBePresentInElement(comparisonNumber, "1")));
    }
}
