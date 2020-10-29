package ui.google;
/*
Create 1 automation test for testing of search result listing
in ui.google by search text "iphone kyiv buy". Link to "stylus.ua" should
be present on any of first 5 pages of results. After text execution you
have to log in console which page contained searched link
("STYLUS.UA found on 2 page") or print "STYLUS.UA not found on first 5 pages"
 */

import ui.googlePages.PO_pages.SearchPage;
import ui.googlePages.PO_pages.SearchResultPage;
import org.testng.annotations.Test;
import ui.seleniumDemoLesson.BaseUITest;

import static org.testng.Assert.assertTrue;

public class PO_SearchPhoneTest extends BaseUITest {
    private String baseUrl = "https://google.com/ncr";
    private int maxPageNumber = 5;
    private String textForSearchRequest = "iphone kyiv buy";
    private String expectedText = "stylus.ua";

    @Test
    public void positiveEnterSearchTest() {
        SearchPage googleSearchPage = new SearchPage(driver);
        SearchResultPage googleSearchResultPage = new SearchResultPage(driver);

        googleSearchPage.goToMainPage(driver, baseUrl);
        googleSearchPage.performSearchRequest(textForSearchRequest);
        googleSearchResultPage.waitForStatisticsAppear();
        assertTrue(googleSearchResultPage.isTextOnFirstNPages(maxPageNumber, expectedText));
    }
}



