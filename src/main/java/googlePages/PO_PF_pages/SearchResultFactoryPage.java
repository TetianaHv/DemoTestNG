package googlePages.PO_PF_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SearchResultFactoryPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public SearchResultFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "#result-stats")
    WebElement statistics;
    @FindBy(css = "div cite")
    List<WebElement> searchResultLinks;
    @FindBy(xpath = "//span[contains(text(), 'Next')]")
    WebElement nextButton;

    public void waitForStatisticsAppear() {
        wait.until(visibilityOf(statistics));
    }

    private List<WebElement> getAllLinksFromSearchResult() {
        return searchResultLinks;
    }

    private void clickOnNextPageButton() {
        nextButton.click();
    }

    public void findTextOnFirstNPages(int maxPageNumber, String expectedText) throws Exception {
        for (int i = 1; i <= maxPageNumber; i++) {
            List<WebElement> searchResults = getAllLinksFromSearchResult();
            for (WebElement elem : searchResults) {
                if (elem.getText().contains(expectedText)) {
                    System.out.println("[" + expectedText + "] was found on " + i + " page");
                    return;
                }
            }
            clickOnNextPageButton();
            if (maxPageNumber == i) {
                throw new Exception("[" + expectedText + "] was not found");
            }
        }
    }
}
