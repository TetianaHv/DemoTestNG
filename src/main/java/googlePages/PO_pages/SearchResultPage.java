package googlePages.PO_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SearchResultPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public SearchResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
    }

    By statistics = By.cssSelector("#result-stats");
    By searchResultLinks = By.cssSelector("div cite");
    By nextButton = By.xpath("//span[contains(text(), 'Next')]");

    public void waitForStatisticsAppear() {
        wait.until(presenceOfElementLocated(statistics));
    }

    private List<WebElement> getAllLinksFromSearchResult() {
        return webDriver.findElements(searchResultLinks);
    }

    private void clickOnNextPageButton() {
        webDriver.findElement(nextButton).click();
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
