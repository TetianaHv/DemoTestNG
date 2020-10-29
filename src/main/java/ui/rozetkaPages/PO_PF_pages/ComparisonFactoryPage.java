package ui.rozetkaPages.PO_PF_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class ComparisonFactoryPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public ComparisonFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "li.products-grid__cell")
    private List<WebElement> productList;
    @FindBy(xpath = "//a[@class='product__heading']")
    private List<WebElement> productName;
    @FindBy(css = "div.product__price.product__price--red")
    private List<WebElement> productPrice;

    public void waitForProductsAppear() {
        wait.until(visibilityOfAllElements(productList));
    }

    public List<WebElement> getAllProducts() {
        return productList;
    }

    public String getProductName(int i) {
        return productName.get(i).getText();
    }

    public String getProductPrice(int i) {
        String price = productPrice.get(i).getText().replaceAll("\\s+", "");
        String[] priceSplit = price.split("₴");
        return priceSplit[1] + "₴";
    }
}
