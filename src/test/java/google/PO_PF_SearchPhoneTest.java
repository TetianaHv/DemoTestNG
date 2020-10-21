package google;
/*
Create 1 automation test for testing of search result listing
in google by search text "iphone kyiv buy". Link to "stylus.ua" should
be present on any of first 5 pages of results. After text execution you
have to log in console which page contained searched link
("STYLUS.UA found on 2 page") or print "STYLUS.UA not found on first 5 pages"
 */

import googlePages.PO_PF_pages.SearchFactoryPage;
import googlePages.PO_PF_pages.SearchResultFactoryPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumDemoLesson.BaseUITest;

public class PO_PF_SearchPhoneTest extends BaseUITest {
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
        SearchFactoryPage googleSearchFactoryPage = new SearchFactoryPage(driver);
        SearchResultFactoryPage googleSearchResultFactoryPage = new SearchResultFactoryPage(driver);

        googleSearchFactoryPage.performSearchRequest(textForSearchRequest);
        googleSearchResultFactoryPage.waitForStatisticsAppear();
        googleSearchResultFactoryPage.findTextOnFirstNPages(maxPageNumber, expectedText);
    }
}



