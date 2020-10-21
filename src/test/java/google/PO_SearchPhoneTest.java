package google;
/*
Create 1 automation test for testing of search result listing
in google by search text "iphone kyiv buy". Link to "stylus.ua" should
be present on any of first 5 pages of results. After text execution you
have to log in console which page contained searched link
("STYLUS.UA found on 2 page") or print "STYLUS.UA not found on first 5 pages"
 */

import googlePages.PO_pages.SearchPage;
import googlePages.PO_pages.SearchResultPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumDemoLesson.BaseUITest;

public class PO_SearchPhoneTest extends BaseUITest {
    private String baseUrl = "https://google.com/ncr";
    private int maxPageNumber = 5;
    private String textForSearchRequest = "iphone kyiv buy";
    private String expectedText = "stylus.ua";

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(baseUrl);
    }

    @Test
    public void positiveEnterSearchTest() throws Exception {
        SearchPage googleSearchPage = new SearchPage(driver);
        SearchResultPage googleSearchResultPage = new SearchResultPage(driver);

        googleSearchPage.performSearchRequest(textForSearchRequest);
        googleSearchResultPage.waitForStatisticsAppear();
        googleSearchResultPage.findTextOnFirstNPages(maxPageNumber, expectedText);
    }
}



