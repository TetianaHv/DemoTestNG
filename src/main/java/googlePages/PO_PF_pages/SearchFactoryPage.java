package googlePages.PO_PF_pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchFactoryPage {
    WebDriver webDriver;

    public SearchFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchInput;

    public void performSearchRequest(String text) {
        searchInput.sendKeys(text + Keys.ENTER);
    }
}
